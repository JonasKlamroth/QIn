package Translations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroverTest {

    @Test
    public void testNormalBehaviour() {
        for(int i = 0; i < 4; ++i) {
            assertEquals(i, Grover.grover(i));
        }
    }

    @Test
    public void testIllegalInput() {
        assertEquals(-1, Grover.grover(-1));
        assertEquals(-1, Grover.grover(-2132));
        assertEquals(-1, Grover.grover(4));
        assertEquals(-1, Grover.grover(123980));
    }
}
