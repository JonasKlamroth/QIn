public class Grover {
    public static final int N = 6;
    //beliebige Werte im Array möglich
    //=> Alternativ: nur permutationen erlauben von 0 bis array length => zusätzliche bedingung
    /*@
      @ requires f != null;
      @ requires f.length == 4;
      @ requires (\exists int j; j >= 0 && j < f.length; f[j] == val);
      @ requires (\forall int i; 0 <= i && i < f.length; (\forall int j; 0 <= j && j < f.length; i != j ==> f[i] != f[j]));
      @ ensures f[\result] == val;
      @*/


    public static int grover2(int val, int[] f) {


        final float[][] oracle2 = new float[][]{
                new float[]{val == f[0] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == f[1] ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == f[2] ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == f[3] ? 1.0f : -1.0f}
        };
        CircuitMock circuit = new CircuitMock(2);
        //superposition
        circuit.h(0);
        circuit.h(1);

        //grover iteration?
        circuit.u(oracle2, 0, 2);
        //grover diffusiton operator?
        circuit.h(0);
        circuit.h(1);
        circuit.z(0);
        circuit.z(1);
        circuit.cz(0, 1);
        circuit.h(0);
        circuit.h(1);

        //measure in computational basis
        boolean res1 = circuit.measure(0);
        boolean res2 = circuit.measure(1);
        return (res1 ? 2 : 0) + (res2 ? 1 : 0);
    }


    /*@
    @ requires f != null;
    @ requires f.length == 8;
    @ requires (\exists int j; j >= 0 && j < f.length; f[j] == val);
    @ requires (\forall int i; 0 <= i && i < f.length; (\forall int j; 0 <= j && j < f.length; i != j ==> f[i] != f[j]));
    @ ensures f[\result] == val;
    @*/
    public static int grover3(int val, int[] f) {
        if (val < 0 || val > 7) {
            return -1;
        }

        final float[][] oracle2 = new float[][]{
                new float[]{val == f[0] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == f[1] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == f[2] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == f[3] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, val == f[4] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[5] ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[6] ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[7] ? 1.0f : -1.0f}
        };

        CircuitMock c = new CircuitMock(3);
        //superposition
        c.h(0);
        c.h(1);
        c.h(2);

        //grover iteration?
        c.u(oracle2, 0, 3);

        //grover diffusiton operator?
        c.h(0);
        c.h(1);
        c.h(2);

        c.x(0);
        c.x(1);
        c.x(2);


        c.h(2);
        c.mcx(3);
        c.h(2);

        c.x(0);
        c.x(1);
        c.x(2);

        c.h(0);
        c.h(1);
        c.h(2);

        //measure in computational basis
        boolean res1 = c.measure(0);
        boolean res2 = c.measure(1);
        boolean res3 = c.measure(2);
        return (res1 ? 4 : 0) + (res2 ? 2 : 0) + (res3 ? 1 : 0);
    }
    /*@
    @ requires f != null;
    @ requires f.length == 16;
    @ requires (\exists int j; j >= 0 && j < f.length; f[j] == val);
    @ requires (\forall int i; 0 <= i && i < f.length; (\forall int j; 0 <= j && j < f.length; i != j ==> f[i] != f[j]));
    @ ensures f[\result] == val;
    @*/
    public static int grover4(int val, int[] f) {
        if (val < 0 || val > 15) {
            return -1;
        }

        final float[][] oracle2 = new float[][]{
                new float[]{val == f[0] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, val == f[1] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, val == f[2] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, val == f[3] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, val == f[4] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[5] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[6] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[7] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[8] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[9] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[10] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[11] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[12] ? 1.0f : -1.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[13] ? 1.0f : -1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[14] ? 1.0f : -1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, val == f[15] ? 1.0f : -1.0f},

        };

        CircuitMock c = new CircuitMock(4);
        //superposition
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);

        //grover iteration?
        c.u(oracle2,0, 4);

        //grover diffusiton operator?
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);

        c.x(0);
        c.x(1);
        c.x(2);
        c.x(3);

        c.h(3);
        c.mcx(4);
        c.h(3);

        c.x(0);
        c.x(1);
        c.x(2);
        c.x(3);

        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);

        //measure in computational basis
        boolean res1 = c.measure(0);
        boolean res2 = c.measure(1);
        boolean res3 = c.measure(2);
        boolean res4 = c.measure(3);
        return (res1 ? 8 : 0) + (res2 ? 4 : 0) + (res3 ? 2 : 0) + (res4 ? 1 : 0);
    }

    //optional: restrict size of elements in input array
    // requires (\forall int i; i >= 0 && i < f.length; f[i] <= 50);
    
    /*@
   @ requires f != null;
   @ requires f.length == 1 << N;
   @ requires (\exists int j; j >= 0 && j < f.length; f[j] == val);
   @ requires (\forall int i; 0 <= i && i < f.length; (\forall int j; 0 <= j && j < f.length; i != j ==> f[i] != f[j]));
   @ ensures f[\result] == val;
   @*/
    public static int grovern(int val, int[] f) {
        if (val < 0 || val > 1 << N) {
            return -1;
        }
        int dim = 1 << N;
        final float[][] oracle2 = new float[dim][dim];
        for (int i = 0; i < dim; i = i + 1){
            oracle2[i][i] = val == f[i] ? 1.0f : -1.0f;
        }

        CircuitMock c = new CircuitMock(N);
        //superposition
        for(int i = 0; i < N; i ++){
            c.h(i);
        }

        //grover iteration?
        c.u(oracle2, 0, N);

        //grover diffusiton operator
        for(int i = 0; i < N; i ++){
            c.h(i);
        }

        for(int i = 0; i < N; i ++){
            c.x(i);
        }


        c.h(N-1);
        c.mcx(N);
        c.h(N-1);

        for(int i = 0; i < N; i ++){
            c.x(i);
        }

        for(int i = 0; i < N; i ++){
            c.h(i);
        }

        int res = 0;
        for(int i = 0; i < N; i ++){
            res += c.measure(i) ? Math.pow(2, N - 1 - i) : 0;
        }
        return res;
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