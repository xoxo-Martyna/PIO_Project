public class AttackItem extends Item{
    private int attackPoints;

    public AttackItem( String id, String name, int attackPoints ){
        super( id, name );
        this.attackPoints = attackPoints;
    } 

    public int getAttackPoints() {
        return attackPoints;
    }

    @Override
    public String toString() {
        return getName() + ". Attack points = " + attackPoints;
    }

    public void setAttackPoints( int attackPoints ){
        this.attackPoints = attackPoints;
    }
}