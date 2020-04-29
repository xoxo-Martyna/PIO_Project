import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ExpPanel extends JPanel implements KeyListener {
    private Game game;

    private BufferedImage imageArmor, imageSword;//, imageWeed;

    private final int imageSize = 64;
    private final int levelSize = 10;
    private final int d = 10;
    private final int eqY = 300;
    private final int hpY = 100;

    public ExpPanel( Game game ){
        this.game = game;

        try{
            imageArmor = ImageIO.read(new File ("res/items/outline_armor.png"));
            imageSword = ImageIO.read(new File ("res/items/outline_sword.png"));
            //imageWeed = ImageIO.read(new File ("res/items/outline_weed.png"));
        } catch( IOException e ){
            e.printStackTrace();
        }

        setPreferredSize( new Dimension(imageSize*levelSize+4*d+3*imageSize, imageSize*levelSize) );

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        Level level = game.getCurrentLevel();
        Player player = game.getPlayer();

        if( game.getState() == GameState.exploration ){
            drawLevel(g2d, level); // <-- mozna dodac rysowanie itemkow
            g2d.drawImage( player.getImage(), player.getX()*imageSize, player.getY()*imageSize, this );
        }
        else if( game.getState() == GameState.fight ){
            drawFight( g2d, player );
        }

        drawHP(g2d, level, player);
        drawDP(g2d, level, player);
        drawAP(g2d, level, player);

        drawEQ(g2d, level, player);
    }

    private void drawLevel( Graphics2D g2d, Level level ){
        for( int y = 0; y < level.getHeight(); y++ )
            for( int x = 0; x < level.getWidth(); x++ ){
                Tile tile = level.getTile(x, y);
                if (tile == null) continue;

                Item tileItem = tile.getItem();

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

                if (tileItem != null) {
                    BufferedImage itemImage = tileItem.getImage();

                    if (itemImage != null) {
                        g2d.drawImage( itemImage, imageSize*x, imageSize*y, this );
                    }
                }
            }
    }

    private void drawFight( Graphics2D g2d, Player player ){
        g2d.drawOval(350, 100, 220, 125); // przeciwnik
        g2d.drawOval(150, 450, 220, 125); // gracz
        g2d.drawImage( player.getImage().getScaledInstance(128, 128, 1), 200, 400, this );
        drawFightMenu( g2d, player, game.getCurrentFight().getOpponent() );
    }

    private void drawFightMenu( Graphics2D g2d, Player player, Opponent opponent ){
    }

    private void drawHP( Graphics2D g2d, Level level, Player player ){
        int x = level.getWidth()*imageSize+d;
        int y = hpY;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x-1, y-1, Player.maxHealthPoints*3+1, imageSize/2+1 ) );
        g2d.setColor( Color.RED );
        g2d.fill( new Rectangle( x, y, player.getHealthPoints()*3, imageSize/2 ) );
    }

    private void drawDP( Graphics2D g2d, Level level, Player player ){
        int x = level.getWidth()*imageSize+d;
        int y = hpY + imageSize/2+d;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x-1, y-1, Player.maxDefensePoints*3+1, imageSize/2+1 ) );

        g2d.setColor( Color.DARK_GRAY );
        g2d.fill( new Rectangle( x, y, player.getDefensePoints()*3, imageSize/2 ) );
    }

    private void drawAP( Graphics2D g2d, Level level, Player player ){
        int x = level.getWidth()*imageSize+d;
        int y = hpY + 2*(imageSize/2+d);

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x-1, y-1, Player.maxAttackPoints*3+1, imageSize/2+1 ) );

        g2d.setColor( Color.LIGHT_GRAY );
        g2d.fill( new Rectangle( x, y, player.getAttackPoints()*3, imageSize/2 ) );
    }

    private void drawEQ( Graphics2D g2d, Level level, Player player ){
        g2d.setColor( Color.DARK_GRAY );
        int x, y, i, j, isd;
        y = eqY;
        x = level.getWidth()*imageSize+d;
        isd = imageSize+d;

        try{
            g2d.draw( new Rectangle( x, y, imageSize, imageSize) );
            g2d.drawImage( imageSword, x, y, this );
            g2d.drawImage( player.getItem(0, 0).getImage(), x, y, this );
        } catch( NullPointerException e ){}

        try{
            x += isd;
            g2d.draw( new Rectangle( x, y, imageSize, imageSize) );
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem(1, 0).getImage(), x, y, this );
        } catch( NullPointerException e ){}
        
        try{
            x += isd;
            g2d.draw( new Rectangle( x, y, imageSize, imageSize) );
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem(2, 0).getImage(), x, y, this );
        } catch( NullPointerException e ){}


        for( y += imageSize+5*d, i = 1; y < eqY+3*isd; y += isd, i++ )
            for( x = level.getWidth()*imageSize+d, j = 0; x < getSize().width; x += isd, j++ ){
                g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
                try{
                    g2d.drawImage( player.getItem(j, i).getImage(), x, y, this );
                } catch( ArrayIndexOutOfBoundsException | NullPointerException e){}
            }
        
        g2d.setColor( Color.BLACK );
        if( player.getItemsY() == 0 )
            y = eqY;
        else if( player.getItemsY() == 1 )
            y = eqY + imageSize+5*d;
        else
            y = eqY + imageSize+5*d+isd;

        x = level.getWidth()*imageSize+d+isd*player.getItemsX();
        g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
        g2d.draw( new Rectangle( x+1, y+1, imageSize-2, imageSize-2 ) );

        drawItemInfo(g2d, level, player);
    }

    private void drawItemInfo( Graphics2D g2d, Level level, Player player ){
        setFont( new Font("Serif", Font.PLAIN, 12) );
        Item item = player.getCurrentItem();

        try{
            g2d.drawString( item.toString(), level.getWidth()*imageSize+d, eqY-20);
        } catch( NullPointerException e ){
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        Player player = game.getPlayer();

        if( game.getState() == GameState.exploration )
            expKeyClicked(key);
        else if( game.getState() == GameState.fight )
            fightKeyClicked(key);

        menuKeyClicked(key);
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    private void expKeyClicked( int key ){
        Player player = game.getPlayer();

        switch( key ){
            case(KeyEvent.VK_D):
                player.move(1, 0);
                repaint();
            break;

            case(KeyEvent.VK_A):
                player.move(-1, 0);
                repaint();
            break;

            case(KeyEvent.VK_W):
                player.move( 0, -1);
                repaint();
            break;

            case(KeyEvent.VK_S):
                player.move(0, 1);
                repaint();
            break;

            case(KeyEvent.VK_P):
                player.pickItem();
                repaint();
            break;

            case(KeyEvent.VK_L):
                player.dropItem();;
                repaint();
            break;
        }
    }

    private void fightKeyClicked( int key ){
        switch( key ){ // tu bedzie kursor po menu walki
            case(KeyEvent.VK_D):
                
                repaint();
            break;

            case(KeyEvent.VK_A):
                
                repaint();
            break;

            case(KeyEvent.VK_W):
                
                repaint();
            break;

            case(KeyEvent.VK_S):
                
                repaint();
            break;

            case(KeyEvent.VK_ENTER):
                
                repaint();
            break;
        }
    }

    private void menuKeyClicked( int key ){
        Player player = game.getPlayer();

        switch( key ){
            case(KeyEvent.VK_UP):
                player.moveEQ(0, -1);
                repaint();
            break;

            case(KeyEvent.VK_DOWN):
                player.moveEQ(0, 1);
                repaint();
            break;

            case(KeyEvent.VK_LEFT):
                player.moveEQ(-1, 0);
                repaint();
            break;

            case(KeyEvent.VK_RIGHT):
                player.moveEQ(1, 0);
                repaint();
            break;

            case(KeyEvent.VK_E):
                player.useItem();
                repaint();
            break;
        }
    }
}
