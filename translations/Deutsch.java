
public class Deutsch {
  
  public Deutsch() {
    super();
  }
  
  public boolean applyDeutsch(/*@ non_null */ 
  boolean[] f) {
    /*@ non_null */ 
    float[][] m = new float[][]{new float[]{!f[0] ? 1.0F : 0.0F, f[0] ? 1.0F : 0.0F, 0.0F, 0.0F}, new float[]{f[0] ? 1.0F : 0.0F, !f[0] ? 1.0F : 0.0F, 0.0F, 0.0F}, new float[]{0.0F, 0.0F, !f[1] ? 1.0F : 0.0F, f[1] ? 1.0F : 0.0F}, new float[]{0.0F, 0.0F, f[1] ? 1.0F : 0.0F, !f[1] ? 1.0F : 0.0F}};
    int[][] q_state_0 = new int[][]{new int[]{32768}, new int[]{0}, new int[]{0}, new int[]{0}};
    int[][] q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    q_state_0 = new int[][]{new int[]{0}, new int[]{32768}, new int[]{0}, new int[]{0}};
    q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    q_state_0 = new int[][]{new int[]{23170}, new int[]{-23170}, new int[]{0}, new int[]{0}};
    q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    q_state_0 = new int[][]{new int[]{16383}, new int[]{-16383}, new int[]{16383}, new int[]{-16383}};
    q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    q_state_0 = new int[][]{new int[]{16383 * m[0][0] / 32768 + -16383 * m[0][1] / 32768 + 16383 * m[0][2] / 32768 + -16383 * m[0][3] / 32768}, new int[]{16383 * m[1][0] / 32768 + -16383 * m[1][1] / 32768 + 16383 * m[1][2] / 32768 + -16383 * m[1][3] / 32768}, new int[]{16383 * m[2][0] / 32768 + -16383 * m[2][1] / 32768 + 16383 * m[2][2] / 32768 + -16383 * m[2][3] / 32768}, new int[]{16383 * m[3][0] / 32768 + -16383 * m[3][1] / 32768 + 16383 * m[3][2] / 32768 + -16383 * m[3][3] / 32768}};
    q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    q_state_0 = new int[][]{new int[]{23170 * (16383 * m[0][0] / 32768 + -16383 * m[0][1] / 32768 + 16383 * m[0][2] / 32768 + -16383 * m[0][3] / 32768) / 32768 + 23170 * (16383 * m[2][0] / 32768 + -16383 * m[2][1] / 32768 + 16383 * m[2][2] / 32768 + -16383 * m[2][3] / 32768) / 32768}, new int[]{23170 * (16383 * m[1][0] / 32768 + -16383 * m[1][1] / 32768 + 16383 * m[1][2] / 32768 + -16383 * m[1][3] / 32768) / 32768 + 23170 * (16383 * m[3][0] / 32768 + -16383 * m[3][1] / 32768 + 16383 * m[3][2] / 32768 + -16383 * m[3][3] / 32768) / 32768}, new int[]{23170 * (16383 * m[0][0] / 32768 + -16383 * m[0][1] / 32768 + 16383 * m[0][2] / 32768 + -16383 * m[0][3] / 32768) / 32768 + -23170 * (16383 * m[2][0] / 32768 + -16383 * m[2][1] / 32768 + 16383 * m[2][2] / 32768 + -16383 * m[2][3] / 32768) / 32768}, new int[]{23170 * (16383 * m[1][0] / 32768 + -16383 * m[1][1] / 32768 + 16383 * m[1][2] / 32768 + -16383 * m[1][3] / 32768) / 32768 + -23170 * (16383 * m[3][0] / 32768 + -16383 * m[3][1] / 32768 + 16383 * m[3][2] / 32768 + -16383 * m[3][3] / 32768) / 32768}};
    q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}};
    boolean $$_tmp_measureVar1;
    if ((q_state_0[2][0] * q_state_0[2][0] / 32768 + q_state_1[2][0] * q_state_1[2][0] / 32768) * (q_state_0[2][0] * q_state_0[2][0] / 32768 + q_state_1[2][0] * q_state_1[2][0] / 32768) / 32768 + (q_state_0[3][0] * q_state_0[3][0] / 32768 + q_state_1[3][0] * q_state_1[3][0] / 32768) * (q_state_0[3][0] * q_state_0[3][0] / 32768 + q_state_1[3][0] * q_state_1[3][0] / 32768) / 32768 > (q_state_0[0][0] * q_state_0[0][0] / 32768 + q_state_1[0][0] * q_state_1[0][0] / 32768) * (q_state_0[0][0] * q_state_0[0][0] / 32768 + q_state_1[0][0] * q_state_1[0][0] / 32768) / 32768 + (q_state_0[1][0] * q_state_0[1][0] / 32768 + q_state_1[1][0] * q_state_1[1][0] / 32768) * (q_state_0[1][0] * q_state_0[1][0] / 32768 + q_state_1[1][0] * q_state_1[1][0] / 32768) / 32768) {
      q_state_0 = new int[][]{new int[]{0}, new int[]{0}, new int[]{q_state_0[2][0]}, new int[]{q_state_0[3][0]}};
      q_state_1 = new int[][]{new int[]{0}, new int[]{0}, new int[]{q_state_1[2][0]}, new int[]{q_state_1[3][0]}};
      $$_tmp_measureVar1 = true;
    } else {
      q_state_0 = new int[][]{new int[]{q_state_0[0][0]}, new int[]{q_state_0[1][0]}, new int[]{0}, new int[]{0}};
      q_state_1 = new int[][]{new int[]{q_state_1[0][0]}, new int[]{q_state_1[1][0]}, new int[]{0}, new int[]{0}};
      $$_tmp_measureVar1 = false;
    }
    return $$_tmp_measureVar1;
  }
}