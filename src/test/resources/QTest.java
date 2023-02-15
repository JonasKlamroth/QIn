public class QTest {
    public static void test() {
        CircuitMock c = new CircuitMock(2);
        c.x(1);
        c.measure(0);
        c.measurePos(0);
    }

    //@ ensures \result == true;
    public static boolean test1() {
        CircuitMock c = new CircuitMock(1);
        c.h(0);
        return c.measurePos(0);
    }

    //@ ensures \result == true;
    public static boolean test2() {
        CircuitMock c = new CircuitMock(1);
        c.x(0);
        return c.measurePos(0);
    }

    public static void testBell() {
        CircuitMock c = new CircuitMock(2);
        c.h(0);
        c.cx(0, 1);
        assert c.measurePos(0) == c.measurePos(1);
    }


}