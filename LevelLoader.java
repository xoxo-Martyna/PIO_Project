import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {
    public Level loadFromFile(String id) throws IOException {
        Level level = new Level(id);

        BufferedReader reader = new BufferedReader(
            new FileReader(
                "levels/" + id + ".xoxo"
            )
        );

        String line = reader.readLine();
        parseScriptLine(line, level);

        while (line != null) {
            line = reader.readLine();
            parseScriptLine(line, level);
        }

        reader.close();

        return level;
    }

    public Level loadTestLevel() {
        Level level = new Level("test");

        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                level.setTile(
                    x, y,
                    new GenericFloorTile("testblue")
                );
            }
        }

        return level;
    }

    private void parseScriptLine(String line, Level target) {
        System.out.println(line);
    }
}