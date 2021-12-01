public class Grover {
    /*@
      @ requires val >= 0 && val < 4;
      @ ensures \result == val;
      @*/
    public static int grover(int val) {
        float[][] m = getOracle(val);
        CircuitMock circuit = new CircuitMock(2, q_states, q_states_i);
        circuit.h(0);
        circuit.h(1);
        circuit.u(m, 0, 1);
        circuit.h(0);
        circuit.h(1);
        circuit.z(0);
        circuit.z(1);
        circuit.cz(0, 1);
        circuit.h(0);
        circuit.h(1);
        boolean res1 = circuit.measureMax(0);
        boolean res2 = circuit.measureMax(1);
        return (res1 ? 2 : 0) + (res2 ? 1 : 0);
    }

    private static float [][] getOracle(int val) {
        assert val >= 0 && val < 4;
        return new float[][]{
                new float[]{val == 0 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == 1 ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == 2 ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == 3 ? 1.0f : -1.0f}
        };
    }
}