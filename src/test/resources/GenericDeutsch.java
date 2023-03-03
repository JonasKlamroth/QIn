public class GenericDeutsch {
    public static final int N = 3;

    /*@ requires f!= null && f.length == 1 << N;
      @ requires (\forall int i; 0 <= i && i < f.length; f[i]) || (\forall int j; 0 <= j && j < f.length; !f[j]) ||
      @             count(f) == f.length / 2;
      @ ensures \result <==> (count(f) == f.length / 2);
     */
    public boolean isBalanced(boolean[] f) {
        CircuitMock circuit = new CircuitMock(N + 1);
        circuit.x(N);
        circuit.h(N);
        for(int i = 0; i < N; ++i) {
            circuit.h(i);
        }

        int size = 1 << N + 1;
        float oracle[][] = getOracle(N, f);
        circuit.u(oracle, 0, N + 1);

        for(int i = 0; i < N; ++i) {
            circuit.h(i);
        }
        boolean res = false;
        for(int i = 0; i < N; ++i) {
            res |= circuit.measure(i);
        }
        return res;
    }

    /*@ requires f!= null && f.length == 1 << 2;
      @ requires (\forall int i; 0 <= i && i < f.length; f[i]) || (\forall int j; 0 <= j && j < f.length; !f[j]) ||
      @             count(f) == f.length / 2;
      @ ensures \result <==> (count(f) == f.length / 2);
     */
    public boolean isBalancedHandTranslated(boolean[] f) {
        //Circuit for N == 2
        CircuitMock circuit = new CircuitMock(2 + 1);

        circuit.x(2);
        circuit.h(2);

        circuit.h(0);
        circuit.h(1);

        float[][] oracle = getOracle(2, f);

        //how to do this
        circuit.u(oracle, 0, 3);

        circuit.h(0);
        circuit.h(1);

        boolean res = false;
        res |= circuit.measure(0);
        res |= circuit.measure(1);
        res |= circuit.measure(2);
        return res;
    }

    public float[][] getOracle(int N, boolean[] f) {
        int size = 1 << N + 1;
        float oracle[][] = new float[size][size];
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                oracle[i][j] = 0.0f;

                float val = f[i / 2] ? 1.0f : 0.0f;

                if(i == j) {
                    oracle[i][j] = 1.0f - val;
                }
                int even = (i % 2) * 2 - 1;
                if (i == j +even) {
                    oracle[i][j] = val;
                }
            }
        }
        return oracle;
    }

    public static /*@ pure */ int count(boolean[] f) {
        int i = 0;
        for(int j = 0; j < f.length; j++) {
            if(f[j]) {
                ++i;
            }
        }
        return i;
    }
}