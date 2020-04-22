
public class GenericDoorTile extends Tile {
    public GenericDoorTile(String id) {
        this.id = id;
        this.collideable = false;

        loadImage();
    }

    public boolean exit(){
        return true;
    }
}
