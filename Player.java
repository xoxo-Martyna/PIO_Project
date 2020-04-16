
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

    //private List<IItem> items;

    public Player(){
        try{
            down = ImageIO.read(new File ("res/g_front.png"));
        } catch(IOException e){}
    
        try{
            up = ImageIO.read(new File ("res/g_back.png"));
        } catch(IOException e){}
    
        try{
            left = ImageIO.read(new File ("res/g_left.png"));
        } catch(IOException e){}
    
        try{
            right = ImageIO.read(new File ("res/g_right.png"));
        } catch(IOException e){}
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

    public void move( Level level, int dx, int dy ){
        // def = left ( jak w lewo) itp
    }

    //public void addItem( IItem item );
    //public void dropItem( IItem item );
    //public void discardDestroyedItems();
   
}