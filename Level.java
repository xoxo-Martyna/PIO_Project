

public class Level {
    private String id;
    private Tile[][] tiles;
    private int height;
    private int width;

    public String getId();
    public Tile getTile( int x, int y);

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}