package tests;

import org.junit.jupiter.api.Test;
import src.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GenericMoveableTileTest {

    private Tile floorTile;
    private boolean light;

    @Test
    void willMove() {
        Game game = new Game();
        Player player = new Player( game );
        game.setPlayer( player );
        LevelLoader loader = new LevelLoader();

        try {
            loader.loadAllLevels( game );
        } catch ( IOException e ) {
            System.out.println( e + "\nNie mozna uruchomic plików z poziomami" );
            return;
        }
        game.setLevel( "e3_0" );

        Level level = game.getCurrentLevel();

        Tile tile = level.getTile(2, 2);
        GenericMoveableTile moveableTile = new GenericMoveableTile("w_concrete3", tile, true);

        assertTrue(moveableTile.willMove(level, 1, 1, 0, 1));
        assertTrue(moveableTile.willMove(level, 1, 1, 5, 1));
        assertFalse(moveableTile.willMove(level, 1, 1, -2, 1));

        tile = level.getTile(1, 1);
        moveableTile = new GenericMoveableTile("w_concrete3", tile, true);
        assertFalse(moveableTile.willMove(level, 1, 1, 1, 1));
        assertFalse(moveableTile.willMove(level, 1, 1, -2, 1));




    }
}
