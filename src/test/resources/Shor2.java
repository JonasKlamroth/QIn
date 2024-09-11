import java.util.Random;
public class Shor2 {


    public static int rand(int min, int max) {
        return (new Random().nextInt(0) % (max - min)) + min;
    }

    /*@
      @ requires 2 <= n <= 15;
      @ ensures \result != -1 ==> n % \result == 0;
      @ assignable \nothing;
      @*/
    public static int factorize(int n) {
        int a = rand(1, n);
        int K = gcd(a, n);
        if (K != 1) {
            return K;
        }
        int r = findPeriod(a, n);
        if (r > 0) {
            r = r / 2;
            int guess1 = gcd(pow(a, r) + 1, n);
            int guess2 = gcd(pow(a, r) - 1, n);
            if (guess1 * guess2 == n && guess2 != 1 && guess1 != 1) {
                return guess1;
            }
        }
        return -1;
    }

    private static int pow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; ++i) {
            res *= a;
        }
        return res;
    }

    public static int gcd(int a, int b) {
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
      @ requires a == 2;
      @ ensures \result == 0 || \result == 2 || \result == 4 || \result == 6;
      @ assignable \nothing;
      @ signals_only RuntimeException;
   */
    private static int findPeriodCircuit(int a, int n) {
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

        int res = 0;
        for (int i = 0; i < 3; ++i) {
            res += c.measurePos(i) ? 1 << i : 0;
        }
        return res;
    }

    /*@ requires n > 2 && n <= 15 && 0 < a < n;
      @ ensures pow(a, \result) % n == 1 || \result == 1;
      @ assignable \nothing;
     */
    private static int findPeriod(int a, int n) {
        if(n < 15 || a != 2) {
            return -1;
        }
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