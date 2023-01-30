package QIn;

import QIn.Expressions.ComplexExpression;
import QIn.Expressions.Const;
import QIn.Expressions.Expr;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Pair;
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

    @Test
    public void testRXGate() {
        Expr[][] rx = Utils.getRXGate(Math.PI*3);
        assertEquals("-1.8369701E-16f + 0.0fi,0.0f + 1.0fi,\n" +
                        "0.0f + 1.0fi,-1.8369701E-16f + 0.0fi,\n",
                matrixToString(rx));
        rx = Utils.getRXGate(0.0f);
        assertEquals("1.0f + 0.0fi,0.0f + -0.0fi,\n" +
                        "0.0f + -0.0fi,1.0f + 0.0fi,\n",
                matrixToString(rx));
    }

    @Test
    public void testRZGate() {
        Expr[][] rz = Utils.getRZGate(Math.PI*3);
        assertEquals("-1.8369701E-16f + 1.0fi,0.0f + 0.0fi,\n" +
                        "0.0f + 0.0fi,-1.8369701E-16f + -1.0fi,\n",
                matrixToString(rz));
        rz = Utils.getRZGate(0.0f);
        assertEquals("1.0f + -0.0fi,0.0f + 0.0fi,\n" +
                        "0.0f + 0.0fi,1.0f + 0.0fi,\n",
                matrixToString(rz));
    }

    @Test
    public void testNecessarySwaps() {
        Expr[][] qState = Utils.getInitialState(2);
        Pair<Integer, List<Pair<Integer, Integer>>> res = Utils.applyNecessarySwaps(qState, List.of(0, 1));
        assertEquals(0, res.fst);
        assertEquals(0, res.snd.length());

        res = Utils.applyNecessarySwaps(qState, List.of(1, 0));
        assertEquals(0, res.fst);
        assertEquals(1, res.snd.length());
        assertEquals(Pair.of(0, 1), res.snd.get(0));

        qState = Utils.getInitialState(4);
        res = Utils.applyNecessarySwaps(qState, List.of(1, 3));
        assertEquals(1, res.fst);
        assertEquals(1, res.snd.length());
        assertEquals(Pair.of(3, 2), res.snd.get(0));


        res = Utils.applyNecessarySwaps(qState, List.of(3, 2));
        assertEquals(2, res.fst);
        assertEquals(1, res.snd.length());
        assertEquals(Pair.of(2, 3), res.snd.get(0));

        res = Utils.applyNecessarySwaps(qState, List.of(3, 1));
        assertEquals(1, res.fst);
        assertEquals(2, res.snd.length());
        assertEquals(Pair.of(3, 2), res.snd.get(0));
        assertEquals(Pair.of(1, 3), res.snd.get(1));

        res = Utils.applyNecessarySwaps(qState, List.of(3, 1, 2));
        assertEquals(1, res.fst);
        assertEquals(2, res.snd.length());
        assertEquals(Pair.of(3, 2), res.snd.get(0));
        assertEquals(Pair.of(1, 3), res.snd.get(1));

        res = Utils.applyNecessarySwaps(qState, List.of(3, 1, 0));
        assertEquals(0, res.fst);
        assertEquals(2, res.snd.length());
        assertEquals(Pair.of(3, 2), res.snd.get(0));
        assertEquals(Pair.of(0, 3), res.snd.get(1));
    }

    @Test
    public void testNecessarySwapsInvalidInput() {
        Expr[][] qState = Utils.getInitialState(2);
        assertThrows(UnsupportedException.class, () -> Utils.applyNecessarySwaps(qState, List.of(0, 2)));
        assertThrows(UnsupportedException.class, () -> Utils.applyNecessarySwaps(qState, List.of(0, -1)));

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
