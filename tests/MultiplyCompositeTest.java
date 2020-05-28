package tests;

import src.MultiplyComposite;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyCompositeTest {

    @org.junit.jupiter.api.Test
    void mixColors() {
        // White * Red = Red
        // Cyan * Green = Green
        // White * Gray = Gray
        // Magenta * Cyan = Blue
        assertEquals(0xFF0000, MultiplyComposite.mixColors(0xFFFFFF, 0xFF0000));
        assertEquals(0x00FF00, MultiplyComposite.mixColors(0x00FF00, 0x00FFFF));
        assertEquals(0x808080, MultiplyComposite.mixColors(0xFFFFFF, 0x808080));
        assertEquals(0x0000FF, MultiplyComposite.mixColors(0xFF00FF, 0x00FFFF));

        // A * B = B *
        int ab = MultiplyComposite.mixColors(0x52ABF3, 0x49BFC1);
        int ba = MultiplyComposite.mixColors(0x49BFC1, 0x52ABF3);
        assertEquals(ab, ba);

        // Everything multiplied by black = black
        // Black * Blue = Black
        // Black * Red = Black
        // Black * Some weird color = Black
        assertEquals(0, MultiplyComposite.mixColors(0, 0x0000FF));
        assertEquals(0, MultiplyComposite.mixColors(0, 0xFF0000));
        assertEquals(0, MultiplyComposite.mixColors(0, 0x9A0BF3));
    }
}