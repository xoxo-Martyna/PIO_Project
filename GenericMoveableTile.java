
public class GenericMoveableTile extends Tile {
    private Tile floorTile;

    public GenericMoveableTile(String id, Tile floorTile) {
        this.id = id;
        this.collidable = true;

        this.floorTile = floorTile;

        loadImage();
    }

    public boolean willMove(Level level, int x, int y, int dx, int dy) {
        try {
            Tile nextTile = level.getTile(x + dx, y + dy);
            if (nextTile.getCollidable()) return false;

            level.setTile(x, y, floorTile);
            level.setTile(x + dx, y + dy, this);

            this.floorTile = nextTile;

            return true;
        } catch (ArrayIndexOutOfBoundsException e) {}
        
        return false;
    }
}