
public class GenericWaterTile extends Tile {
    public GenericWaterTile(String id) {
        this.id = id;
        this.collidable = true;
        this.castsShadows = false;

        loadImage();
    }
}