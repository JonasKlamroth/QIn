
import org.cprover.CProver;

public class GroverQSharpTut {
  
  public GroverQSharpTut() {
    super();
  }
    /*@
      requires N == 3 && iterations == 1; 
      ensures (\forall int i; 0 <= i < 2; ((\result & (1 << i)) == 0) != ((\result & (1 << (i + 1))) == 0)); 
   */

  public int groverAlternating(int N, int iterations) {
    int res = 0;
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
    {
      {
      }
    }
    {
      {
        {
          {
            q0 = new float[]{0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F};
            q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
            if (0 % 2 == 0) {
              {
              }
              q0 = new float[]{0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F};
              q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
            }
          }
        }
        {
          {
            if (1 % 2 == 0) {
              {
              }
              q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
              q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
            }
          }
        }
        {
          {
            if (2 % 2 == 0) {
              {
              }
              q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
              q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
            }
          }
        }
        {
          {
            q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], -1.0F * q0[7]};
            q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], -1.0F * q1[7]};
            if (0 % 2 == 0) {
              {
              }
              q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
              q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
            }
          }
        }
        {
          {
            if (1 % 2 == 0) {
              {
              }
              q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
              q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
            }
          }
        }
        {
          {
            if (2 % 2 == 0) {
              {
              }
              q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
              q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
            }
          }
        }
        {
          {
            if (0 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
              q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
            }
          }
        }
        {
          {
            if (1 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
              q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
            }
          }
        }
        {
          {
            if (2 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
              q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
            }
          }
        }
        {
          {
            q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], -1.0F * q0[7]};
            q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], -1.0F * q1[7]};
            if (0 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
              q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
            }
          }
        }
        {
          {
            if (1 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
              q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
            }
          }
        }
        {
          {
            if (2 % 2 == 1) {
              {
              }
              q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
              q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
            }
          }
        }
        {
          {
          }
        }
        {
          {
            q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[4], 0.70710677F * q0[1] + 0.70710677F * q0[5], 0.70710677F * q0[2] + 0.70710677F * q0[6], 0.70710677F * q0[3] + 0.70710677F * q0[7], 0.70710677F * q0[0] + -0.70710677F * q0[4], 0.70710677F * q0[1] + -0.70710677F * q0[5], 0.70710677F * q0[2] + -0.70710677F * q0[6], 0.70710677F * q0[3] + -0.70710677F * q0[7]};
            q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[4], 0.70710677F * q1[1] + 0.70710677F * q1[5], 0.70710677F * q1[2] + 0.70710677F * q1[6], 0.70710677F * q1[3] + 0.70710677F * q1[7], 0.70710677F * q1[0] + -0.70710677F * q1[4], 0.70710677F * q1[1] + -0.70710677F * q1[5], 0.70710677F * q1[2] + -0.70710677F * q1[6], 0.70710677F * q1[3] + -0.70710677F * q1[7]};
          }
        }
        {
          {
            q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[6], 0.70710677F * q0[5] + 0.70710677F * q0[7], 0.70710677F * q0[4] + -0.70710677F * q0[6], 0.70710677F * q0[5] + -0.70710677F * q0[7]};
            q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[2], 0.70710677F * q1[1] + 0.70710677F * q1[3], 0.70710677F * q1[0] + -0.70710677F * q1[2], 0.70710677F * q1[1] + -0.70710677F * q1[3], 0.70710677F * q1[4] + 0.70710677F * q1[6], 0.70710677F * q1[5] + 0.70710677F * q1[7], 0.70710677F * q1[4] + -0.70710677F * q1[6], 0.70710677F * q1[5] + -0.70710677F * q1[7]};
          }
        }
        {
          {
            q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[5], 0.70710677F * q0[4] + -0.70710677F * q0[5], 0.70710677F * q0[6] + 0.70710677F * q0[7], 0.70710677F * q0[6] + -0.70710677F * q0[7]};
            q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[1], 0.70710677F * q1[0] + -0.70710677F * q1[1], 0.70710677F * q1[2] + 0.70710677F * q1[3], 0.70710677F * q1[2] + -0.70710677F * q1[3], 0.70710677F * q1[4] + 0.70710677F * q1[5], 0.70710677F * q1[4] + -0.70710677F * q1[5], 0.70710677F * q1[6] + 0.70710677F * q1[7], 0.70710677F * q1[6] + -0.70710677F * q1[7]};
          }
        }
        {
          {
            q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
            q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
          }
        }
        {
          {
            q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
            q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
          }
        }
        q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
        q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
        {
          {
            q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], -1.0F * q0[7]};
            q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], -1.0F * q1[7]};
          }
        }
        {
          {
            q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
            q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
          }
        }
        {
          {
            q0 = new float[]{q0[2], q0[3], q0[0], q0[1], q0[6], q0[7], q0[4], q0[5]};
            q1 = new float[]{q1[2], q1[3], q1[0], q1[1], q1[6], q1[7], q1[4], q1[5]};
          }
        }
        {
          {
            q0 = new float[]{q0[1], q0[0], q0[3], q0[2], q0[5], q0[4], q0[7], q0[6]};
            q1 = new float[]{q1[1], q1[0], q1[3], q1[2], q1[5], q1[4], q1[7], q1[6]};
          }
        }
        {
          {
            q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[4], 0.70710677F * q0[1] + 0.70710677F * q0[5], 0.70710677F * q0[2] + 0.70710677F * q0[6], 0.70710677F * q0[3] + 0.70710677F * q0[7], 0.70710677F * q0[0] + -0.70710677F * q0[4], 0.70710677F * q0[1] + -0.70710677F * q0[5], 0.70710677F * q0[2] + -0.70710677F * q0[6], 0.70710677F * q0[3] + -0.70710677F * q0[7]};
            q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[4], 0.70710677F * q1[1] + 0.70710677F * q1[5], 0.70710677F * q1[2] + 0.70710677F * q1[6], 0.70710677F * q1[3] + 0.70710677F * q1[7], 0.70710677F * q1[0] + -0.70710677F * q1[4], 0.70710677F * q1[1] + -0.70710677F * q1[5], 0.70710677F * q1[2] + -0.70710677F * q1[6], 0.70710677F * q1[3] + -0.70710677F * q1[7]};
          }
        }
        {
          {
            q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[6], 0.70710677F * q0[5] + 0.70710677F * q0[7], 0.70710677F * q0[4] + -0.70710677F * q0[6], 0.70710677F * q0[5] + -0.70710677F * q0[7]};
            q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[2], 0.70710677F * q1[1] + 0.70710677F * q1[3], 0.70710677F * q1[0] + -0.70710677F * q1[2], 0.70710677F * q1[1] + -0.70710677F * q1[3], 0.70710677F * q1[4] + 0.70710677F * q1[6], 0.70710677F * q1[5] + 0.70710677F * q1[7], 0.70710677F * q1[4] + -0.70710677F * q1[6], 0.70710677F * q1[5] + -0.70710677F * q1[7]};
          }
        }
      }
    }
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[5], 0.70710677F * q0[4] + -0.70710677F * q0[5], 0.70710677F * q0[6] + 0.70710677F * q0[7], 0.70710677F * q0[6] + -0.70710677F * q0[7]};
    q1 = new float[]{0.70710677F * q1[0] + 0.70710677F * q1[1], 0.70710677F * q1[0] + -0.70710677F * q1[1], 0.70710677F * q1[2] + 0.70710677F * q1[3], 0.70710677F * q1[2] + -0.70710677F * q1[3], 0.70710677F * q1[4] + 0.70710677F * q1[5], 0.70710677F * q1[4] + -0.70710677F * q1[5], 0.70710677F * q1[6] + 0.70710677F * q1[7], 0.70710677F * q1[6] + -0.70710677F * q1[7]};
    float[] probs = new float[]{q0[0] * q0[0] + q1[0] * q1[0], q0[1] * q0[1] + q1[1] * q1[1], q0[2] * q0[2] + q1[2] * q1[2], q0[3] * q0[3] + q1[3] * q1[3], q0[4] * q0[4] + q1[4] * q1[4], q0[5] * q0[5] + q1[5] * q1[5], q0[6] * q0[6] + q1[6] * q1[6], q0[7] * q0[7] + q1[7] * q1[7]};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 7; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 8;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.001F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    res = tmp_measure_var;
    return res;
  }
}