
//import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player { //implements IFightMember {
    private int x;
    private int y;

    private BufferedImage def, down, up, left, right;
    
    private int healthPoints;
    private int defensePoints;

    private Game game;

    public static final int maxHealthPoints = 70;
    public static final int maxDefensePoints = 70;

    //private List<IItem> items;

    public Player(Game game){
        this.game = game;

        healthPoints = maxHealthPoints;
        defensePoints = 0;
        try{
            down = ImageIO.read(new File ("res/g_front.png"));
        
            up = ImageIO.read(new File ("res/g_back.png"));
        
            left = ImageIO.read(new File ("res/g_left.png"));
        
            right = ImageIO.read(new File ("res/g_right.png"));

            def = down;

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX( int x ){
        this.x = x;
    }

    public void setY( int y ){
        this.y = y;
    }

    public BufferedImage getImage(){    
        return def;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public int getDefensePoints(){
        return defensePoints;
    }

    public void move(int dx, int dy ){
        this.x+=dx;
        this.y+=dy;
         // def = left ( jak w lewo) itp
        
    }

    //public void addItem( IItem item );
    //public void dropItem( IItem item );
    //public void discardDestroyedItems();
   
}