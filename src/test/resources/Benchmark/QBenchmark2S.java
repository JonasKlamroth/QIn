public class QBenchmark2S {
    private static final int N = 1;


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest3singleX(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.x(0);
        }
        for (int i = 2; i >= 0; --i) {
            assert c.measure(2-i) == ((idx & (1 << i)) != 0);
        }
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest3single(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.h(0);
        }
        for (int i = 2; i >= 0; --i) {
            assert c.measure(2-i) == ((idx & (1 << i)) != 0);
        }
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest3(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.hhh(0);
        }
        for (int i = 2; i >= 0; --i) {
            assert c.measure(2-i) == ((idx & (1 << i)) != 0);
        }
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest2singleX(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(2, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.x(0);
        }
        for (int i = 1; i >= 0; --i) {
            assert c.measure(1-i) == ((idx & (1 << i)) != 0);
        }
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest2single(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(2, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.h(0);
        }
        for (int i = 1; i >= 0; --i) {
            assert c.measure(1-i) == ((idx & (1 << i)) != 0);
        }
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest2(float[] qstate, float[] qstatei) {
        int idx = 0;
        for(int i = 0; i < qstate.length; ++i) {
            if(qstate[i] != 0.0f) {
                idx = i;
            }
        }
        CircuitMock c = new CircuitMock(2, qstate, qstatei);
        for (int i = 0; i < N; ++i) {
            c.hh(0);
        }
        for (int i = 1; i >= 0; --i) {
            assert c.measure(1-i) == ((idx & (1 << i)) != 0);
        }
    }
}