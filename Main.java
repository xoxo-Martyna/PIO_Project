import java.io.IOException;

public class Main {

    public static void main( String[] args ){
        
        Game game = new Game();
        LevelLoader loader = new LevelLoader();

        String firstLevel = "example"; //pozniej to usuniemy

        try {
            game.addLevel( loader.loadFromFile( firstLevel ) );
        } catch (IOException e) {
            System.out.println(e + "\nNie mozna uruchomic pliku z poziomami");
            return;
        }
        game.setLevel( firstLevel );

        Frame frame = new Frame( game );
    }
}