import javax.swing.JFrame;

public class Frame extends JFrame {    
    private ExpPanel expPanel;

    private Game game;

    public Frame( Game game ){
        super("GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.game = game;
        expPanel = new ExpPanel( game.setLevel("example"), game.getPlayer() );

        add( expPanel );
        pack();

        setResizable(false);
        setVisible(true);
    }

    public ExpPanel getExpPanel(){
        return expPanel;
    }
}