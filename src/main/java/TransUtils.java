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

    public static JCTree.JCExpression makeArrayExpression(Expr[][] elems) {
        List<JCTree.JCExpression> allElemnts = List.nil();
        for(Expr[] row : elems) {
            List<JCTree.JCExpression> rowElements = List.nil();
            for(Expr el : row) {
                rowElements = rowElements.append(el.getSimplifiedAST());
            }
            JCTree.JCNewArray array = M.NewArray(M.Type(rowElements.get(0).type), List.nil(), rowElements);
            array.type = new Type.ArrayType(rowElements.get(0).type, rowElements.get(0).type.tsym);
            allElemnts = allElemnts.append(array);
        }
        JCTree.JCNewArray newArray = M.NewArray(M.Type(allElemnts.get(0).type), List.nil(), allElemnts);
        newArray.type = new Type.ArrayType(allElemnts.get(0).type, allElemnts.get(0).type.tsym);
        return newArray;
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

    public static JCTree.JCStatement updateState(Expr[][] newState, JCTree.JCVariableDecl qStateVar) {
        JCTree.JCExpression init = TransUtils.makeArrayExpression(newState);
        return M.Exec(M.Assign(M.Ident(qStateVar), init));
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
        JCTree.JCExpression left = M.Literal(0.0f);
        JCTree.JCExpression right = M.Literal(0.0f);
        int numQbits = Utils.log2(qState.length);
        int shift = numQbits - qBit - 1;
        for(int i = 0; i < qState.length; ++i) {
            MultOp tmp = new MultOp(qState[i][0], qState[i][0]);
            if((i & (1 << shift)) != 0) {
                left = M.Binary(JCTree.Tag.PLUS, left, tmp.getSimplifiedAST());
            } else {
                right = M.Binary(JCTree.Tag.PLUS, right, tmp.getSimplifiedAST());
            }
        }
        return M.Binary(JCTree.Tag.GT, left, right);
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
}
