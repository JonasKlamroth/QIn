public class QFEBug {
    // https://quantumcomputing.stackexchange.com/questions/9246/quantum-phase-estimation-implementation
    // extracted from https://github.com/Z-928/Bugs4Q

    float PI = 3.141592653f;
    private final int N = 3;


    //@ ensures \result == 3;
    public int computeQFE() {
        float theta = 0.78f;
        CircuitMock c = new CircuitMock(N + 1);
        //create initial state
        c.x(N);

        //apply hadamards to ancillas
        for(int i = 0; i < N; ++i) {
            c.h(i);
        }

        //Applying U^(2^(n-i)) on qubit i
        for(int i = 0; i < N; ++i) {
            c.swap(i, N - 1);
            c.cp(N - 1, N, 2* PI * theta * ((double)(1 << (N - i - 1))));
            c.swap(i, N - 1);
        }

        //Applying qft_dagger
        for(int i = 0; i < N / 2; ++i) {
            c.swap(i, N - i - 1);
        }
        for(int i = N - 1; i >= 0; i--) {
            c.h(i);
            for(int j = i - 1; j >= 0; j--) {
                c.swap(j, i - 1);
                c.cp(i - 1, i, -2.0 * PI / ((double)(1 << (i - j  + 1))));
                c.swap(j, i - 1);
            }
        }

        int res = 0;
        for(int i = 0; i < N; i++) {
            res +=  c.measure(i) ? 1 << i : 0;
        }
        return res;
    }

    //@ ensures \result == 3;
    public int computeQFEBroken() {
        float theta = 0.78f;
        CircuitMock c = new CircuitMock(N + 1);
        //create initial state
        c.x(N);

        //apply hadamards to ancillas
        for(int i = 0; i < N; ++i) {
            c.h(i);
        }

        //Applying U^(2^(n-i)) on qubit i
        for(int i = 0; i < N; ++i) {
            c.swap(i, N - 1);
            c.cp(N - 1, N, 2* PI * theta * ((double)(1 << (N - i - 1))));
            c.swap(i, N - 1);
        }

        //Applying qft_dagger
        for(int i = N - 1; i >= 0; i--) {
            for(int j = i - 1; j >= 0; j--) {
                c.swap(j, i - 1);
                c.cp(i - 1, i, -2.0 * PI / ((double)(1 << (i - j  + 1))));
                c.swap(j, i - 1);
            }
            c.h(i);
        }

        int res = 0;
        for(int i = 0; i < N; i++) {
            res +=  c.measure(i) ? 1 << i : 0;
        }
        return res;
    }
}