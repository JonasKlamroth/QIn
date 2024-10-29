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
        int res = c.measureAll();
        assert res == 0 || res == ((1 << N) - 1);
    }
}