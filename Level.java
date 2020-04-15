

public class Level {
    private String id;
    private Tile[][] tiles;
    private int height;
    private int width;

    public Level(String levelId) {
        id = levelId;

        width = 10;
        height = 10;
        tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = null;
            }
        }
    }

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