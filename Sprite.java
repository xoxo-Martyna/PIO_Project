import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage sourceImage;
    private int frameCount;

    public BufferedImage getImage(){
        return sourceImage;
    }

    public int getFrameCount(){
        return frameCount;
    }
}