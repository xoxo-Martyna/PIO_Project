import java.awt.*;
import java.awt.image.*;

public class MultiplyComposite implements Composite, CompositeContext {
    @Override
    public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
        int width = Math.min(src.getWidth(), dstIn.getWidth());
        int height = Math.min(src.getHeight(), dstIn.getHeight());

        int x, y;

        int[] srcPixels = new int[width];
        int[] dstPixels = new int[width];

        for (y = 0; y < height; y++) {
            src.getDataElements(0, y, width, 1, srcPixels);
            dstIn.getDataElements(0, y, width, 1, dstPixels);

            for (x = 0; x < width; x++) {
                dstPixels[x] = mixColors(srcPixels[x], dstPixels[x]);
            }

            dstOut.setDataElements(0, y, width, 1, dstPixels);
        }
    }

    private static int mixColors(int x, int y) {
        int xb = (x) & 0xFF;
        int yb = (y) & 0xFF;
        int b = (xb * yb) / 255;

        int xg = (x >> 8) & 0xFF;
        int yg = (y >> 8) & 0xFF;
        int g = (xg * yg) / 255;

        int xr = (x >> 16) & 0xFF;
        int yr = (y >> 16) & 0xFF;
        int r = (xr * yr) / 255;

        int xa = (x >> 24) & 0xFF;
        int ya = (y >> 24) & 0xFF;
        int a = Math.min(255, xa + ya);

        return (b) | (g << 8) | (r << 16) | (a << 24);
    }


    @Override
    public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
        return this;
    }

    @Override
    public void dispose() {}

    public static final MultiplyComposite Multiply = new MultiplyComposite();
}