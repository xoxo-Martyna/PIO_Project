
public class Tile {
    private String id;
    private Sprite sprite;
    private boolean collideable;
    private ISpawner spawner;

    public String getId();
    public Sprite getSprite();
    public boolean getCollideable();
    public ISpawner getSpawner();
}