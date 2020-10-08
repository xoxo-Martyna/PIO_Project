public class Direction {
    private double x;
    private double y;

    public Direction( double x, double y ){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public boolean isHorizontal(){
        double x = this.x < 0 ? -1*this.x: this.x;
        double y = this.y < 0 ? -1*this.y: this.y;
        return x >= y;
    }
}