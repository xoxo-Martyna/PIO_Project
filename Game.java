import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Timer gameLoopTimer;

    public Game() {
        state = GameState.exploration;

        levels = new ArrayList<Level>();
    }

    public void handleGameLoop() {
        if (isInFight) {

        } else {
            currentLevel.handleGameLoop(this);
        }


        render();

        currentTime++;
    }

    public void startGameLoop() {
        ActionListener loopCallback = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGameLoop();
            }
        };

        this.gameLoopTimer = new Timer(33, loopCallback);
        this.gameLoopTimer.start();
    }

    public void stopGameLoop() {
        this.gameLoopTimer.stop();
        this.gameLoopTimer = null;
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

