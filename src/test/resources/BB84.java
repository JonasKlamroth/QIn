public class BB84 {

    public boolean generateKeyBit(boolean aprime, boolean bprime) {
        CircuitMock c = new CircuitMock(1);
        c.h(0);
        boolean a = c.measurePos(0);
        if(aprime) {
            c.h(0);
        }
        if(bprime) {
            c.h(0);
        }
        boolean b = c.measure(0);
        assert aprime != bprime || a == b;
        return a;
    }
}