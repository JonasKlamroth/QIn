package QIn;

import QIn.Expressions.ComplexExpression;
import QIn.Expressions.Const;
import QIn.Expressions.Expr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestUtil {

    @BeforeAll
    public static void setUp() {
        CLI.useFix = false;
        CLI.useReals = true;
    }

    @Test
    public void testAdapt() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.ID), 0, 2);

        assertEquals("1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,1.0f,0.0f,0.0f,\n" +
                "0.0f,0.0f,1.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n", matrixToString(m));
    }

    @Test
    public void testAdapt1() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.X), 1, 2);

        assertEquals("0.0f,1.0f,0.0f,0.0f,\n" +
                "1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n" +
                "0.0f,0.0f,1.0f,0.0f,\n", matrixToString(m));
    }

    @Test
    public void testAdapt12() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.X), 0, 2);

        assertEquals("0.0f,0.0f,1.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n" +
                "1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,1.0f,0.0f,0.0f,\n", matrixToString(m));
    }


    @Test
    public void testAdapt2() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.X), 2, 3);

        assertEquals("0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,\n",
                matrixToString(m));
    }

    @Test
    public void testAdapt3() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.X), 0, 3);

        assertEquals("0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,\n" +
                        "1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,\n",
                matrixToString(m));
    }

    @Test
    public void testAdapt4() {
        Expr[][] m = Utils.adapt(Utils.getExprMatrix(Utils.X), 0, 2);
        Expr[][] m2 = Utils.adapt(m, 0, 3);

        assertEquals("0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,1.0f,\n" +
                        "1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,\n" +
                        "0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,\n",
                matrixToString(m2));
    }

    @Test
    public void testInitialState() {
        CLI.useReals = true;
        Expr[][] state = Utils.getInitialState(2);
        for(int i = 0; i < state.length; ++i) {
            assertEquals(1, state[i].length);
            assertTrue(state[i][0] instanceof Const);
            assertFalse(state[i][0] instanceof ComplexExpression);
        }
        assertTrue(((Const)state[0][0]).isOne());
        CLI.useReals = false;
        state = Utils.getInitialState(2);
        for(int i = 0; i < state.length; ++i) {
            assertEquals(1, state[i].length);
            assertTrue(state[i][0] instanceof ComplexExpression);
        }
        CLI.useReals = true;

    }

    public String matrixToString(Expr[][] m) {
        StringBuilder sb = new StringBuilder();
        for(Expr[] a : m) {
            for(Expr s : a) {
                sb.append(s.simplify() + ",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
