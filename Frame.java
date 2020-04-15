import java.io.IOException;

import javax.swing.JFrame;

public class Frame extends JFrame {    
    private Panel expPanel;

    private Game game;

    public Frame( Player player ){
        expPanel = new Panel( player );
    }

    public Frame(){
        super("GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        game = new Game();

        LevelLoader loader = new LevelLoader();

        try {
            game.addLevel(
                loader.loadFromFile("example")
            );
        } catch (IOException e) {}

        expPanel = new Panel(
            game.setLevel("test")
        );
        add( expPanel );
        pack();

        setVisible(true);
    }

    public Panel getExpPanel(){
        return expPanel;
    }

    public static void main( String[] args ){
        // panel.setlevel najpierw
        new Frame();
    }
}