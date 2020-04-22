
public class GenericDoorTile extends Tile {

    public GenericDoorTile(String id) {
        this.id = id;
        this.collidable = false;

        loadImage();
    }

    public boolean exit(){
        return true;
    }
}
