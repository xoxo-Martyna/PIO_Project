//import java.util.List;

public class Player implements IFightMember {
    private int x;
    private int y;

    private Sprite sprite;
    
    private int healthPoints;
    private int defensePoints;

    //private List<IItem> items;
    
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

    public Sprite getSprite(){
        return sprite;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public int getDefensePoints(){
        return defensePoints;
    }

    public void move( Level level, int dx, int dy ){

    }

    //public void addItem( IItem item );
    //public void dropItem( IItem item );
    //public void discardDestroyedItems();
   
}