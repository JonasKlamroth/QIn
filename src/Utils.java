import Expressions.AddOp;
import Expressions.Const;
import Expressions.Expr;
import Expressions.MultOp;

public class Utils {
    public static final float h = 0.70710678118f;
    public static final Expr[][] H = new Expr[][]{{new Const(h), new Const(h)},
            {new Const(h), new Const(-h)}};
    public static final Expr[][] ID = new Expr[][]{{new Const(1.0f), new Const(0.0f)},
            {new Const(0.0f), new Const(1.0f)}};
    public static final Expr[][] X = new Expr[][]{{new Const(0.0f), new Const(1.0f)},
            {new Const(1.0f), new Const(0.0f)}};
    public static final Expr[][] Z = new Expr[][]{{new Const(1.0f), new Const(0.0f)},
            {new Const(0.0f), new Const(-1.0f)}};
    public static final Expr[][] CX = new Expr[][]{{new Const(1.0f), new Const(0.0f), new Const(0.0f), new Const(0.0f)},
            {new Const(0.0f), new Const(1.0f), new Const(0.0f), new Const(0.0f)},
            {new Const(0.0f), new Const(0.0f), new Const(0.0f), new Const(1.0f)},
            {new Const(0.0f), new Const(0.0f), new Const(1.0f), new Const(0.0f)}};

    public static Expr[][] mult(Expr[][] a, Expr[][] b){//a[m][n], b[n][p]
        assert a.length != 0;
        assert (a[0].length == b.length);

        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;

        Expr[][] ans = new Expr[m][p];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < p;j++){
                for(int k = 0;k < n;k++){
                    if(ans[i][j] == null) {
                        ans[i][j] = new Const(0.0f);
                    }
                    ans[i][j] = new AddOp(ans[i][j], new MultOp(a[i][k], b[k][j]));
                }
            }
        }
        return ans;
    }

    public static int log2(int N) {
        return (int)(Math.log(N) / Math.log(2));
    }

    public static Expr[][] adapt(Expr[][] m, int qbit, int numQbits) {
        Expr[][] res = ID;
        int matrixSize = log2(m.length);
        assert (float)matrixSize == log2(m.length);
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

    public static Expr[][] tensorProd(Expr[][] a, Expr[][] b) {
        // Taken from: https://rosettacode.org/wiki/Kronecker_product#Java
        // Create matrix c as the matrix to fill and return.
        // The length of a matrix is its number of rows.
        final Expr[][] c = new Expr[a.length*b.length][];
        // Fill in the (empty) rows of c.
        // The length of each row is the number of columns.
        for (int ix = 0; ix < c.length; ix++) {
            final int num_cols = a[0].length*b[0].length;
            c[ix] = new Expr[num_cols];
        }
        // Now fill in the values: the products of each pair.
        // Go through all the elements of a.
        for (int ia = 0; ia < a.length; ia++) {
            for (int ja = 0; ja < a[ia].length; ja++) {
                // For each element of a, multiply it by all the elements of b.
                for (int ib = 0; ib < b.length; ib++) {
                    for (int jb = 0; jb < b[ib].length; jb++) {
                        c[b.length*ia+ib][b[ib].length*ja+jb] = new MultOp(a[ia][ja], b[ib][jb]);
                    }
                }
            }
        }

        // Return the completed product matrix c.
        return c;
    }
}
