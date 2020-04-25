import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item {
    private final String id;
    private final String name;

    private BufferedImage image;

    public static ItemFactory factory = new ItemFactory();

    public static Item create(String id) {
        String[] itemInfo = factory.get(id);
        if (itemInfo == null) return null;

        if (itemInfo[2].equals("attack")) {
            return new AttackItem(
                itemInfo[1], itemInfo[3],
                Integer.parseInt(itemInfo[4])
            );
        } else if (itemInfo[2].equals("health")) {
            return new HealthItem(
                itemInfo[1], itemInfo[3],
                Integer.parseInt(itemInfo[4])
            );
        } else if (itemInfo[2].equals("defense")) {
            return new DefenseItem(
                itemInfo[1], itemInfo[3],
                Integer.parseInt(itemInfo[4])
            );
        }

        return null;
    }

    public Item( String id, String name ){
        this.id = id;
        this.name = name;

        loadImage();
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(new File("res/items/" + id + ".png"));
        } catch (IOException e) {
        }
    }

    public String getName(){
        return name;
    }

    public BufferedImage getImage(){
        return image;
    }
}