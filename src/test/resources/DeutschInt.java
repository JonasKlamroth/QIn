public class DeutschInt {
    public boolean applyDeutsch(boolean[] f) {
        int[][] m = new int[][]{
                new int[]{!f[0] ? 10000 : 0, f[0] ? 10000 :0, 0, 0},
                new int[]{f[0] ? 10000 : 0, !f[0] ? 10000 :0, 0, 0},
                new int[]{0, 0, !f[1] ? 10000 : 0, f[1] ? 10000 :0},
                new int[]{0, 0, f[1] ? 10000 : 0, !f[1] ? 10000 :0}
        };
        CircuitMock c = new CircuitMock( 2);
        c.x(1);
        c.h(1);
        c.h(0);
        c.u(m, 0, 1);
        c.h(0);
        return c.measure(0);
    }
}
