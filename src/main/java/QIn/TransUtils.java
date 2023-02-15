package QIn;

import QIn.Expressions.*;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Position;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeUtils;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Array;

public class TransUtils {
    public static JmlTreeUtils treeutils;
    public static JmlTree.Maker M;
    private static Symtab syms = null;

    public static void init(Context ctx) {
        TransUtils.M = JmlTree.Maker.instance(ctx);
        TransUtils.treeutils = JmlTreeUtils.instance(ctx);
        TransUtils.syms = Symtab.instance(ctx);
    }

    public static boolean isFinal(JmlTree.JmlVariableDecl decl) {
        return decl.sym.getModifiers().contains(Modifier.FINAL);
    }

    public static boolean isFinal(JCTree.JCIdent ident) {
        return ident.sym.getModifiers().contains(Modifier.FINAL);
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
                allElemnts = allElemnts.appendList(rowElements);
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

    public static Expr[][] getUnitaryFromCompIdent(JCTree.JCIdent ident, JCTree.JCIdent i_ident, int stateSize) {
        JmlTree.JmlVariableDecl decl = null;
        JmlTree.JmlVariableDecl i_decl = null;
        for(JmlTree.JmlVariableDecl d : QCircuitVisitor.localFinalVars) {
            if(ident.sym == d.sym) {
                decl = d;
            }
            if(i_ident.sym == d.sym) {
                i_decl = d;
            }
        }
        for(JmlTree.JmlVariableDecl d : QCircuitVisitor.classFinalVars) {
            if(ident.sym == d.sym) {
                decl = d;
            }
            if(i_ident.sym == d.sym) {
                i_decl = d;
            }
        }
        Expr[][] res = new Expr[stateSize][stateSize];
        for(int i = 0; i < res[0].length; ++i) {
            for(int j = 0; j < res.length; ++j) {
                Expr expr = null;
                if(decl != null) {
                    expr = tryGetElement(decl.init, i, j);
                }
                if(expr == null) {
                    JCTree.JCExpression element = treeutils.makeArrayElement(Position.NOPOS,
                            treeutils.makeArrayElement(Position.NOPOS, ident, M.Literal(j)),
                            M.Literal(i));
                    expr = new SymbExpr(element);
                }
                Expr i_expr = null;
                if(i_decl != null) {
                    i_expr = tryGetElement(i_decl.init, i, j);
                }
                if(i_expr == null) {
                    JCTree.JCExpression i_element = treeutils.makeArrayElement(Position.NOPOS,
                            treeutils.makeArrayElement(Position.NOPOS, i_ident, M.Literal(j)),
                            M.Literal(i));
                    i_expr = new SymbExpr(i_element);
                }
                res[j][i] = new ComplexExpression(expr, i_expr);
            }
        }
        return res;
    }

    private static Expr tryGetElement(JCTree.JCExpression init, int i, int j) {
        if(init instanceof JCTree.JCNewArray) {
            if(((JCTree.JCNewArray) init).elemtype instanceof JCTree.JCArrayTypeTree) {
                JCTree.JCExpression element = ((JCTree.JCNewArray) init).elems.get(i);
                if(element instanceof JCTree.JCNewArray) {
                    element = ((JCTree.JCNewArray) element).elems.get(j);
                    if(element instanceof JCTree.JCLiteral) {
                        try {
                            float f = Float.parseFloat(element.toString());
                            return new FloatConst(f);
                        } catch (NumberFormatException ex) {
                            try {
                                int inte = Integer.parseInt(element.toString());
                                return new FixedConst(inte);
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("Unexpected Literal: " + element);
                            }
                        }
                    }
                    if(element instanceof JCTree.JCIdent) {
                        return new SymbExpr(element);
                    }
                }
            }
        }
        return null;
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
                    left = abs;
                } else {
                    left = left.add(abs);
                }
            } else {
                Expr abs = qState[i][0].getAbs();
                if(right == null) {
                    right = abs;
                } else {
                    right = right.add(abs);
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


    public static JCTree.JCMethodInvocation makeNondetBoolean() {
        List<JCTree.JCExpression> args = List.nil();
        return makeCProverMethod("nondetBoolean", args);
    }

    public static JCTree.JCMethodInvocation makeAssume(JCTree.JCExpression arg) {
        List<JCTree.JCExpression> args = List.of(arg);
        return makeCProverMethod("assume", args);
    }

    public static JCTree.JCMethodInvocation makeCProverMethod(String methodName, List<JCTree.JCExpression> args) {
        JCTree.JCIdent classCProver = M.Ident(M.Name("CProver"));
        JCTree.JCFieldAccess nondetFunc = M.Select(classCProver, M.Name(methodName));
        return M.Apply(List.nil(), nondetFunc, args);
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


    public static JCTree.JCExpression makeMeasurePosCondition(Expr[][] qState, int qBit, boolean b) {
        int numQbits = Utils.log2(qState.length);
        int shift = numQbits - qBit - 1;
        JCTree.JCExpression trueCase = M.Literal(true);
        JCTree.JCExpression falseCase = M.Literal(true);
        for(int i = 0; i < qState.length; ++i) {
            if((i & (1 << shift)) != 0) {
                List<JCTree.JCExpression> complexAst = qState[i][0].getSimplifiedAST();
                for(JCTree.JCExpression ast : complexAst) {
                    ast = treeutils.makeEquality(Position.NOPOS, ast, M.Literal(0.0f));
                    trueCase = treeutils.makeAnd(Position.NOPOS, trueCase, ast);
                }
            } else {
                List<JCTree.JCExpression> complexAst = qState[i][0].getSimplifiedAST();
                for(JCTree.JCExpression ast : complexAst) {
                    ast = treeutils.makeEquality(Position.NOPOS, ast, M.Literal(0.0f));
                    falseCase = treeutils.makeAnd(Position.NOPOS, falseCase, ast);
                }
            }
        }
        if(b) {
            return falseCase;
        }
        return trueCase;
    }

    public static JCTree.JCStatement makeRuntimeException(String message) {
        JCTree.JCExpression ty = M.Type(syms.runtimeExceptionType);
        return M.Throw(M.NewClass(null, null, ty, List.of(M.Literal(message)), null));
    }
}
