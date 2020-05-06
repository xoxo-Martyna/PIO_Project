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

    private BufferedImage imageArmor, imageSword;// , imageWeed;

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
            // imageWeed = ImageIO.read(new File ("res/items/outline_weed.png"));
        } catch ( IOException e ) {
            e.printStackTrace();
        }

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
            drawLevel( g2d, level );
            g2d.drawImage( player.getImage(), player.getX() * imageSize, player.getY() * imageSize, this );
            
            if ( useRTX ) {
                try {
                    rtxRenderer.renderLights( g2d, level, player );
                } catch( Exception e ) {
                    useRTX = false;
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
    }
    
    private void drawSidePanel( Graphics2D g2d, Player player ) {
        drawHealthBar( g2d, player );
        drawDefenseBar( g2d, player );
        drawAttackBar( g2d, player );

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

        for( int i = 0; i < 2; i++ )
            for( int j = 0; j < 3; j++ )
                g2d.drawRect( x + j * (w+d), y + i * 250, w, h );
        
        if( fight.isPlayerTurn() ){
            for( int i = 0; i < 3; i++ ) {
                if( i != fight.getXCursor() ){
                    g2d.drawRect( x + 1 + i * (w+d), y + 1, w - 2, h - 2);
                    g2d.drawRect( x + 2 + i * (w+d), y + 2, w - 4, h - 4);
                }
            }
        }

        drawOpponent( g2d, game.getCurrentFight().getOpponent(), 16 + x + fight.getOpponentPosition() * (w+d), y + 18 );

        g2d.drawImage( player.getImageDown().getScaledInstance( 128, 128, Image.SCALE_DEFAULT ), 16 + x + fight.getPlayerPosition() * (w+d), y + 268, this );
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

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x - 1, y - 1, Player.maxHealthPoints * 3 + 1, imageSize / 2 + 1 ) );
        g2d.setColor( Color.RED );
        g2d.fill( new Rectangle( x, y, player.getHealthPoints() * 3, imageSize / 2 ) );
    }

    private void drawDefenseBar( Graphics2D g2d, Player player ) {
        int x = levelAreaWidth + spaceSize;
        int y = statBarsPosition + imageSize / 2 + spaceSize;

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x - 1, y - 1, Player.maxDefensePoints * 3 + 1, imageSize / 2 + 1 ) );

        g2d.setColor( Color.DARK_GRAY );
        g2d.fill( new Rectangle( x, y, player.getDefensePoints() * 3, imageSize / 2 ) );
    }

    private void drawAttackBar( Graphics2D g2d, Player player ) {
        int x = levelAreaWidth + spaceSize;
        int y = statBarsPosition + 2 * ( imageSize / 2 + spaceSize );

        g2d.setColor( Color.BLACK );
        g2d.draw( new Rectangle( x - 1, y - 1, Player.maxAttackPoints * 3 + 1, imageSize / 2 + 1 ) );

        g2d.setColor( Color.LIGHT_GRAY );
        g2d.fill( new Rectangle( x, y, player.getAttackPoints() * 3, imageSize / 2 ) );
    }

    private void drawEquipment( Graphics2D g2d, Player player ) {
        g2d.setColor( Color.DARK_GRAY );
        int x, y, i, j, isd;
        y = equipmentPosition;
        x = levelAreaWidth + spaceSize;
        isd = imageSize + spaceSize;

        try {
            g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
            g2d.drawImage( imageSword, x, y, this );
            g2d.drawImage( player.getItem( 0, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        try {
            x += isd;
            g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem( 1, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        try {
            x += isd;
            g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
            g2d.drawImage( imageArmor, x, y, this );
            g2d.drawImage( player.getItem( 2, 0 ).getImage(), x, y, this );
        } catch ( NullPointerException e ) {
        }

        for ( y += imageSize + 5 * spaceSize, i = 1; y < equipmentPosition + 3 * isd; y += isd, i++ )
            for ( x = levelAreaWidth + spaceSize, j = 0; x < getSize().width; x += isd, j++ ) {
                g2d.draw( new Rectangle( x, y, imageSize, imageSize ) );
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

        g2d.setColor( Color.BLACK );

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
                player.move( 0, 1) ;
                break;

            case KeyEvent.VK_P:
                player.pickUpItem();
                break;

            case KeyEvent.VK_L:
                player.dropItem();
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
