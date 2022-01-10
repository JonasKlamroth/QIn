package Translations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroverTest {

    @Test
    public void testNormalBehaviour() {
        for(int i = 0; i < 4; ++i) {
            assertEquals(i, Grover.grover2(i));
        }
    }

    @Test
    public void testIllegalInput() {
        assertEquals(-1, Grover.grover2(-1));
        assertEquals(-1, Grover.grover2(-2132));
        assertEquals(-1, Grover.grover2(4));
        assertEquals(-1, Grover.grover2(123980));
    }


    @Test
    public void testNormalBehaviourBroken() {
        //this is wrong but we check that we find that
        assertEquals(0, Grover.grover2broken(1));
    }

}
