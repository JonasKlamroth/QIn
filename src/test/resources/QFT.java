public class QFT {
    private static final float[][] R4i = new float[][]{
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.707106781f},
    };
    private static final float[][] R4 = new float[][]{
            new float[]{1.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 1.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 1.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.707106781f},
    };
    private static final float[][] R2i = new float[][]{
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 1.0f},
    };
    private static final float[][] R2 = new float[][]{
            new float[]{1.0f , 0.0f, 0.0f, 0.0f},
            new float[]{0.0f , 1.0f, 0.0f, 0.0f},
            new float[]{0.0f , 0.0f, 1.0f, 0.0f},
            new float[]{0.0f , 0.0f, 0.0f, 0.0f},
    };

    /*@ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstate[i] != null && qstatei[i] != null && qstate[i].length == 1 && qstatei[i].length == 1);
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i][0] == 1.0f &&
      @     (\forall int j; j >= 0 && j < qstate.length; qstate[i][0] == 0.0f));
      @*/
    public void qft(float[][] qstate, float[][] qstatei) {
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.h(2);
        c.u(R2, R2i,1, 2);
        c.swap(0, 1);
        c.u(R4, R4i, 1, 2);
        c.h(0);
        c.u(R2, R2i, 0, 1);
        c.h(1);
        c.swap(0, 1);
        c.swap(0, 2);
        boolean b0 = c.measureMax(0);
        assert b0 = false;
        boolean b1 = c.measureMax(1);
        assert b1 = false;
        boolean b2 = c.measureMax(2);
        assert b2 = false;
    }
}