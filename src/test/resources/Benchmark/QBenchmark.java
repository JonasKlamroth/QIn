public class QBenchmark {

    public static void qbits_1() {
        CircuitMock c = new CircuitMock(1);
        c.h(0);
        boolean b_0 = c.measure(0);
        assert !b_0;
    }

    public static void qbits_2() {
        CircuitMock c = new CircuitMock(2);
        c.h(0);
        c.h(1);
        boolean b_0 = c.measure(0);
        assert !b_0;
        boolean b_1 = c.measure(1);
        assert !b_1;
    }


    public static void qbits_3() {
        CircuitMock c = new CircuitMock(3);
        c.h(0);
        c.h(1);
        c.h(2);
        boolean b_0 = c.measure(0);
        assert !b_0;
        boolean b_1 = c.measure(1);
        assert !b_1;
        boolean b_2 = c.measure(2);
        assert !b_2;
    }

    public static void qbits_4() {
        CircuitMock c = new CircuitMock(4);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        boolean b_0 = c.measure(0);
        assert !b_0;
        boolean b_1 = c.measure(1);
        assert !b_1;
        boolean b_2 = c.measure(2);
        assert !b_2;
        boolean b_3 = c.measure(3);
        assert !b_3;
    }

    public static void qbits_5() {
        CircuitMock c = new CircuitMock(5);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        c.h(4);
        boolean b_0 = c.measure(0);
        assert !b_0;
        boolean b_1 = c.measure(1);
        assert !b_1;
        boolean b_2 = c.measure(2);
        assert !b_2;
        boolean b_3 = c.measure(3);
        assert !b_3;
        boolean b_4 = c.measure(4);
        assert !b_4;
    }

    public static void qbits_6() {
        CircuitMock c = new CircuitMock(6);
        c.h(0);
        c.h(1);
        c.h(2);
        c.h(3);
        c.h(4);
        c.h(5);
        boolean b_0 = c.measure(0);
        assert !b_0;
        boolean b_1 = c.measure(1);
        assert !b_1;
        boolean b_2 = c.measure(2);
        assert !b_2;
        boolean b_3 = c.measure(3);
        assert !b_3;
        boolean b_4 = c.measure(4);
        assert !b_4;
        boolean b_5 = c.measure(5);
        assert !b_5;
    }

}