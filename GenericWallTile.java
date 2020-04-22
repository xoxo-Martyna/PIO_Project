
public class GenericWallTile extends Tile {
    public GenericWallTile(String id) {
        this.id = id;
        this.collidable = true;

        loadImage();
    }
}