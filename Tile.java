
public class Tile implements ISpawner{
    private String id;
    private Sprite sprite;
    private boolean collideable;
    private ISpawner spawner;

    public String getId();
    public Sprite getSprite();
    public boolean getCollideable();
    public ISpawner getSpawner();
    public boolean canPlayerEnter();
    public void handlePlayerEnter(Game);
    public void handlePlayerStay(Game);
}