
public class GenericDoorTile extends Tile {
    private String targetLevelId;

    public GenericDoorTile(String id, String targetLevelId) {
        this.id = id;
        this.collidable = false;

        this.targetLevelId = targetLevelId;

        loadImage();
    }

    public void handlePlayerEnter(Game game){
        game.setLevel(targetLevelId);
        game.render();
        // game.getFrame().customUpdate();
    }

    public boolean exit(){
        return true;
    }
}
