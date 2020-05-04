
public class GenericDoorTile extends Tile {
    private String targetLevelId;

    private int targetX, targetY;

    public GenericDoorTile(
        String id, String targetLevelId,
        int targetX, int targetY
    ) {
        super(id);
        this.collidable = false;
        this.castsShadows = true;

        this.targetLevelId = targetLevelId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void handlePlayerEnter(Game game){
        Player player = game.getPlayer();
        
        if (game.setLevel(targetLevelId) != null) {
            player.setX(targetX);
            player.setY(targetY);

            game.render();
        }
        // game.getFrame().customUpdate();
    }

    public boolean exit(){
        return true;
    }
}
