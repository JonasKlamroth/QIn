public class Grover {
    /*@
      requires n == 4;
      requires database != null && database.length == n;
      requires (\forall int i; 0 <= i < n; database[i] >= 0 && database[i] < n);
      requires (\forall int i; 0 <= i < n; (\forall int j; 0 <= j < n; i == j || database[i] != database[j]));
      ensures ((val >= 0 && val < n) ==> val == database[\result]);
      ensures ((val < 0 || val >= n) ==> \result == -1);
      @*/
    public static int grover2(int[] database, int val, int n) {
        if (val < 0 || val > 3) {
            return -1;
        }
        final float[][] oracle2 = new float[][]{
                new float[]{val == database[0] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == database[1] ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == database[2] ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == database[3] ? 1.0f : -1.0f}
        };
        CircuitMock circuit = new CircuitMock(2);
        circuit.h(0);
        circuit.h(1);
        circuit.u(oracle2, 0, 2);
        circuit.h(0);
        circuit.h(1);
        circuit.z(0);
        circuit.z(1);
        circuit.cz(0, 1);
        circuit.h(0);
        circuit.h(1);
        int res = circuit.measureAll();
        return res;
    }

    /*@
      requires n == 4;
      requires database != null && database.length == n;
      requires (\forall int i; 0 <= i < n; database[i] >= 0 && database[i] < n);
      requires (\forall int i; 0 <= i < n; (\forall int j; 0 <= j < n; i == j || database[i] != database[j]));
      ensures ((val >= 0 && val < n) ==> val == database[\result]);
      ensures ((val < 0 || val >= n) ==> \result == -1);
      @*/
    public static int grover2broken(int[] database, int val, int n) {
        if (val < 0 || val > 3) {
            return -1;
        }
        final float[][] oracle2 = new float[][]{
            new float[]{val == database[0] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
            new float[]{0.0f, val == database[1] ? 1.0f : -1.0f, 0.0f, 0.0f},
            new float[]{0.0f, 0.0f, val == database[2] ? 1.0f : -1.0f, 0.0f},
            new float[]{0.0f, 0.0f, 0.0f, val == database[3] ? 1.0f : -1.0f}
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
        int res = circuit.measureAll();
        return res;
    }
}
