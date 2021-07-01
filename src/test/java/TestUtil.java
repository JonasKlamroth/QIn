import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestUtil {

    @Test
    public void testAdapt() {
        Expr[][] m = Utils.adapt(Utils.ID, 0, 2);

        assertEquals("1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,1.0f,0.0f,0.0f,\n" +
                "0.0f,0.0f,1.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n", matrixToString(m));
    }

    @Test
    public void testAdapt1() {
        Expr[][] m = Utils.adapt(Utils.X, 1, 2);

        assertEquals("0.0f,1.0f,0.0f,0.0f,\n" +
                "1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n" +
                "0.0f,0.0f,1.0f,0.0f,\n", matrixToString(m));
    }

    @Test
    public void testAdapt12() {
        Expr[][] m = Utils.adapt(Utils.X, 0, 2);

        assertEquals("0.0f,0.0f,1.0f,0.0f,\n" +
                "0.0f,0.0f,0.0f,1.0f,\n" +
                "1.0f,0.0f,0.0f,0.0f,\n" +
                "0.0f,1.0f,0.0f,0.0f,\n", matrixToString(m));
    }


    @Test
    public void testAdapt2() {
        Expr[][] m = Utils.adapt(Utils.X, 2, 3);

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
        Expr[][] m = Utils.adapt(Utils.X, 0, 3);

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
        Expr[][] m = Utils.adapt(Utils.X, 0, 2);
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
