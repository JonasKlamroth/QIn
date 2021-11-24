package QIn;

import QIn.Expressions.Expr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOperations {

    @Test
    public void testSwap() {
        Expr[][] state = Utils.getInitialState(3);
        state = Utils.apply(Utils.getExprMatrix(Utils.X), 0, state);
        Utils.applySwap(state, 0, 2);
        assertEquals(matrixToString(state), "0.0f + 0.0fi,\n" +
                "1.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n");
    }

    @Test
    public void testX() {
        Expr[][] state = Utils.getInitialState(1);
        state = Utils.apply(Utils.getExprMatrix(Utils.X), 0, state);
        assertEquals(matrixToString(state), "0.0f + 0.0fi,\n" +
                "1.0f + 0.0fi,\n");
        state = Utils.getInitialState(3);
        state = Utils.apply(Utils.getExprMatrix(Utils.X), 0, state);
        assertEquals("0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "1.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n",
                matrixToString(state));
        state = Utils.getInitialState(3);
        state = Utils.apply(Utils.getExprMatrix(Utils.X), 2, state);
        assertEquals("0.0f + 0.0fi,\n" +
                "1.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n" +
                "0.0f + 0.0fi,\n",
                matrixToString(state));
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
