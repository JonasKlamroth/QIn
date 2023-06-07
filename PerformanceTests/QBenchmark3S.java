
public class QBenchmark3S {
  
  public QBenchmark3S() {
    super();
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 2 && qstatei.length == 2; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_1(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 1; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1]};
    float[] q1 = new float[]{qstatei[0], qstatei[1]};
    q0 = new float[]{q0[1], q0[0]};
    q1 = new float[]{q1[1], q1[0]};
    boolean $$_tmp_measureVar1;
    if (q0[1] * q0[1] + q1[1] * q1[1] > q0[0] * q0[0] + q1[0] * q1[0]) {
      q0 = new float[]{0.0F, q0[1]};
      q1 = new float[]{0.0F, q1[1]};
      $$_tmp_measureVar1 = true;
    } else {
      q0 = new float[]{q0[0], 0.0F};
      q1 = new float[]{q1[0], 0.0F};
      $$_tmp_measureVar1 = false;
    }
    boolean b_0 = $$_tmp_measureVar1;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 4 && qstatei.length == 4; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_2(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 2; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3]};
    q0 = new float[]{q0[2], q0[3], q0[0], q0[1]};
    q1 = new float[]{q1[2], q1[3], q1[0], q1[1]};
    boolean $$_tmp_measureVar2;
    if (q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1]) {
      q0 = new float[]{0.0F, 0.0F, q0[2], q0[3]};
      q1 = new float[]{0.0F, 0.0F, q1[2], q1[3]};
      $$_tmp_measureVar2 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], 0.0F, 0.0F};
      $$_tmp_measureVar2 = false;
    }
    boolean b_0 = $$_tmp_measureVar2;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 8 && qstatei.length == 8; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_3(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 4; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3], qstate[4], qstate[5], qstate[6], qstate[7]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3], qstatei[4], qstatei[5], qstatei[6], qstatei[7]};
    q0 = new float[]{q0[4], q0[5], q0[6], q0[7], q0[0], q0[1], q0[2], q0[3]};
    q1 = new float[]{q1[4], q1[5], q1[6], q1[7], q1[0], q1[1], q1[2], q1[3]};
    boolean $$_tmp_measureVar3;
    if (q0[4] * q0[4] + q1[4] * q1[4] + q0[5] * q0[5] + q1[5] * q1[5] + q0[6] * q0[6] + q1[6] * q1[6] + q0[7] * q0[7] + q1[7] * q1[7] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1] + q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3]) {
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q0[4], q0[5], q0[6], q0[7]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, q1[4], q1[5], q1[6], q1[7]};
      $$_tmp_measureVar3 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar3 = false;
    }
    boolean b_0 = $$_tmp_measureVar3;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 16 && qstatei.length == 16; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_4(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 8; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3], qstate[4], qstate[5], qstate[6], qstate[7], qstate[8], qstate[9], qstate[10], qstate[11], qstate[12], qstate[13], qstate[14], qstate[15]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3], qstatei[4], qstatei[5], qstatei[6], qstatei[7], qstatei[8], qstatei[9], qstatei[10], qstatei[11], qstatei[12], qstatei[13], qstatei[14], qstatei[15]};
    q0 = new float[]{q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7]};
    q1 = new float[]{q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7]};
    boolean $$_tmp_measureVar4;
    if (q0[8] * q0[8] + q1[8] * q1[8] + q0[9] * q0[9] + q1[9] * q1[9] + q0[10] * q0[10] + q1[10] * q1[10] + q0[11] * q0[11] + q1[11] * q1[11] + q0[12] * q0[12] + q1[12] * q1[12] + q0[13] * q0[13] + q1[13] * q1[13] + q0[14] * q0[14] + q1[14] * q1[14] + q0[15] * q0[15] + q1[15] * q1[15] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1] + q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3] + q0[4] * q0[4] + q1[4] * q1[4] + q0[5] * q0[5] + q1[5] * q1[5] + q0[6] * q0[6] + q1[6] * q1[6] + q0[7] * q0[7] + q1[7] * q1[7]) {
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15]};
      $$_tmp_measureVar4 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar4 = false;
    }
    boolean b_0 = $$_tmp_measureVar4;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 32 && qstatei.length == 32; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_5(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 16; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3], qstate[4], qstate[5], qstate[6], qstate[7], qstate[8], qstate[9], qstate[10], qstate[11], qstate[12], qstate[13], qstate[14], qstate[15], qstate[16], qstate[17], qstate[18], qstate[19], qstate[20], qstate[21], qstate[22], qstate[23], qstate[24], qstate[25], qstate[26], qstate[27], qstate[28], qstate[29], qstate[30], qstate[31]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3], qstatei[4], qstatei[5], qstatei[6], qstatei[7], qstatei[8], qstatei[9], qstatei[10], qstatei[11], qstatei[12], qstatei[13], qstatei[14], qstatei[15], qstatei[16], qstatei[17], qstatei[18], qstatei[19], qstatei[20], qstatei[21], qstatei[22], qstatei[23], qstatei[24], qstatei[25], qstatei[26], qstatei[27], qstatei[28], qstatei[29], qstatei[30], qstatei[31]};
    q0 = new float[]{q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31], q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15]};
    q1 = new float[]{q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31], q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15]};
    boolean $$_tmp_measureVar5;
    if (q0[16] * q0[16] + q1[16] * q1[16] + q0[17] * q0[17] + q1[17] * q1[17] + q0[18] * q0[18] + q1[18] * q1[18] + q0[19] * q0[19] + q1[19] * q1[19] + q0[20] * q0[20] + q1[20] * q1[20] + q0[21] * q0[21] + q1[21] * q1[21] + q0[22] * q0[22] + q1[22] * q1[22] + q0[23] * q0[23] + q1[23] * q1[23] + q0[24] * q0[24] + q1[24] * q1[24] + q0[25] * q0[25] + q1[25] * q1[25] + q0[26] * q0[26] + q1[26] * q1[26] + q0[27] * q0[27] + q1[27] * q1[27] + q0[28] * q0[28] + q1[28] * q1[28] + q0[29] * q0[29] + q1[29] * q1[29] + q0[30] * q0[30] + q1[30] * q1[30] + q0[31] * q0[31] + q1[31] * q1[31] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1] + q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3] + q0[4] * q0[4] + q1[4] * q1[4] + q0[5] * q0[5] + q1[5] * q1[5] + q0[6] * q0[6] + q1[6] * q1[6] + q0[7] * q0[7] + q1[7] * q1[7] + q0[8] * q0[8] + q1[8] * q1[8] + q0[9] * q0[9] + q1[9] * q1[9] + q0[10] * q0[10] + q1[10] * q1[10] + q0[11] * q0[11] + q1[11] * q1[11] + q0[12] * q0[12] + q1[12] * q1[12] + q0[13] * q0[13] + q1[13] * q1[13] + q0[14] * q0[14] + q1[14] * q1[14] + q0[15] * q0[15] + q1[15] * q1[15]) {
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31]};
      $$_tmp_measureVar5 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar5 = false;
    }
    boolean b_0 = $$_tmp_measureVar5;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 64 && qstatei.length == 64; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_6(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 32; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3], qstate[4], qstate[5], qstate[6], qstate[7], qstate[8], qstate[9], qstate[10], qstate[11], qstate[12], qstate[13], qstate[14], qstate[15], qstate[16], qstate[17], qstate[18], qstate[19], qstate[20], qstate[21], qstate[22], qstate[23], qstate[24], qstate[25], qstate[26], qstate[27], qstate[28], qstate[29], qstate[30], qstate[31], qstate[32], qstate[33], qstate[34], qstate[35], qstate[36], qstate[37], qstate[38], qstate[39], qstate[40], qstate[41], qstate[42], qstate[43], qstate[44], qstate[45], qstate[46], qstate[47], qstate[48], qstate[49], qstate[50], qstate[51], qstate[52], qstate[53], qstate[54], qstate[55], qstate[56], qstate[57], qstate[58], qstate[59], qstate[60], qstate[61], qstate[62], qstate[63]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3], qstatei[4], qstatei[5], qstatei[6], qstatei[7], qstatei[8], qstatei[9], qstatei[10], qstatei[11], qstatei[12], qstatei[13], qstatei[14], qstatei[15], qstatei[16], qstatei[17], qstatei[18], qstatei[19], qstatei[20], qstatei[21], qstatei[22], qstatei[23], qstatei[24], qstatei[25], qstatei[26], qstatei[27], qstatei[28], qstatei[29], qstatei[30], qstatei[31], qstatei[32], qstatei[33], qstatei[34], qstatei[35], qstatei[36], qstatei[37], qstatei[38], qstatei[39], qstatei[40], qstatei[41], qstatei[42], qstatei[43], qstatei[44], qstatei[45], qstatei[46], qstatei[47], qstatei[48], qstatei[49], qstatei[50], qstatei[51], qstatei[52], qstatei[53], qstatei[54], qstatei[55], qstatei[56], qstatei[57], qstatei[58], qstatei[59], qstatei[60], qstatei[61], qstatei[62], qstatei[63]};
    q0 = new float[]{q0[32], q0[33], q0[34], q0[35], q0[36], q0[37], q0[38], q0[39], q0[40], q0[41], q0[42], q0[43], q0[44], q0[45], q0[46], q0[47], q0[48], q0[49], q0[50], q0[51], q0[52], q0[53], q0[54], q0[55], q0[56], q0[57], q0[58], q0[59], q0[60], q0[61], q0[62], q0[63], q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31]};
    q1 = new float[]{q1[32], q1[33], q1[34], q1[35], q1[36], q1[37], q1[38], q1[39], q1[40], q1[41], q1[42], q1[43], q1[44], q1[45], q1[46], q1[47], q1[48], q1[49], q1[50], q1[51], q1[52], q1[53], q1[54], q1[55], q1[56], q1[57], q1[58], q1[59], q1[60], q1[61], q1[62], q1[63], q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31]};
    boolean $$_tmp_measureVar6;
    if (q0[32] * q0[32] + q1[32] * q1[32] + q0[33] * q0[33] + q1[33] * q1[33] + q0[34] * q0[34] + q1[34] * q1[34] + q0[35] * q0[35] + q1[35] * q1[35] + q0[36] * q0[36] + q1[36] * q1[36] + q0[37] * q0[37] + q1[37] * q1[37] + q0[38] * q0[38] + q1[38] * q1[38] + q0[39] * q0[39] + q1[39] * q1[39] + q0[40] * q0[40] + q1[40] * q1[40] + q0[41] * q0[41] + q1[41] * q1[41] + q0[42] * q0[42] + q1[42] * q1[42] + q0[43] * q0[43] + q1[43] * q1[43] + q0[44] * q0[44] + q1[44] * q1[44] + q0[45] * q0[45] + q1[45] * q1[45] + q0[46] * q0[46] + q1[46] * q1[46] + q0[47] * q0[47] + q1[47] * q1[47] + q0[48] * q0[48] + q1[48] * q1[48] + q0[49] * q0[49] + q1[49] * q1[49] + q0[50] * q0[50] + q1[50] * q1[50] + q0[51] * q0[51] + q1[51] * q1[51] + q0[52] * q0[52] + q1[52] * q1[52] + q0[53] * q0[53] + q1[53] * q1[53] + q0[54] * q0[54] + q1[54] * q1[54] + q0[55] * q0[55] + q1[55] * q1[55] + q0[56] * q0[56] + q1[56] * q1[56] + q0[57] * q0[57] + q1[57] * q1[57] + q0[58] * q0[58] + q1[58] * q1[58] + q0[59] * q0[59] + q1[59] * q1[59] + q0[60] * q0[60] + q1[60] * q1[60] + q0[61] * q0[61] + q1[61] * q1[61] + q0[62] * q0[62] + q1[62] * q1[62] + q0[63] * q0[63] + q1[63] * q1[63] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1] + q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3] + q0[4] * q0[4] + q1[4] * q1[4] + q0[5] * q0[5] + q1[5] * q1[5] + q0[6] * q0[6] + q1[6] * q1[6] + q0[7] * q0[7] + q1[7] * q1[7] + q0[8] * q0[8] + q1[8] * q1[8] + q0[9] * q0[9] + q1[9] * q1[9] + q0[10] * q0[10] + q1[10] * q1[10] + q0[11] * q0[11] + q1[11] * q1[11] + q0[12] * q0[12] + q1[12] * q1[12] + q0[13] * q0[13] + q1[13] * q1[13] + q0[14] * q0[14] + q1[14] * q1[14] + q0[15] * q0[15] + q1[15] * q1[15] + q0[16] * q0[16] + q1[16] * q1[16] + q0[17] * q0[17] + q1[17] * q1[17] + q0[18] * q0[18] + q1[18] * q1[18] + q0[19] * q0[19] + q1[19] * q1[19] + q0[20] * q0[20] + q1[20] * q1[20] + q0[21] * q0[21] + q1[21] * q1[21] + q0[22] * q0[22] + q1[22] * q1[22] + q0[23] * q0[23] + q1[23] * q1[23] + q0[24] * q0[24] + q1[24] * q1[24] + q0[25] * q0[25] + q1[25] * q1[25] + q0[26] * q0[26] + q1[26] * q1[26] + q0[27] * q0[27] + q1[27] * q1[27] + q0[28] * q0[28] + q1[28] * q1[28] + q0[29] * q0[29] + q1[29] * q1[29] + q0[30] * q0[30] + q1[30] * q1[30] + q0[31] * q0[31] + q1[31] * q1[31]) {
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q0[32], q0[33], q0[34], q0[35], q0[36], q0[37], q0[38], q0[39], q0[40], q0[41], q0[42], q0[43], q0[44], q0[45], q0[46], q0[47], q0[48], q0[49], q0[50], q0[51], q0[52], q0[53], q0[54], q0[55], q0[56], q0[57], q0[58], q0[59], q0[60], q0[61], q0[62], q0[63]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q1[32], q1[33], q1[34], q1[35], q1[36], q1[37], q1[38], q1[39], q1[40], q1[41], q1[42], q1[43], q1[44], q1[45], q1[46], q1[47], q1[48], q1[49], q1[50], q1[51], q1[52], q1[53], q1[54], q1[55], q1[56], q1[57], q1[58], q1[59], q1[60], q1[61], q1[62], q1[63]};
      $$_tmp_measureVar6 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar6 = false;
    }
    boolean b_0 = $$_tmp_measureVar6;
    assert b_0 != q_test;
  }
    /*@
      requires qstate != null && qstatei != null && qstate.length == 128 && qstatei.length == 128; 
      requires (\exists int i; i >= 0 && i < qstate.length; qstate[i] == 1.0F && (\forall int j; j >= 0 && j < qstate.length; (i != j) ==> qstate[j] == 0.0F)); 
      requires (\forall int i; i >= 0 && i < qstate.length; qstatei[i] == 0.0F); 
   */

  public static void qbits_7(/*@ non_null */ 
  float[] qstate, /*@ non_null */ 
  float[] qstatei) {
    boolean q_test = false;
    for (int i = 64; i < qstate.length; ++i) {
      q_test = q_test || qstate[i] == 1.0F;
    }
    float[] q0 = new float[]{qstate[0], qstate[1], qstate[2], qstate[3], qstate[4], qstate[5], qstate[6], qstate[7], qstate[8], qstate[9], qstate[10], qstate[11], qstate[12], qstate[13], qstate[14], qstate[15], qstate[16], qstate[17], qstate[18], qstate[19], qstate[20], qstate[21], qstate[22], qstate[23], qstate[24], qstate[25], qstate[26], qstate[27], qstate[28], qstate[29], qstate[30], qstate[31], qstate[32], qstate[33], qstate[34], qstate[35], qstate[36], qstate[37], qstate[38], qstate[39], qstate[40], qstate[41], qstate[42], qstate[43], qstate[44], qstate[45], qstate[46], qstate[47], qstate[48], qstate[49], qstate[50], qstate[51], qstate[52], qstate[53], qstate[54], qstate[55], qstate[56], qstate[57], qstate[58], qstate[59], qstate[60], qstate[61], qstate[62], qstate[63], qstate[64], qstate[65], qstate[66], qstate[67], qstate[68], qstate[69], qstate[70], qstate[71], qstate[72], qstate[73], qstate[74], qstate[75], qstate[76], qstate[77], qstate[78], qstate[79], qstate[80], qstate[81], qstate[82], qstate[83], qstate[84], qstate[85], qstate[86], qstate[87], qstate[88], qstate[89], qstate[90], qstate[91], qstate[92], qstate[93], qstate[94], qstate[95], qstate[96], qstate[97], qstate[98], qstate[99], qstate[100], qstate[101], qstate[102], qstate[103], qstate[104], qstate[105], qstate[106], qstate[107], qstate[108], qstate[109], qstate[110], qstate[111], qstate[112], qstate[113], qstate[114], qstate[115], qstate[116], qstate[117], qstate[118], qstate[119], qstate[120], qstate[121], qstate[122], qstate[123], qstate[124], qstate[125], qstate[126], qstate[127]};
    float[] q1 = new float[]{qstatei[0], qstatei[1], qstatei[2], qstatei[3], qstatei[4], qstatei[5], qstatei[6], qstatei[7], qstatei[8], qstatei[9], qstatei[10], qstatei[11], qstatei[12], qstatei[13], qstatei[14], qstatei[15], qstatei[16], qstatei[17], qstatei[18], qstatei[19], qstatei[20], qstatei[21], qstatei[22], qstatei[23], qstatei[24], qstatei[25], qstatei[26], qstatei[27], qstatei[28], qstatei[29], qstatei[30], qstatei[31], qstatei[32], qstatei[33], qstatei[34], qstatei[35], qstatei[36], qstatei[37], qstatei[38], qstatei[39], qstatei[40], qstatei[41], qstatei[42], qstatei[43], qstatei[44], qstatei[45], qstatei[46], qstatei[47], qstatei[48], qstatei[49], qstatei[50], qstatei[51], qstatei[52], qstatei[53], qstatei[54], qstatei[55], qstatei[56], qstatei[57], qstatei[58], qstatei[59], qstatei[60], qstatei[61], qstatei[62], qstatei[63], qstatei[64], qstatei[65], qstatei[66], qstatei[67], qstatei[68], qstatei[69], qstatei[70], qstatei[71], qstatei[72], qstatei[73], qstatei[74], qstatei[75], qstatei[76], qstatei[77], qstatei[78], qstatei[79], qstatei[80], qstatei[81], qstatei[82], qstatei[83], qstatei[84], qstatei[85], qstatei[86], qstatei[87], qstatei[88], qstatei[89], qstatei[90], qstatei[91], qstatei[92], qstatei[93], qstatei[94], qstatei[95], qstatei[96], qstatei[97], qstatei[98], qstatei[99], qstatei[100], qstatei[101], qstatei[102], qstatei[103], qstatei[104], qstatei[105], qstatei[106], qstatei[107], qstatei[108], qstatei[109], qstatei[110], qstatei[111], qstatei[112], qstatei[113], qstatei[114], qstatei[115], qstatei[116], qstatei[117], qstatei[118], qstatei[119], qstatei[120], qstatei[121], qstatei[122], qstatei[123], qstatei[124], qstatei[125], qstatei[126], qstatei[127]};
    q0 = new float[]{q0[64], q0[65], q0[66], q0[67], q0[68], q0[69], q0[70], q0[71], q0[72], q0[73], q0[74], q0[75], q0[76], q0[77], q0[78], q0[79], q0[80], q0[81], q0[82], q0[83], q0[84], q0[85], q0[86], q0[87], q0[88], q0[89], q0[90], q0[91], q0[92], q0[93], q0[94], q0[95], q0[96], q0[97], q0[98], q0[99], q0[100], q0[101], q0[102], q0[103], q0[104], q0[105], q0[106], q0[107], q0[108], q0[109], q0[110], q0[111], q0[112], q0[113], q0[114], q0[115], q0[116], q0[117], q0[118], q0[119], q0[120], q0[121], q0[122], q0[123], q0[124], q0[125], q0[126], q0[127], q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31], q0[32], q0[33], q0[34], q0[35], q0[36], q0[37], q0[38], q0[39], q0[40], q0[41], q0[42], q0[43], q0[44], q0[45], q0[46], q0[47], q0[48], q0[49], q0[50], q0[51], q0[52], q0[53], q0[54], q0[55], q0[56], q0[57], q0[58], q0[59], q0[60], q0[61], q0[62], q0[63]};
    q1 = new float[]{q1[64], q1[65], q1[66], q1[67], q1[68], q1[69], q1[70], q1[71], q1[72], q1[73], q1[74], q1[75], q1[76], q1[77], q1[78], q1[79], q1[80], q1[81], q1[82], q1[83], q1[84], q1[85], q1[86], q1[87], q1[88], q1[89], q1[90], q1[91], q1[92], q1[93], q1[94], q1[95], q1[96], q1[97], q1[98], q1[99], q1[100], q1[101], q1[102], q1[103], q1[104], q1[105], q1[106], q1[107], q1[108], q1[109], q1[110], q1[111], q1[112], q1[113], q1[114], q1[115], q1[116], q1[117], q1[118], q1[119], q1[120], q1[121], q1[122], q1[123], q1[124], q1[125], q1[126], q1[127], q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31], q1[32], q1[33], q1[34], q1[35], q1[36], q1[37], q1[38], q1[39], q1[40], q1[41], q1[42], q1[43], q1[44], q1[45], q1[46], q1[47], q1[48], q1[49], q1[50], q1[51], q1[52], q1[53], q1[54], q1[55], q1[56], q1[57], q1[58], q1[59], q1[60], q1[61], q1[62], q1[63]};
    boolean $$_tmp_measureVar7;
    if (q0[64] * q0[64] + q1[64] * q1[64] + q0[65] * q0[65] + q1[65] * q1[65] + q0[66] * q0[66] + q1[66] * q1[66] + q0[67] * q0[67] + q1[67] * q1[67] + q0[68] * q0[68] + q1[68] * q1[68] + q0[69] * q0[69] + q1[69] * q1[69] + q0[70] * q0[70] + q1[70] * q1[70] + q0[71] * q0[71] + q1[71] * q1[71] + q0[72] * q0[72] + q1[72] * q1[72] + q0[73] * q0[73] + q1[73] * q1[73] + q0[74] * q0[74] + q1[74] * q1[74] + q0[75] * q0[75] + q1[75] * q1[75] + q0[76] * q0[76] + q1[76] * q1[76] + q0[77] * q0[77] + q1[77] * q1[77] + q0[78] * q0[78] + q1[78] * q1[78] + q0[79] * q0[79] + q1[79] * q1[79] + q0[80] * q0[80] + q1[80] * q1[80] + q0[81] * q0[81] + q1[81] * q1[81] + q0[82] * q0[82] + q1[82] * q1[82] + q0[83] * q0[83] + q1[83] * q1[83] + q0[84] * q0[84] + q1[84] * q1[84] + q0[85] * q0[85] + q1[85] * q1[85] + q0[86] * q0[86] + q1[86] * q1[86] + q0[87] * q0[87] + q1[87] * q1[87] + q0[88] * q0[88] + q1[88] * q1[88] + q0[89] * q0[89] + q1[89] * q1[89] + q0[90] * q0[90] + q1[90] * q1[90] + q0[91] * q0[91] + q1[91] * q1[91] + q0[92] * q0[92] + q1[92] * q1[92] + q0[93] * q0[93] + q1[93] * q1[93] + q0[94] * q0[94] + q1[94] * q1[94] + q0[95] * q0[95] + q1[95] * q1[95] + q0[96] * q0[96] + q1[96] * q1[96] + q0[97] * q0[97] + q1[97] * q1[97] + q0[98] * q0[98] + q1[98] * q1[98] + q0[99] * q0[99] + q1[99] * q1[99] + q0[100] * q0[100] + q1[100] * q1[100] + q0[101] * q0[101] + q1[101] * q1[101] + q0[102] * q0[102] + q1[102] * q1[102] + q0[103] * q0[103] + q1[103] * q1[103] + q0[104] * q0[104] + q1[104] * q1[104] + q0[105] * q0[105] + q1[105] * q1[105] + q0[106] * q0[106] + q1[106] * q1[106] + q0[107] * q0[107] + q1[107] * q1[107] + q0[108] * q0[108] + q1[108] * q1[108] + q0[109] * q0[109] + q1[109] * q1[109] + q0[110] * q0[110] + q1[110] * q1[110] + q0[111] * q0[111] + q1[111] * q1[111] + q0[112] * q0[112] + q1[112] * q1[112] + q0[113] * q0[113] + q1[113] * q1[113] + q0[114] * q0[114] + q1[114] * q1[114] + q0[115] * q0[115] + q1[115] * q1[115] + q0[116] * q0[116] + q1[116] * q1[116] + q0[117] * q0[117] + q1[117] * q1[117] + q0[118] * q0[118] + q1[118] * q1[118] + q0[119] * q0[119] + q1[119] * q1[119] + q0[120] * q0[120] + q1[120] * q1[120] + q0[121] * q0[121] + q1[121] * q1[121] + q0[122] * q0[122] + q1[122] * q1[122] + q0[123] * q0[123] + q1[123] * q1[123] + q0[124] * q0[124] + q1[124] * q1[124] + q0[125] * q0[125] + q1[125] * q1[125] + q0[126] * q0[126] + q1[126] * q1[126] + q0[127] * q0[127] + q1[127] * q1[127] > q0[0] * q0[0] + q1[0] * q1[0] + q0[1] * q0[1] + q1[1] * q1[1] + q0[2] * q0[2] + q1[2] * q1[2] + q0[3] * q0[3] + q1[3] * q1[3] + q0[4] * q0[4] + q1[4] * q1[4] + q0[5] * q0[5] + q1[5] * q1[5] + q0[6] * q0[6] + q1[6] * q1[6] + q0[7] * q0[7] + q1[7] * q1[7] + q0[8] * q0[8] + q1[8] * q1[8] + q0[9] * q0[9] + q1[9] * q1[9] + q0[10] * q0[10] + q1[10] * q1[10] + q0[11] * q0[11] + q1[11] * q1[11] + q0[12] * q0[12] + q1[12] * q1[12] + q0[13] * q0[13] + q1[13] * q1[13] + q0[14] * q0[14] + q1[14] * q1[14] + q0[15] * q0[15] + q1[15] * q1[15] + q0[16] * q0[16] + q1[16] * q1[16] + q0[17] * q0[17] + q1[17] * q1[17] + q0[18] * q0[18] + q1[18] * q1[18] + q0[19] * q0[19] + q1[19] * q1[19] + q0[20] * q0[20] + q1[20] * q1[20] + q0[21] * q0[21] + q1[21] * q1[21] + q0[22] * q0[22] + q1[22] * q1[22] + q0[23] * q0[23] + q1[23] * q1[23] + q0[24] * q0[24] + q1[24] * q1[24] + q0[25] * q0[25] + q1[25] * q1[25] + q0[26] * q0[26] + q1[26] * q1[26] + q0[27] * q0[27] + q1[27] * q1[27] + q0[28] * q0[28] + q1[28] * q1[28] + q0[29] * q0[29] + q1[29] * q1[29] + q0[30] * q0[30] + q1[30] * q1[30] + q0[31] * q0[31] + q1[31] * q1[31] + q0[32] * q0[32] + q1[32] * q1[32] + q0[33] * q0[33] + q1[33] * q1[33] + q0[34] * q0[34] + q1[34] * q1[34] + q0[35] * q0[35] + q1[35] * q1[35] + q0[36] * q0[36] + q1[36] * q1[36] + q0[37] * q0[37] + q1[37] * q1[37] + q0[38] * q0[38] + q1[38] * q1[38] + q0[39] * q0[39] + q1[39] * q1[39] + q0[40] * q0[40] + q1[40] * q1[40] + q0[41] * q0[41] + q1[41] * q1[41] + q0[42] * q0[42] + q1[42] * q1[42] + q0[43] * q0[43] + q1[43] * q1[43] + q0[44] * q0[44] + q1[44] * q1[44] + q0[45] * q0[45] + q1[45] * q1[45] + q0[46] * q0[46] + q1[46] * q1[46] + q0[47] * q0[47] + q1[47] * q1[47] + q0[48] * q0[48] + q1[48] * q1[48] + q0[49] * q0[49] + q1[49] * q1[49] + q0[50] * q0[50] + q1[50] * q1[50] + q0[51] * q0[51] + q1[51] * q1[51] + q0[52] * q0[52] + q1[52] * q1[52] + q0[53] * q0[53] + q1[53] * q1[53] + q0[54] * q0[54] + q1[54] * q1[54] + q0[55] * q0[55] + q1[55] * q1[55] + q0[56] * q0[56] + q1[56] * q1[56] + q0[57] * q0[57] + q1[57] * q1[57] + q0[58] * q0[58] + q1[58] * q1[58] + q0[59] * q0[59] + q1[59] * q1[59] + q0[60] * q0[60] + q1[60] * q1[60] + q0[61] * q0[61] + q1[61] * q1[61] + q0[62] * q0[62] + q1[62] * q1[62] + q0[63] * q0[63] + q1[63] * q1[63]) {
      q0 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q0[64], q0[65], q0[66], q0[67], q0[68], q0[69], q0[70], q0[71], q0[72], q0[73], q0[74], q0[75], q0[76], q0[77], q0[78], q0[79], q0[80], q0[81], q0[82], q0[83], q0[84], q0[85], q0[86], q0[87], q0[88], q0[89], q0[90], q0[91], q0[92], q0[93], q0[94], q0[95], q0[96], q0[97], q0[98], q0[99], q0[100], q0[101], q0[102], q0[103], q0[104], q0[105], q0[106], q0[107], q0[108], q0[109], q0[110], q0[111], q0[112], q0[113], q0[114], q0[115], q0[116], q0[117], q0[118], q0[119], q0[120], q0[121], q0[122], q0[123], q0[124], q0[125], q0[126], q0[127]};
      q1 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, q1[64], q1[65], q1[66], q1[67], q1[68], q1[69], q1[70], q1[71], q1[72], q1[73], q1[74], q1[75], q1[76], q1[77], q1[78], q1[79], q1[80], q1[81], q1[82], q1[83], q1[84], q1[85], q1[86], q1[87], q1[88], q1[89], q1[90], q1[91], q1[92], q1[93], q1[94], q1[95], q1[96], q1[97], q1[98], q1[99], q1[100], q1[101], q1[102], q1[103], q1[104], q1[105], q1[106], q1[107], q1[108], q1[109], q1[110], q1[111], q1[112], q1[113], q1[114], q1[115], q1[116], q1[117], q1[118], q1[119], q1[120], q1[121], q1[122], q1[123], q1[124], q1[125], q1[126], q1[127]};
      $$_tmp_measureVar7 = true;
    } else {
      q0 = new float[]{q0[0], q0[1], q0[2], q0[3], q0[4], q0[5], q0[6], q0[7], q0[8], q0[9], q0[10], q0[11], q0[12], q0[13], q0[14], q0[15], q0[16], q0[17], q0[18], q0[19], q0[20], q0[21], q0[22], q0[23], q0[24], q0[25], q0[26], q0[27], q0[28], q0[29], q0[30], q0[31], q0[32], q0[33], q0[34], q0[35], q0[36], q0[37], q0[38], q0[39], q0[40], q0[41], q0[42], q0[43], q0[44], q0[45], q0[46], q0[47], q0[48], q0[49], q0[50], q0[51], q0[52], q0[53], q0[54], q0[55], q0[56], q0[57], q0[58], q0[59], q0[60], q0[61], q0[62], q0[63], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      q1 = new float[]{q1[0], q1[1], q1[2], q1[3], q1[4], q1[5], q1[6], q1[7], q1[8], q1[9], q1[10], q1[11], q1[12], q1[13], q1[14], q1[15], q1[16], q1[17], q1[18], q1[19], q1[20], q1[21], q1[22], q1[23], q1[24], q1[25], q1[26], q1[27], q1[28], q1[29], q1[30], q1[31], q1[32], q1[33], q1[34], q1[35], q1[36], q1[37], q1[38], q1[39], q1[40], q1[41], q1[42], q1[43], q1[44], q1[45], q1[46], q1[47], q1[48], q1[49], q1[50], q1[51], q1[52], q1[53], q1[54], q1[55], q1[56], q1[57], q1[58], q1[59], q1[60], q1[61], q1[62], q1[63], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
      $$_tmp_measureVar7 = false;
    }
    boolean b_0 = $$_tmp_measureVar7;
    assert b_0 != q_test;
  }
}