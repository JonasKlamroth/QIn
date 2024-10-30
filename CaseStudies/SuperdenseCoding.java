
import org.cprover.CProver;

public class SuperdenseCoding {
  
  public SuperdenseCoding() {
    super();
  }
    /*@
      ensures ((\result & 2) != 0) == b1 && ((\result & 1) != 0) == b2; 
   */

  public static int sdc(boolean b1, boolean b2) {
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F, 0.0F, 0.0F, 0.70710677F};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    if (b2) {
      {
      }
      q0 = new float[]{0.0F, 0.70710677F, 0.70710677F, 0.0F};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    }
    if (b1) {
      {
      }
      q0 = new float[]{q0[0], q0[1], -1.0F * q0[2], -1.0F * q0[3]};
      q1 = new float[]{q1[0], q1[1], -1.0F * q1[2], -1.0F * q1[3]};
    }
    q0 = new float[]{q0[0], q0[1], q0[3], q0[2]};
    q1 = new float[]{q1[0], q1[1], q1[3], q1[2]};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3]};
    q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[2], 0.70710677F * q1[1] + 0.70710677F * q1[3], 0.70710677F * q1[0] + -0.70710677F * q1[2], 0.70710677F * q1[1] + -0.70710677F * q1[3]};
    float[] probs = new float[]{q0[0] * q0[0] + q1[0] * q1[0], q0[1] * q0[1] + q1[1] * q1[1], q0[2] * q0[2] + q1[2] * q1[2], q0[3] * q0[3] + q1[3] * q1[3]};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 3; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 4;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.02F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    return tmp_measure_var;
  }
}