package src;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String id;

    private Tile[][] tiles;
    private int height;
    private int width;

    private List<LightSource> lights;

    private String musicPath;

    private int spawnX;
    private int spawnY;

    public Level( String levelId ) {
        id = levelId;

        width = 10;
        height = 10;
        tiles = new Tile[width][height];

        lights = new ArrayList<LightSource>();

        for ( int x = 0; x < width; x++ ) {
            for ( int y = 0; y < height; y++ ) {
                tiles[x][y] = null;
            }
        }
    }

    public void handleGameLoop( Game game ) {
        for ( LightSource l : lights )
            l.handleGameLoop( game );
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public void addLight( LightSource light ) {
        lights.add( light );
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX( int spawnX ) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY( int spawnY ) {
        this.spawnY = spawnY;
    }

    public String getId() {
        return id;
    }
    public Tile getTile( int x, int y ){
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

    public String getMusic(){
        return musicPath;
    }

    public void setMusic(String musicPath){
        this.musicPath = musicPath;
    }
}