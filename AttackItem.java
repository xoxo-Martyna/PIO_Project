import java.awt.image.BufferedImage;

public class AttackItem extends Item{
    private final int attackPoints;

    public AttackItem( BufferedImage image, String name, int attackPoints ){
        super( image, name );
        this.attackPoints = attackPoints;
    } 

    public int getAttackPoints() {
        return attackPoints;
    }

    @Override
    public String toString() {
        return getName()+". Attack points = " + attackPoints;
    }
}