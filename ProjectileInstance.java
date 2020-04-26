import java.awt.Graphics2D;

public class ProjectileInstance {
    private Projectile type;

    private float x;
    private float y;
    private float vx;
    private float vy;

    private boolean discarded = false;

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

    public boolean isDiscarded() {
        return discarded;
    }

    public void discard() {
        discarded = true;
    }

    public float getX() {
		return x;
	}

    public float getY() {
		return y;
	}

	public void render(Graphics2D g2d) {
        type.render(g2d, this);
    }

    private boolean checkPlayerCollision(Player p) {
        return p.getX() == (int)x && p.getY() == (int)y;
    }

    public void advancePosition(Game game) {
        x += vx;
        y += vy;

        if (checkPlayerCollision(game.getPlayer()))
            type.handlePlayerCollision(game, this);
        else if (
            game.getCurrentLevel().getTile((int)x, (int)y).getCollidable()
        )
            type.handleWallCollision(
                game, game.getCurrentLevel().getTile((int)x, (int)y),
                this
            );

        type.adjustVelocity(game, this);
    }
}