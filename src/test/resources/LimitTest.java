public class LimitTest {
    public static void testALot() {
        CircuitMock c = new CircuitMock(30);
        c.h(10);
        c.z(28);
        c.x(22);
        c.h(2);
        c.z(19);
        c.cx(19, 20);
        c.cz(20, 21);
    }
}