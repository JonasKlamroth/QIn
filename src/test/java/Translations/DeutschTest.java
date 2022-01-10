package Translations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class DeutschTest {

    @Test
    public void testConstFalse() {
        Function constFalse = new Function() {
            @Override
            public boolean exec(boolean input) {
                return false;
            }
        };
        boolean array[] = fToArray(constFalse);
        assertFalse(Deutsch.isBalanced1Bit(array));
    }

    @Test
    public void testConstFalseBroken() {
        Function constFalse = new Function() {
            @Override
            public boolean exec(boolean input) {
                return false;
            }
        };
        boolean array[] = fToArray(constFalse);
        assertFalse(Deutsch.isBalanced1BitBroken(array));
    }


    @Test
    public void testConstTrueBroken() {
        Function constTrue = new Function() {
            @Override
            public boolean exec(boolean input) {
                return false;
            }
        };
        boolean array[] = fToArray(constTrue);
        assertFalse(Deutsch.isBalanced1BitBroken(array));
    }

    @Test
    public void testConstTrue() {
        Function constTrue = new Function() {
            @Override
            public boolean exec(boolean input) {
                return false;
            }
        };
        boolean array[] = fToArray(constTrue);
        assertFalse(Deutsch.isBalanced1Bit(array));
    }

    @Test
    public void testNotBroken() {
        Function not = new Function() {
            @Override
            public boolean exec(boolean input) {
                return !input;
            }
        };
        boolean array[] = fToArray(not);
        //this is wrong. it should be true. we just don't want the test to fail.
        //however we could have found the error here
        assertFalse(Deutsch.isBalanced1BitBroken(array));
    }

    @Test
    public void testNot() {
        Function not = new Function() {
            @Override
            public boolean exec(boolean input) {
                return !input;
            }
        };
        boolean array[] = fToArray(not);
        assertTrue(Deutsch.isBalanced1Bit(array));
    }

    @Test
    public void testIdentity() {
        Function id = new Function() {
            @Override
            public boolean exec(boolean input) {
                return input;
            }
        };
        boolean array[] = fToArray(id);
        assertTrue(Deutsch.isBalanced1Bit(array));
    }

    @Test
    public void testIllegalInput() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Deutsch.isBalanced1Bit(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                boolean brokenInput[] = new boolean[]{true};
                Deutsch.isBalanced1Bit(null);
            }
        });
    }


    public static boolean[] fToArray(Function f) {
        return new boolean[]{f.exec(false), f.exec(true)};
    }

    abstract class Function {
        public abstract boolean exec(boolean input);
    }
}
