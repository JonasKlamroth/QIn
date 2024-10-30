public class BooleanQFunctions {
    //@ ensures \result == !(a || b);
    public static boolean nor(boolean a, boolean b) {
        CircuitMock c = new CircuitMock(3);
        if(!a) {
            c.x(0);
        }
        if(!b) {
            c.x(1);
        }

        c.ccx(0, 1, 2);

        return (c.measureAll() & 1) != 0;
    }

    //@ ensures \result == (a || b);
    public static boolean or(boolean a, boolean b) {
        CircuitMock qc = new CircuitMock(3);

        if(!a) {
            qc.x(0);
        }
        if(!b) {
            qc.x(1);
        }

        qc.ccx(0, 1, 2);
        qc.x(2);

        return (qc.measureAll() & 1) != 0;
    }

    //@ ensures \result == (a ==> b);
    public static boolean implies(boolean a, boolean b) {
        CircuitMock qc = new CircuitMock(3);

        if(a) {
            qc.x(0);
        }
        if(!b) {
            qc.x(1);
        }

        qc.ccx(0, 1, 2);
        qc.x(2);

        return (qc.measureAll() & 1) != 0;
    }
}