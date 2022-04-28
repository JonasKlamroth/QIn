public class Deutsch_parameter_decompiled {
    static final int n = 1;

    public Deutsch_parameter_decompiled() {
    }

    public static boolean isBalancednBit(boolean[] f) {
        if(f == null || f.length != 8) {
            throw new IllegalArgumentException("Input for Deutschalgorithm has to be boolean array of size 2 ** n.");
        }

            float[][] m = new float[16][16];

            for(int i = 0; i < 16; i += 2) {
                m[i][i] = !f[i / 2] ? 1.0F : 0.0F;
                m[i][i + 1] = f[i / 2] ? 1.0F : 0.0F;
                m[i + 1][i] = f[i / 2] ? 1.0F : 0.0F;
                m[i + 1][i + 1] = !f[i / 2] ? 1.0F : 0.0F;
            }

            CircuitMock c = new CircuitMock(4);
            c.x(3);

            for(int i = 0; i < 4; ++i) {
                c.h(i);
            }

            int[] q = new int[4];


            for(int i = 0; i < 4; q[i] = i++) {
            }

            c.u(m, 0, 1, 2, 3);

            for(int i = 0; i < 3; ++i) {
                c.h(i);
            }

            boolean result = false;

            for(int i = 0; i < 3; ++i) {
                result |= c.measure(i);
            }

            return result;
        }
}
