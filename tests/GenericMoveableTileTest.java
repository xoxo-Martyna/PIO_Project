package tests;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import src.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GenericMoveableTileTest {

    private Tile floorTile;
    private boolean light;
/*
    @Before
    public void  init() {
    }

 */

    @Test
    void willMove() {
        Game game = new Game();
        LevelLoader loader = new LevelLoader();
        try {
            loader.loadAllLevels( game );
        } catch ( IOException e ) {
            System.out.println( e + "\nNie mozna uruchomic plików z poziomami" );
            return;
        }
        game.setLevel( "e3_0" );
        Level level = game.getCurrentLevel();
        Tile tile = level.getTile(1, 1);
        GenericMoveableTile moveableTile = new GenericMoveableTile("w_concrete3", tile, true);
        assertFalse(moveableTile.willMove(level, 1, 1, 1, 1));
    }
}
/* public boolean willMove(Level level, int x, int y, int dx, int dy) {
        try {
            Tile nextTile = level.getTile(x + dx, y + dy);
            boolean intoWater = false;

            if (nextTile instanceof GenericMoveableTile) {
                boolean isLight = ((GenericMoveableTile)nextTile).isLight();

                if (
                        isLight &&
                                ((GenericMoveableTile)nextTile).willMove(
                                        level, x + dx, y + dy, dx, dy
                                )
                ) {
                    // It changed again.
                    nextTile = level.getTile(x + dx, y + dy);
                } else {
                    return false;
                }
            } else if (nextTile instanceof GenericWaterTile) {
                intoWater = true;
            } else if (nextTile.getCollidable()) {
                return false;
            }

            if (intoWater) {
                level.setTile(x, y, floorTile);
                level.setTile(
                        x + dx, y + dy,
                        new GenericFloorTile(id)
                );
            } else {
                level.setTile(x, y, floorTile);
                level.setTile(x + dx, y + dy, this);

                this.floorTile = nextTile;
            }

            return true;
        } catch (ArrayIndexOutOfBoundsException e) {}

        return false;
    }*/