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

        while (true) {
            String line = reader.readLine();
            if (line == null) break;

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
        if (
            line.startsWith("//") ||
            line.length() == 0
        ) return;

        String[] args = line.split("\s+");

        if (args[0].equals("Tile") && args.length > 2) {
            String tileId = args[1];
            Tile tile = new GenericFloorTile(tileId);

            for (int i = 2; i < args.length; i += 2) {
                if (i + 1 >= args.length) break;

                int x = Integer.parseInt(args[i]);
                int y = Integer.parseInt(args[i + 1]);

                if (
                    x >= target.getWidth() ||
                    y >= target.getHeight()
                )
                    break;
                
                target.setTile(x, y, tile);
            }
        }
    }
}