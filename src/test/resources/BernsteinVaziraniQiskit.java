public class BernsteinVaziraniQiskit {
    public static final int N = 3;

    /*@ requires 0 <= a < (1 << N);
      @ ensures \result == a;
      @ assignable \nothing;
     */
    public int findHiddenShift(int a) {
        CircuitMock bvCircuit = new CircuitMock(N);

        // Apply Hadamard gates before querying the oracle
        for (int i = 0; i < N; i++) {
            bvCircuit.h(i);
        }

        // Apply the inner-product oracle
        for (int i = 0; i < N; i++) {
            if ((a & (1 << i)) != 0) {
                bvCircuit.z(i);  // Z gate applied based on 'a'
            }
        }

        // Apply Hadamard gates after querying the oracle
        for (int i = 0; i < N; i++) {
            bvCircuit.h(i);
        }

        int res = 0;
        for(int i = 0; i < N; ++i) {
            res += (bvCircuit.measurePos(i) ? (1 << i) : 0);
        }
        return res;
    }
}