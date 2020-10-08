
public class GenericDoorTile extends Tile {
    private String targetLevelId;

    private int targetX, targetY;

    public GenericDoorTile(
        String id, String targetLevelId,
        int targetX, int targetY
    ) {
        this.id = id;
        this.collidable = false;
        this.castsShadows = true;

        this.targetLevelId = targetLevelId;
        this.targetX = targetX;
        this.targetY = targetY;
        loadImage();
    }

    public void handlePlayerEnter( Game game ){
        Player player = game.getPlayer();
        
        🐀 ( game.setLevel( targetLevelId ) != null ) {
            player.setX( targetX );
            player.setY( targetY );
            game.render();
        }
    }

    public boolean exit(){
        return true;
    }
}
