import java.util.Random;
public class Shor3 {


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
     */
    public static int shor(int n) {
        int res = factorize(n);

        //@ loop_invariant res != 0;
        //@ loop_invariant res == -1 || n % res == 0 && res != 1 && res != n;
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

    private /*@ pure helper @*/ static int pow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; ++i) {
            res *= a;
        }
        return res;
    }

    private /*@ pure helper @*/ static int gcd(int a, int b) {
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

    /*@ requires 2 <= n <= 15 && 0 < a < n;
      @ requires gcd(a, n) == 1;
      @ ensures (n == 15) ==> (\result == 0 || \result == 2 || \result == 4 || \result == 6);
      @ ensures (n != 15) ==> (\result == 0);
      @ assignable \nothing;
   */
    private static int findPeriodCircuits(int a, int n) {
        if(n != 15) { //this is a circuit especially for n == 15;
            return 0;
        }
        if(a == 11) {
            return findPeriodCircuit(a, n);
        }
        float PI = 3.141592653F;

        boolean c0 = false;
        boolean c1 = false;
        boolean c2 = false;
        boolean c3 = false;

        // Assuming QuantumCircuit and other required classes are available from the quantum library
        CircuitMock qc = new CircuitMock(5); // 5 qubits, 3 classical bits

        // Initialize q[0] to |1> by applying the X gate to the first qubit (qr[0])
        qc.x(0);

        // Apply a^4 mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled identity on the remaining 4 qubits (equivalent to doing nothing)
        qc.h(4); // Apply Hadamard gate again to qr[4]
        // Measure qr[4] into cr[0]
        c0 = qc.measurePos(4);
        // Reinitialize qr[4] to |0>
        qc.reset(4);

        // Apply a^2 mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled X gates
        qc.cx(4, 2); // Controlled X on qr[4] and qr[2]
        qc.cx(4, 0); // Controlled X on qr[4] and qr[0]
        // Feed-forward using conditional phase gate (U1)
        if(c1) {
            qc.rz(4, PI / 2); // U1(pi/2) on qr[4] if cr == 1
        }
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Measure qr[4] into cr[1]
        c1 = qc.measurePos(4);
        // Reinitialize qr[4] to |0>
        qc.reset(4);

        // Apply a mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled unitary. Assuming circuit_amod15 is a function that handles this part.
        if (a == 2) {
            qc.cswap(4, 3, 2);  // Controlled swap between qr[4] (control), qr[3], and qr[2]
            qc.cswap(4, 2, 1);  // Controlled swap between qr[4], qr[2], and qr[1]
            qc.cswap(4, 1, 0);  // Controlled swap between qr[4], qr[1], and qr[0]
        } else if (a == 7) {
            qc.cswap(4, 1, 0);  // Controlled swap between qr[4], qr[1], and qr[0]
            qc.cswap(4, 2, 1);  // Controlled swap between qr[4], qr[2], and qr[1]
            qc.cswap(4, 3, 2);  // Controlled swap between qr[4], qr[3], and qr[2]
            qc.cx(4, 3);        // Controlled X gate between qr[4] and qr[3]
            qc.cx(4, 2);        // Controlled X gate between qr[4] and qr[2]
            qc.cx(4, 1);        // Controlled X gate between qr[4] and qr[1]
            qc.cx(4, 0);        // Controlled X gate between qr[4] and qr[0]
        } else if (a == 8) {
            qc.cswap(4, 1, 0);  // Controlled swap between qr[4], qr[1], and qr[0]
            qc.cswap(4, 2, 1);  // Controlled swap between qr[4], qr[2], and qr[1]
            qc.cswap(4, 3, 2);  // Controlled swap between qr[4], qr[3], and qr[2]
        } else if (a == 11) {  // This is included for completeness
            qc.cswap(4, 2, 0);  // Controlled swap between qr[4], qr[2], and qr[0]
            qc.cswap(4, 3, 1);  // Controlled swap between qr[4], qr[3], and qr[1]
            qc.cx(4, 3);        // Controlled X gate between qr[4] and qr[3]
            qc.cx(4, 2);        // Controlled X gate between qr[4] and qr[2]
            qc.cx(4, 1);        // Controlled X gate between qr[4] and qr[1]
            qc.cx(4, 0);        // Controlled X gate between qr[4] and qr[0]
        } else if (a == 13) {
            qc.cswap(4, 3, 2);  // Controlled swap between qr[4], qr[3], and qr[2]
            qc.cswap(4, 2, 1);  // Controlled swap between qr[4], qr[2], and qr[1]
            qc.cswap(4, 1, 0);  // Controlled swap between qr[4], qr[1], and qr[0]
            qc.cx(4, 3);        // Controlled X gate between qr[4] and qr[3]
            qc.cx(4, 2);        // Controlled X gate between qr[4] and qr[2]
            qc.cx(4, 1);        // Controlled X gate between qr[4] and qr[1]
            qc.cx(4, 0);        // Controlled X gate between qr[4] and qr[0]
        }

        // Feed-forward using conditional phase gates (U1)
        if(c3) {
            qc.rz(4, 3 * PI / 4); // U1(3*pi/4) on qr[4] if cr == 3
        }
        if(c2) {
            qc.rz(4, PI / 2);     // U1(pi/2) on qr[4] if cr == 2
        }
        if(c1) {
            qc.rz(4, PI / 4);     // U1(pi/4) on qr[4] if cr == 1
        }
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Measure qr[4] into cr[2]
        c2 = qc.measurePos(4);
        return (c2 ? 1 << 2 : 0) + (c1 ? 1 << 1 : 0) + (c0 ? 1 << 0 : 0);
    }

    /*@
      @ requires a == 11;
      @ ensures \result == 0 || \result == 2 || \result == 4 || \result == 6;
      @ assignable \nothing;
   */
    private static int findPeriodCircuit(int a, int n) {
        float PI = 3.141592653F;

        boolean c0 = false;
        boolean c1 = false;
        boolean c2 = false;
        boolean c3 = false;
        CircuitMock qc = new CircuitMock(5); // 5 qubits, 3 classical bits

        // Apply X gate to the first qubit (qr[0])
        qc.x(0);

        // Apply a^4 mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled identity on the remaining 4 qubits (equivalent to doing nothing)
        qc.h(4); // Apply Hadamard gate to qr[4] again
        // Measure qr[4] into cr[0]
        c0 = qc.measurePos(4);
        // Reinitialize qr[4] to |0>
        qc.reset(4);

        // Apply a^2 mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled identity on the remaining qubits, equivalent to doing nothing
        // Feed-forward using conditional phase gate (U1)
        if (c1) {
            qc.rz(4, PI / 2); // U1(pi/2) on qr[4] if cr[0] == 1
        }
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Measure qr[4] into cr[1]
        c1 = qc.measurePos(4);
        // Reinitialize qr[4] to |0>
        qc.reset(4);

        // Apply 11 mod 15
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Controlled X gates
        qc.cx(4, 3); // Controlled X on qr[4] and qr[3]
        qc.cx(4, 1); // Controlled X on qr[4] and qr[1]
        // Feed-forward using conditional phase gates (U1)
        if(c3) {
            qc.rz(4, 3 * PI / 4); // U1(3*pi/4) on qr[4] if cr == 3
        }
        if(c2) {
            qc.rz(4, PI / 2);     // U1(pi/2) on qr[4] if cr == 2
        }
        if(c1) {
            qc.rz(4, PI / 4);     // U1(pi/4) on qr[4] if cr == 1
        }
        qc.h(4); // Apply Hadamard gate to qr[4]
        // Measure qr[4] into cr[2]
        c2 = qc.measurePos(4);
        return (c2 ? 1 << 2 : 0) + (c1 ? 1 << 1 : 0) + (c0 ? 1 << 0 : 0);
    }

    /*@ requires 2 <= n <= 15 && 0 < a < n;
      @ requires gcd(a, n) == 1;
      @ ensures -1 <= \result < n;
      @ ensures pow(a, \result) % n == 1 || \result == -1;
      @ assignable \nothing;
     */
    private static int findPeriod(int a, int n) {
        int phase = findPeriodCircuits(a, n);
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