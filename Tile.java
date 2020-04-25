import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile { // implements ISpawner {
    protected String id;
    private BufferedImage image;
    protected boolean collidable;

    private Item item;

    // private ISpawner spawner;

    protected void loadImage() {
        try {
            image = ImageIO.read(new File("res/tiles/" + id + ".png"));
        } catch (IOException e) {
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public BufferedImage getImage(){
        return image;
    }

    public boolean getCollidable(){
        return collidable;
    }

    /*public ISpawner getSpawner(){
        return spawner;
    }*/
    public boolean canPlayerEnter(){
        return !collidable;
    }

    public void handlePlayerEnter(Game game){

    
    }

    public void handlePlayerStay(Game game){

    }
}