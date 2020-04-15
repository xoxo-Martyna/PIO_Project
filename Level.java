

public class Level {
    private String id;
    private Tile[][] tiles;
    private int height;
    private int width;

    public String getId(){
        return id;
    }
    public Tile getTile( int x, int y){
        return tiles[y][x];
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}