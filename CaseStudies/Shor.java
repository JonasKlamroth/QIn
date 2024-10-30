
import org.cprover.CProver;
import java.util.Random;

public class Shor {
  
  public Shor() {
    super();
  }
    /*@
      requires 0 <= min < max; 
      ensures min <= \result < max; 
      assignable \nothing; 
   */

  public static int rand(int min, int max) {
    int i = new Random().nextInt(0);
    if (i < 0) {
      i = 0;
    }
    return (i % (max - min)) + min;
  }
    /*@
      requires 2 <= n <= 15; 
      ensures n % \result == 0 && \result != 1 && \result != n; 
      assignable \nothing; 
   */

  public static int shor(int n) {
    int res = factorize(n);
    //@ loop_invariant res != 0;
    //@ loop_invariant res == -1 || n % res == 0 && res != 1 && res != n;
    //@ loop_modifies \nothing;
    while (res == -1) {
      res = factorize(n);
    }
    return res;
  }
    /*@
      requires 2 <= n <= 15; 
      ensures \result != 0; 
      ensures \result != -1 ==> (n % \result == 0 && \result != 1 && \result != n); 
      assignable \nothing; 
   */

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
  
  /*@ pure */ 
  private static int pow(int a, int b) {
    int res = 1;
    for (int i = 0; i < b; ++i) {
      res *= a;
    }
    return res;
  }
  
  /*@ pure */ 
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
      requires 2 <= n <= 15 && 0 < a < n; 
      requires gcd(a, n) == 1; 
      ensures (n == 15) ==> (\result == 0 || \result == 2 || \result == 4 || \result == 6); 
      ensures (n != 15) ==> (\result == 0); 
      assignable \nothing; 
   */

  private static int findPeriodCircuit(int a, int n) {
    if (n != 15) {
      return 0;
    }
    double PI = 3.141592653;
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    {
      {
      }
    }
    {
      {
      }
    }
    {
      {
      }
    }
    q0 = new float[]{0.24999997F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.24999997F, 0.0F, 0.0F, 0.24999997F, 0.0F, -0.24999997F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.5852886E-14F, 0.0F, 0.0F, -0.24999997F, 0.0F, -2.5852886E-14F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.5852886E-14F, 0.0F, 0.0F, -0.24999997F, 0.0F, 2.5852886E-14F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, -0.24999997F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    float[] probs = new float[]{0.062499985F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.062499985F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 127; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 128;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.02F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    int res = tmp_measure_var >> 4;
    return res * 2;
  }
    /*@
      requires 2 <= n <= 15 && 0 < a < n; 
      requires gcd(a, n) == 1; 
      ensures -1 <= \result < n; 
      ensures pow(a, \result) % n == 1 || \result == -1; 
      assignable \nothing; 
   */

  private static int findPeriod(int a, int n) {
    int phase = findPeriodCircuit(a, n);
    int fraction = getFraction((float)phase / (float)8, 3);
    if (pow(a, fraction) % n == 1) {
      {
        return fraction;
      }
    }
    return -1;
  }
  
  private static int getFraction(float val, int numConsideredDigits) {
    if (val == 0.0F) {
      {
        return 1;
      }
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