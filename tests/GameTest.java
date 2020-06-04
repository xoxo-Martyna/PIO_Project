package tests;

import org.junit.jupiter.api.Test;
import src.*;

import javax.imageio.IIOException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @org.junit.jupiter.api.Test
    void endFight() {
        Game game = new Game();
        Player player = new Player(game);
        game.setPlayer(player);
        Opponent opponent = Opponent.create("lean");
        Fight fight = new Fight(opponent, game);
        game.currentLevel = null;
        LevelLoader loader = new LevelLoader();
        try{
            loader.loadAllLevels(game);

            game.setLevel("e1_0");
            Tile targetTile = game.getCurrentLevel().getTile(player.getX(), player.getY());

            assertEquals(null, targetTile.getItem());
        }catch (IOException e ) {
            fail();
        }


        //assertEquals();
    }

    /*public void endFight( boolean isWin ){
        if( isWin && !currentFight.getOpponent().getId().equals("bear")){
            previousFight = currentFight;
            if(currentFight.getOpponent().getId().equals("cyclops") || currentFight.getOpponent().getId().equals("bear") || currentFight.getOpponent().getId().equals("frog")  ){
                stopMusic();
                playSound(currentLevel.getMusic());
            }

            setState( GameState.postWin );
            Tile targetTile = getCurrentLevel().getTile(player.getX(), player.getY());
            targetTile.setItem(previousFight.getOpponent().getItem());
        }else if(isWin && currentFight.getOpponent().getId().equals("bear") ){
            setState( GameState.postFinalWin );
        } else {
            currentFight = null;
            setState( GameState.postLose );
        }
    }*/

    @Test
    void setLevel(){
        Game game = new Game();
        Player player = new Player(game);
        game.setPlayer(player);
        LevelLoader loader = new LevelLoader();
        game.currentLevel = null;

        try {
            loader.loadAllLevels(game);
            Level testlevel = game.getLevel("e1_0");

            game.setLevel("e1_0");

            assertEquals(game.getPlayer().getX(), testlevel.getSpawnX() );
            assertEquals(game.getPlayer().getY(), testlevel.getSpawnY() );

            assertEquals(game.getCurrentLevel(), testlevel);


        } catch (IOException e) {
            fail();
        }
    }
}