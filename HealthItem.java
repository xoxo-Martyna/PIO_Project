public class HealthItem extends Item {
    private final int recoverPoints;

    public HealthItem( String id, String name, int recoverPoints ){
        super( id, name );
        this.recoverPoints = recoverPoints;
    }

    public int getRecoverPoints() {
        return recoverPoints;
    }

    @Override
    public String toString() {
        return getName() + ". Recover points = " + recoverPoints;
    }
}