
public class Tile{ //implements ISpawner {
    private String id;
    private Sprite sprite;
    private boolean collideable;
    private ISpawner spawner;

    public String getId(){
        return id;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public boolean getCollideable(){
        return collideable;
    }

    public ISpawner getSpawner(){
        return spawner;
    }

    public boolean canPlayerEnter(){
        return !collideable;
    }

    public void handlePlayerEnter(Game game){

    }

    public void handlePlayerStay(Game game){

    }
}