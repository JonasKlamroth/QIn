public class QBenchmark2S {

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_1(float[] qstate, float[] qstatei) {
        boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_2(float[] qstate, float[] qstatei) {
        boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_3(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_4(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_5(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_6(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_7(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_8(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_1_9(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 1; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(1, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_1(float[] qstate, float[] qstatei) {
        boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_2(float[] qstate, float[] qstatei) {
        boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_3(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_4(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_5(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_6(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_7(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_8(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_3_9(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 4; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(3, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_1(float[] qstate, float[] qstatei) {
        boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_2(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_3(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_4(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_5(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_6(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }


    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_7(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_8(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 == b_test;
    }

    /*@
      @ requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32;
      @ requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0f && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0f));
      @ requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0f);
      @*/
    public static void gatesTest_5_9(float[] qstate, float[] qstatei) {
         boolean b_test = false;
        for(int i = 16; i < qstate.length; ++i) {
            b_test = b_test || qstate[i] == 1.0f;
        }
        CircuitMock c = new CircuitMock(5, qstate, qstatei);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        c.x(0);
        boolean b_0 = c.measure(0);
        assert b_0 != b_test;
    }
}