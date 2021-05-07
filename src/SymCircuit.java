import java.util.Arrays;

public class SymCircuit {
    private final String h = "1.41421356237";
    private final String[][] H = new String[][]{{h, h},
            {h, "-" + h}};
    private final String[][] ID = new String[][]{{"1.0", "0.0"},
            {"0.0", "1.0"}};
    private final String[][] X = new String[][]{{"0.0", "1.0"},
            {"1.0", "0.0"}};
    private final String[][] CX = new String[][]{{"1.0", "0.0", "0.0", "0.0"},
            {"0.0", "1.0", "0.0", "0.0"},
            {"0.0", "0.0", "0.0", "1.0"},
            {"0.0", "0.0", "1.0", "0.0"}};
    private StringBuilder sb;
    private int numQbits;
    private int stateSize;
    private int numStates = 0;
    private String[][] currentState;

    public SymCircuit(int numQbits) {
        this.numQbits = numQbits;
        this.stateSize = (int)Math.pow(2, numQbits);

        sb = new StringBuilder();

        currentState = newState();
    }

    private String[][] newState() {
        String[][] res = new String[stateSize][1];
        res[0][0] = "1.0";
        for(int i = 1; i < stateSize; ++i) {
            res[i][0] = "0.0";
        }
        return res;
    }

    public void h(int qbit) {
        String[][] m = adapt(H, qbit);
        currentState = mult(m, currentState);
    }
    public void x(int qbit) {
        String[][] m = adapt(X, qbit);
        currentState = mult(m, currentState);
    }

    public void cnot(int cqbit, int tqbit) {
        assert cqbit == tqbit - 1;
        String[][] m = adapt(CX, cqbit);
        currentState = mult(m, currentState);
    }

    public void u(double[][] m, int... qbits) {
        assert m.length > 1 && m.length < stateSize;
        assert m.length == m[0].length;
        assert Math.pow(2, qbits.length) == m.length;
        for(int i = 1; i < qbits.length; ++i) {
            assert qbits[i] == qbits[i - 1] + 1;
        }
        String[][] m1 = adapt(CX, qbits[0]);
        currentState = mult(m1, currentState);
    }

    public static String[][] mult(String[][] a, String[][] b){//a[m][n], b[n][p]
        assert a.length != 0;
        assert (a[0].length != b.length);

        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;

        String[][] ans = new String[m][p];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < p;j++){
                for(int k = 0;k < n;k++){
                    if(ans[i][j] == null) {
                        ans[i][j] = "0.0";
                    }
                    ans[i][j] = "(" + ans[i][j] + ") + (" + a[i][k] + ") * (" + b[k][j] + ")";
                }
            }
        }
        return ans;
    }

    private String[][] adapt(String[][] m, int qbit) {
        String[][] res = ID;
        int matrixSize = (int)Math.log(m.length);
        assert (double)matrixSize == Math.log(m.length);
        int i = 1;
        if(qbit == 0) {
            res = m;
            i = matrixSize;
        }
        for(; i < numQbits; ++i) {
            if(i != qbit) {
                res = tensorProd(res, ID);
            } else {
                res = tensorProd(res, m);
                i += matrixSize - 1;
            }
        }
        return res;
    }

    private String[][] tensorProd(String[][] a, String[][] b) {
        // Taken from: https://rosettacode.org/wiki/Kronecker_product#Java
        // Create matrix c as the matrix to fill and return.
        // The length of a matrix is its number of rows.
        final String[][] c = new String[a.length*b.length][];
        // Fill in the (empty) rows of c.
        // The length of each row is the number of columns.
        for (int ix = 0; ix < c.length; ix++) {
            final int num_cols = a[0].length*b[0].length;
            c[ix] = new String[num_cols];
        }
        // Now fill in the values: the products of each pair.
        // Go through all the elements of a.
        for (int ia = 0; ia < a.length; ia++) {
            for (int ja = 0; ja < a[ia].length; ja++) {
                // For each element of a, multiply it by all the elements of b.
                for (int ib = 0; ib < b.length; ib++) {
                    for (int jb = 0; jb < b[ib].length; jb++) {
                        c[b.length*ia+ib][b[ib].length*ja+jb] = "(" + a[ia][ja] + ") * (" + b[ib][jb] + ")";
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
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String[] strings : currentState) {
            assert strings.length == 1;
            sb.append(strings[0]);
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
        //return currentState;
    }
}
