public class Taffoli {
    // https://quantumcomputing.stackexchange.com/questions/9871/achieve-a-control-gate-with-2-hadamard-coins
    // extracted from https://github.com/Z-928/Bugs4Q

    //@ ensures \result == (b1 && b2 && b3);
    public boolean testTaffoli(boolean b1, boolean b2, boolean b3) {
        CircuitMock c = new CircuitMock(5);
        if (b1) {
            c.x(0);
        }
        if (b2) {
            c.x(1);
        }
        if (b3) {
            c.x(2);
        }
        c.swap(2, 3);
        c.ccx(0, 1, 2);
        c.swap(2, 3);
        c.ccx(2, 3, 4);
        c.swap(2, 3);
        c.ccx(0, 1, 2);
        return c.measureAll() == 27;
    }

    //@ ensures \result == (b1 && b2 && b3);
    public boolean testTaffoliBroken(boolean b1, boolean b2, boolean b3) {
        CircuitMock c = new CircuitMock(4);
        if(b1) {
            c.x(0);
        }
        if(b2) {
            c.x(1);
        }
        if(b3) {
            c.x(2);
        }
        c.swap(2, 3);
        c.ccx(0, 1, 2);
        //c.swap(2, 3); canceled out
        //c.swap(2, 3);
        c.cx(2, 3);
        c.swap(2, 3);
        c.x(3);
        c.swap(2, 3);
        c.ccx(0, 1, 2);
        c.swap(2, 3);
        return c.measure(3);
    }
}
