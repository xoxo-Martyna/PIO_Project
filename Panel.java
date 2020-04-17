import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private Level level;
    private Player player;

    private final int imageSize = 64;
    private final int levelSize = 10;
    private final int d = 10;
    private final int eqY = 320;
    private final int hpY = 100;

    public Panel( Level level, Player player ){
        setPreferredSize( new Dimension(imageSize*levelSize+4*d+3*imageSize, imageSize*levelSize) );

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
        int y, x;
        
        for( y = 0; y < level.getHeight(); y++ )
            for( x = 0; x < level.getWidth(); x++ ){
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

        //rysowanie hp
        x = level.getWidth()*imageSize+d;
        y = hpY;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x-1, y-1, Player.maxHealthPoints*3+1, imageSize/2+1 ) );
        g2d.setColor( Color.RED );
        g2d.fill( new Rectangle( x, y, player.getHealthPoints()*3, imageSize/2 ) );

        //rysowanie defense'a
        y += imageSize/2+d;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x-1, y-1, Player.maxDefensePoints*3+1, imageSize/2+1 ) );

        g2d.setColor( Color.GRAY );
        g2d.fill( new Rectangle( x, y, player.getDefensePoints()*3, imageSize/2 ) );

        //rysowanie ekwipunku, na razie bez przedmiotow
        g2d.setColor( Color.BLACK );
        for( y = eqY; y < eqY+3*(imageSize+d); y += imageSize+d )
            for( x = level.getWidth()*imageSize+d; x < getSize().width; x += imageSize+d )
                g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );

	}
}