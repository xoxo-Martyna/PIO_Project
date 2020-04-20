import java.io.IOException;

public class Main {

    public static void main( String[] args ){
        
        Game game = new Game();
        LevelLoader loader = new LevelLoader();

        try {
            game.addLevel(
                loader.loadFromFile("example")
            );
        } catch (IOException e) {
            System.out.println(e + "\nNie mozna uruchomic pliku z poziomami");
            return;
        }

        Frame frame = new Frame( game );
    }
}