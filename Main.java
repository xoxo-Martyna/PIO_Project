import java.io.IOException;

public class Main {
 public static void main(String[] args ){

        Game game = new Game();
        Player player = new Player(game);
        game.setPlayer(player);
        LevelLoader loader = new LevelLoader();

        //String firstLevel = "example"; //pozniej to usuniemy

        try {
            loader.loadAllLevels(game);
        } catch (IOException e) {
            System.out.println(e + "\nNie mozna uruchomic plików z poziomami");
            return;
        }
        game.setLevel( LevelName.firstLevel );

        ExpPanel expPanel = new ExpPanel( game );
        Frame frame = new Frame( game );
        frame.setExpPanel(expPanel);
        game.setFrame(frame);


    }
}

