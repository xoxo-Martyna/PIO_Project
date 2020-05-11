public class PlayerLightSource extends SpotLightSource {
    private Player player;

    public PlayerLightSource(
        Player player,
        float red, float green, float blue
    ) {
        super( 0.0f, 0.0f, red, green, blue, 0.0f, 0.0f, 0.8f );

        this.player = player;
    }

    public void setAngle(float degrees) {
        angle = (float)Math.toRadians(degrees);
    }

    public float getX() {
        return (float)player.getX() + 0.5f;
    }

    public float getY() {
        return (float)player.getY() + 0.5f;
    }
}