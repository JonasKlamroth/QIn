public class QBenchmark3S {


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_1(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_2(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 2; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(2, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_3(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 16 && qstatei.length == 16;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_4(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 8; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(4, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_5(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for (int i = 16; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 64 && qstatei.length == 64;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_6(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 32; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(6, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 128 && qstatei.length == 128;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void qbits_7(float[] qstate, float[] qstatei) {
        boolean q_test = false;
        for(int i = 64; i < qstate.length; ++i) {
            q_test = q_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(7, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != q_test;
    }
}