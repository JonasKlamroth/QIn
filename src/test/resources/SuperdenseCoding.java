public class SuperdenseCoding {

    //@ ensures ((\result & 2) != 0) == b1 && ((\result & 1) != 0) == b2;
    public static int sdc(boolean b1, boolean b2) {
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
        return c.measureAll();
    }
}