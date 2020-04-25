import java.awt.image.BufferedImage;

public class Item {
    private final BufferedImage image;
    private final String name;

    public Item(String id) {
        // Grab stuff from the item database maybe?
    }

    public Item( BufferedImage image, String name ){
        this.image = image;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public BufferedImage getImage(){
        return image;
    }
}