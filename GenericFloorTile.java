
public class GenericFloorTile extends Tile {
    public GenericFloorTile(String id) {
        this.id = id;
        this.collidable = false;
        this.castsShadows = false;

        loadImage();
    }
}