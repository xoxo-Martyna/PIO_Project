

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

    public void handleGameLoop(){

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

    public Level getLevelFromId( String id ){
        return null;
    }

    public Level getCurrentLevel(){
        return null;
    }

    public Fight getCurrentFight(){
        return null;
    }

    public void startFight( Fight fight ){

    }

    public void endFight(){

    }
}