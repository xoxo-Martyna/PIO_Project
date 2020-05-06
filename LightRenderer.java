import java.awt.*;
import java.lang.Math;

public class LightRenderer {
    private final int imageSize;
    private final int lightGridSize;

    private final int shadowOversample = 6;

    private int lightGridTileSize;

    public LightRenderer( int imageSize, int lightGridSize ) {
        this.imageSize = imageSize;
        this.lightGridSize = lightGridSize;

        this.lightGridTileSize = imageSize / lightGridSize;
    }

    private boolean isLightOccluded( Level level, LightSource light, float sampleX, float sampleY ) {
        if ( light instanceof AmbientLightSource ) return false;

        double distance = light.distance( sampleX, sampleY );
        int shadowSampleCount = (int)Math.round( distance ) * shadowOversample;

        float lightX = light.getX(), lightY = light.getY();

        Tile tile = level.getTile( (int)lightX, (int)lightY );
        if ( tile != null && tile.isCastingShadows() ) return true;

        tile = level.getTile( (int)sampleX, (int)sampleY );
        if ( tile != null && tile.isCastingShadows() ) return false;

        for ( int i = 0; i < shadowSampleCount; i++ ) {
            float position = (float)(i + 1) / (float)(shadowSampleCount + 1);
            float shadowX = lightX + position * (sampleX - lightX);
            float shadowY = lightY + position * (sampleY - lightY);

            tile = level.getTile( (int)shadowX, (int)shadowY );

            if ( tile != null && tile.isCastingShadows() ) return true;
        }

        return false;
    }

    private Color sampleLights( Level level, Player player, float sampleX, float sampleY ) {
        float red = 0.0f, green = 0.0f, blue = 0.0f;

        for ( LightSource light : level.getLights() ) {
            if ( isLightOccluded( level, light, sampleX, sampleY ) ) continue;

            Color lightSample = light.getColor( sampleX, sampleY );
            float[] sampleComponents = lightSample.getColorComponents( null );

            red += sampleComponents[0];
            green += sampleComponents[1];
            blue += sampleComponents[2];
        }

        PlayerLightSource playerLight = player.getFlashlight();
        if ( playerLight != null && !isLightOccluded( level, playerLight, sampleX, sampleY ) ) {
            Color lightSample = playerLight.getColor( sampleX, sampleY );
            float[] sampleComponents = lightSample.getColorComponents( null );

            red += sampleComponents[0];
            green += sampleComponents[1];
            blue += sampleComponents[2];
        }

        return new Color(
            Math.min( red, 1.0f ),
            Math.min( green, 1.0f ),
            Math.min( blue, 1.0f )
        );
    }

    private void renderTile( Graphics2D g2d, Level level, Player player, int x, int y ) {
        for ( int gridX = 0; gridX < lightGridSize; gridX++ ) {
            for ( int gridY = 0; gridY < lightGridSize; gridY++ ) {
                float sampleX = (float)x + ( (float)gridX + 0.5f ) / (float)lightGridSize;
                float sampleY = (float)y + ( (float)gridY + 0.5f ) / (float)lightGridSize;

                g2d.setColor(
                    sampleLights( level, player, sampleX, sampleY )
                );
                g2d.fillRect(
                    (int)(sampleX * imageSize) - lightGridTileSize / 2,
                    (int)(sampleY * imageSize) - lightGridTileSize / 2,
                    lightGridTileSize, lightGridTileSize
                );
            }
        }
    }

    public void renderAO( Graphics2D g2d, Level level ) {
        g2d.setComposite( MultiplyComposite.Multiply );

        g2d.setColor(
            new Color(
                0.85f, 0.85f, 0.85f
            )
        );

        for ( int y = 0; y < level.getHeight(); y++ ) {
            for ( int x = 0; x < level.getWidth(); x++ ) {
                if ( level.getTile( x, y ).isCastingShadows() ) continue;

                if ( x > 0 && level.getTile( x - 1, y ).isCastingShadows() ) {
                    g2d.fillRect(
                        x * imageSize, y * imageSize,
                        16, imageSize
                    );
                    g2d.fillRect(
                        x * imageSize, y * imageSize,
                        8, imageSize
                    );
                }
                if ( x < level.getWidth() - 1 && level.getTile( x + 1, y ).isCastingShadows() ) {
                    g2d.fillRect(
                        x * imageSize + imageSize - 16, y * imageSize,
                        16, imageSize
                    );
                    g2d.fillRect(
                        x * imageSize + imageSize - 8, y * imageSize,
                        8, imageSize
                    );
                }
                if ( y > 0 && level.getTile( x, y - 1 ).isCastingShadows() ) {
                    g2d.fillRect(
                        x * imageSize, y * imageSize,
                        imageSize, 16
                    );
                    g2d.fillRect(
                        x * imageSize, y * imageSize,
                        imageSize, 8
                    );
                }
                if ( y < level.getHeight() - 1 && level.getTile( x, y + 1 ).isCastingShadows() ) {
                    g2d.fillRect(
                        x * imageSize, y * imageSize + imageSize - 16,
                        imageSize, 16
                    );
                    g2d.fillRect(
                        x * imageSize, y * imageSize + imageSize - 8,
                        imageSize, 8
                    );
                }
            }
        }

        g2d.setComposite( AlphaComposite.SrcOver );
    }

    public void renderLights( Graphics2D g2d, Level level, Player player ) {
        g2d.setComposite( MultiplyComposite.Multiply );
 
        for ( int y = 0; y < level.getHeight(); y++ ) {
            for ( int x = 0; x < level.getWidth(); x++ ) {
                renderTile( g2d, level, player, x, y );
            }
        }

        g2d.setComposite( AlphaComposite.SrcOver );
    }
}