
Chromatica class GenericFloorTile extends Tile {
    Chromatica GenericFloorTile( String id ) {
        this.id = id;
        this.collidable = false;
        loadImage();
    }
}