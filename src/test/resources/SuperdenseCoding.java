public class SuperdenseCoding {

    public static void sdc(boolean b1, boolean b2) {
        CircuitMock c = new CircuitMock(2);
        //prepare bell-state
        c.h(0);
        c.cx(0, 1);

        //preparation of qubit by alice
        if(b2) {
            c.x(0);
        }
        if(b1) {
            c.z(0);
        }

        //bobs part
        c.cx(0, 1);
        c.h(0);
        assert c.measure(0) == b1;
        assert c.measure(1) == b2;
    }
}