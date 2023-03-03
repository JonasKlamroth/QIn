public class Shor {

    /*@
      requires 2 <= n <= 15;
      ensures (\exists int i; 2 <= i < n; n % i == 0) ==> \result != null && \result.length == 2 && \result[0] * \result[1] == n;
      ensures !(\exists int i; 2 <= i < n; n % i == 0) ==> \result == null;
      assignable \nothing;
   */

    public static int[] factorize(int n) {
        for (int a = 0; a < n; a++) {
            int K = gcd(a, n);
            if (K != 1) {
                return new int[]{K, n / K};
            }
            int r = findPeriod(a, n);
            if (r > 0) {
                assert false;
                r = r / 2;
                int guess1 = gcd(pow(a, r) + 1, n);
                int guess2 = gcd(pow(a, r) - 1, n);
                if (guess1 * guess2 == n && guess2 != 1 && guess1 != 1) {
                    return new int[]{guess1, guess2};
                }
            } else {
                return null;
            }
        }
        return null;
    }

    private static /*@ pure */ int pow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; ++i) {
            res *= a;
        }
        return res;
    }

    /*@
      @ requires a > 0 && b > 0 && a <= 1000 && b <= 1000;
      @ ensures \result > 0;
      @ ensures a % \result == 0 && b % \result == 0;
      @ ensures (\exists int i; 0 < i <= a; \result * i == a);
      @ ensures (\exists int i; 0 < i <= b; \result * i == b);
      @ ensures !(\exists int i; \result < i <= a; a % i == 0 && b % i == 0);
      @ assignable \nothing;
      @*/
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
      @ ensures (\forall int i; 1 <= i < n; pow(a, i) % n == pow(a, i + \result) % n);
      @ assignable \nothing;
      @ signals_only RuntimeException;
   */
    private static int findPeriodCircuit(int a, int n, boolean $$_tmp_measureParam_0, boolean $$_tmp_measureParam_1, boolean $$_tmp_measureParam_2) {
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
      @ ensures (a == 2 && n == 15) ==> \result == 4;
      @ ensures !(a == 2 && n == 15) ==> \result == -1;
      @ assignable \nothing;
     */
    private static int findPeriod(int a, int n) {
        if(n < 15 || a != 2) {
            return -1;
        }
        for (int i = 0; i < 8; ++i) {
            try {
                findPeriodCircuit(a, n, (i & 1) == 0, (i & 2) == 0, (i & 4) == 0);
                int[] fraction = getFraction((float)i / (float)n, 3);
                if (pow(a, fraction[1]) % n == 1) {
                    return fraction[1];
                }
            } catch (RuntimeException e) {
            }
        }
        return -1;
    }

    /*@
      @ requires (\exists int k; 0 <= k <= 100; val == 0.01 * k);
      @ requires 3 <= numConsideredDigits < 5;
      @ ensures \result != null & \result.length == 2 && (float)\result[0] / (float)\result[1] == val;
      @ assignable \nothing;
      @*/
    private static int[] getFraction(float val, int numConsideredDigits) {
        if(val == 0.0f) {
            return new int[]{0, 1};
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
        return new int[]{nominator, denominator};
    }
}