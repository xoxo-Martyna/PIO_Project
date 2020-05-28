package src;

public class GenericMoveableTile extends Tile {
    private Tile floorTile;

    private boolean light;

    public GenericMoveableTile(
            String id, Tile floorTile,
            boolean isLight
    ) {
        this.id = id;
        this.collidable = true;

        this.floorTile = floorTile;
        this.light = isLight;

        loadImage();
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean willMove(Level level, int x, int y, int dx, int dy) {
        try {
            Tile nextTile = level.getTile(x + dx, y + dy);
            boolean intoWater = false;

            if (nextTile instanceof GenericMoveableTile) {
                boolean isLight = ((GenericMoveableTile)nextTile).isLight();

                if (
                        isLight &&
                                ((GenericMoveableTile)nextTile).willMove(
                                        level, x + dx, y + dy, dx, dy
                                )
                ) {
                    // It changed again.
                    nextTile = level.getTile(x + dx, y + dy);
                } else {
                    return false;
                }
            } else if (nextTile instanceof GenericWaterTile) {
                intoWater = true;
            } else if (nextTile.getCollidable()) {
                return false;
            }

            if (intoWater) {
                level.setTile(x, y, floorTile);
                level.setTile(
                        x + dx, y + dy,
                        new GenericFloorTile(id)
                );
            } else {
                level.setTile(x, y, floorTile);
                level.setTile(x + dx, y + dy, this);

                this.floorTile = nextTile;
            }

            return true;
        } catch (ArrayIndexOutOfBoundsException e) {}

        return false;
    }
}