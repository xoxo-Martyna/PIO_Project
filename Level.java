

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

    public static Level getTestLevel() {
        Level level = new Level("test");

        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                level.setTile(
                    x, y,
                    new GenericFloorTile("testblue")
                );
            }
        }

        return level;
    }

    public String getId(){
        return id;
    }
    public Tile getTile( int x, int y){
        return tiles[x][y];
    }

    public void setTile( int x, int y, Tile tile ) {
        tiles[x][y] = tile;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}