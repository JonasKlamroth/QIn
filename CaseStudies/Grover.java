
import org.cprover.CProver;

public class Grover {
  
  public Grover() {
    super();
  }
    /*@
      requires database != null && database.length == 4; 
      requires (\forall int i; 0 <= i < 4; database[i] >= 0 && database[i] < 4); 
      requires (\forall int i; 0 <= i < 4; (\forall int j; 0 <= j < 4; i == j || database[i] != database[j])); 
      ensures ((val >= 0 && val < 4) ==> val == database[\result]); 
      ensures ((val < 0 || val > 3) ==> \result == -1); 
   */

  public static int grover2(/*@ non_null */ 
  int[] database, int val) {
    if (val < 0 || val > 3) {
      return -1;
    }
    /*@ non_null */ 
    final float[][] oracle2 = new float[][]{new float[]{val == database[0] ? 1.0F : -1.0F, 0.0F, 0.0F, 0.0F}, new float[]{0.0F, val == database[1] ? 1.0F : -1.0F, 0.0F, 0.0F}, new float[]{0.0F, 0.0F, val == database[2] ? 1.0F : -1.0F, 0.0F}, new float[]{0.0F, 0.0F, 0.0F, val == database[3] ? 1.0F : -1.0F}};
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.49999997F * oracle2[0][0] + 0.49999997F * oracle2[0][1] + 0.49999997F * oracle2[0][2] + 0.49999997F * oracle2[0][3], 0.49999997F * oracle2[1][0] + 0.49999997F * oracle2[1][1] + 0.49999997F * oracle2[1][2] + 0.49999997F * oracle2[1][3], 0.49999997F * oracle2[2][0] + 0.49999997F * oracle2[2][1] + 0.49999997F * oracle2[2][2] + 0.49999997F * oracle2[2][3], 0.49999997F * oracle2[3][0] + 0.49999997F * oracle2[3][1] + 0.49999997F * oracle2[3][2] + 0.49999997F * oracle2[3][3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], q0[1], -1.0F * q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], -1.0F * q0[1], q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], q0[1], q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    float[] probs = new float[]{q0[0] * q0[0], q0[1] * q0[1], q0[2] * q0[2], q0[3] * q0[3]};
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
    int res = tmp_measure_var;
    return res;
  }
    /*@
      requires database != null && database.length == 4; 
      requires (\forall int i; 0 <= i < 4; database[i] >= 0 && database[i] < 4); 
      requires (\forall int i; 0 <= i < 4; (\forall int j; 0 <= j < 4; i == j || database[i] != database[j])); 
      ensures ((val >= 0 && val < 4) ==> val == database[\result]); 
      ensures ((val < 0 || val > 3) ==> \result == -1); 
   */

  public static int grover2broken(/*@ non_null */ 
  int[] database, int val) {
    if (val < 0 || val > 3) {
      {
        return -1;
      }
    }
    /*@ non_null */ 
    final float[][] oracle2 = new float[][]{new float[]{val == database[0] ? 1.0F : -1.0F, 0.0F, 0.0F, 0.0F}, new float[]{0.0F, val == database[1] ? 1.0F : -1.0F, 0.0F, 0.0F}, new float[]{0.0F, 0.0F, val == database[2] ? 1.0F : -1.0F, 0.0F}, new float[]{0.0F, 0.0F, 0.0F, val == database[3] ? 1.0F : -1.0F}};
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F};
    float[] q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.49999997F * oracle2[0][0] + 0.49999997F * oracle2[0][1], 0.49999997F * oracle2[0][0] + 0.49999997F * oracle2[0][1], 0.49999997F * oracle2[1][0] + 0.49999997F * oracle2[1][1], 0.49999997F * oracle2[1][0] + 0.49999997F * oracle2[1][1]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[1], 0.70710677F * q0[0] + -0.70710677F * q0[1], 0.70710677F * q0[2] + 0.70710677F * q0[3], 0.70710677F * q0[2] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], q0[1], -1.0F * q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], -1.0F * q0[1], q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{q0[0], q0[1], q0[2], -1.0F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3]};
    q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    float[] probs = new float[]{q0[0] * q0[0], q0[1] * q0[1], q0[2] * q0[2], q0[3] * q0[3]};
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
    int res = tmp_measure_var;
    return res;
  }
}