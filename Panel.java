import javax.swing.JPanel;

public class Panel extends JPanel {
    private Level level;
    private Player player;

    public Panel( Player player ){
        this.player = player;
    }

    public void setLevel( Level level ){
        this.level = level;
    }

    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}