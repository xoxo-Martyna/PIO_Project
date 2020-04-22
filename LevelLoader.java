import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LevelLoader {
    public void loadAllLevels(Game game) throws IOException {
        Stream<Path> pathWalk = Files.walk(
            Paths.get("levels")
        );

        pathWalk.filter(
            Files::isRegularFile
        ).map(
            f -> f.getFileName().toString()
        ).forEach(
            f -> {
                if (f.endsWith(".xoxo"))
					try {
						game.addLevel(
						    loadFromFile(
						        f.substring(
						            0, f.length() - 5
						        )
						    )
						);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
        );

        pathWalk.close();
    }

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

        String[] args = line.split("\\s+");

        if (args[0].equals("Tile") && args.length > 2) {
            String tileId = args[1];
            Tile tile = null;

            if (tileId.startsWith("f_"))
                tile = new GenericFloorTile(tileId);
           if (tileId.startsWith("w_"))
                tile = new GenericWallTile(tileId);

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
        } else if (args[0].equals("SpawnPoint") && args.length == 3) {
            target.setSpawnX(Integer.parseInt(args[1]));
            target.setSpawnY(Integer.parseInt(args[2]));
        } else if (args[0].equals("DoorTile") && args.length == 7) {
            Tile door = new GenericDoorTile(
                args[1], args[4],
                Integer.parseInt(args[5]),
                Integer.parseInt(args[6])
            );

            target.setTile(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                door
            );
        } else if (args[0].equals("MoveableTile") && args.length == 6) {
            Tile floor = new GenericFloorTile(args[4]);
            Tile crate = new GenericMoveableTile(
                args[1], floor, args[5].equals("light")
            );

            target.setTile(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                crate
            );
        }
    }
}
