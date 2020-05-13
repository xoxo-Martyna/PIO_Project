import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game {
    private GameState state = GameState.exploration;
    private boolean isInFight;

    private Player player;

     private GameFrame frame;

    public void setFrame( GameFrame frame ) {
        this.frame = frame;
    }

    public GameFrame getFrame() {
        return frame;
    }

    private List<Level> levels;
    private Level currentLevel;

    private Fight currentFight;

    private int currentTime;

    private Timer gameLoopTimer;

    private List<ProjectileInstance> projectiles;

    public Clip clip = null;

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

    public void handleGameLoop() {
        if ( state == GameState.postWin && currentTime == 60 ) {
            setState( GameState.exploration );
        } else {
            currentLevel.handleGameLoop( this );
        }
        if (currentTime % 10 == 0) {
            projectiles.add(
                    new ProjectileInstance(
                            new Projectile(),
                            2.0f, 2.5f, 0.1f, 0.0f
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
            public void actionPerformed( ActionEvent e ) {
                handleGameLoop();
            }
        };

        this.gameLoopTimer = new Timer( 33, loopCallback );
        this.gameLoopTimer.start();
    }

    public void stopGameLoop() {
        this.gameLoopTimer.stop();
        this.gameLoopTimer = null;
    }

    public void addLevel( Level level ) {
        levels.add( level );
    }

    public Level setLevel( String id ) {
        if(clip != null)
            stopMusic();
        playSound(id);
        currentLevel = getLevel( id );
        player.setX( currentLevel.getSpawnX() );
        player.setY( currentLevel.getSpawnY() );
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
        currentTime = 0;
    }

    public Level getLevel( String id ){
        for ( Level l : levels ) {
            if ( l.getId().equals( id ) )
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
        if(clip != null)
            stopMusic();
        playSound(fight.getOpponent().getId());
        currentFight = fight;
        state = GameState.fight;
    }

    public void endFight( boolean isWin ){
        if( isWin ){
            currentFight = null;
            stopMusic();
            playSound(currentLevel.getId());
            setState( GameState.postWin );
        } else{
            currentFight = null;
            setState( GameState.postLose );
        }
    }
    
    public void setPlayer( Player player ) {
        this.player = player;
    }

    public void render() {
        frame.getExpPanel().repaint();
    }

    public void playSound(String musicName) {
        String musicLocation = "./res/music/" + musicName +".wav";
       try {
           File musicPath = new File(musicLocation);

           if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
           } else{
               System.out.println("Cannot find file");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void stopMusic(){
        clip.stop();
    }

    
}

