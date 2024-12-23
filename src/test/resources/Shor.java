import java.util.Random;

public class Shor {

    //@ requires 0 <= min < max;
    //@ ensures min <= \result < max;
    //@ assignable \nothing;
    public static int rand(int min, int max) {
        int i = new Random().nextInt(0);
        if (i < 0) {
            i = 0;
        }
        return (i % (max - min)) + min;
    }

    /*@ requires 2 <= n <= 15;
      @ ensures n % \result == 0 && \result != 1 && \result != n;
      @ assignable \nothing;
     */
    public static int shor(int n) {
        int res = factorize(n);

        //@ loop_invariant res != 0;
        //@ loop_invariant res == -1 || n % res == 0 && res != 1 && res != n;
        //@ assignable \nothing;
        while (res == -1) {
            res = factorize(n);
        }
        return res;
    }

    /*@
      @ requires 2 <= n <= 15;
      @ ensures \result != 0;
      @ ensures \result != -1 ==> (n % \result == 0 && \result != 1 && \result != n);
      @ assignable \nothing;
      @*/
    public static int factorize(int n) {
        int a = rand(1, n);
        int K = gcd(a, n);
        if (K != 1) {
            return K;
        }
        int r = findPeriod(a, n);
        if (r > 1) {
            r = r / 2;
            int guess1 = gcd(pow(a, r) + 1, n);
            int guess2 = gcd(pow(a, r) - 1, n);
            if (guess1 * guess2 == n && guess2 != 1 && guess1 != 1) {
                return guess1;
            }
        }
        return -1;
    }

    private static /*@ pure */ int pow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; ++i) {
            res *= a;
        }
        return res;
    }

    public static /*@ pure */ int gcd(int a, int b) {
        int r_0 = a;
        int r_1 = b;
        int r_2 = 0;
        while (r_1 != 0) {
            r_2 = r_0 % r_1;
            r_0 = r_1;
            r_1 = r_2;
        }
        return r_0;
    }

   /*@
      requires 2 <= n <= 15 && 0 < a < n;
      requires gcd(a, n) == 1;
      ensures (n == 15) ==> (\result == 0 || \result == 2 || \result == 4 || \result == 6);
      ensures (n != 15) ==> (\result == 0);
      assignable \nothing;
   */
    private static int findPeriodCircuit(int a, int n) {
        if(n != 15) { //this is a circuit especially for n == 15;
            return 0;
        }
        double PI = 3.141592653;
        CircuitMock c = new CircuitMock(7);
        c.x(6);
        for (int i = 0; i < 3; i++) {
            c.h(i);
        }
        c.x(2);
        c.x(1);
        c.ccx(2, 1, 3);
        c.x(2);
        c.ccx(2, 1, 4);
        c.x(1);
        c.x(2);
        c.ccx(2, 1, 5);
        c.x(2);
        c.ccx(2, 1, 6);
        c.h(0);
        c.cp(0, 1, PI / 2.0);
        c.h(1);
        c.cp(0, 2, PI / 4.0);
        c.cp(1, 2, PI / 2.0);
        c.h(2);

        int res = c.measureAll() >> 4;
        return res * 2;
    }

    /*@ requires 2 <= n <= 15 && 0 < a < n;
      @ requires gcd(a, n) == 1;
      @ ensures -1 <= \result < n;
      @ ensures pow(a, \result) % n == 1 || \result == -1;
      @ assignable \nothing;
     */
    private static int findPeriod(int a, int n) {
        int phase = findPeriodCircuit(a, n);
        int fraction = getFraction((float)phase / (float)8, 3);
        if (pow(a, fraction) % n == 1) {
            return fraction;
        }
        return -1;
    }

    private static int getFraction(float val, int numConsideredDigits) {
        if(val == 0.0f) {
            return 1;
        }
        int n = 0;
        float p = val;
        while (p - (int)p > 0 && n <= numConsideredDigits) {
            p = 10.0F * p;
            n = n + 1;
        }
        int power = pow(10, n);
        int r = gcd((int)p, power);
        int nominator = (int)p / r;
        int denominator = power / r;
        return denominator;
    }
}