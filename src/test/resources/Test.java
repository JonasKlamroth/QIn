public class Test {
    public static void test() {
        CircuitMock c = new CircuitMock(2);
        c.x(1);
        c.cx(1, 0);
        c.measure(0);
    }
}