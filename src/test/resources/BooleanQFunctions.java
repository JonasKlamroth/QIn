public class BooleanQFunctions {
    //@ ensures \result == !(a || b);
    public static boolean nor(boolean a, boolean b) {
        CircuitMock c = new CircuitMock(4);
        if(a) {
            c.x(0);
        }
        if(b) {
            c.x(1);
        }
        c.cxx(0, 1, 2);
        c.swap(0, 1);
        c.swap(0, 2);
        c.cx(0, 1);
        c.swap(0, 2);
        //c.swap(0, 1);

        //c.swap(0, 1);
        c.cx(0, 1);
        c.swap(0, 1);

        c.swap(1, 3);
        c.cx(0, 1);
        c.swap(1, 3);

        c.swap(0, 1);
        c.cx(0, 1);
        c.swap(0, 1);

        c.swap(0, 1);
        c.swap(0, 2);
        c.cx(0, 1);
        c.swap(0, 2);
        c.swap(0, 1);

        c.cxx(0, 1, 2);
        c.x(3);
        return c.measure(3);
    }
}