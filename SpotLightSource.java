import java.lang.Math;
import java.awt.Color;

public class SpotLightSource extends LightSource {
    protected float angle;
    protected float range;

    public SpotLightSource(
        float x, float y,
        float red, float green, float blue,
        float falloff,
        float angle, float range
    ) {
        super( x, y, red, green, blue, falloff );

        this.angle = angle;
        this.range = range;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double distance( float sampleX, float sampleY ) {
        return Math.hypot( sampleX - getX(), sampleY - getY() );
    }

    public Color getColor( float sampleX, float sampleY, Tile tile ) {
        double dist = distance( sampleX, sampleY );
        double coneX = Math.cos( angle );
        double coneY = Math.sin( angle );

        double dot = (
            coneX * ( sampleX - getX() ) / dist +
            coneY * ( sampleY - getY() ) / dist
        );

        if (Math.cos( range ) >= dot) return new Color(
            0.0f, 0.0f, 0.0f
        );
        
        return super.getColor( sampleX, sampleY, tile );
    }
}