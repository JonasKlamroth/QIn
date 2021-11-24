public class LimitTest {
    public static void testALot() {
        CircuitMock c = new CircuitMock(5);
        c.h(4);
        c.h(4);
        c.x(1);
        c.h(1);
        c.h(1);
        c.x(2);
        assert c.measureMax(4) == false;
        assert c.measureMax(1) == true;
        assert c.measureMax(2) == true;
        assert c.measureMax(3) == false;
    }
}