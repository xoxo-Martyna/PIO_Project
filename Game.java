import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameState state;
    private boolean isInFight;

    private Player player;

     private Frame frame;

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    private List<Level> levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    public Game() {
        state = GameState.exploration;

        levels = new ArrayList<Level>();
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
        player.resetFacing();

        return currentLevel;
    }

    public int getTime(){
        return currentTime;
    }

    public Player getPlayer(){
        return player;
    }

    public GameState getState(){
        return state;
    }

    public void setState( GameState state ){
        this.state = state;
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
        return currentFight;
    }

    public void startFight( Fight fight ){
        currentFight = fight;
        state = GameState.fight;
    }

    public void endFight(){
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void render() {
        if (isInFight) {
            // no fight panel yet
        } else {
            frame.getExpPanel().repaint();
        }
    }
}

