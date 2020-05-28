package src;

import java.awt.*;
import java.awt.image.*;

public class MultiplyComposite implements Composite, CompositeContext {
    @Override
    public void compose( Raster src, Raster dstIn, WritableRaster dstOut ) {
        int width = Math.min( src.getWidth(), dstIn.getWidth() );
        int height = Math.min( src.getHeight(), dstIn.getHeight() );

        int x, y;

        int[] srcPixels = new int[width];
        int[] dstPixels = new int[width];

        for ( y = 0; y < height; y++ ) {
            src.getDataElements( 0, y, width, 1, srcPixels );
            dstIn.getDataElements( 0, y, width, 1, dstPixels );

            for ( x = 0; x < width; x++ ) {
                dstPixels[x] = mixColors( srcPixels[x], dstPixels[x] );
            }

            dstOut.setDataElements( 0, y, width, 1, dstPixels );
        }
    }

    private static int[] separateRGBInt( int color ) {
        int[] components = new int[4];

        components[0] = ( color >> 16 ) & 0xFF; // Red
        components[1] = ( color >> 8 ) & 0xFF; // Green
        components[2] = color & 0xFF; // Blue
        components[3] = ( color >> 24 ) & 0xFF; // Alpha

        return components;
    }

    private static int combineRGBInt( int[] components ) {
        return components[2] | (components[1] << 8) | (components[0] << 16) | ( components[3] << 24 );
    }

    public static int mixColors( int color1, int color2 ) {
        int[] c1 = separateRGBInt( color1 );
        int[] c2 = separateRGBInt( color2 );

        int[] outComponents = new int[4];
        outComponents[0] = ( c1[0] * c2[0] ) / 255;
        outComponents[1] = ( c1[1] * c2[1] ) / 255;
        outComponents[2] = ( c1[2] * c2[2] ) / 255;
        outComponents[3] = Math.min( 255, c1[3] + c2[3] );

        return combineRGBInt( outComponents );
    }


    @Override
    public CompositeContext createContext(
        ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints
    ) {
        return this;
    }

    @Override
    public void dispose() {}

    public static final MultiplyComposite Multiply = new MultiplyComposite();
}