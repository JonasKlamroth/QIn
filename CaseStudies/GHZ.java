
import org.cprover.CProver;

public class GHZ {
  
  public GHZ() {
    super();
  }
  public static final int N = 3;
    /*@
      requires true; 
   */

  public void createGHZState(boolean $$_tmp_measureParam_0) {
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    {
      {
      }
    }
    {
      {
      }
    }
    q0 = new float[]{0.70710677F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.70710677F};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    boolean $$_tmp_measureVar1;
    if ($$_tmp_measureParam_0) {
      if (true && q0[0] == 0.0F && q1[0] == 0.0F && q0[1] == 0.0F && q1[1] == 0.0F && q0[2] == 0.0F && q1[2] == 0.0F && q0[3] == 0.0F && q1[3] == 0.0F) {
        /*@ assume false;*/
      }
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar1 = false;
    } else {
      if (true && q0[4] == 0.0F && q1[4] == 0.0F && q0[5] == 0.0F && q1[5] == 0.0F && q0[6] == 0.0F && q1[6] == 0.0F && q0[7] == 0.0F && q1[7] == 0.0F) {
        /*@ assume false;*/
      }
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q0[4], q0[5], q0[6], q0[7]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q1[4], q1[5], q1[6], q1[7]};
      $$_tmp_measureVar1 = true;
    }
    boolean m = $$_tmp_measureVar1;
    float[] probs = new float[]{q0[0] * q0[0] + q1[0] * q1[0], q0[1] * q0[1] + q1[1] * q1[1], q0[2] * q0[2] + q1[2] * q1[2], q0[3] * q0[3] + q1[3] * q1[3], q0[4] * q0[4] + q1[4] * q1[4], q0[5] * q0[5] + q1[5] * q1[5], q0[6] * q0[6] + q1[6] * q1[6], q0[7] * q0[7] + q1[7] * q1[7]};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 7; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 8;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.02F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    int res = tmp_measure_var;
    assert res == 0 || res == ((1 << N) - 1);
  }
}