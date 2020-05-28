package src;

import java.lang.Math;
import java.awt.Color;

public class SpotLightSource extends LightSource {
    protected float angle;
    protected float range;
    protected float blend;

    public SpotLightSource(
        float x, float y,
        float red, float green, float blue,
        float falloff,
        float angle, float range, float blend
    ) {
        super( x, y, red, green, blue, falloff );

        this.angle = angle;
        this.range = range;
        this.blend = blend;
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
        double highLimit = Math.cos( range );
        double lowLimit = Math.cos( range + blend );

        if (lowLimit >= dot) return new Color(
            0.0f, 0.0f, 0.0f
        );

        double intensity = 1.0;

        if (dot < highLimit)
            intensity = ( dot - lowLimit ) / ( highLimit - lowLimit );
        
        return super.getColor( sampleX, sampleY, tile, (float)intensity );
    }
}