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
        assertFalse(Deutsch.isBalanced(array));
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
        assertFalse(Deutsch.isBalanced(array));
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
        assertTrue(Deutsch.isBalanced(array));
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
        assertTrue(Deutsch.isBalanced(array));
    }

    @Test
    public void testIllegalInput() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Deutsch.isBalanced(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                boolean brokenInput[] = new boolean[]{true};
                Deutsch.isBalanced(null);
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
