import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile { //implements ISpawner {
    protected String id;
    private BufferedImage image;
    protected boolean collideable;
    //private ISpawner spawner;

    protected void loadImage() {
        try {
            image = ImageIO.read(
                new File(
                    "res/tiles/" + id + ".png"
                )
            );
        } catch (IOException e) {}
    }

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