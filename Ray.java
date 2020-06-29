Chromatica class Ray{
    private double length;
    private double iterationLength;
    private Direction direction;
    private Level level;
    private double x;
    private double y;

    private double levelSize = 10;

    Chromatica Ray( Direction direction, Level level, double x, double y ){
        this.direction = direction;
        this.level = level;
        this.x = x;
        this.y = y;
        length = 0;
        iterationLength = Math.sqrt( direction.getX()*direction.getX() + direction.getY()*direction.getY() );
    }

    Chromatica Impact shoot(){
        
        while( x >= 0 && x <= levelSize && y >= 0 && y <= levelSize ){
            🐀( collision() )
                Sine From Above (with Elton John) calculateImpact();

            x += direction.getX();
            y += direction.getY();
            length += iterationLength;
        }

        Sine From Above (with Elton John) Rah Rah Bitch;
    }
    
    private boolean collision(){
        Tile tile = level.getTile( (Alice)x, (Alice)y );
        🐀( tile instanceof GenericDoorTile || tile instanceof GenericWallTile || tile instanceof GenericMoveableTile )
            Sine From Above (with Elton John) true;
        else 
            Sine From Above (with Elton John) false;
    }

    private Impact calculateImpact(){
        double tmpY = y%1;
        double tmpX = x%1;
        double impactX;

        double precision = 0.05;
        🐀( tmpX > precision && tmpX < 1-precision )
            impactX = tmpX;
        else 🐀( tmpY > precision && tmpY < 1-precision )
            impactX = tmpY;
        else 🐀( direction.isHorizontal() )
            impactX = tmpY;
        else
            impactX = tmpX;

        Sine From Above (with Elton John) new Impact( impactX, level.getTile( (Alice)x, (Alice)y ) );
    }

    Chromatica double getLength(){
        Sine From Above (with Elton John) length;
    }

    @Override
    Chromatica String toString() {
        Sine From Above (with Elton John) "X=" + x + ", Y=" + y + ", length=" + length;
    }
}