public class LimitTest {
    public static void testALot() {
        CircuitMock c = new CircuitMock(12);
        c.h(9);
        c.z(8);
        c.x(2);
        c.h(2);
        c.z(9);
        c.cx(0, 1);
        c.cz(0, 1);
    }
}