public class Level {
    private String id;

    private Tile[][] tiles;
    private int height;
    private int width;

    private int spawnX;
    private int spawnY;

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

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public String getId() {
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