

public class Level {
    private String id;
    private ILevelTile[][] tiles;

    public String getId();
    public ILevelTile getTile( int x, int y);
}