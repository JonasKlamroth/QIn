public class GroverQSharpTut {

    //resulting int has alternating bits
    //@ requires N == 3 && iterations == 1;
    //@ ensures (\forall int i; 0 <= i < 2; ((\result & (1 << (32 - i))) == 0) != ((\result & (i << (32 - (i + 1)))) == 0));
    public int groverAlternating(int N, int iterations) {
        int res = 0;
        CircuitMock c = new CircuitMock(N);
        for(int i = 0; i < N; ++i) {
            c.h(i);
        }
        for(int i = 0; i < iterations; ++i) {
            //oracle
            for(int j = 0; j < N; ++j) {
                if(j % 2 == 0) {
                    c.x(j);
                }
            }
            c.ccz(1, 2, 0);
            for(int j = 0; j < N; ++j) {
                if(j % 2 == 0) {
                    c.x(j);
                }
            }
            for(int j = 0; j < N; ++j) {
                if(j % 2 == 1) {
                    c.x(j);
                }
            }
            c.ccz(1, 2, 0);
            for(int j = 0; j < N; ++j) {
                if(j % 2 == 1) {
                    c.x(j);
                }
            }

            //Reflect about the mean
            for(int j = 0; j < N; j++) {
                c.h(j);
            }
            for(int j = 0; j < N; j++) {
                c.x(j);
            }
            c.ccz(0, 1, 2);
            for(int j = 0; j < N; j++) {
                c.x(j);
            }
            for(int j = 0; j < N; j++) {
                c.h(j);
            }
        }
        for(int j = 0; j < N; ++j) {
            res += c.measure(j) ? (1 << (N - j - 1)) : 0;
        }
        return res;
    }
}