public class Ray{
    private double length;
    private double iterationLength;
    private Direction direction;
    private Level level;
    private double x;
    private double y;

    private double levelSize = 10;

    public Ray( Direction direction, Level level, double x, double y ){
        this.direction = direction;
        this.level = level;
        this.x = x;
        this.y = y;
        length = 0;
        iterationLength = Math.sqrt( direction.getX()*direction.getX() + direction.getY()*direction.getY() );
    }

    public Impact shoot(){
        
        while( x >= 0 && x <= levelSize && y >= 0 && y <= levelSize ){
            if( collision() )
                return calculateImpact();

            x += direction.getX();
            y += direction.getY();
            length += iterationLength;
        }

        return null;
    }
    
    private boolean collision(){
        Tile tile = level.getTile( (int)x, (int)y );
        if( tile instanceof GenericDoorTile || tile instanceof GenericWallTile || tile instanceof GenericMoveableTile )
            return true;
        else 
            return false;
    }

    private Impact calculateImpact(){
        double tmpY = y%1;
        double tmpX = x%1;
        double impactX = tmpX > 0.05 && tmpX < 0.95 ? tmpX : tmpY;

        return new Impact( impactX, level.getTile( (int)x, (int)y ) );
    }

    public double getLength(){
        return length;
    }

    @Override
    public String toString() {
        return "X=" + x + ", Y=" + y + ", length=" + length;
    }
}