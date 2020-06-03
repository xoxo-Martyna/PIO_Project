package tests;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @org.junit.jupiter.api.Test
    void endFight() {
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
}