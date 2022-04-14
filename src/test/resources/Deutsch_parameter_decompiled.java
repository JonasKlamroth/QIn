public class Deutsch_parameter_decompiled {
    static final int n = 1;

    public Deutsch_parameter_decompiled() {
    }

    public static boolean isBalancednBit(boolean[] f) {
        if (f == null || f.length != 2) {
            throw new IllegalArgumentException("Input for Deutschalgorithm has to be boolean array of size 2 ** n.");
        }

            int dim = 4;
            float[][] m = new float[dim][dim];

            for(int i = 0; i < dim; i += 2) {
                m[i][i] = !f[0] ? 1.0F : 0.0F;
                m[i][i + 1] = f[0] ? 1.0F : 0.0F;
                m[i + 1][i] = f[0] ? 1.0F : 0.0F;
                m[i + 1][i + 1] = !f[0] ? 1.0F : 0.0F;
            }

            CircuitMock c = new CircuitMock(2);
            c.x(1);

            for(int i = 0; i < 2; ++i) {
                c.h(i);
            }

            int[] q = new int[2];


            for(int i = 0; i < 2; q[i] = i++) {
            }

            c.u(m, 0, 1);

            for(int i = 0; i < 1; ++i) {
                c.h(i);
            }

            boolean result = false;

            for(int i = 0; i < 1; ++i) {
                result |= c.measure(i);
            }

            return result;

    }
}
