import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private Level level;
    private Player player;
    private final int imageSize = 64;

    public Panel( Player player ){
        this.player = player;
    }

    public void setLevel( Level level ){
        this.level = level;
    }

    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for( int i = 0; i < level.getHeight(); i++ )
            for( int j = 0; j < level.getWidth(); j++ ){
                Tile tile = level.getTile(j, i);
                BufferedImage image = (tile.getSprite()).getImage();

                g2d.drawImage( image, imageSize*j, imageSize*i, this );
            }

        //w przyszlosci dodatkowo rysowanie przedmiotow lezacych na planszy

        g2d.drawImage( player.getSprite().getImage(), player.getX()*imageSize, player.getY()*imageSize, this );

        //jeszcze ekwipunek i postac :o
	}
}