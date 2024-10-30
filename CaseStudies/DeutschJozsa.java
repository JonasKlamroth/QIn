
import org.cprover.CProver;

public class DeutschJozsa {
  
  public DeutschJozsa() {
    super();
  }
  public static final int N = 3;
    /*@
      requires f != null && f.length == 1 << N; 
      requires (\forall int i; 0 <= i && i < f.length; f[i]) || (\forall int j; 0 <= j && j < f.length; !f[j]) || count(f) == f.length / 2; 
      ensures \result <==> (count(f) == f.length / 2); 
   */

  public boolean isBalanced(/*@ non_null */ 
  boolean[] f) {
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
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
    /*@ non_null */ 
    float[][] oracle = getOracle(N, f);
    {
      {
        q0 = new float[]{0.24999997F * oracle[0][0] + -0.24999997F * oracle[0][1] + 0.24999997F * oracle[0][2] + -0.24999997F * oracle[0][3] + 0.24999997F * oracle[0][4] + -0.24999997F * oracle[0][5] + 0.24999997F * oracle[0][6] + -0.24999997F * oracle[0][7] + 0.24999997F * oracle[0][8] + -0.24999997F * oracle[0][9] + 0.24999997F * oracle[0][10] + -0.24999997F * oracle[0][11] + 0.24999997F * oracle[0][12] + -0.24999997F * oracle[0][13] + 0.24999997F * oracle[0][14] + -0.24999997F * oracle[0][15], 0.24999997F * oracle[1][0] + -0.24999997F * oracle[1][1] + 0.24999997F * oracle[1][2] + -0.24999997F * oracle[1][3] + 0.24999997F * oracle[1][4] + -0.24999997F * oracle[1][5] + 0.24999997F * oracle[1][6] + -0.24999997F * oracle[1][7] + 0.24999997F * oracle[1][8] + -0.24999997F * oracle[1][9] + 0.24999997F * oracle[1][10] + -0.24999997F * oracle[1][11] + 0.24999997F * oracle[1][12] + -0.24999997F * oracle[1][13] + 0.24999997F * oracle[1][14] + -0.24999997F * oracle[1][15], 0.24999997F * oracle[2][0] + -0.24999997F * oracle[2][1] + 0.24999997F * oracle[2][2] + -0.24999997F * oracle[2][3] + 0.24999997F * oracle[2][4] + -0.24999997F * oracle[2][5] + 0.24999997F * oracle[2][6] + -0.24999997F * oracle[2][7] + 0.24999997F * oracle[2][8] + -0.24999997F * oracle[2][9] + 0.24999997F * oracle[2][10] + -0.24999997F * oracle[2][11] + 0.24999997F * oracle[2][12] + -0.24999997F * oracle[2][13] + 0.24999997F * oracle[2][14] + -0.24999997F * oracle[2][15], 0.24999997F * oracle[3][0] + -0.24999997F * oracle[3][1] + 0.24999997F * oracle[3][2] + -0.24999997F * oracle[3][3] + 0.24999997F * oracle[3][4] + -0.24999997F * oracle[3][5] + 0.24999997F * oracle[3][6] + -0.24999997F * oracle[3][7] + 0.24999997F * oracle[3][8] + -0.24999997F * oracle[3][9] + 0.24999997F * oracle[3][10] + -0.24999997F * oracle[3][11] + 0.24999997F * oracle[3][12] + -0.24999997F * oracle[3][13] + 0.24999997F * oracle[3][14] + -0.24999997F * oracle[3][15], 0.24999997F * oracle[4][0] + -0.24999997F * oracle[4][1] + 0.24999997F * oracle[4][2] + -0.24999997F * oracle[4][3] + 0.24999997F * oracle[4][4] + -0.24999997F * oracle[4][5] + 0.24999997F * oracle[4][6] + -0.24999997F * oracle[4][7] + 0.24999997F * oracle[4][8] + -0.24999997F * oracle[4][9] + 0.24999997F * oracle[4][10] + -0.24999997F * oracle[4][11] + 0.24999997F * oracle[4][12] + -0.24999997F * oracle[4][13] + 0.24999997F * oracle[4][14] + -0.24999997F * oracle[4][15], 0.24999997F * oracle[5][0] + -0.24999997F * oracle[5][1] + 0.24999997F * oracle[5][2] + -0.24999997F * oracle[5][3] + 0.24999997F * oracle[5][4] + -0.24999997F * oracle[5][5] + 0.24999997F * oracle[5][6] + -0.24999997F * oracle[5][7] + 0.24999997F * oracle[5][8] + -0.24999997F * oracle[5][9] + 0.24999997F * oracle[5][10] + -0.24999997F * oracle[5][11] + 0.24999997F * oracle[5][12] + -0.24999997F * oracle[5][13] + 0.24999997F * oracle[5][14] + -0.24999997F * oracle[5][15], 0.24999997F * oracle[6][0] + -0.24999997F * oracle[6][1] + 0.24999997F * oracle[6][2] + -0.24999997F * oracle[6][3] + 0.24999997F * oracle[6][4] + -0.24999997F * oracle[6][5] + 0.24999997F * oracle[6][6] + -0.24999997F * oracle[6][7] + 0.24999997F * oracle[6][8] + -0.24999997F * oracle[6][9] + 0.24999997F * oracle[6][10] + -0.24999997F * oracle[6][11] + 0.24999997F * oracle[6][12] + -0.24999997F * oracle[6][13] + 0.24999997F * oracle[6][14] + -0.24999997F * oracle[6][15], 0.24999997F * oracle[7][0] + -0.24999997F * oracle[7][1] + 0.24999997F * oracle[7][2] + -0.24999997F * oracle[7][3] + 0.24999997F * oracle[7][4] + -0.24999997F * oracle[7][5] + 0.24999997F * oracle[7][6] + -0.24999997F * oracle[7][7] + 0.24999997F * oracle[7][8] + -0.24999997F * oracle[7][9] + 0.24999997F * oracle[7][10] + -0.24999997F * oracle[7][11] + 0.24999997F * oracle[7][12] + -0.24999997F * oracle[7][13] + 0.24999997F * oracle[7][14] + -0.24999997F * oracle[7][15], 0.24999997F * oracle[8][0] + -0.24999997F * oracle[8][1] + 0.24999997F * oracle[8][2] + -0.24999997F * oracle[8][3] + 0.24999997F * oracle[8][4] + -0.24999997F * oracle[8][5] + 0.24999997F * oracle[8][6] + -0.24999997F * oracle[8][7] + 0.24999997F * oracle[8][8] + -0.24999997F * oracle[8][9] + 0.24999997F * oracle[8][10] + -0.24999997F * oracle[8][11] + 0.24999997F * oracle[8][12] + -0.24999997F * oracle[8][13] + 0.24999997F * oracle[8][14] + -0.24999997F * oracle[8][15], 0.24999997F * oracle[9][0] + -0.24999997F * oracle[9][1] + 0.24999997F * oracle[9][2] + -0.24999997F * oracle[9][3] + 0.24999997F * oracle[9][4] + -0.24999997F * oracle[9][5] + 0.24999997F * oracle[9][6] + -0.24999997F * oracle[9][7] + 0.24999997F * oracle[9][8] + -0.24999997F * oracle[9][9] + 0.24999997F * oracle[9][10] + -0.24999997F * oracle[9][11] + 0.24999997F * oracle[9][12] + -0.24999997F * oracle[9][13] + 0.24999997F * oracle[9][14] + -0.24999997F * oracle[9][15], 0.24999997F * oracle[10][0] + -0.24999997F * oracle[10][1] + 0.24999997F * oracle[10][2] + -0.24999997F * oracle[10][3] + 0.24999997F * oracle[10][4] + -0.24999997F * oracle[10][5] + 0.24999997F * oracle[10][6] + -0.24999997F * oracle[10][7] + 0.24999997F * oracle[10][8] + -0.24999997F * oracle[10][9] + 0.24999997F * oracle[10][10] + -0.24999997F * oracle[10][11] + 0.24999997F * oracle[10][12] + -0.24999997F * oracle[10][13] + 0.24999997F * oracle[10][14] + -0.24999997F * oracle[10][15], 0.24999997F * oracle[11][0] + -0.24999997F * oracle[11][1] + 0.24999997F * oracle[11][2] + -0.24999997F * oracle[11][3] + 0.24999997F * oracle[11][4] + -0.24999997F * oracle[11][5] + 0.24999997F * oracle[11][6] + -0.24999997F * oracle[11][7] + 0.24999997F * oracle[11][8] + -0.24999997F * oracle[11][9] + 0.24999997F * oracle[11][10] + -0.24999997F * oracle[11][11] + 0.24999997F * oracle[11][12] + -0.24999997F * oracle[11][13] + 0.24999997F * oracle[11][14] + -0.24999997F * oracle[11][15], 0.24999997F * oracle[12][0] + -0.24999997F * oracle[12][1] + 0.24999997F * oracle[12][2] + -0.24999997F * oracle[12][3] + 0.24999997F * oracle[12][4] + -0.24999997F * oracle[12][5] + 0.24999997F * oracle[12][6] + -0.24999997F * oracle[12][7] + 0.24999997F * oracle[12][8] + -0.24999997F * oracle[12][9] + 0.24999997F * oracle[12][10] + -0.24999997F * oracle[12][11] + 0.24999997F * oracle[12][12] + -0.24999997F * oracle[12][13] + 0.24999997F * oracle[12][14] + -0.24999997F * oracle[12][15], 0.24999997F * oracle[13][0] + -0.24999997F * oracle[13][1] + 0.24999997F * oracle[13][2] + -0.24999997F * oracle[13][3] + 0.24999997F * oracle[13][4] + -0.24999997F * oracle[13][5] + 0.24999997F * oracle[13][6] + -0.24999997F * oracle[13][7] + 0.24999997F * oracle[13][8] + -0.24999997F * oracle[13][9] + 0.24999997F * oracle[13][10] + -0.24999997F * oracle[13][11] + 0.24999997F * oracle[13][12] + -0.24999997F * oracle[13][13] + 0.24999997F * oracle[13][14] + -0.24999997F * oracle[13][15], 0.24999997F * oracle[14][0] + -0.24999997F * oracle[14][1] + 0.24999997F * oracle[14][2] + -0.24999997F * oracle[14][3] + 0.24999997F * oracle[14][4] + -0.24999997F * oracle[14][5] + 0.24999997F * oracle[14][6] + -0.24999997F * oracle[14][7] + 0.24999997F * oracle[14][8] + -0.24999997F * oracle[14][9] + 0.24999997F * oracle[14][10] + -0.24999997F * oracle[14][11] + 0.24999997F * oracle[14][12] + -0.24999997F * oracle[14][13] + 0.24999997F * oracle[14][14] + -0.24999997F * oracle[14][15], 0.24999997F * oracle[15][0] + -0.24999997F * oracle[15][1] + 0.24999997F * oracle[15][2] + -0.24999997F * oracle[15][3] + 0.24999997F * oracle[15][4] + -0.24999997F * oracle[15][5] + 0.24999997F * oracle[15][6] + -0.24999997F * oracle[15][7] + 0.24999997F * oracle[15][8] + -0.24999997F * oracle[15][9] + 0.24999997F * oracle[15][10] + -0.24999997F * oracle[15][11] + 0.24999997F * oracle[15][12] + -0.24999997F * oracle[15][13] + 0.24999997F * oracle[15][14] + -0.24999997F * oracle[15][15]};
      }
    }
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[8], 0.70710677F * q0[1] + 0.70710677F * q0[9], 0.70710677F * q0[2] + 0.70710677F * q0[10], 0.70710677F * q0[3] + 0.70710677F * q0[11], 0.70710677F * q0[4] + 0.70710677F * q0[12], 0.70710677F * q0[5] + 0.70710677F * q0[13], 0.70710677F * q0[6] + 0.70710677F * q0[14], 0.70710677F * q0[7] + 0.70710677F * q0[15], 0.70710677F * q0[0] + -0.70710677F * q0[8], 0.70710677F * q0[1] + -0.70710677F * q0[9], 0.70710677F * q0[2] + -0.70710677F * q0[10], 0.70710677F * q0[3] + -0.70710677F * q0[11], 0.70710677F * q0[4] + -0.70710677F * q0[12], 0.70710677F * q0[5] + -0.70710677F * q0[13], 0.70710677F * q0[6] + -0.70710677F * q0[14], 0.70710677F * q0[7] + -0.70710677F * q0[15]};
      }
    }
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[4], 0.70710677F * q0[1] + 0.70710677F * q0[5], 0.70710677F * q0[2] + 0.70710677F * q0[6], 0.70710677F * q0[3] + 0.70710677F * q0[7], 0.70710677F * q0[0] + -0.70710677F * q0[4], 0.70710677F * q0[1] + -0.70710677F * q0[5], 0.70710677F * q0[2] + -0.70710677F * q0[6], 0.70710677F * q0[3] + -0.70710677F * q0[7], 0.70710677F * q0[8] + 0.70710677F * q0[12], 0.70710677F * q0[9] + 0.70710677F * q0[13], 0.70710677F * q0[10] + 0.70710677F * q0[14], 0.70710677F * q0[11] + 0.70710677F * q0[15], 0.70710677F * q0[8] + -0.70710677F * q0[12], 0.70710677F * q0[9] + -0.70710677F * q0[13], 0.70710677F * q0[10] + -0.70710677F * q0[14], 0.70710677F * q0[11] + -0.70710677F * q0[15]};
      }
    }
    q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[6], 0.70710677F * q0[5] + 0.70710677F * q0[7], 0.70710677F * q0[4] + -0.70710677F * q0[6], 0.70710677F * q0[5] + -0.70710677F * q0[7], 0.70710677F * q0[8] + 0.70710677F * q0[10], 0.70710677F * q0[9] + 0.70710677F * q0[11], 0.70710677F * q0[8] + -0.70710677F * q0[10], 0.70710677F * q0[9] + -0.70710677F * q0[11], 0.70710677F * q0[12] + 0.70710677F * q0[14], 0.70710677F * q0[13] + 0.70710677F * q0[15], 0.70710677F * q0[12] + -0.70710677F * q0[14], 0.70710677F * q0[13] + -0.70710677F * q0[15]};
    float[] probs = new float[]{q0[0] * q0[0], q0[1] * q0[1], q0[2] * q0[2], q0[3] * q0[3], q0[4] * q0[4], q0[5] * q0[5], q0[6] * q0[6], q0[7] * q0[7], q0[8] * q0[8], q0[9] * q0[9], q0[10] * q0[10], q0[11] * q0[11], q0[12] * q0[12], q0[13] * q0[13], q0[14] * q0[14], q0[15] * q0[15]};
    float highestProb = 0.0F;
    for (int loopVarI = 0; loopVarI <= 15; ++loopVarI) {
      if (probs[loopVarI] > highestProb) highestProb = probs[loopVarI];
    }
    int randIdx = CProver.nondetInt();
    /*@ assume 0 <= randIdx && randIdx < 16;*/
    int tmp_measure_var = 0;
    if (probs[randIdx] > highestProb - 0.02F) tmp_measure_var = randIdx; else {
      /*@ assume false;*/
    }
    int res = tmp_measure_var >> 1;
    return res != 0;
  }
    /*@
      requires f != null && f.length == 1 << N; 
      requires (\forall int i; 0 <= i && i < f.length; f[i]) || (\forall int j; 0 <= j && j < f.length; !f[j]) || count(f) == f.length / 2; 
      ensures \result <==> (count(f) == f.length / 2); 
   */

  public boolean isBalancedBroken(/*@ non_null */ 
  boolean[] f) {
    float[] q0 = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
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
    /*@ non_null */ 
    float[][] oracle = getOracle(N, f);
    {
      {
        q0 = new float[]{0.24999997F * oracle[0][0] + 0.24999997F * oracle[0][1] + 0.24999997F * oracle[0][2] + 0.24999997F * oracle[0][3] + 0.24999997F * oracle[0][4] + 0.24999997F * oracle[0][5] + 0.24999997F * oracle[0][6] + 0.24999997F * oracle[0][7] + 0.24999997F * oracle[0][8] + 0.24999997F * oracle[0][9] + 0.24999997F * oracle[0][10] + 0.24999997F * oracle[0][11] + 0.24999997F * oracle[0][12] + 0.24999997F * oracle[0][13] + 0.24999997F * oracle[0][14] + 0.24999997F * oracle[0][15], 0.24999997F * oracle[1][0] + 0.24999997F * oracle[1][1] + 0.24999997F * oracle[1][2] + 0.24999997F * oracle[1][3] + 0.24999997F * oracle[1][4] + 0.24999997F * oracle[1][5] + 0.24999997F * oracle[1][6] + 0.24999997F * oracle[1][7] + 0.24999997F * oracle[1][8] + 0.24999997F * oracle[1][9] + 0.24999997F * oracle[1][10] + 0.24999997F * oracle[1][11] + 0.24999997F * oracle[1][12] + 0.24999997F * oracle[1][13] + 0.24999997F * oracle[1][14] + 0.24999997F * oracle[1][15], 0.24999997F * oracle[2][0] + 0.24999997F * oracle[2][1] + 0.24999997F * oracle[2][2] + 0.24999997F * oracle[2][3] + 0.24999997F * oracle[2][4] + 0.24999997F * oracle[2][5] + 0.24999997F * oracle[2][6] + 0.24999997F * oracle[2][7] + 0.24999997F * oracle[2][8] + 0.24999997F * oracle[2][9] + 0.24999997F * oracle[2][10] + 0.24999997F * oracle[2][11] + 0.24999997F * oracle[2][12] + 0.24999997F * oracle[2][13] + 0.24999997F * oracle[2][14] + 0.24999997F * oracle[2][15], 0.24999997F * oracle[3][0] + 0.24999997F * oracle[3][1] + 0.24999997F * oracle[3][2] + 0.24999997F * oracle[3][3] + 0.24999997F * oracle[3][4] + 0.24999997F * oracle[3][5] + 0.24999997F * oracle[3][6] + 0.24999997F * oracle[3][7] + 0.24999997F * oracle[3][8] + 0.24999997F * oracle[3][9] + 0.24999997F * oracle[3][10] + 0.24999997F * oracle[3][11] + 0.24999997F * oracle[3][12] + 0.24999997F * oracle[3][13] + 0.24999997F * oracle[3][14] + 0.24999997F * oracle[3][15], 0.24999997F * oracle[4][0] + 0.24999997F * oracle[4][1] + 0.24999997F * oracle[4][2] + 0.24999997F * oracle[4][3] + 0.24999997F * oracle[4][4] + 0.24999997F * oracle[4][5] + 0.24999997F * oracle[4][6] + 0.24999997F * oracle[4][7] + 0.24999997F * oracle[4][8] + 0.24999997F * oracle[4][9] + 0.24999997F * oracle[4][10] + 0.24999997F * oracle[4][11] + 0.24999997F * oracle[4][12] + 0.24999997F * oracle[4][13] + 0.24999997F * oracle[4][14] + 0.24999997F * oracle[4][15], 0.24999997F * oracle[5][0] + 0.24999997F * oracle[5][1] + 0.24999997F * oracle[5][2] + 0.24999997F * oracle[5][3] + 0.24999997F * oracle[5][4] + 0.24999997F * oracle[5][5] + 0.24999997F * oracle[5][6] + 0.24999997F * oracle[5][7] + 0.24999997F * oracle[5][8] + 0.24999997F * oracle[5][9] + 0.24999997F * oracle[5][10] + 0.24999997F * oracle[5][11] + 0.24999997F * oracle[5][12] + 0.24999997F * oracle[5][13] + 0.24999997F * oracle[5][14] + 0.24999997F * oracle[5][15], 0.24999997F * oracle[6][0] + 0.24999997F * oracle[6][1] + 0.24999997F * oracle[6][2] + 0.24999997F * oracle[6][3] + 0.24999997F * oracle[6][4] + 0.24999997F * oracle[6][5] + 0.24999997F * oracle[6][6] + 0.24999997F * oracle[6][7] + 0.24999997F * oracle[6][8] + 0.24999997F * oracle[6][9] + 0.24999997F * oracle[6][10] + 0.24999997F * oracle[6][11] + 0.24999997F * oracle[6][12] + 0.24999997F * oracle[6][13] + 0.24999997F * oracle[6][14] + 0.24999997F * oracle[6][15], 0.24999997F * oracle[7][0] + 0.24999997F * oracle[7][1] + 0.24999997F * oracle[7][2] + 0.24999997F * oracle[7][3] + 0.24999997F * oracle[7][4] + 0.24999997F * oracle[7][5] + 0.24999997F * oracle[7][6] + 0.24999997F * oracle[7][7] + 0.24999997F * oracle[7][8] + 0.24999997F * oracle[7][9] + 0.24999997F * oracle[7][10] + 0.24999997F * oracle[7][11] + 0.24999997F * oracle[7][12] + 0.24999997F * oracle[7][13] + 0.24999997F * oracle[7][14] + 0.24999997F * oracle[7][15], 0.24999997F * oracle[8][0] + 0.24999997F * oracle[8][1] + 0.24999997F * oracle[8][2] + 0.24999997F * oracle[8][3] + 0.24999997F * oracle[8][4] + 0.24999997F * oracle[8][5] + 0.24999997F * oracle[8][6] + 0.24999997F * oracle[8][7] + 0.24999997F * oracle[8][8] + 0.24999997F * oracle[8][9] + 0.24999997F * oracle[8][10] + 0.24999997F * oracle[8][11] + 0.24999997F * oracle[8][12] + 0.24999997F * oracle[8][13] + 0.24999997F * oracle[8][14] + 0.24999997F * oracle[8][15], 0.24999997F * oracle[9][0] + 0.24999997F * oracle[9][1] + 0.24999997F * oracle[9][2] + 0.24999997F * oracle[9][3] + 0.24999997F * oracle[9][4] + 0.24999997F * oracle[9][5] + 0.24999997F * oracle[9][6] + 0.24999997F * oracle[9][7] + 0.24999997F * oracle[9][8] + 0.24999997F * oracle[9][9] + 0.24999997F * oracle[9][10] + 0.24999997F * oracle[9][11] + 0.24999997F * oracle[9][12] + 0.24999997F * oracle[9][13] + 0.24999997F * oracle[9][14] + 0.24999997F * oracle[9][15], 0.24999997F * oracle[10][0] + 0.24999997F * oracle[10][1] + 0.24999997F * oracle[10][2] + 0.24999997F * oracle[10][3] + 0.24999997F * oracle[10][4] + 0.24999997F * oracle[10][5] + 0.24999997F * oracle[10][6] + 0.24999997F * oracle[10][7] + 0.24999997F * oracle[10][8] + 0.24999997F * oracle[10][9] + 0.24999997F * oracle[10][10] + 0.24999997F * oracle[10][11] + 0.24999997F * oracle[10][12] + 0.24999997F * oracle[10][13] + 0.24999997F * oracle[10][14] + 0.24999997F * oracle[10][15], 0.24999997F * oracle[11][0] + 0.24999997F * oracle[11][1] + 0.24999997F * oracle[11][2] + 0.24999997F * oracle[11][3] + 0.24999997F * oracle[11][4] + 0.24999997F * oracle[11][5] + 0.24999997F * oracle[11][6] + 0.24999997F * oracle[11][7] + 0.24999997F * oracle[11][8] + 0.24999997F * oracle[11][9] + 0.24999997F * oracle[11][10] + 0.24999997F * oracle[11][11] + 0.24999997F * oracle[11][12] + 0.24999997F * oracle[11][13] + 0.24999997F * oracle[11][14] + 0.24999997F * oracle[11][15], 0.24999997F * oracle[12][0] + 0.24999997F * oracle[12][1] + 0.24999997F * oracle[12][2] + 0.24999997F * oracle[12][3] + 0.24999997F * oracle[12][4] + 0.24999997F * oracle[12][5] + 0.24999997F * oracle[12][6] + 0.24999997F * oracle[12][7] + 0.24999997F * oracle[12][8] + 0.24999997F * oracle[12][9] + 0.24999997F * oracle[12][10] + 0.24999997F * oracle[12][11] + 0.24999997F * oracle[12][12] + 0.24999997F * oracle[12][13] + 0.24999997F * oracle[12][14] + 0.24999997F * oracle[12][15], 0.24999997F * oracle[13][0] + 0.24999997F * oracle[13][1] + 0.24999997F * oracle[13][2] + 0.24999997F * oracle[13][3] + 0.24999997F * oracle[13][4] + 0.24999997F * oracle[13][5] + 0.24999997F * oracle[13][6] + 0.24999997F * oracle[13][7] + 0.24999997F * oracle[13][8] + 0.24999997F * oracle[13][9] + 0.24999997F * oracle[13][10] + 0.24999997F * oracle[13][11] + 0.24999997F * oracle[13][12] + 0.24999997F * oracle[13][13] + 0.24999997F * oracle[13][14] + 0.24999997F * oracle[13][15], 0.24999997F * oracle[14][0] + 0.24999997F * oracle[14][1] + 0.24999997F * oracle[14][2] + 0.24999997F * oracle[14][3] + 0.24999997F * oracle[14][4] + 0.24999997F * oracle[14][5] + 0.24999997F * oracle[14][6] + 0.24999997F * oracle[14][7] + 0.24999997F * oracle[14][8] + 0.24999997F * oracle[14][9] + 0.24999997F * oracle[14][10] + 0.24999997F * oracle[14][11] + 0.24999997F * oracle[14][12] + 0.24999997F * oracle[14][13] + 0.24999997F * oracle[14][14] + 0.24999997F * oracle[14][15], 0.24999997F * oracle[15][0] + 0.24999997F * oracle[15][1] + 0.24999997F * oracle[15][2] + 0.24999997F * oracle[15][3] + 0.24999997F * oracle[15][4] + 0.24999997F * oracle[15][5] + 0.24999997F * oracle[15][6] + 0.24999997F * oracle[15][7] + 0.24999997F * oracle[15][8] + 0.24999997F * oracle[15][9] + 0.24999997F * oracle[15][10] + 0.24999997F * oracle[15][11] + 0.24999997F * oracle[15][12] + 0.24999997F * oracle[15][13] + 0.24999997F * oracle[15][14] + 0.24999997F * oracle[15][15]};
      }
    }
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[8], 0.70710677F * q0[1] + 0.70710677F * q0[9], 0.70710677F * q0[2] + 0.70710677F * q0[10], 0.70710677F * q0[3] + 0.70710677F * q0[11], 0.70710677F * q0[4] + 0.70710677F * q0[12], 0.70710677F * q0[5] + 0.70710677F * q0[13], 0.70710677F * q0[6] + 0.70710677F * q0[14], 0.70710677F * q0[7] + 0.70710677F * q0[15], 0.70710677F * q0[0] + -0.70710677F * q0[8], 0.70710677F * q0[1] + -0.70710677F * q0[9], 0.70710677F * q0[2] + -0.70710677F * q0[10], 0.70710677F * q0[3] + -0.70710677F * q0[11], 0.70710677F * q0[4] + -0.70710677F * q0[12], 0.70710677F * q0[5] + -0.70710677F * q0[13], 0.70710677F * q0[6] + -0.70710677F * q0[14], 0.70710677F * q0[7] + -0.70710677F * q0[15]};
      }
    }
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[4], 0.70710677F * q0[1] + 0.70710677F * q0[5], 0.70710677F * q0[2] + 0.70710677F * q0[6], 0.70710677F * q0[3] + 0.70710677F * q0[7], 0.70710677F * q0[0] + -0.70710677F * q0[4], 0.70710677F * q0[1] + -0.70710677F * q0[5], 0.70710677F * q0[2] + -0.70710677F * q0[6], 0.70710677F * q0[3] + -0.70710677F * q0[7], 0.70710677F * q0[8] + 0.70710677F * q0[12], 0.70710677F * q0[9] + 0.70710677F * q0[13], 0.70710677F * q0[10] + 0.70710677F * q0[14], 0.70710677F * q0[11] + 0.70710677F * q0[15], 0.70710677F * q0[8] + -0.70710677F * q0[12], 0.70710677F * q0[9] + -0.70710677F * q0[13], 0.70710677F * q0[10] + -0.70710677F * q0[14], 0.70710677F * q0[11] + -0.70710677F * q0[15]};
      }
    }
    boolean res = false;
    {
      {
        q0 = new float[]{0.70710677F * q0[0] + 0.70710677F * q0[2], 0.70710677F * q0[1] + 0.70710677F * q0[3], 0.70710677F * q0[0] + -0.70710677F * q0[2], 0.70710677F * q0[1] + -0.70710677F * q0[3], 0.70710677F * q0[4] + 0.70710677F * q0[6], 0.70710677F * q0[5] + 0.70710677F * q0[7], 0.70710677F * q0[4] + -0.70710677F * q0[6], 0.70710677F * q0[5] + -0.70710677F * q0[7], 0.70710677F * q0[8] + 0.70710677F * q0[10], 0.70710677F * q0[9] + 0.70710677F * q0[11], 0.70710677F * q0[8] + -0.70710677F * q0[10], 0.70710677F * q0[9] + -0.70710677F * q0[11], 0.70710677F * q0[12] + 0.70710677F * q0[14], 0.70710677F * q0[13] + 0.70710677F * q0[15], 0.70710677F * q0[12] + -0.70710677F * q0[14], 0.70710677F * q0[13] + -0.70710677F * q0[15]};
        boolean $$_tmp_measureVar1;
        if (q0[8] * q0[8] + q0[9] * q0[9] + q0[10] * q0[10] + q0[11] * q0[11] + q0[12] * q0[12] + q0[13] * q0[13] + q0[14] * q0[14] + q0[15] * q0[15] > q0[0] * q0[0] + q0[1] * q0[1] + q0[2] * q0[2] + q0[3] * q0[3] + q0[4] * q0[4] + q0[5] * q0[5] + q0[6] * q0[6] + q0[7] * q0[7]) {
          q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15]};
          $$_tmp_measureVar1 = true;
        } else {
          q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
          $$_tmp_measureVar1 = false;
        }
        res |= $$_tmp_measureVar1;
      }
    }
    {
      {
        boolean $$_tmp_measureVar2;
        if (q0[4] * q0[4] + q0[5] * q0[5] + q0[6] * q0[6] + q0[7] * q0[7] + q0[12] * q0[12] + q0[13] * q0[13] + q0[14] * q0[14] + q0[15] * q0[15] > q0[0] * q0[0] + q0[1] * q0[1] + q0[2] * q0[2] + q0[3] * q0[3] + q0[8] * q0[8] + q0[9] * q0[9] + q0[10] * q0[10] + q0[11] * q0[11]) {
          q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q0[4], q0[5], q0[6], q0[7], 0.0F, 0.0F, 0.0F, 0.0F, q0[12], q0[13], q0[14], q0[15]};
          $$_tmp_measureVar2 = true;
        } else {
          q0 = new float[]{q0[0], q0[1], q0[2], q0[3], 0.0F, 0.0F, 0.0F, 0.0F, q0[8], q0[9], q0[10], q0[11], 0.0F, 0.0F, 0.0F, 0.0F};
          $$_tmp_measureVar2 = false;
        }
        res |= $$_tmp_measureVar2;
      }
    }
    {
      {
        boolean $$_tmp_measureVar3;
        if (q0[2] * q0[2] + q0[3] * q0[3] + q0[6] * q0[6] + q0[7] * q0[7] + q0[10] * q0[10] + q0[11] * q0[11] + q0[14] * q0[14] + q0[15] * q0[15] > q0[0] * q0[0] + q0[1] * q0[1] + q0[4] * q0[4] + q0[5] * q0[5] + q0[8] * q0[8] + q0[9] * q0[9] + q0[12] * q0[12] + q0[13] * q0[13]) {
          q0 = new float[]{0.0F, 0.0F, q0[2], q0[3], 0.0F, 0.0F, q0[6], q0[7], 0.0F, 0.0F, q0[10], q0[11], 0.0F, 0.0F, q0[14], q0[15]};
          $$_tmp_measureVar3 = true;
        } else {
          q0 = new float[]{q0[0], q0[1], 0.0F, 0.0F, q0[4], q0[5], 0.0F, 0.0F, q0[8], q0[9], 0.0F, 0.0F, q0[12], q0[13], 0.0F, 0.0F};
          $$_tmp_measureVar3 = false;
        }
        res |= $$_tmp_measureVar3;
      }
    }
    return res;
  }
  
  public float[][] getOracle(int N, /*@ non_null */ 
  boolean[] f) {
    int size = 1 << N + 1;
    /*@ non_null */ 
    float[][] oracle = new float[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        oracle[i][j] = 0.0F;
        float val = f[i / 2] ? 1.0F : 0.0F;
        if (i == j) {
          {
            oracle[i][j] = 1.0F - val;
          }
        }
        int even = (i % 2) * 2 - 1;
        if (i == j + even) {
          {
            oracle[i][j] = val;
          }
        }
      }
    }
    return oracle;
  }
  
  /*@ pure */ 
  public static int count(/*@ non_null */ 
  boolean[] f) {
    int i = 0;
    for (int j = 0; j < f.length; j++) {
      if (f[j]) {
        {
          ++i;
        }
      }
    }
    return i;
  }
}