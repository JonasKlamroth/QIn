public class Deutsch_parameter {

    static final int n = 1;

    /*@ requires f != null && f.length == 1<<n;
      @ requires (\forall int i; 0 <= i && i < f.length; f[i]) || (\forall int j; 0 <= j && j < f.length; !f[j]) ||
      @             count(f) == f.length / 2;
      @ ensures \result <==> (count(f) == f.length / 2);
      @*/
    public static boolean isBalancednBit(boolean[] f) {
        //Deutsch_parameter.java -o Deutsch_parameter.java -n 3
        if(f == null || f.length != 1 << n) {
            throw new IllegalArgumentException("Input for Deutschalgorithm has to be boolean array of size 2 ** n.");
        }
        //create 2^n matrix
        int dim = 1 << (n+1);

        final float[][] m = new float[dim][dim];

        for (int i = 0; i < dim; i = i + 2){
            m[i][i] = !f[0] ? 1.0f : 0.0f;
            m[i][i + 1] = f[0] ? 1.0f : 0.0f;

            m[i + 1][i] = f[0] ? 1.0f : 0.0f;
            m[i + 1][i + 1] = !f[0] ? 1.0f : 0.0f;
        }

        CircuitMock c = new CircuitMock(n + 1);
        c.x(n);

        for (int i = 0; i < n + 1; i ++){
            c.h(i);
        }

        int[] q = new int[n + 1];

        for (int i = 0; i < n + 1; i ++){
            q[i] = i;
        }

        c.u(m, q); //on all qubits

        for (int i = 0; i < n; i ++){
            c.h(i);
        }

        boolean result = false;
        for (int i = 0; i < n; i ++){
            result |= c.measure(i);
        }
        return result;
    }

}
