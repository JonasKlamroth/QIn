public class GHZ {
    public static final int N = 3;

    //@ requires true;
    public void createGHZState() {
        CircuitMock c = new CircuitMock(N);
        c.h(0);
        for(int i = 0; i < N - 1; ++i) {
            c.cx(i, i+1);
        }

        boolean m = c.measurePos(0);
        for(int i = 1; i < N; ++i) {
            assert c.measurePos(i) == m;
        }
    }
}