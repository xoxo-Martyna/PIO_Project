import java.awt.image.BufferedImage;

public class DefenseItem extends Item {
    private final int protectPoints;

    public DefenseItem( BufferedImage image, String name, int protectPoints ){
        super( image, name );
        this.protectPoints = protectPoints;
    } 

    public int getProtectPoints() {
        return protectPoints;
    }

    @Override
    public String toString() {
        return getName()+". Protect points = " + protectPoints;
    }
}