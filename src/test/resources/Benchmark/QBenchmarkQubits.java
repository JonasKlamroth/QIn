public class QBenchmarkQubits {
    public static final int N = 2;

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == (1 << N) && qstatei.length == (1 << N);
      @ requires 0 <= k < qstate.length;
      @ requires qstate[k] == 1.0F;
      @ requires (\forall int i; i >= 0 && i < qstate.length; (i != k) ==> qstate[i] == 0.0f);
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_n1(float[] qstate, float[] qstatei, int k) {
        CircuitMock c = new CircuitMock(N, qstate, qstatei);
        for(int i = 0; i < N; ++i) {
            c.x(i);
        }
        int mask = k ^ ((1 << N) - 1);
        for (int i = 0; i < N; ++i) {
            assert c.measure(i) == ((mask & (1 << (N - 1 - i))) != 0);
        }
    }
}