import java.lang.Math;
import java.awt.Color;

public class LightSource {
    protected float x, y;
    protected float red, green, blue;
    protected float falloff;

    public LightSource(
        float x, float y,
        float red, float green, float blue,
        float falloff
    ) {
        this.x = x;
        this.y = y;

        this.red = red;
        this.green = green;
        this.blue = blue;

        this.falloff = falloff;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double distance(float sampleX, float sampleY) {
        return Math.hypot(sampleX - getX(), sampleY - getY());
    }

    public Color getColor(float sampleX, float sampleY) {
        double intensity = Math.max(
            0.0f, 1.0f - distance(sampleX, sampleY) / falloff
        );
        intensity = Math.pow(intensity, 2.0);

        return new Color(
            (float)intensity * red,
            (float)intensity * green,
            (float)intensity * blue
        );
    }

    public void handleGameLoop(Game game) {}
}