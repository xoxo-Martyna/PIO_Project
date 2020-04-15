import javax.swing.JFrame;

public class Frame extends JFrame {
    
    private Panel expPanel;

    public Frame( Player player ){
        expPanel = new Panel( player );
    }

    public Frame(){
        super("GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        expPanel = new Panel();
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