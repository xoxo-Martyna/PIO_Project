import java.awt.image.BufferedImage;

public class HealthItem extends Item {
    private final int recoverPoints;

    public HealthItem( BufferedImage image, String name, int recoverPoints ){
        super( image, name );
        this.recoverPoints = recoverPoints;
    }

    public int getRecoverPoints() {
        return recoverPoints;
    }

    @Override
    public String toString() {
        return getName()+". Recover points = " + recoverPoints;
    }
}