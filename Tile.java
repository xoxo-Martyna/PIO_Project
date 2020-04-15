import java.awt.image.BufferedImage;

public class Tile { //implements ISpawner {
    protected String id;
    private BufferedImage image;
    protected boolean collideable;
    //private ISpawner spawner;

    public String getId(){
        return id;
    }

    public BufferedImage getImage(){
        return image;
    }

    public boolean getCollideable(){
        return collideable;
    }

    /*public ISpawner getSpawner(){
        return spawner;
    }*/

    public boolean canPlayerEnter(){
        return !collideable;
    }

    public void handlePlayerEnter(Game game){

    }

    public void handlePlayerStay(Game game){

    }
}