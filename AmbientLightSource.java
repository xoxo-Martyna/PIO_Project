import java.awt.Color;

public class AmbientLightSource extends LightSource {
    public AmbientLightSource(
        float red, float green, float blue
    ) {
        super( 0.0f, 0.0f, red, green, blue, 0.0f );
    }

    @Override
    public Color getColor( float sampleX, float sampleY ) {
        return new Color(
            red, green, blue
        );
    }
}