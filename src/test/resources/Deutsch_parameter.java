public class Deutsch_parameter {

    static int n = 2;
    public static boolean isBalancednBit(boolean[] f) {
        if(f == null || f.length != 2**n) {
            throw new IllegalArgumentException("Input for Deutschalgorithm has to be boolean array of size 2 ** n.");
        }
        //create 2^n matrix
        final float[][] m = new float[][]{
                new float[]{!f[0] ? 1.0f : 0.0f, f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{f[0] ? 1.0f : 0.0f, !f[0] ? 1.0f :0.0f, 0.f, 0.f},
                new float[]{0.f, 0.f, !f[1] ? 1.0f : 0.0f, f[1] ? 1.0f :0.0f},
                new float[]{0.f, 0.f, f[1] ? 1.0f : 0.0f, !f[1] ? 1.0f :0.0f}
        };
        CircuitMock c = new CircuitMock( n + 1);
        c.x(n);

        for (int i = 0; i < n; i ++){
            c.h(i);
        }
        c.u(m, 0, 1); //on all qubits

        for (int i = 0; i < n; i ++){
            c.h(i);
        }
        return c.measure(0);
    }

}