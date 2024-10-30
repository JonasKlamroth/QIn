
import org.cprover.CProver;

public class BernsteinVaziraniQiskit {
  
  public BernsteinVaziraniQiskit() {
    super();
  }
  public static final int N = 3;
    /*@
      requires 0 <= a < (1 << N); 
      ensures \result == a; 
      assignable \nothing; 
   */

  public int findHiddenShift(int a) {
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
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
        q0 = new float[]{0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F};
        if ((a & (1 << 0)) != 0) {
          {
          }
          q0 = new float[]{0.35355335F, 0.35355335F, 0.35355335F, 0.35355335F, -0.35355335F, -0.35355335F, -0.35355335F, -0.35355335F};
        }
      }
    }
    {
      {
        if ((a & (1 << 1)) != 0) {
          {
          }
          q0 = new float[]{q0[0], q0[1], -1.0F * q0[2], -1.0F * q0[3], q0[4], q0[5], -1.0F * q0[6], -1.0F * q0[7]};
        }
      }
    }
    {
      {
        if ((a & (1 << 2)) != 0) {
          {
          }
          q0 = new float[]{q0[0], -1.0F * q0[1], q0[2], -1.0F * q0[3], q0[4], -1.0F * q0[5], q0[6], -1.0F * q0[7]};
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
      }
    }
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[6], 0.70710677F * q0[5] + 0.70710677F * q0[7], 0.70710677F * q0[4] + -0.70710677F * q0[6], 0.70710677F * q0[5] + -0.70710677F * q0[7]};
      }
    }
    int res = 0;
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[5], 0.70710677F * q0[4] + -0.70710677F * q0[5], 0.70710677F * q0[6] + 0.70710677F * q0[7], 0.70710677F * q0[6] + -0.70710677F * q0[7]};
        boolean $$_tmp_measureVar1;
        if (CProver.nondetBoolean()) {
          if (true && q0[0] == 0.0F && q0[1] == 0.0F && q0[2] == 0.0F && q0[3] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{q0[0], q0[1], q0[2], q0[3], 0.0F, 0.0F, 0.0F, 0.0F};
          $$_tmp_measureVar1 = false;
        } else {
          if (true && q0[4] == 0.0F && q0[5] == 0.0F && q0[6] == 0.0F && q0[7] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q0[4], q0[5], q0[6], q0[7]};
          $$_tmp_measureVar1 = true;
        }
        res += ($$_tmp_measureVar1 ? (1 << 0) : 0);
      }
    }
    {
      {
        boolean $$_tmp_measureVar2;
        if (CProver.nondetBoolean()) {
          if (true && q0[0] == 0.0F && q0[1] == 0.0F && q0[4] == 0.0F && q0[5] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{q0[0], q0[1], 0.0F, 0.0F, q0[4], q0[5], 0.0F, 0.0F};
          $$_tmp_measureVar2 = false;
        } else {
          if (true && q0[2] == 0.0F && q0[3] == 0.0F && q0[6] == 0.0F && q0[7] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{0.0F, 0.0F, q0[2], q0[3], 0.0F, 0.0F, q0[6], q0[7]};
          $$_tmp_measureVar2 = true;
        }
        res += ($$_tmp_measureVar2 ? (1 << 1) : 0);
      }
    }
    {
      {
        boolean $$_tmp_measureVar3;
        if (CProver.nondetBoolean()) {
          if (true && q0[0] == 0.0F && q0[2] == 0.0F && q0[4] == 0.0F && q0[6] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{q0[0], 0.0F, q0[2], 0.0F, q0[4], 0.0F, q0[6], 0.0F};
          $$_tmp_measureVar3 = false;
        } else {
          if (true && q0[1] == 0.0F && q0[3] == 0.0F && q0[5] == 0.0F && q0[7] == 0.0F) {
            /*@ assume false;*/
          }
          q0 = new float[]{0.0F, q0[1], 0.0F, q0[3], 0.0F, q0[5], 0.0F, q0[7]};
          $$_tmp_measureVar3 = true;
        }
        res += ($$_tmp_measureVar3 ? (1 << 2) : 0);
      }
    }
    return res;
  }
}