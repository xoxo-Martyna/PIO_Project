
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player { //implements IFightMember {
    private int x;
    private int y;

    private BufferedImage def, down, up, left, right;
    
    private Game game;  

    private int healthPoints;
    private int defensePoints;

    public static final int maxHealthPoints = 70;
    public static final int maxDefensePoints = 70;

    private Item items[][];
    private final int itemsH = 3;
    private final int itemsW = 3;
    private int itemsX;
    private int itemsY;

    public Player(Game game){
        this.game = game;

        items = new Item[itemsH][itemsW];
        itemsX = 0;
        itemsY = 0;

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

    public void resetFacing() {
        def = down;
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

    public int hurt(int points) {
        healthPoints -= points;

        return healthPoints;
    }

    public int getDefensePoints(){
        return defensePoints;
    }

    public Item getItem( int x, int y ){
        return items[y][x];
    }

    public int getItemsX(){
        return itemsX;
    }

    public int getItemsY(){
        return itemsY;
    }
 
    public void move(int dx, int dy ){
        try {
            Tile targetTile = game.getCurrentLevel().getTile(x+dx, y+dy);
            if(targetTile.canPlayerEnter()){
                this.x+=dx;
                this.y+=dy;
                if(dx == 1)
                    def = right;
                else if(dx == -1)
                    def = left;

                if(dy == 1)
                    def = down;
                else if(dy == -1)
                    def = up;
                targetTile.handlePlayerEnter(game);
            }
        } catch (ArrayIndexOutOfBoundsException | java.lang.NullPointerException f) {
            move(0, 0);
        }
    }
    
    public void moveEQ( int dx, int dy ){
        if( itemsX+dx < itemsW && itemsX+dx >= 0 )
            itemsX += dx;

        if( itemsY+dy < itemsH && itemsY+dy >= 0 )
            itemsY += dy;
    }

    public void useItem(){
        System.out.println("Uzyles przedmiotu ( " + itemsX +", "+ itemsY + " )"); // do wywalenia pozniej
        Item item = items[itemsY][itemsX];

        if( item instanceof HealthItem )
            useHealthItem( (HealthItem)item );
        else if( item instanceof DefenseItem )
            useDefenseItem( (DefenseItem)item );
        else if( item instanceof AttackItem )
            useAttackItem( (AttackItem)item );
    }

    private void useHealthItem( HealthItem item ){
        if( healthPoints == maxHealthPoints )
                return;
            
        healthPoints += item.getRecoverPoints();
        if( healthPoints >= maxHealthPoints )
            healthPoints = maxHealthPoints;
        deleteItem();
    }

    private void useDefenseItem( DefenseItem item ){
        
    }

    private void useAttackItem( AttackItem item ){

    }

    private void deleteItem(){
        items[itemsY][itemsX] = null;
    }
}