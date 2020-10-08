public class Impact {
    private Tile tile;
    private double x; // 0-1 miejsce uderzenia 0-lewa krawedz, 1-prawa krawedz

    public Impact( double x, Tile tile ){
        this.tile = tile;
        this.x = x;
    }

    public Tile getTile(){
        return tile;
    }

    public double getX(){
        return x;
    }

    @Override
    public String toString() {
        return tile.getId() + ", X=" + x;
    }
}