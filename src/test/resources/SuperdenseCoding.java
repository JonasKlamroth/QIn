public class SuperdenseCoding {

    //@ ensures \result[0] == b1 && \result[1] == b2;
    public static boolean[] sdc(boolean b1, boolean b2) {
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
        boolean m1 = c.measure(0);
        boolean m2 = c.measure(1);
        return new boolean[]{m1, m2};
    }
}