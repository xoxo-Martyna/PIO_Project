import java.io.IOException;

public class Main {
    public static void main(String[] args ){

        Game game = new Game();
        Player player = new Player(game);
        game.setPlayer(player);
        LevelLoader loader = new LevelLoader();

        try {
            loader.loadAllLevels(game);
        } catch (IOException e) {
            System.out.println(e + "\nNie mozna uruchomic plików z poziomami");
            return;
        }
        game.setLevel("example");

        ExpPanel expPanel = new ExpPanel( game );
        Frame frame = new Frame( game );
        frame.setExpPanel(expPanel);
        game.setFrame(frame);


    }
}

