package tests;

import src.GenericFloorTile;
import src.LightSource;
import src.Tile;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LightSourceTest {

    @org.junit.jupiter.api.Test
    void getColor() {
        LightSource pointLight = new LightSource(
                0.0f, 0.0f, 1.0f, 0.5f, 0.3f, 0.0f
        );

        Tile testTile = new GenericFloorTile("f_concrete");

        Color lightColor = pointLight.getColor(
                1.0f, 1.0f, testTile
        );

        double expectedLightVecX = 0.0 - 1.0;
        double expectedLightVecY = 0.0 - 1.0;
        double expectedLightVecZ = 1.0 - 0.0;
        double expectedLightVecLength = Math.sqrt(
                expectedLightVecX * expectedLightVecX +
                expectedLightVecY * expectedLightVecY +
                expectedLightVecZ * expectedLightVecZ
        );
        expectedLightVecX /= expectedLightVecLength;
        expectedLightVecY /= expectedLightVecLength;
        expectedLightVecZ /= expectedLightVecLength;

        double expectedIntensity = (
                0.0 * expectedLightVecX +
                0.0 * expectedLightVecY +
                1.0 * expectedLightVecZ
        );
        float[] components = lightColor.getRGBComponents(null);
        float eps = 1e-4f;

        assertTrue(Math.abs(components[0] - expectedIntensity) < eps);
        assertTrue(Math.abs(components[1] - expectedIntensity * 0.5) < eps);
        assertTrue(Math.abs(components[2] - expectedIntensity * 0.3) < eps);
    }
}