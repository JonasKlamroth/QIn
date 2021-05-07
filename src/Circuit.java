public class Circuit {
    private final double h = 1.41421356237;
    private final double[][] H = new double[][]{{h, h},
                                                {h, -h}};
    private final double[][] ID = new double[][]{{1.0, 0.0},
                                                 {0.0, 1.0}};
    private final double[][] CX = new double[][]{{1.0, 0.0, 0.0, 0.0},
                                                {0.0, 1.0, 0.0, 0.0},
                                                {0.0, 0.0, 0.0, 1.0},
                                                {0.0, 0.0, 1.0, 0.0}};
    private StringBuilder sb;
    private int numQbits;
    private int stateSize;
    private int numStates = 0;
    private double[][] currentState;

    public Circuit(int numQbits) {
        this.numQbits = numQbits;
        this.stateSize = (int)Math.pow(2, numQbits);

        sb = new StringBuilder();

        currentState = newState();
    }

    private double[][] newState() {
        double[][] res = new double[stateSize][1];
        res[0][0] = 1.0;
        return res;
    }

    private String zeroState() {
        return "{0.0 " + (", 0.0".repeat(stateSize - 1)) + "}";
    }

    public void h(int qbit) {
        double[][] m = adapt(H, qbit);
        currentState = mult(m, currentState);
    }

    public void cnot(int cqbit, int tqbit) {
        assert cqbit == tqbit - 1;
        double[][] m = adapt(CX, cqbit, tqbit);
        currentState = mult(m, currentState);
    }

    private double[][] adapt(double[][] m, int qbit) {
        double res[][] = ID;
        if(qbit == 0) {
            res = m;
        }
        for(int i = 1; i < numQbits; ++i) {
            if(i != qbit) {
                res = tensorProd(res, ID);
            } else {
                res = tensorProd(res, m);
            }
        }
        return res;
    }

    public static double[][] mult(double a[][], double b[][]){//a[m][n], b[n][p]
        assert a.length != 0;
        assert (a[0].length != b.length);

        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;

        double ans[][] = new double[m][p];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < p;j++){
                for(int k = 0;k < n;k++){
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    private double[][] adapt(double[][] m, int qbit, int qbit2) {
        assert qbit == qbit2 - 1;
        double res[][] = ID;
        int i = 1;
        if(qbit == 0) {
            res = m;
            i = 2;
        }
        for(; i < numQbits; ++i) {
            if(i != qbit) {
                res = tensorProd(res, ID);
            } else {
                res = tensorProd(res, m);
                i++;
            }
        }
        return res;
    }

    private double[][] tensorProd(double[][] a, double[][] b) {
        // Taken from: https://rosettacode.org/wiki/Kronecker_product#Java
        // Create matrix c as the matrix to fill and return.
        // The length of a matrix is its number of rows.
        final double[][] c = new double[a.length*b.length][];
        // Fill in the (empty) rows of c.
        // The length of each row is the number of columns.
        for (int ix = 0; ix < c.length; ix++) {
            final int num_cols = a[0].length*b[0].length;
            c[ix] = new double[num_cols];
        }
        // Now fill in the values: the products of each pair.
        // Go through all the elements of a.
        for (int ia = 0; ia < a.length; ia++) {
            for (int ja = 0; ja < a[ia].length; ja++) {
                // For each element of a, multiply it by all the elements of b.
                for (int ib = 0; ib < b.length; ib++) {
                    for (int jb = 0; jb < b[ib].length; jb++) {
                        c[b.length*ia+ib][b[ib].length*ja+jb] = a[ia][ja] * b[ib][jb];
                    }
                }
            }
        }

        // Return the completed product matrix c.
        return c;
    }

    private String matrixToString(double[][] res) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i < res.length; ++i) {
            for(int j = 0; j < res[0].length; ++j) {
                sb.append(" ");
                sb.append(res[i][j]);
                if (res.length - 1 != i || j != res[0].length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public String getRes() {
        return matrixToString(currentState);
    }
}
