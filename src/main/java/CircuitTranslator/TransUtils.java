package CircuitTranslator;

import CircuitTranslator.Expressions.Expr;
import CircuitTranslator.Expressions.MultOp;
import CircuitTranslator.Expressions.SymbExpr;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Position;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeUtils;

import java.lang.reflect.Array;

public class TransUtils {
    public static JmlTreeUtils treeutils;
    public static JmlTree.Maker M;
    private static Context ctx;

    public static void init(Context ctx) {
        TransUtils.M = JmlTree.Maker.instance(ctx);
        TransUtils.ctx = ctx;
        TransUtils.treeutils = JmlTreeUtils.instance(ctx);
    }

    public static List<JCTree.JCExpression> makeArrayExpression(Expr[][] elems) {
        List<JCTree.JCExpression> res = List.nil();
        for(int i = 0; i < elems[0][0].getSimplifiedAST().size(); ++i) {
            List<JCTree.JCExpression> allElemnts = List.nil();
            for (Expr[] row : elems) {
                List<JCTree.JCExpression> rowElements = List.nil();
                for (Expr el : row) {
                    rowElements = rowElements.append(el.getSimplifiedAST().get(i));
                }
                JCTree.JCNewArray array = M.NewArray(M.Type(rowElements.get(0).type), List.nil(), rowElements);
                array.type = new Type.ArrayType(rowElements.get(0).type, rowElements.get(0).type.tsym);
                allElemnts = allElemnts.append(array);
            }
            JCTree.JCNewArray newArray = M.NewArray(M.Type(allElemnts.get(0).type), List.nil(), allElemnts);
            newArray.type = new Type.ArrayType(allElemnts.get(0).type, allElemnts.get(0).type.tsym);
            res = res.append(newArray);
        }
        return res;
    }


    public static JCTree.JCExpression makeArrayExpression(Object elems) {
        if(elems == null) {
            return M.Literal(null);
        }
        if(elems.getClass().isArray()) {
            List<JCTree.JCExpression> elements = List.nil();
            Object[] array;
            if(elems.getClass().getComponentType().isPrimitive()) {
                List ar = List.nil();
                int length = Array.getLength(elems);
                for (int i = 0; i < length; i++) {
                    ar = ar.append(Array.get(elems, i));
                }
                array = ar.toArray();
            } else {
                array = (Object[]) elems;
            }
            for(Object el : array) {
                elements = elements.append(makeArrayExpression(el));
            }
            JCTree.JCNewArray newArray = M.NewArray(M.Type(elements.get(0).type), List.nil(), elements);
            newArray.type = new Type.ArrayType(elements.get(0).type, elements.get(0).type.tsym);
            return newArray;
        }
        return M.Literal(elems);
    }

    public static List<JCTree.JCStatement> updateState(Expr[][] newState, List<JCTree.JCVariableDecl> qStateVar) {
        List<JCTree.JCExpression> init = TransUtils.makeArrayExpression(newState);
        List<JCTree.JCStatement> res = List.nil();
        for(int i = 0; i < init.size(); ++i) {
            res = res.append(M.Exec(M.Assign(M.Ident(qStateVar.get(i)), init.get(i))));
        }
        return res;
    }

    public static Expr[][] getUnitaryFromIdent(JCTree.JCIdent ident, int stateSize) {
        Expr[][] res = new Expr[stateSize][stateSize];
        for(int i = 0; i < res[0].length; ++i) {
            for(int j = 0; j < res.length; ++j) {
                JCTree.JCExpression element = treeutils.makeArrayElement(Position.NOPOS,
                        treeutils.makeArrayElement(Position.NOPOS, ident, M.Literal(j)),
                        M.Literal(i));
                res[j][i] = new SymbExpr(element);
            }
        }
        return res;
    }

    public static JCTree.JCExpression makeMeasureMaxCondition(Expr[][] qState, int qBit) {
        int numQbits = Utils.log2(qState.length);
        int shift = numQbits - qBit - 1;
        Expr left = null;
        Expr right = null;
        for(int i = 0; i < qState.length; ++i) {
            if((i & (1 << shift)) != 0) {
                Expr abs = qState[i][0].getAbs();
                if(left == null) {
                    left = abs.mult(abs);
                } else {
                    left = left.add(abs.mult(abs));
                }
            } else {
                Expr abs = qState[i][0].getAbs();
                if(right == null) {
                    right = abs.mult(abs);
                } else {
                    right = right.add(abs.mult(abs));
                }
            }
        }
        List<JCTree.JCExpression> lexpr = left.getSimplifiedAST();
        List<JCTree.JCExpression> rexpr = right.getSimplifiedAST();
        assert lexpr.size() == 1;
        assert rexpr.size() == 1;
        return M.Binary(JCTree.Tag.GT, lexpr.get(0), rexpr.get(0));
    }

    public static JCTree.JCStatement setCState(JCTree.JCExpression currentAssignExpression, boolean val) {
        return M.Exec(M.Assign(currentAssignExpression, M.Literal(val)));
    }


    public static JCTree.JCMethodInvocation makeNondetBoolean(Symbol currentSymbol) {
        JCTree.JCIdent classCProver = M.Ident(M.Name("CProver"));
        JCTree.JCFieldAccess nondetFunc = M.Select(classCProver, M.Name("nondetBoolean"));
        List<JCTree.JCExpression> largs = List.nil();
        return M.Apply(List.nil(), nondetFunc, largs);
    }

    public static JCTree.JCStatement makePrintStatement(JCTree.JCExpression expr) {
        JCTree.JCIdent sout = M.Ident("printState");
        return M.Exec(M.Apply(List.nil(), sout, List.of(expr)));
    }

    public static List<JCTree.JCStatement> makePrintStatement(List<JCTree.JCVariableDecl> decls) {
        List<JCTree.JCStatement> res = List.nil();
        for(JCTree.JCVariableDecl e : decls) {
            res = res.append(makePrintStatement(M.Ident(e)));
        }
        return res;
    }


}
