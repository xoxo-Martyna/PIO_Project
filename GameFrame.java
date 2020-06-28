import javax.swing.JFrame;

public class GameFrame extends JFrame {    
    private ExpPanel expPanel;

    private Game game;

    public GameFrame( Game game ){
        super( "GAME" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        this.game = game;
    }

    public ExpPanel getExpPanel(){
        return expPanel;
    }

    public void setExpPanel( ExpPanel expPanel ) {
        this.expPanel = expPanel;
        add( expPanel );
        pack();

        setResizable( false );
        setVisible( true );
    }
}

