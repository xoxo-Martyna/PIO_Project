package tests;

import src.LevelLoader;

import static org.junit.jupiter.api.Assertions.*;

class LevelLoaderTest {

    @org.junit.jupiter.api.Test
    void loadFromFile() {
        LevelLoader loader = new LevelLoader();

        Level testLevel = loader.loadFromFile("e1_0");
    }
}