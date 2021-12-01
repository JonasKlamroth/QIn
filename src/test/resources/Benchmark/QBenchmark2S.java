public class QBenchmark2S {

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
     */
    public static void gatesTest_1_1(float[][] qstate, float[][] qstatei) {
        boolean b_test = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != b_test;
    }

    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_2(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 == b_test_0;
    }

    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_3(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != b_test_0;
    }


    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_4(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 == b_test_0;
    }


    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_5(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != b_test_0;
    }


    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_6(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 == b_test_0;
    }


    /*@
          @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
          @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j][0] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i][0] == 0.0f);
         */
    public static void gatesTest_1_7(float[][] qstate, float[][] qstatei) {
        boolean b_test_0 = qstate[1][0] == 1.0f;
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measureMax(0);
        assert b_0 != b_test_0;
    }
}