
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

    public static final int maxHealthPoints = 70;
    public static final int maxDefensePoints = 70;
    public static final int maxAttackPoints = 70;
    private final int defaultAttackPoints = 1;


    private Item items[][];         // dla y == 0, mamy eq z miejscem na miecz i zbroje
    private final int itemsH = 3;   // dla x == 0, miejsce na miecz
    private final int itemsW = 3;   // dla x == 1 lub 2, miejsce na zbroje
    private int itemsX;
    private int itemsY;

    public Player(Game game){
        this.game = game;

        items = new Item[itemsH][itemsW];
        itemsX = 0;
        itemsY = 0;

        healthPoints = maxHealthPoints-1;

        try{
            down = ImageIO.read(new File ("res/g_front.png"));
        
            up = ImageIO.read(new File ("res/g_back.png"));
        
            left = ImageIO.read(new File ("res/g_left.png"));
        
            right = ImageIO.read(new File ("res/g_right.png"));

            def = down;

        } catch(IOException e){
            e.printStackTrace();
        }

        items[1][1] = Item.create("diamond_sword");
        items[2][0] = Item.create("salvia_potion");
        items[2][2] = Item.create("ayahuasca_poison");
        items[2][1] = Item.create("iron_helmet");

        items[1][0] = Item.create("strass_sword");
        items[1][2] = Item.create("almost_iron_helmet");
    }

    public void setHPpoints( int slap){
        this.healthPoints -= slap;
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

    public BufferedImage getImageDown(){
        return down;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public int getDefensePoints(){
        int pp = 0;
        if( items[0][1] != null )
            pp += ((DefenseItem)items[0][1]).getProtectPoints();
        if( items[0][2] != null )
            pp += ((DefenseItem)items[0][2]).getProtectPoints();
        return pp;
    }

    public int getAttackPoints(){
        if( items[0][0] == null ){
            return defaultAttackPoints;
        }
        return ((AttackItem)items[0][0]).getAttackPoints();
    }

    public Item getAttackItem(){
        return items[0][0];
    }

    public Item getDefenseItem( int x ){
        if( x == 1 )
            return items[0][1];
        return items[0][2];
    }

    public Item getItem( int x, int y ){
        return items[y][x];
    }

    public Item getCurrentItem(){
        return items[itemsY][itemsX];
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
        } catch (ArrayIndexOutOfBoundsException | java.lang.NullPointerException f) {}
        checkCollisionOpponent();
    }

    private void checkCollisionOpponent(){
        try{
            Tile tile = game.getCurrentLevel().getTile(x, y);
            if( tile.getOpponent() != null ){
                game.startFight( new Fight(tile.getOpponent(), game) );
                tile.setOpponent( null );
            }
        } catch( NullPointerException e ){}
    }
    
    public void moveEQ( int dx, int dy ){
        if( itemsX+dx < itemsW && itemsX+dx >= 0 )
            itemsX += dx;

        if( itemsY+dy < itemsH && itemsY+dy >= 0 )
            itemsY += dy;
    }

    public void useItem(){
        if( itemsY == 0 ){
            takeOffItem();
            return;
        }

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
        deleteCurrentItem();
    }

    private void useDefenseItem(DefenseItem item){
        if(items[0][1]==null) {
            items[0][1]=item;
            deleteCurrentItem();
        }
        else if(items[0][2]==null){
            items[0][2]=item;
            deleteCurrentItem();
        }
        else {
            Item tempItem = items[0][1];
            items[0][1] = item;
            items[itemsY][itemsX] = tempItem;
        }
    }

    private void useAttackItem (AttackItem item){
        if(items[0][0]==null){
            items[0][0]=item;
            deleteCurrentItem();
        }
        else{
            Item tempItem = items[0][0];
            items[0][0] = item;
            items[itemsY][itemsX] = tempItem;
        }
    
    }

    private void takeOffItem(){
        for(int i=1;i<itemsH;i++)
            for(int j=0;j<itemsW;j++)
                if(items[i][j] == null){
                    items[i][j] = items[itemsY][itemsX];
                    deleteCurrentItem();                  
                    return;
                }
    }

    private void deleteCurrentItem(){
        items[itemsY][itemsX] = null;
    }

    public void pickItem(){
        Tile targetTile = game.getCurrentLevel().getTile(x, y);
        if( targetTile.getItem() != null){
            if(itemsY != 0 && items[itemsY][itemsX] == null){
                items[itemsY][itemsX] = targetTile.getItem();
                targetTile.setItem(null); 
            } else if (itemsY == 0 || items[itemsY][itemsX] != null) {
                for (int i = 1; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(items[i][j] == null ){
                            items[i][j] = targetTile.getItem();
                            targetTile.setItem(null);
                        }
                    }
                }
            }
        }
    }
    public void dropItem(){
        Tile targetTile = game.getCurrentLevel().getTile(x, y);
        if(items[itemsY][itemsX] != null && targetTile.getItem() == null ){
            targetTile.setItem(items[itemsY][itemsX]);
            deleteCurrentItem();
        }
        
    }
}