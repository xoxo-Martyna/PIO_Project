import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
    protected String id;

    private BufferedImage image;
    private BufferedImage normalMapImage = null;

    protected boolean collidable;
    protected boolean castsShadows = false;

    private Item item;
    private Opponent opponent;

    public Tile( String id ) {
        this.id = id;

        loadImage();
    }

    protected void loadImage() {
        try {
            image = ImageIO.read( new File( "res/tiles/" + id + ".png" ) );
        } catch ( IOException e ) {
        }

        try {
            normalMapImage = ImageIO.read( new File( "res/tiles/" + id + "_nrm.png" ) );
        } catch ( IOException e ) {
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem( Item item ) {
        this.item = item;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent( Opponent opponent ) {
        this.opponent = opponent;
    }

    public String getId() {
        return id;
    }

    public BufferedImage getImage(){
        return image;
    }

    public BufferedImage getNormalMapImage(){
        return normalMapImage;
    }

    public boolean getCollidable(){
        return collidable;
    }

    public boolean isCastingShadows() {
        return castsShadows;
    }

    public boolean canPlayerEnter(){
        return !collidable;
    }

    public void handlePlayerEnter( Game game ) {
    }
}