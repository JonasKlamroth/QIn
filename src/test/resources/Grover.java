public class Grover {
    private static final float[][] T = new float[][]{
            new float[]{1.f, 0.f},
            new float[]{0.f, 0.707106781f}
    };
    private static final float[][] Tc = new float[][]{
            new float[]{0.f, 0.f},
            new float[]{0.f, 0.707106781f}
    };
    private static final float[][] Tc_t = new float[][]{
            new float[]{0.f, 0.f},
            new float[]{0.f, -0.707106781f}
    };

    /*@
      @ ensures ((val >= 0 && val < 4) ==> \result == val) && ((val < 0 || val > 3) ==> \result == -1);
      @*/
    public static int grover2(int val) {
        if(val < 0 || val > 3) {
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
      @ ensures ((val >= 0 && val < 8) ==> \result == val) && ((val < 0 || val > 7) ==> \result == -1);
      @*/
    public static int grover3(int val) {
        if(val < 0 || val > 7) {
            return -1;
        }
        final float[][] oracle4 = new float[][]{
                new float[]{val == 0 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == 1 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == 2 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == 3 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, val == 4 ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == 5 ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == 6 ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == 7 ? 1.0f : -1.0f}
        };
        CircuitMock circuit = new CircuitMock(3);
        circuit.h(0);
        circuit.h(1);
        circuit.h(2);
        circuit.u(oracle4, 0, 1, 2);
        circuit.h(0);
        circuit.h(1);
        circuit.h(2);
        circuit.x(0);
        circuit.x(1);
        circuit.x(2);

        //ccz
        circuit.cx(1, 2);
        circuit.u(T, Tc_t, 2);
        circuit.swap(0, 1);
        circuit.cx(1, 2);
        circuit.swap(0, 1);
        circuit.u(T, Tc, 2);
        circuit.cx(1, 2);
        circuit.u(T, Tc_t, 2);
        circuit.swap(0, 1);
        circuit.cx(1, 2);
        circuit.swap(0, 1);
        circuit.u(T, Tc, 2);
        circuit.u(T, Tc, 1);
        circuit.cx(0, 1);
        circuit.u(T, Tc, 0);
        circuit.u(T, Tc_t, 1);
        circuit.cx(0, 1);

        circuit.x(0);
        circuit.x(1);
        circuit.x(2);
        circuit.h(0);
        circuit.h(1);
        circuit.h(2);
        boolean res1 = circuit.measureMax(0);
        boolean res2 = circuit.measureMax(1);
        boolean res3 = circuit.measureMax(2);
        return (res1 ? 4 : 0) + (res2 ? 2 : 0) + (res3 ? 1 : 0);
    }
}