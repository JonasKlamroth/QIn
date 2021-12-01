public class QBenchmark {

    public static void qbits_1() {
        CircuitMock c = new CircuitMock(1, q_states, q_states_i);
        c.h(0);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
    }

    public static void qbits_2() {
        CircuitMock c = new CircuitMock(2, q_states, q_states_i);
        c.h(0);
        c.h(1);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
        boolean b_1 = c.measureMax(1);
        assert !b_1;
    }


    public static void qbits_3() {
        CircuitMock c = new CircuitMock(3, q_states, q_states_i);
        c.h(0);
        c.h(1);
        c.h(2);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
        boolean b_1 = c.measureMax(1);
        assert !b_1;
        boolean b_2 = c.measureMax(2);
        assert !b_2;
    }

    public static void qbits_4() {
        CircuitMock c = new CircuitMock(4, q_states, q_states_i);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
        boolean b_1 = c.measureMax(1);
        assert !b_1;
        boolean b_2 = c.measureMax(2);
        assert !b_2;
        boolean b_3 = c.measureMax(3);
        assert !b_3;
    }

    public static void qbits_5() {
        CircuitMock c = new CircuitMock(5, q_states, q_states_i);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        c.h(4);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
        boolean b_1 = c.measureMax(1);
        assert !b_1;
        boolean b_2 = c.measureMax(2);
        assert !b_2;
        boolean b_3 = c.measureMax(3);
        assert !b_3;
        boolean b_4 = c.measureMax(4);
        assert !b_4;
    }

    public static void qbits_6() {
        CircuitMock c = new CircuitMock(6, q_states, q_states_i);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        c.h(4);
        c.h(5);
        boolean b_0 = c.measureMax(0);
        assert !b_0;
        boolean b_1 = c.measureMax(1);
        assert !b_1;
        boolean b_2 = c.measureMax(2);
        assert !b_2;
        boolean b_3 = c.measureMax(3);
        assert !b_3;
        boolean b_4 = c.measureMax(4);
        assert !b_4;
        boolean b_5 = c.measureMax(5);
        assert !b_5;
    }

}