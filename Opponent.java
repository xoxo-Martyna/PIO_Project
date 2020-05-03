import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Opponent implements IFightMember {
    private int healthPoints;
    private int defensePoints;
    private int attackPoints; 


    private String id;
    private String name;

    private BufferedImage image;

    private static XaxaLoader factory = null;

    public Opponent(String id, String name, int healthPoints, int defensePoints) {
        this.name = name;
        this.id = id;
        this.healthPoints = healthPoints;
        this.defensePoints = defensePoints;

        loadImage();
    }

    public static Opponent create(String id) {
        try {
            if (factory == null) factory = new XaxaLoader("res/opponents.xaxa");
            
            String[] oppInfo = factory.get(id);
            if (oppInfo == null) return null;
    

            return new Opponent(
                oppInfo[0], oppInfo[1],
                Integer.parseInt(oppInfo[2]),
                Integer.parseInt(oppInfo[3])
            );
        } catch(IOException e) {
        }

        return null;
    }

    protected void loadImage() {
        try {
            image = ImageIO.read(new File("res/opponents/" + id + ".png"));
        } catch (IOException e) {
        }
    }

    public BufferedImage getImage(){
        return image;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    
    public int getDefensePoints() {
        return defensePoints;
    }

    public String getName() {
        return "";
    }

    public void setHealthPoints(int hp) {
        healthPoints=hp;
    }
}