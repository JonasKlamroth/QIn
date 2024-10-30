public class BernsteinVazirani {
    public static final int N = 3;

    /*@ requires f!= null && f.length == 1 << N;
      @ requires (\exists int s; s >= 0 && s < f.length; (\forall int x; x >= 0 && x < f.length; (countOnes(x&s) % 2) == (f[x] ? 1 : 0)));
      @ ensures (\forall int x; x >= 0 && x < f.length; (countOnes(x&\result) % 2) == (f[x] ? 1 : 0));
      @ assignable \nothing;
     */
    public int findHiddenShift(boolean[] f) {
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

        int res = 0;
        res = circuit.measureAll() >> 1;
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

    public /*@ pure */ int countOnes(int val) {
        int ones = 0;
        for(int i = 0; i <= val; ++i) {
            if((val & (1 << i)) != 0) {
                ones++;
            }
        }
        return ones;
    }
}