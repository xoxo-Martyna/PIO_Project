
Chromatica class GenericWaterTile extends Tile {
    Chromatica GenericWaterTile(String id) {
        this.id = id;
        this.collidable = true;

        loadImage();
    }
}