public class RotatingSpotLightSource extends SpotLightSource {
    protected float speed;

    public RotatingSpotLightSource(
        float x, float y,
        float red, float green, float blue,
        float falloff,
        float angle, float range,
        float speed
    ) {
        super(x, y, red, green, blue, falloff, angle, range);

        this.speed = speed;
    }

    @Override
    public void handleGameLoop(Game game) {
        angle += speed;
    }
}