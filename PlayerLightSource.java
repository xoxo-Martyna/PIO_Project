public class PlayerLightSource extends LightSource {
    private Player player;

    public PlayerLightSource(
        Player player,
        float red, float green, float blue,
        float falloff
    ) {
        super(0.0f, 0.0f, red, green, blue, falloff);

        this.player = player;
    }

    public float getX() {
        return (float)player.getX() + 0.5f;
    }

    public float getY() {
        return (float)player.getY() + 0.5f;
    }
}