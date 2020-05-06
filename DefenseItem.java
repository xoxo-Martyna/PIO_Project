public class DefenseItem extends Item {
    private int protectPoints;
    
    public DefenseItem( String id, String name, int protectPoints ){
        super( id, name );
        this.protectPoints = protectPoints;
    } 

    public int getProtectPoints() {
        return protectPoints;
    }

    @Override
    public String toString() {
        return getName()+". Protect points = " + protectPoints;
    }

    public void setProtectPoints( int protectPoints ){
        this.protectPoints = protectPoints;
    }
}