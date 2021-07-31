public class LimitTest {
    public static void testALot() {
        CircuitMock c = new CircuitMock(100);
        c.h(10);
        c.z(98);
        c.x(98);
        c.h(2);
        c.z(89);
        c.cx(89, 90);
        c.cz(90, 91);
    }
}