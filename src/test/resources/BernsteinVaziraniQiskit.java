public class BernsteinVaziraniQiskit {
    public static final int N = 3;

    /*@ requires 0 <= a < (1 << N);
      @ requires a == 6;
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

        // Swap order of qubits to obtain correct measurement
        for (int i = 0; i < N/2; ++i) {
            bvCircuit.swap(i, N - i - 1);
        }
        int res = bvCircuit.measureAll();
        return res;
    }
}