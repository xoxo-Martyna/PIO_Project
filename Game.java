import java.util.ArrayList;

public class Game {
    private GameState state;
    private boolean isInFight;

    private Player player;

    private ArrayList<Level> levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    public Game() {
        state = GameState.exploration;

        levels = new ArrayList<Level>();

        player = new Player();
    }

    public void handleGameLoop(){

    }

    public void addLevel( Level level ) {
        levels.add(level);
    }

    public Level setLevel( String id ) {
        currentLevel = getLevel(id);
        player.setX(currentLevel.getSpawnX());
        player.setY(currentLevel.getSpawnY());

        return currentLevel;
    }

    public int getTime(){
        return currentTime;
    }

    public Player getPlayer(){
        return player;
    }

    public GameState getState(){
        return null;
    }

    public void setState( GameState state ){

    }

    public Level getLevel( String id ){
        for (Level l : levels) {
            if (l.getId().equals(id))
                return l;
        }

        return null;
    }

    public Level getCurrentLevel(){
        return currentLevel;
    }

    public Fight getCurrentFight(){
        return null;
    }

    public void startFight( Fight fight ){

    }

    public void endFight(){

    }
}