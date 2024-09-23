public class BB84 {

    //@ ensures b != bprime || a == \result;
    //@ assignable \nothing;
    public boolean generateKeyBit(boolean a, boolean b, boolean bprime) {
        CircuitMock c = new CircuitMock(1);
        if(a) {
            c.x(0);
        }
        if(b) {
            c.h(0);
        }
        //Transmission of the qubit over a quantum channel
        if(bprime) {
            //if bprime we measure in hadamard basis which is equal to applying the hadamard gate and then measuring in computational basis
            c.h(0);
        }
        boolean aprime = c.measurePos(0);
        return aprime;
    }

    /*@ requires a != null && b != null && bprime != null;
      @ requires a.length == b.length && b.length == bprime.length;
      @ ensures \result != null && \result.length <= a.length;
     */
    public boolean[] generateKeyBits(boolean[] a, boolean[] b, boolean[] bprime) {
        boolean[] res = new boolean[a.length];
        int idx = 0;
        for(int i = 0; i < a.length; ++i) {
            boolean bit = generateKeyBit(a[i], b[i], bprime[i]);
            if(b[i] == bprime[i]) {
                res[idx] = bit;
                idx++;
            }
        }
        boolean[] bits = new boolean[idx];
        for(int i = 0; i < bits.length; ++i) {
            bits[i] = res[i];
        }
        return bits;
    }
}