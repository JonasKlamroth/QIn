public class Deutsch {

    //@ requires f != null && f.length == 2;
    //@ ensures \result <==> (f[0] != f[1]);
    public static boolean isBalanced(boolean[] f) {
        if(f == null || f.length != 2) {
            throw new IllegalArgumentException("Input for Deutschalgorithm has to be boolean array of size 2.");
        }
        float[][] m = new float[][]{
                new float[]{!f[0] ? 1.0f : 0.0f, f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{f[0] ? 1.0f : 0.0f, !f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{0.f, 0.f, !f[1] ? 1.0f : 0.0f, f[1] ? 1.0f :0.0f},
                new float[]{0.f, 0.f, f[1] ? 1.0f : 0.0f, !f[1] ? 1.0f :0.0f}
        };
        CircuitMock c = new CircuitMock( 2);
        c.x(1);
        c.h(1);
        c.h(0);
        c.u(m, 0, 1);
        c.h(0);
        return c.measureMax(0);
    }
}
