public class QBenchmark3S {


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
      @*/
    public static void qbits_1(float[][] qstate, float[][] qstatei) {
        boolean q_test = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
      @*/
    public static void qbits_2(float[][] qstate, float[][] qstatei) {
        boolean q_test = qstate[2][0] == 1.0f || qstate[3][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
      @*/
    public static void qbits_3(float[][] qstate, float[][] qstatei) {
        boolean q_test = qstate[4][0] == 1.0f || qstate[5][0] == 1.0f || qstate[6][0] == 1.0f || qstate[7][0] == 1.0f;
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != q_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 16 && qstatei.length == 16;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
      @*/
    public static void qbits_4(float[][] qstate, float[][] qstatei) {
        boolean q_test = qstate[8][0] == 1.0f || qstate[9][0] == 1.0f || qstate[10][0] == 1.0f || qstate[11][0] == 1.0f || qstate[12][0] == 1.0f || qstate[13][0] == 1.0f || qstate[14][0] == 1.0f || qstate[15][0] == 1.0f;
        CircuitMock c = new CircuitMock(4, qstate, qstatei);
        c.h(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != q_test;
    }
}