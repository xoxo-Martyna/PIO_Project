
public class ProjectileInstance {
    private Projectile type;

    private float x;
    private float y;
    private float vx;
    private float vy;

    public ProjectileInstance(
        Projectile type,
        float x, float y,
        float vx, float vy
    ) {
        this.type = type;

        this.x = x;
        this.y = y;

        this.vx = vx;
        this.vy = vy;
    }
}