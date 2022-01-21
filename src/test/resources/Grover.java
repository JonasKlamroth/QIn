public class Grover {
    /*@
      @ ensures ((val >= 0 && val < 4) ==> \result == val) && ((val < 0 || val > 3) ==> \result == -1);
      @*/
    public static int grover2(int val) {
        if (val < 0 || val > 3) {
            return -1;
        }
        final float[][] oracle2 = new float[][]{
                new float[]{val == 0 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == 1 ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == 2 ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == 3 ? 1.0f : -1.0f}
        };
        CircuitMock circuit = new CircuitMock(2);
        circuit.h(0);
        circuit.h(1);
        circuit.u(oracle2, 0, 1);
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

    /*@
          @ ensures ((val >= 0 && val < 4) ==> \result == val) && ((val < 0 || val > 3) ==> \result == -1);
          @*/
    public static int grover2broken(int val) {
        if (val < 0 || val > 3) {
            return -1;
        }
        final float[][] oracle2 = new float[][]{
                new float[]{val == 0 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == 1 ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == 2 ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == 3 ? 1.0f : -1.0f}
        };
        CircuitMock circuit = new CircuitMock(2);
        circuit.h(0);
        circuit.h(1);
        circuit.u(oracle2, 0, 1);
        circuit.h(0);
        circuit.h(1);
        circuit.z(0);
        circuit.z(1);
        circuit.cz(0, 1);
        circuit.h(0);
        boolean res1 = circuit.measure(0);
        boolean res2 = circuit.measure(1);
        return (res1 ? 2 : 0) + (res2 ? 1 : 0);
    }
}
