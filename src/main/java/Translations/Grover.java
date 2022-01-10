package Translations;
public class Grover {
  
  public Grover() {
    super();
  }
    /*@
      ensures ((val >= 0 && val < 4) ==> \result == val) && ((val < 0 || val > 3) ==> \result == -1); 
   */

  public static int grover2(int val) {
    if (val < 0 || val > 3) {
      return -1;
    }
    /*@ non_null */ 
    final float[][] oracle2 = new float[][]{new float[]{val == 0 ? 1.0F : -1.0F, 0.0F, 0.0F, 0.0F}, new float[]{0.0F, val == 1 ? 1.0F : -1.0F, 0.0F, 0.0F}, new float[]{0.0F, 0.0F, val == 2 ? 1.0F : -1.0F, 0.0F}, new float[]{0.0F, 0.0F, 0.0F, val == 3 ? 1.0F : -1.0F}};
    float[] q_state_0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F};
    float[] q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F, 0.0F, 0.70710677F, 0.0F};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.49999997F, 0.49999997F, 0.49999997F, 0.49999997F};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.49999997F * oracle2[0][0] + 0.49999997F * oracle2[0][1] + 0.49999997F * oracle2[0][2] + 0.49999997F * oracle2[0][3], 0.49999997F * oracle2[1][0] + 0.49999997F * oracle2[1][1] + 0.49999997F * oracle2[1][2] + 0.49999997F * oracle2[1][3], 0.49999997F * oracle2[2][0] + 0.49999997F * oracle2[2][1] + 0.49999997F * oracle2[2][2] + 0.49999997F * oracle2[2][3], 0.49999997F * oracle2[3][0] + 0.49999997F * oracle2[3][1] + 0.49999997F * oracle2[3][2] + 0.49999997F * oracle2[3][3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[1], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[1], 0.70710677F * q_state_0[2] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[2] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], q_state_0[1], -1.0F * q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], -1.0F * q_state_0[1], q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], q_state_0[1], q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[1], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[1], 0.70710677F * q_state_0[2] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[2] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    boolean $$_tmp_measureVar1;
    if (q_state_0[2] * q_state_0[2] + q_state_1[2] * q_state_1[2] + q_state_0[3] * q_state_0[3] + q_state_1[3] * q_state_1[3] > q_state_0[0] * q_state_0[0] + q_state_1[0] * q_state_1[0] + q_state_0[1] * q_state_0[1] + q_state_1[1] * q_state_1[1]) {
      q_state_0 = new float[]{0.0F, 0.0F, q_state_0[2], q_state_0[3]};
      q_state_1 = new float[]{0.0F, 0.0F, q_state_1[2], q_state_1[3]};
      $$_tmp_measureVar1 = true;
    } else {
      q_state_0 = new float[]{q_state_0[0], q_state_0[1], 0.0F, 0.0F};
      q_state_1 = new float[]{q_state_1[0], q_state_1[1], 0.0F, 0.0F};
      $$_tmp_measureVar1 = false;
    }
    boolean res1 = $$_tmp_measureVar1;
    boolean $$_tmp_measureVar2;
    if (q_state_0[1] * q_state_0[1] + q_state_1[1] * q_state_1[1] + q_state_0[3] * q_state_0[3] + q_state_1[3] * q_state_1[3] > q_state_0[0] * q_state_0[0] + q_state_1[0] * q_state_1[0] + q_state_0[2] * q_state_0[2] + q_state_1[2] * q_state_1[2]) {
      q_state_0 = new float[]{0.0F, q_state_0[1], 0.0F, q_state_0[3]};
      q_state_1 = new float[]{0.0F, q_state_1[1], 0.0F, q_state_1[3]};
      $$_tmp_measureVar2 = true;
    } else {
      q_state_0 = new float[]{q_state_0[0], 0.0F, q_state_0[2], 0.0F};
      q_state_1 = new float[]{q_state_1[0], 0.0F, q_state_1[2], 0.0F};
      $$_tmp_measureVar2 = false;
    }
    boolean res2 = $$_tmp_measureVar2;
    return (res1 ? 2 : 0) + (res2 ? 1 : 0);
  }
    /*@
      ensures ((val >= 0 && val < 4) ==> \result == val) && ((val < 0 || val > 3) ==> \result == -1); 
   */

  public static int grover2broken(int val) {
    if (val < 0 || val > 3) {
      return -1;
    }
    /*@ non_null */ 
    final float[][] oracle2 = new float[][]{new float[]{val == 0 ? 1.0F : -1.0F, 0.0F, 0.0F, 0.0F}, new float[]{0.0F, val == 1 ? 1.0F : -1.0F, 0.0F, 0.0F}, new float[]{0.0F, 0.0F, val == 2 ? 1.0F : -1.0F, 0.0F}, new float[]{0.0F, 0.0F, 0.0F, val == 3 ? 1.0F : -1.0F}};
    float[] q_state_0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F};
    float[] q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F, 0.0F, 0.70710677F, 0.0F};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.49999997F, 0.49999997F, 0.49999997F, 0.49999997F};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.49999997F * oracle2[0][0] + 0.49999997F * oracle2[0][1] + 0.49999997F * oracle2[0][2] + 0.49999997F * oracle2[0][3], 0.49999997F * oracle2[1][0] + 0.49999997F * oracle2[1][1] + 0.49999997F * oracle2[1][2] + 0.49999997F * oracle2[1][3], 0.49999997F * oracle2[2][0] + 0.49999997F * oracle2[2][1] + 0.49999997F * oracle2[2][2] + 0.49999997F * oracle2[2][3], 0.49999997F * oracle2[3][0] + 0.49999997F * oracle2[3][1] + 0.49999997F * oracle2[3][2] + 0.49999997F * oracle2[3][3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[1], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[1], 0.70710677F * q_state_0[2] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[2] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], q_state_0[1], -1.0F * q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], -1.0F * q_state_0[1], q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{q_state_0[0], q_state_0[1], q_state_0[2], -1.0F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    q_state_0 = new float[]{0.70710677F * q_state_0[0] + 0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + 0.70710677F * q_state_0[3], 0.70710677F * q_state_0[0] + -0.70710677F * q_state_0[2], 0.70710677F * q_state_0[1] + -0.70710677F * q_state_0[3]};
    q_state_1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
    boolean $$_tmp_measureVar3;
    if (q_state_0[2] * q_state_0[2] + q_state_1[2] * q_state_1[2] + q_state_0[3] * q_state_0[3] + q_state_1[3] * q_state_1[3] > q_state_0[0] * q_state_0[0] + q_state_1[0] * q_state_1[0] + q_state_0[1] * q_state_0[1] + q_state_1[1] * q_state_1[1]) {
      q_state_0 = new float[]{0.0F, 0.0F, q_state_0[2], q_state_0[3]};
      q_state_1 = new float[]{0.0F, 0.0F, q_state_1[2], q_state_1[3]};
      $$_tmp_measureVar3 = true;
    } else {
      q_state_0 = new float[]{q_state_0[0], q_state_0[1], 0.0F, 0.0F};
      q_state_1 = new float[]{q_state_1[0], q_state_1[1], 0.0F, 0.0F};
      $$_tmp_measureVar3 = false;
    }
    boolean res1 = $$_tmp_measureVar3;
    boolean $$_tmp_measureVar4;
    if (q_state_0[1] * q_state_0[1] + q_state_1[1] * q_state_1[1] + q_state_0[3] * q_state_0[3] + q_state_1[3] * q_state_1[3] > q_state_0[0] * q_state_0[0] + q_state_1[0] * q_state_1[0] + q_state_0[2] * q_state_0[2] + q_state_1[2] * q_state_1[2]) {
      q_state_0 = new float[]{0.0F, q_state_0[1], 0.0F, q_state_0[3]};
      q_state_1 = new float[]{0.0F, q_state_1[1], 0.0F, q_state_1[3]};
      $$_tmp_measureVar4 = true;
    } else {
      q_state_0 = new float[]{q_state_0[0], 0.0F, q_state_0[2], 0.0F};
      q_state_1 = new float[]{q_state_1[0], 0.0F, q_state_1[2], 0.0F};
      $$_tmp_measureVar4 = false;
    }
    boolean res2 = $$_tmp_measureVar4;
    return (res1 ? 2 : 0) + (res2 ? 1 : 0);
  }
}