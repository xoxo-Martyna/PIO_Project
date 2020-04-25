public class AttackItem extends Item{
    private final int attackPoints;

    public AttackItem( String id, String name, int attackPoints ){
        super( id, name );
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