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

    private BufferedImage imageArmor, imageSword;// , imageWeed;// , imageWeed;
    private BufferedImage attackBar, healthBar, defenseBar;
    private BufferedImage sidebarOverlay;
    private BufferedImage boss1BG, boss2BG, boss3BG, standard1BG, standard2BG, standard3BG;

    private final int imageSize = 64;
    private final int levelSize = 10;
    private final int levelAreaWidth = imageSize * levelSize;
    
    private final int spaceSize = 10;
    private final int equipmentPosition = 300;
    private final int statBarsPosition = 100;

    private boolean useRTX = true;
    private final LightRenderer rtxRenderer = new LightRenderer(
        imageSize, 16
    );

    public ExpPanel( Game game ) {
        this.game = game;

        try {
            imageArmor = ImageIO.read(new File( "res/items/outline_armor.png" ));
            imageSword = ImageIO.read(new File( "res/items/outline_sword.png" ));
            // imageFlashlight = ImageIO.read(new File( "res/items/outline_flashlight.png" ));
            // imageWeed = ImageIO.read(new File ("res/items/outline_weed.png"));

            attackBar = ImageIO.read(new File( "res/attack_bar.png" ));
            defenseBar = ImageIO.read(new File( "res/defense_bar.png" ));
            healthBar = ImageIO.read(new File( "res/health_bar.png" ));
            sidebarOverlay = ImageIO.read(new File( "res/sidebar.png" ));

            boss1BG = ImageIO.read(new File("res/background/1boss.png"));
            boss2BG = ImageIO.read(new File("res/background/2boss.png"));
            boss3BG = ImageIO.read(new File("res/background/3boss.png"));

            standard1BG = ImageIO.read(new File("res/background/1standard.png"));
            standard2BG = ImageIO.read(new File("res/background/2standard.png"));
            standard3BG = ImageIO.read(new File("res/background/3standard.png"));
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        setBackground(new Color(13, 12, 10));
        setPreferredSize( new Dimension( levelAreaWidth + 4 * spaceSize + 3 * imageSize, levelAreaWidth ) );

        addKeyListener( this );
        setFocusable( true );
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D)g;

        Level level = game.getCurrentLevel();
        Player player = game.getPlayer();

        if ( game.getState() == GameState.exploration ) {
            if( game.is3D() ){
                drawLevel3D(g2d, level);
            }
            else{
                drawLevel( g2d, level );
                g2d.drawImage( player.getImage(), player.getX() * imageSize, player.getY() * imageSize, this );
            
                if ( useRTX ) {
                    try {
                        rtxRenderer.renderLights( g2d, level, player );
                    } catch( Exception e ) {
                        useRTX = false;
                    }
                }
            }
        } else if ( game.getState() == GameState.fight ) {
            drawFight( g2d, player );
        }
        
        drawSidePanel( g2d, player );

        if( game.getState() == GameState.postLose ){
            drawGameOver( g2d );
        }
        if( game.getState() == GameState.postWin ){
            drawYouWon( g2d );
        }

        if( game.getState() == GameState.postFinalWin ){
            drawFinalGameOver( g2d );
        }
    }

    private void drawLevel3D( Graphics2D g2d, Level level ){
        int nRays = 640;
        Player player = game.getPlayer();
        Direction playerDir = player.getDirection();
        double dirX = playerDir.getX()/100;
        double dirY = playerDir.getY()/100;

        double dirHorizontal = 0.0065;
        double dirD = 2*dirHorizontal/(double)nRays;


        if( dirX == 0 )
            dirX = -1*dirHorizontal;
        else
            dirY = -1*dirHorizontal;

        for( int i = 0; i < nRays; i++ ){
            Direction rayDir;
            rayDir = new Direction( dirX, dirY );
            Ray ray = new Ray( rayDir, level, (double)player.getX()+0.5, (double)player.getY()+0.5 );
            Impact impact = ray.shoot();

            if( impact != null )
                drawTileLine( g2d, i*(imageSize*levelSize/nRays) , ray.getLength(), impact, imageSize*levelSize/nRays );

            if( playerDir.getX() == 0 )
                dirX += dirD;
            else
                dirY += dirD;
        }
    }

    private void drawTileLine( Graphics2D g2d, int x, double distance, Impact impact, int width ){
        BufferedImage image = impact.getTile().getImage();
        int imageX = (int)(impact.getX()*(double)image.getWidth());

        int h = (int)(576/distance);  // hiperbola h = 576/distance
        
        //double fi = 2*Math.atan( 0.5/distance );    // jeden chuj, bo po uproszczeniu
        //int h = (int)(640*2*Math.tan( fi/2 ));      // to i to to jest hiperbola xd

        double pixelSize = (double)h/(image.getHeight());
        double drawY = (levelSize*imageSize-h)/2;


        for( int i = image.getHeight()-1; i >= 0; i-- ){
            g2d.setColor( new Color( image.getRGB( imageX, i) ) );
            drawY += pixelSize;
            g2d.fillRect(x, (int)drawY, width, (int)(pixelSize+0.999) );
        }
    }
    
    private void drawSidePanel( Graphics2D g2d, Player player ) {
        drawHealthBar( g2d, player );
        drawDefenseBar( g2d, player );
        drawAttackBar( g2d, player );

        g2d.drawImage( sidebarOverlay, levelAreaWidth, 0, this );

        drawEquipment( g2d, player );
    }

    private void drawTile( Graphics2D g2d, Tile tile, int x, int y ) {
        BufferedImage image = ( tile.getImage() );
        if ( image == null ) {
            Rectangle rect = new Rectangle( imageSize * x, imageSize * y, imageSize, imageSize );
            g2d.setColor( new Color( 1.0f, 0.0f, 1.0f ) );
            g2d.fill( rect );
        } else {
            g2d.drawImage( image, imageSize * x, imageSize * y, this );
        }
        
        Item tileItem = tile.getItem();
        if ( tileItem != null ) {
            BufferedImage itemImage = tileItem.getImage();
            if ( itemImage != null ) {
                g2d.drawImage( itemImage, imageSize * x, imageSize * y, this );
            }
        }

        Opponent tileOpponent = tile.getOpponent();
        if ( tileOpponent != null ) {
            BufferedImage oppImage = tileOpponent.getImage();
            if ( oppImage != null ) {
                g2d.drawImage( oppImage, imageSize * x, imageSize * y, this );
            }
        }
    }

    private void drawLevel( Graphics2D g2d, Level level ) {
        for ( int y = 0; y < level.getHeight(); y++ ) {
            for ( int x = 0; x < level.getWidth(); x++ ) {
                Tile tile = level.getTile( x, y );

                if ( tile != null ) {
                    drawTile( g2d, tile, x, y );       
                }         
            }
        }
        
        if ( useRTX ) {
            try {
                rtxRenderer.renderAO( g2d, level );
            } catch( Exception e ) {
                useRTX = false;
            }
        }
    }

    private void drawFight( Graphics2D g2d, Player player ) {
        int x = 50, y = 90, d = 30, w = 160, h = 160;
        Fight fight = game.getCurrentFight();

        drawFightBackgoround( g2d, fight.getOpponent() );

        g2d.setColor( new Color(255, 255, 255, 100));
        for( int i = 0; i < 2; i++ )
            for( int j = 0; j < 3; j++ )
                g2d.fillRect( x + j * (w+d), y + i * 250, w, h );
        
        g2d.setColor( Color.BLACK );
        if( fight.isPlayerTurn() ){
            for( int i = 0; i < 3; i++ ) {
                if( i != fight.getXCursor() ){
                    g2d.drawRect( x + i * (w+d), y, w, h );
                    g2d.drawRect( x + 1 + i * (w+d), y + 1, w - 2, h - 2);
                }
            }
        }

        drawOpponent( g2d, fight.getOpponent(), 16 + x + fight.getOpponentPosition() * (w+d), y + 18 );

        g2d.drawImage( player.getImageDown().getScaledInstance( 128, 128, Image.SCALE_DEFAULT ), 16 + x + fight.getPlayerPosition() * (w+d), y + 268, this );
    }

    private void drawFightBackgoround( Graphics2D g2d, Opponent opponent ){
        String lvlID = game.getCurrentLevel().getId();

        if( lvlID.contains("e1") ){
            if( opponent.getId().equals("frog") )
                g2d.drawImage( boss1BG, 0, 0, this );
            else
                g2d.drawImage( standard1BG, 0, 0, this );
        }
        else if( lvlID.contains("e2") ){
            if( opponent.getId().equals("cyclops") )
                g2d.drawImage( boss2BG, 0, 0, this );
            else
                g2d.drawImage( standard2BG, 0, 0, this );
        }
        else if( lvlID.contains("e3") ){
            if( opponent.getId().equals("bear") )
                g2d.drawImage( boss3BG, 0, 0, this );
            else
                g2d.drawImage( standard3BG, 0, 0, this );
        }
    }

    private void drawOpponent( Graphics2D g2d, Opponent opponent, int x, int y ) {
        g2d.drawImage( opponent.getImage().getScaledInstance( 128, 128, Image.SCALE_DEFAULT ), x, y, this );


        x += imageSize - opponent.getMaxHealthPoints() / 2;
        y -= 10;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x, y, opponent.getMaxHealthPoints() + 1, imageSize / 8 ) );

        g2d.setColor( Color.RED );
        g2d.fill( new Rectangle( x + 1, y + 1, opponent.getHealthPoints(), (imageSize / 8) - 1 ) );
    }

    private void drawHealthBar( Graphics2D g2d, Player player ) {
        int x = levelAreaWidth + spaceSize;
        int y = statBarsPosition;

        g2d.setColor( Color.RED );
        g2d.fill( new Rectangle( x, y, player.getHealthPoints() * 3, imageSize / 2 ) );
        g2d.drawImage( healthBar, x, y, this );
    }

    private void drawDefenseBar( Graphics2D g2d, Player player ) {
        int x = levelAreaWidth + spaceSize;
        int y = statBarsPosition + imageSize / 2 + spaceSize;

        g2d.setColor( Color.DARK_GRAY );
        g2d.fill( new Rectangle( x, y, player.getDefensePoints() * 3, imageSize / 2 ) );
        g2d.drawImage( defenseBar, x, y, this );
    }

    private void drawAttackBar( Graphics2D g2d, Player player ) {
        int x = levelAreaWidth + spaceSize;
        int y = statBarsPosition + 2 * ( imageSize / 2 + spaceSize );

        g2d.setColor( Color.DARK_GRAY );
        g2d.fill( new Rectangle( x, y, player.getAttackPoints() * 3, imageSize / 2 ) );
        g2d.drawImage( attackBar, x, y, this );
    }

    private void drawEquipment( Graphics2D g2d, Player player ) {
        g2d.setColor( Color.DARK_GRAY );
        int x, y, i, j, isd;
        y = equipmentPosition;
        x = levelAreaWidth + spaceSize;
        isd = imageSize + spaceSize;
        g2d.setColor(new Color(210, 210, 210));

        try {
            g2d.drawImage( imageSword, x, y, this );
            g2d.drawImage( player.getItem( 0, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        try {
            x += isd;
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem( 1, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        try {
            x += isd;
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem( 2, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        for ( y += imageSize + 5 * spaceSize, i = 1; y < equipmentPosition + 3 * isd; y += isd, i++ )
            for ( x = levelAreaWidth + spaceSize, j = 0; x < getSize().width; x += isd, j++ ) {
                try {
                    g2d.drawImage( player.getItem( j, i ).getImage(), x, y, this );
                } catch ( ArrayIndexOutOfBoundsException | NullPointerException e ) {
                }
            }

        drawEquipmentCursor( g2d, player );

        drawItemInfo( g2d, player );
    }

    private void drawEquipmentCursor( Graphics2D g2d, Player player ) {
        int x, y, isd = imageSize + spaceSize;

        g2d.setColor( new Color( 0, 255, 255 ) );

        if ( player.getItemsY() == 0 )
            y = equipmentPosition;
        else if ( player.getItemsY() == 1 )
            y = equipmentPosition + imageSize + 5 * spaceSize;
        else
            y = equipmentPosition + imageSize + 5 * spaceSize + isd;

        x = levelAreaWidth + spaceSize + isd * player.getItemsX();
        g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
        g2d.draw( new Rectangle( x + 1, y + 1, imageSize - 2, imageSize - 2 ) );
    }

    private void drawItemInfo( Graphics2D g2d, Player player ) {
        Item item = player.getCurrentItem();
        g2d.setFont( new Font( "Serif", Font.PLAIN, 11 ) );

        try {
            g2d.drawString( item.toString(), levelAreaWidth + spaceSize, equipmentPosition - 20 );
        } catch ( NullPointerException e ) {
        }
    }

    private void drawDebugOver( Graphics2D g2d, String message ) {
        g2d.setColor( Color.LIGHT_GRAY );
        g2d.fill( new Rectangle( 0,0,imageSize * levelSize + 4 * spaceSize + 3 * imageSize, imageSize * levelSize ) );
        g2d.setFont( new Font( "Serif", Font.PLAIN, 50 ) );
        g2d.setColor( Color.red );
        g2d.drawString( message, ( ( imageSize * levelSize + 4 * spaceSize + 3 * imageSize ) / 2 ) - 150,(imageSize * levelSize) / 2 );
    }

    private void drawGameOver( Graphics2D g2d ){
        drawDebugOver( g2d, "GAME OVER" );
    }

    private void drawFinalGameOver( Graphics2D g2d ){
        drawDebugOver( g2d, "You're a BOSS" );
    }

    private void drawYouWon( Graphics2D g2d ){
        drawDebugOver( g2d, "you won!" );
    }

    public void keyPressed( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( game.getState() == GameState.exploration )
            expKeyClicked( key );
        else if ( game.getState() == GameState.fight )
            fightKeyClicked( key );

        eqKeyClicked( key );
    }

    public void keyReleased( KeyEvent e ) {

    }

    public void keyTyped( KeyEvent e ) {

    }

    private void expKeyClicked( int key ) {
        Player player = game.getPlayer();

        switch ( key ) {
            case KeyEvent.VK_D:
                player.move( 1, 0 );
                break;

            case KeyEvent.VK_A:
                player.move( -1, 0 );
                break;

            case KeyEvent.VK_W:
                player.move( 0, -1 );
                break;

            case KeyEvent.VK_S:
                player.move( 0, 1 );
                break;

            case KeyEvent.VK_P:
                player.pickUpItem();
                break;

            case KeyEvent.VK_L:
                player.dropItem();
                break;

            case KeyEvent.VK_T:
                player.changeDirection( 'N' );
                break;

            case KeyEvent.VK_G:
                player.changeDirection( 'S' );
                break;

            case KeyEvent.VK_F:
                player.changeDirection( 'W' );
                break;

            case KeyEvent.VK_H:
                player.changeDirection( 'E' );
                break;

            case KeyEvent.VK_3:
                game.set3D( !game.is3D() );
                break;
        }
    }

    private void fightKeyClicked( int key ) {
        Fight fight = game.getCurrentFight();
        switch ( key ) {
            case KeyEvent.VK_D:
                if( fight.isPlayerTurn() )
                    fight.moveCursor( 1 );
                else
                    fight.movePlayer( 1 );
                break;

            case KeyEvent.VK_A:
                if( fight.isPlayerTurn() )
                    fight.moveCursor( -1 );
                else
                    fight.movePlayer( -1 );
                break;

            case KeyEvent.VK_ENTER:
                if( fight.isPlayerTurn() ){
                    fight.playerMove();
                }
                else{
                    fight.opponentMove();
                }

                fight.changeTurn();
            break;
        }
    }

    private void eqKeyClicked( int key ){
        Player player = game.getPlayer();

        switch ( key ){
            case KeyEvent.VK_UP:
                player.moveEQ( 0, -1 );
            break;

            case KeyEvent.VK_DOWN:
                player.moveEQ( 0, 1 );
            break;

            case KeyEvent.VK_LEFT:
                player.moveEQ( -1, 0 );
            break;

            case KeyEvent.VK_RIGHT:
                player.moveEQ( 1, 0 );
            break;

            case KeyEvent.VK_E:
                player.useItem();
            break;
        }
    }
}
