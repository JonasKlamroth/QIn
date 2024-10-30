
import org.cprover.CProver;

public class BB84 {
  
  public BB84() {
    super();
  }
    /*@
      ensures b != bprime || a == \result; 
      assignable \nothing; 
   */

  public boolean generateKeyBit(boolean a, boolean b, boolean bprime) {
    float[] q0 = new float[]{1.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F};
    if (a) {
      {
      }
      q0 = new float[]{0.0F, 1.0F};
      q1 = new float[]{0.0F, 0.0F};
    }
    if (b) {
      {
      }
      q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1]};
      q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[1], 0.70710677F * q1[0] + -0.70710677F * q1[1]};
    }
    if (bprime) {
      {
      }
      q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1]};
      q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[1], 0.70710677F * q1[0] + -0.70710677F * q1[1]};
    }
    float[] probs = new float[]{q0[0] * q0[0] + q1[0] * q1[0], q0[1] * q0[1] + q1[1] * q1[1]};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 1; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 2;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.02F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    boolean aprime = (tmp_measure_var == 1);
    return aprime;
  }
    /*@
      requires a != null && b != null && bprime != null; 
      requires a.length == b.length && b.length == bprime.length; 
      ensures \result != null && \result.length <= a.length; 
   */

  public boolean[] generateKeyBits(/*@ non_null */ 
  boolean[] a, /*@ non_null */ 
  boolean[] b, /*@ non_null */ 
  boolean[] bprime) {
    /*@ non_null */ 
    boolean[] res = new boolean[a.length];
    int idx = 0;
    for (int i = 0; i < a.length; ++i) {
      boolean bit = generateKeyBit(a[i], b[i], bprime[i]);
      if (b[i] == bprime[i]) {
        {
          res[idx] = bit;
          idx++;
        }
      }
    }
    /*@ non_null */ 
    boolean[] bits = new boolean[idx];
    for (int i = 0; i < bits.length; ++i) {
      bits[i] = res[i];
    }
    return bits;
  }
}