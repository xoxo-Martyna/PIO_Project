package src;

import java.io.IOException;

public class Main {
    public static void main( String[] args ){

        Game game = new Game();

        Player player = new Player( game );
        game.setPlayer( player );

        LevelLoader loader = new LevelLoader();

        try {
            loader.loadAllLevels( game );
        } catch ( IOException e ) {
            System.out.println( e + "\nNie mozna uruchomic plików z poziomami" );
            return;
        }
        game.setLevel( "e1_4" );
        
        ExpPanel expPanel = new ExpPanel( game );
        GameFrame frame = new GameFrame( game );
        
        frame.setExpPanel( expPanel );
        game.setFrame( frame );

        game.startGameLoop();
    }
}

