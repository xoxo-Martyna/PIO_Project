package tests;

import src.Level;
import src.LevelLoader;
import src.Opponent;
import src.Tile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LevelLoaderTest {

    @org.junit.jupiter.api.Test
    void loadFromFile() {
        LevelLoader loader = new LevelLoader();

        try {
            Level testLevel = loader.loadFromFile("e1_0");

            // Test some tiles
            assertEquals("w_concrete1", testLevel.getTile(0, 0).getId());
            assertEquals("w_concretescaffold1", testLevel.getTile(4, 0).getId());
            assertEquals("w_concrete3", testLevel.getTile(6, 9).getId());
            assertEquals("waves_2", testLevel.getTile(3, 5).getId());
            assertEquals("f_glass_1", testLevel.getTile(7, 4).getId());

            // Opponent?
            Tile opponentTile = testLevel.getTile(1, 6);
            assertNotNull(opponentTile.getOpponent());
            assertEquals("magic_mushroom", opponentTile.getOpponent().getId());

            // Music?
            assertEquals("intro", testLevel.getMusic());
        } catch (IOException e) {
            fail();
        }
    }
}