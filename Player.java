import java.util.List;

public class Player implements IFightMember {
    private int x;
    private int y;
    
    private int healthPoints;
    private int defensePoints;

    private List<IItem> items;
    
    public int getX();
    public int getY();
    public void setX( int x );
    public void setY( int y );

    public int getHealthPoints();
    public int getDefensePoints();

    public void move( Level level, int dx, int dy );

    public void addItem( IItem item );
    public void dropItem( IItem item );
    public void discardDestroyedItems();
   
}