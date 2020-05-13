import java.awt.*;

public class Projectile {

    public void adjustVelocity(Game game, ProjectileInstance instance) {
        // static velocities
    }

    public void handlePlayerCollision(Game game, ProjectileInstance instance) {
        Player p = game.getPlayer();

        p.hurt(5);
        instance.discard();
    }

    public void handleWallCollision(Game game, Tile tile, ProjectileInstance instance) {
        instance.discard();
    }

    public void render(Graphics2D g2d, ProjectileInstance instance) {
        // rendering
        g2d.setColor(
                new Color(1.0f, 1.0f, 1.0f)
        );
        g2d.fillArc(
                (int)(instance.getX() * 64.0f) - 4,
                (int)(instance.getY() * 64.0f) - 4,
                8, 8, 0, 360
        );
    }
}