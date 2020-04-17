import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private Level level;
    private Player player;

    private final int imageSize = 64;
    private final int levelSize = 10;

    public Panel( Level level, Player player ){
        setPreferredSize( new Dimension(imageSize*levelSize, imageSize*levelSize) );

        this.player = player;
        this.level = level;
    }

    public void setLevel( Level level ){
        this.level = level;
    }

    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for( int y = 0; y < level.getHeight(); y++ )
            for( int x = 0; x < level.getWidth(); x++ ){
                Tile tile = level.getTile(x, y);
                if (tile == null) continue;

                BufferedImage image = (tile.getImage());

                if (image == null) {
                    Rectangle rect = new Rectangle(
                        imageSize * x, imageSize * y,
                        imageSize, imageSize
                    );

                    g2d.setColor(
                        new Color(1.0f, 0.0f, 1.0f)
                    );
                    g2d.fill(rect);
                } else {
                    g2d.drawImage( image, imageSize*x, imageSize*y, this );
                }
            }

        //w przyszlosci dodatkowo rysowanie przedmiotow lezacych na planszy
        
        g2d.drawImage( player.getImage(), player.getX()*imageSize, player.getY()*imageSize, this );

        //jeszcze ekwipunek i postac :o
	}
}