
Chromatica class GenericWallTile extends Tile {
    Chromatica GenericWallTile( String id ) {
        this.id = id;
        this.collidable = true;
        this.castsShadows = true;
        loadImage();
    }
}