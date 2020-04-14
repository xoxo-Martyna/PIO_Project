

public class Game {
    private GameState state;
    private boolean isInFight;

    private Player player;

    private Level[] levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    public void handleGameLoop();
    public int getTime();

    public Player getPlayer();

    public GameState getState();
    public void setState( GameState state );

    public Level getLevelFromId( String id );
    public Level getCurrentLevel();

    public Fight getCurrentFight();
    public void startFight( Fight fight );
    public void endFight();
}