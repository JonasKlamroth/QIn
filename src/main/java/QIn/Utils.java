package QIn;

import QIn.Expressions.*;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Position;

import java.util.Comparator;

public class Utils {
    public static final float h = 0.70710678118f;
    public static final float[][] H = new float[][]{{h, h},
            {h, -h}};

    public static final float[][] HH = new float[][]{{ 0.5f, 0.5f, 0.5f, 0.5f},
        {0.5f,-0.5f, 0.5f,-0.5f},
        {0.5f, 0.5f,-0.5f,-0.5f},
        { 0.5f,-0.5f,-0.5f, 0.5f}};

    public static final float[][] HHH = new float[][]{{ 0.35355339f,  0.35355339f,  0.35355339f,  0.35355339f,  0.35355339f,  0.35355339f, 0.35355339f,  0.35355339f},
            {0.35355339f, -0.35355339f,  0.35355339f, -0.35355339f,  0.35355339f, -0.35355339f,   0.35355339f, -0.35355339f},
            {0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f,  0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f},
        {0.35355339f, -0.35355339f, -0.35355339f,  0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f,  0.35355339f},
            {0.35355339f,  0.35355339f,  0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f,  -0.35355339f, -0.35355339f},
            {0.35355339f, -0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f,  0.35355339f, -0.35355339f,  0.35355339f},
            {0.35355339f,  0.35355339f, -0.35355339f, -0.35355339f, -0.35355339f, -0.35355339f, 0.35355339f,  0.35355339f},
        {0.35355339f, -0.35355339f, -0.35355339f,  0.35355339f, -0.35355339f,  0.35355339f,   0.35355339f, -0.35355339f}};

    public static final float[][] ID = new float[][]{{1.0f, 0.0f},
            {0.0f, 1.0f}};
    public static final float[][] X = new float[][]{{0.0f, 1.0f},
            {1.0f, 0.0f}};
    public static final float[][] Z = new float[][]{{1.0f, 0.0f},
            {0.0f, -1.0f}};
    public static final float[][] CX = new float[][]{{1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 1.0f},
            {0.0f, 0.0f, 1.0f, 0.0f}};
    public static final float[][] CZ = new float[][]{{1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, -1.0f}};
    public static final float[][] CSWAP = new float[][]{
            {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}};
    public static final float[][] CCX = new float[][]{
            {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}};
    public static final float[][] CCZ = new float[][]{
            {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f}};

    public static final float[][] CCCX = new float[][]{
            {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}};


    public static Expr[][] getExprMatrix(float[][] m) {
        Expr[][] res = new Expr[m.length][m[0].length];
        for(int i = 0; i < m.length; ++i) {
            for(int j = 0; j < m[0].length; ++j) {
                res[i][j] = getConst(m[i][j]);
            }
        }
        return res;
    }

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
                        ans[i][j] = Utils.getConst(0.0f);
                    }
                    ans[i][j] = ans[i][j].add(a[i][k].mult(b[k][j]));
                }
            }
        }
        return ans;
    }

    public static int log2(int N) {
        return (int)(Math.log(N) / Math.log(2));
    }

    public static Expr[][] adapt(Expr[][] m, int qbit, int numQbits) {
        Expr[][] res = getExprMatrix(ID);
        int matrixSize = log2(m.length);
        assert (float)matrixSize == log2(m.length);
        if(matrixSize == numQbits) {
            return m;
        }
        int i = 1;
        if(qbit == 0) {
            res = m;
            i = matrixSize;
        }
        for(; i < numQbits; ++i) {
            if(i != qbit) {
                res = tensorProd(res, getExprMatrix(ID));
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
                        c[b.length*ia+ib][b[ib].length*ja+jb] = a[ia][ja].mult(b[ib][jb]);
                    }
                }
            }
        }

        // Return the completed product matrix c.
        return c;
    }

    public static Expr[][] getInitialSymbState(int numQbits, JCTree.JCIdent state) {
        List<Expr[][]> initialStates = List.nil();
        for(int i = 0; i < numQbits; ++i) {
            initialStates.add(new Expr[][]{
                    new Expr[]{
                            new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS,
                                    TransUtils.treeutils.makeArrayElement(Position.NOPOS, state, TransUtils.M.Literal(i)),
                                    TransUtils.M.Literal(0)))},
                    new Expr[]{
                            new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS,
                                    TransUtils.treeutils.makeArrayElement(Position.NOPOS, state, TransUtils.M.Literal(i)),
                                    TransUtils.M.Literal(1)))}
            });
        }
        Expr[][] finalState = initialStates.get(0);
        for(int i = 1; i < initialStates.size(); ++i) {
            finalState = tensorProd(finalState, initialStates.get(i));
        }
        return finalState;
    }

    public static Expr[][] getInitialState(int numQbits) {
        int stateSize = (int)Math.pow(2, numQbits);
        Expr[][] initialState = new Expr[stateSize][1];
        for(int i = 0; i < stateSize; ++i) {
            if(CLI.useReals) {
                initialState[i] = new Expr[]{Utils.getConst(0.0f)};
            } else {
                initialState[i] = new Expr[]{new ComplexExpression(Utils.getRealConst(0.0f), Utils.getRealConst(0.0f))};
            }
        }
        if(CLI.useReals) {
            initialState[0][0] = Utils.getConst(1.0f);
        } else {
            initialState[0][0] = new ComplexExpression(Utils.getRealConst(1.0f), Utils.getRealConst(0.0f));
        }
        return initialState;
    }

    public static Expr[][] getUnitaryForName(String name) {
        return getUnitaryForName(name, null);
    }

    public static Expr[][] getUnitaryForName(String name, Object theta) {
        if(name.equals("x")) {
            return getExprMatrix(X);
        }
        if(name.equals("hh")) {
            return getExprMatrix(HH);
        }
        if(name.equals("hhh")) {
            return getExprMatrix(HHH);
        }
        if(name.equals("h")) {
            return getExprMatrix(H);
        }
        if(name.equals("cx")) {
            return getExprMatrix(CX);
        }
        if(name.equals("cz")) {
            return getExprMatrix(CZ);
        }
        if(name.equals("z")) {
            return getExprMatrix(Z);
        }
        if(name.equals("ccx")) {
            return getExprMatrix(CCX);
        }
        if(name.equals("ccz")) {
            return getExprMatrix(CCZ);
        }
        if(name.equals("cswap")) {
            return getExprMatrix(CSWAP);
        }
        if(name.equals("cccx")) {
            return getExprMatrix(CCCX);
        }
        if(name.equals("rx")) {
            try {
                double ftheta = ScriptEngineWrapper.getInstance().eval(theta.toString());
                return getRZGate(ftheta);
            } catch (NumberFormatException e) {
                throw new RuntimeException("The parameter theta of an RX-Gate has to have type float");
            }
        }
        if(name.equals("rz")) {
            try {
                double ftheta = ScriptEngineWrapper.getInstance().eval(theta.toString());
                return getRZGate(ftheta);
            } catch (NumberFormatException e) {
                throw new RuntimeException("The parameter theta of an RZ-Gate has to have type float");
            }
        }
        if(name.equals("ry")) {
            try {
                double ftheta = ScriptEngineWrapper.getInstance().eval(theta.toString());
                return getRYGate(ftheta);
            } catch (NumberFormatException e) {
                throw new RuntimeException("The parameter theta of an RZ-Gate has to have type float");
            }
        }
        if(name.equals("cp")) {
            double ftheta = ScriptEngineWrapper.getInstance().eval(theta.toString());
            return getCPGate(ftheta);
        }
        throw new AssertionError("unsupported unitary " + name);
    }

    private static Expr[][] getCPGate(Object arg) {
        if(!(arg instanceof Float) && !(arg instanceof Double)) {
            throw new RuntimeException("Parameter theta of cp gate has to be float.");
        }
        double theta = 0.0;
        if(arg instanceof Float) {
            theta = ((Float) arg).doubleValue();
        }
        if(arg instanceof Double) {
            theta = (Double) arg;
        }
        float[][] real = new float[][]{
                new float[]{1.0f, 0.0f, 0.0f, 0.f},
                new float[]{0.0f, 1.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 1.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, (float)Math.cos(theta)}
        };
        float[][] img = new float[][]{
                new float[]{0.0f, 0.0f, 0.0f, 0.f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, 0.0f},
                new float[]{0.0f, 0.0f, 0.0f, (float)(Math.sin(theta))}
        };
        return getExprMatrix(real, img);
    }


    public static Expr[][] getRXGate(Object arg) {
        if(!(arg instanceof Float) && !(arg instanceof Double)) {
            throw new RuntimeException("Parameter theta of rx gate has to be float.");
        }
        double theta = 0.0;
        if(arg instanceof Float) {
            theta = ((Float) arg).doubleValue();
        }
        if(arg instanceof Double) {
            theta = (Double) arg;
        }
        float[][] real = new float[][]{
                new float[]{(float)Math.cos(theta/2.0), 0.0f},
                new float[]{0.0f, (float)Math.cos(theta/2.0)}
        };
        float[][] img = new float[][]{
                new float[]{0.0f, -(float)Math.sin(theta/2.0)},
                new float[]{-(float)Math.sin(theta/2.0), 0.0f}
        };
        return getExprMatrix(real, img);
    }

    public static Expr[][] getRYGate(Object arg) {
        if(!(arg instanceof Float) && !(arg instanceof Double)) {
            throw new RuntimeException("Parameter theta of rx gate has to be float.");
        }
        double theta = 0.0;
        if(arg instanceof Float) {
            theta = ((Float) arg).doubleValue();
        }
        if(arg instanceof Double) {
            theta = (Double) arg;
        }
        float[][] real = new float[][]{
                new float[]{(float)Math.cos(theta/2.0), -(float)Math.sin(theta/2.0)},
                new float[]{(float)Math.sin(theta/2.0), (float)Math.cos(theta/2.0)}
        };
        float[][] img = new float[][]{
                new float[]{0.0f, 0.0f},
                new float[]{0.0f, 0.0f}
        };
        return getExprMatrix(real, img);
    }

    public static Expr[][] getRZGate(Object arg) {
        if(!(arg instanceof Float) && !(arg instanceof Double)) {
            throw new RuntimeException("Parameter theta of rx gate has to be float.");
        }
        double theta = 0.0;
        if(arg instanceof Float) {
            theta = ((Float) arg).doubleValue();
        }
        if(arg instanceof Double) {
            theta = (Double) arg;
        }
        float[][] real = new float[][]{
                new float[]{(float)Math.cos(theta/2.0), 0.0f},
                new float[]{0.0f, (float)Math.cos(theta/2.0)}
        };
        float[][] img = new float[][]{
                new float[]{-(float)Math.sin(theta/2.0), 0.0f},
                new float[]{0.0f, (float)Math.sin(theta/2.0)}
        };
        return getExprMatrix(real, img);
    }

    private static Expr[][] getExprMatrix(float[][] real, float[][] img) {
        Expr[][] res = new Expr[real.length][real[0].length];
        for(int i = 0; i < real.length; ++i) {
            for(int j = 0; j < real[0].length; ++j) {
                res[i][j] = new ComplexExpression(getRealConst(real[i][j]), getRealConst(img[i][j]));
            }
        }
        return res;
    }

    public static Expr[][] apply(Expr[][] u, int qBit, Expr[][] state) {
        int numQbits = log2(state.length);
        u = adapt(u, qBit, numQbits);
        return mult(u, state);
    }

    public static Expr[][] applyMeasurement(Expr[][] qState, int qBit, boolean result) {
        Expr[][] res = new Expr[qState.length][qState[0].length];
        int stateSize = qState.length;
        int numQbits = log2(qState.length);
        int shift = numQbits - qBit - 1;
        for(int j = 0; j < stateSize; ++j) {
            if((j & (1 << shift)) == 0) {
                if(result) {
                    res[j][0] = qState[j][0];
                } else {
                    res[j][0] = Utils.getConst(0.0f);
                }
            } else {
                if(!result) {
                    res[j][0] = qState[j][0];
                } else {
                    res[j][0] = Utils.getConst(0.0f);
                }
            }
        }
        return res;
    }

    public static boolean anonymizePartialState(Expr[][] qState, List<JCTree.JCVariableDecl> qStateVar) {
        boolean anonymized = false;
        for (int i = 0; i < qState.length; ++i) {
            Expr realVal = new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, TransUtils.M.Ident(qStateVar.get(0)), TransUtils.M.Literal(i)));
            if(CLI.useReals) {
                if(!(qState[i][0] instanceof Const)) {
                    qState[i][0] = realVal;
                    anonymized = true;
                }
            } else {
                if(qState[i][0] instanceof ComplexExpression) {
                    ComplexExpression ce = (ComplexExpression) qState[i][0];
                    Expr compVal = new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, TransUtils.M.Ident(qStateVar.get(1)), TransUtils.M.Literal(i)));
                    if (ce.getImg() instanceof Const) {
                        compVal = ce.getImg();
                    } else {
                        anonymized = true;
                    }
                    if (ce.getReal() instanceof Const) {
                        realVal = ce.getReal();
                    } else {
                        anonymized = true;
                    }
                    qState[i][0] = new ComplexExpression(realVal, compVal);
                } else {
                    throw new RuntimeException("Expected complex expression but got: " + qState[i][0]);
                }
            }
        }
        return anonymized;
    }

    public static void anonymizeState(Expr[][] qState, List<JCTree.JCVariableDecl> qStateVar) {
        for (int i = 0; i < qState.length; ++i) {
            Expr realVal = new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, TransUtils.M.Ident(qStateVar.get(0)), TransUtils.M.Literal(i)));
            if(CLI.useReals) {
                qState[i][0] = realVal;
            } else {
                Expr compVal = new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, TransUtils.M.Ident(qStateVar.get(1)), TransUtils.M.Literal(i)));
                qState[i][0] = new ComplexExpression(realVal, compVal);
            }
        }
    }

    public static Expr getConst(float r, float i) {
        if(!CLI.useFix) {
            if(CLI.useReals) {
                if(i != 0.0f) {
                    throw new RuntimeException("Cant initialize real const with imaginary part != 0");
                }
                return new FloatConst(r);
            } else {
                return new ComplexExpression(new FloatConst(r), new FloatConst(i));
            }
        } else {
            int val = (int)(r * Math.pow(CLI.base, CLI.numDigits));
            int ival = (int)(i * Math.pow(CLI.base, CLI.numDigits));
            if(CLI.useReals) {
                if(i != 0.0f) {
                    throw new RuntimeException("Cant initialize real const with imaginary part != 0");
                }
                return new FixedConst(val);
            } else {
                return new ComplexExpression(new FixedConst(val), new FixedConst(ival));
            }
        }
    }

    public static Expr getConst(float f) {
        if(!CLI.useFix) {
            if(CLI.useReals) {
                return new FloatConst(f);
            } else {
                return new ComplexExpression(new FloatConst(f), new FloatConst(0.0f));
            }
        } else {
            int val = (int)(f * Math.pow(CLI.base, CLI.numDigits));
            if(CLI.useReals) {
                return new FixedConst(val);
            } else {
                return new ComplexExpression(new FixedConst(val), new FixedConst(0));
            }
        }
    }

    public static Const getRealConst(float f) {
        if(!CLI.useFix) {
            return new FloatConst(f);
        } else {
            int val = (int)(f * Math.pow(CLI.base, CLI.numDigits));
            return new FixedConst(val);
        }
    }

    public static Expr[][] getInitialSymbState(int numQbits, JCTree.JCIdent state, JCTree.JCIdent cstate) {
        int stateSize = 1 << numQbits;
        Expr[][] newState = new Expr[stateSize][1];
        for(int i = 0; i < stateSize; ++i) {
            newState[i][0] = new ComplexExpression(
                    new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, state, TransUtils.M.Literal(i))),
                    new SymbExpr(TransUtils.treeutils.makeArrayElement(Position.NOPOS, cstate, TransUtils.M.Literal(i)))
            );
        }
        return newState;

    }

    public static void applyReset(Expr[][] qState, int qBit1) {
        int stateSize = qState.length;
        int numQbits = log2(stateSize);
        qBit1 = numQbits - qBit1 - 1;
        for(int i = 0; i < stateSize; ++i) {
            if((i / (1 << qBit1)) % 2 != 0) {
                if(CLI.useReals) {
                    qState[i][0] = getConst(0.0f);
                } else {
                    qState[i][0] = new ComplexExpression(getConst(0.0f), getConst(0.0f));
                }
            }
        }
    }

    public static void applySwap(Expr[][] qState, int qBit1, int qBit2) {
        int stateSize = qState.length;
        int numQbits = getStateSize(qState);
        qBit1 = numQbits - qBit1 - 1;
        qBit2 = numQbits - qBit2 - 1;
        for(int i = 0; i < stateSize; ++i) {
            //swap bits on positions qBit1 and qBit2 and exchange the corresponding array elements
            int x = ((i >> qBit1) ^ (i >> qBit2)) & 1;
            int r = i ^ ((x << qBit1) | (x << qBit2));
            if(i < r) {
                Expr tmp = qState[r][0];
                qState[r][0] = qState[i][0];
                qState[i][0] = tmp;
            }
        }
    }

    public static int getStateSize(Expr[][] qState) {
        int stateSize = qState.length;
        return log2(stateSize);
    }

    private static List<Integer> considerSwap(List<Integer> qBits, Pair<Integer, Integer> swap) {
        List<Integer> res = List.nil();
        for(int qBit : qBits) {
            if(qBit == swap.fst) {
                res = res.append(swap.snd);
            } else if(qBit == swap.snd) {
                res = res.append(swap.fst);
            } else {
                res = res.append(qBit);
            }
        }
        return res;
    }
    public static Pair<Integer, List<Pair<Integer, Integer>>> applyNecessarySwaps(Expr[][] qState, com.sun.tools.javac.util.List<Integer> qBits) {
        int maxArgs = qBits.stream().max(Comparator.naturalOrder()).get();
        int numQbits = getStateSize(qState);
        if(maxArgs >= numQbits) {
            throw new UnsupportedException("Found gate application to qBit " + maxArgs + " but only " + numQbits + "qbits in the circuit.");
        }
        int numArgs = qBits.length();
        int minArgs = qBits.stream().min(Comparator.naturalOrder()).get();
        if(minArgs < 0) {
            throw new UnsupportedException("Cant apply gate to negative qbit index: " + minArgs);
        }

        List<Pair<Integer, Integer>> necessarySwaps = List.nil();
        int firstQbit = qBits.get(0);
        if(!(qBits.get(0) + numArgs <= numQbits)) {
            if(minArgs + numArgs <= numQbits) {
                Pair<Integer, Integer> newSwap = Pair.of(minArgs, qBits.get(0));
                necessarySwaps = necessarySwaps.append(newSwap);
                qBits = considerSwap(qBits, newSwap);
                firstQbit = minArgs;
            } else {
                Pair<Integer, Integer> newSwap = Pair.of(minArgs, qBits.get(0));
                necessarySwaps = necessarySwaps.append(newSwap);
                qBits = considerSwap(qBits, newSwap);
                firstQbit = numQbits - numArgs;
            }
        }

        for(int i = 1; i < qBits.length(); ++i) {
            if(qBits.get(i) != firstQbit + i) {
                Pair<Integer, Integer> newSwap = Pair.of(qBits.get(i), firstQbit + i);
                if(!newSwap.fst.equals(newSwap.snd)) {
                    necessarySwaps = necessarySwaps.append(newSwap);
                }
                qBits = considerSwap(qBits, newSwap);
            }
        }

        applySwaps(qState, necessarySwaps);
        necessarySwaps = necessarySwaps.reverse();
        return Pair.of(firstQbit, necessarySwaps);
    }

    public static void applySwaps(Expr[][] qState, List<Pair<Integer, Integer>> necessarySwaps) {
        for(Pair<Integer, Integer> swap : necessarySwaps) {
            applySwap(qState, swap.fst, swap.snd);
        }
    }
}