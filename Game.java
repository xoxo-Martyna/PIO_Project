import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private GameState state = GameState.exploration;
    private boolean isInFight;

    private Player player;

     private Frame frame;

    private List<Level> levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    private Timer gameLoopTimer;

    private List<ProjectileInstance> projectiles;

    public Game() {
        state = GameState.exploration;

        levels = new ArrayList<Level>();
        projectiles = new ArrayList<ProjectileInstance>();
    }

    public List<ProjectileInstance> getProjectiles() {
        return projectiles;
    }

    public void cleanProjectiles() {
        List<ProjectileInstance> trashList = new ArrayList<ProjectileInstance>();

        for (ProjectileInstance p : projectiles) {
            float x = p.getX(), y = p.getY();

            if (
                p.isDiscarded() ||
                x < 0.0f || y < 0.0f ||
                x > (float)(currentLevel.getWidth() + 1) ||
                y > (float)(currentLevel.getHeight() + 1)
            )
                trashList.add(p);
        }

        for (ProjectileInstance p : trashList) {
            projectiles.remove(p);
        }

        trashList.clear();
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    public void handleGameLoop() {
        if (currentTime % 10 == 0) {
            projectiles.add(
                new ProjectileInstance(
                    new Projectile(),
                    2.0f, 2.0f, 0.1f, 0.0f
                )
            );
        }

        for (ProjectileInstance p : projectiles) {
            p.advancePosition(this);
        }
        cleanProjectiles();

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

