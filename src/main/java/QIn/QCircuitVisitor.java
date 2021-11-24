package QIn;

import QIn.Expressions.Expr;
import com.sun.source.tree.*;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Position;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeCopier;
import org.jmlspecs.openjml.JmlTreeUtils;

public class QCircuitVisitor extends JmlTreeCopier {
    private static final String CIRCUIT_TYPE = "CircuitMock";
    private final JmlTreeUtils treeutils;
    public static org.jmlspecs.openjml.Utils utils;
    private List<JCTree.JCStatement> newStatements = List.nil();
    private final Symtab syms;
    private Symbol currentSymbol;
    int numQbits = -1;
    private List<JCTree.JCVariableDecl> qStateVars;
    private int stateSize;
    private Expr[][] qState;
    private int measureVarCounter = 0;
    private int paramVarCounter = 0;
    private JmlTree.JmlVariableDecl currentCircuitName = null;
    public static List<JmlTree.JmlVariableDecl> classFinalVars = List.nil();
    public static List<JmlTree.JmlVariableDecl> localFinalVars = List.nil();

    public QCircuitVisitor(Context context, JmlTree.Maker maker) {
        super(context, maker);
        this.treeutils = JmlTreeUtils.instance(context);
        this.syms = Symtab.instance(context);
        this.utils = org.jmlspecs.openjml.Utils.instance(context);
    }

    @Override
    public JCTree visitJmlVariableDecl(JmlTree.JmlVariableDecl that, Void p) {
        if(that.type.toString().equals(CIRCUIT_TYPE)) {
            JCTree.JCNewClass clazz = null;
            if(that.init instanceof JCTree.JCNewClass) {
                clazz = (JCTree.JCNewClass) that.init;
            } else {
                throw new RuntimeException("Only " + CIRCUIT_TYPE + "s created with constructor supported for now.");
            }
            if(clazz.args == null || clazz.args.size() < 1) {
                throw new RuntimeException("Error creating circuit.");
            }
            currentCircuitName = that;
            numQbits = Integer.parseInt(clazz.args.get(0).toString());
            stateSize = (int)Math.pow(2, numQbits);
            if(clazz.args.size() == 2) {
                if(clazz.args.get(1) instanceof JCTree.JCIdent || !CLI.useReals) {
                    qState = Utils.getInitialSymbState(numQbits, (JCTree.JCIdent) clazz.args.get(1));
                } else {
                    if(!CLI.useReals) {
                        throw new RuntimeException("When using complex coefficients initial states have to be complex as well.");
                    }
                    throw new RuntimeException("Currently only initial states provided via variable are supported.");
                }
            } else if(clazz.args.size() == 3) {
                if(clazz.args.get(1) instanceof JCTree.JCIdent && clazz.args.get(2) instanceof JCTree.JCIdent) {
                    qState = Utils.getInitialSymbState(numQbits, (JCTree.JCIdent) clazz.args.get(1), (JCTree.JCIdent) clazz.args.get(2));
                } else {
                    throw new RuntimeException("Currently only initial states provided via variable are supported.");
                }
            } else {
                qState = Utils.getInitialState(numQbits);
            }
            List<JCTree.JCExpression> init = TransUtils.makeArrayExpression(qState);
            qStateVars = List.nil();
            for(int i = 0; i < init.size(); ++i) {
                qStateVars = qStateVars.append(treeutils.makeVarDef(init.get(i).type, M.Name("q_state_" + i), currentSymbol, init.get(i)));
                if(i != init.size() - 1) {
                    newStatements = newStatements.append(qStateVars.get(qStateVars.size() - 1));
                }
            }
            return qStateVars.get(qStateVars.size() - 1);
        }
        if(TransUtils.isFinal(that)) {
            if(that.sym.owner.equals(currentSymbol)) {
                localFinalVars = localFinalVars.append(that);
            } else {
                classFinalVars = classFinalVars.append(that);
            }
        }
        return super.visitJmlVariableDecl(that, p);
    }

    @Override
    public JCTree visitJmlMethodDecl(JmlTree.JmlMethodDecl that, Void p) {
        currentSymbol = that.sym;
        paramVarCounter = 0;
        JmlTree.JmlMethodDecl copy = (JmlTree.JmlMethodDecl) super.visitJmlMethodDecl(that, p);
        for(int i = 0; i < paramVarCounter; ++i) {
            JCTree.JCVariableDecl newParam = treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureParam" + i), currentSymbol.owner, Position.NOPOS);
            copy.params = copy.params.append(newParam);
        }

        if(copy.methodSpecsCombined.cases != null && copy.methodSpecsCombined.cases.cases != null && copy.methodSpecsCombined.cases.cases.size() == 1) {
            if(copy.methodSpecsCombined.cases.cases.get(0).clauses.stream().noneMatch(x -> x instanceof JmlTree.JmlMethodClauseExpr)) {
                copy.methodSpecsCombined.cases.cases = List.nil();
            }
        }
        return copy;
    }

    @Override
    public JCTree visitJmlClassDecl(JmlTree.JmlClassDecl that, Void p) {
        return super.visitJmlClassDecl(that, p);
    }

    @Override
    public JCTree visitMethodInvocation(MethodInvocationTree node, Void p) {
        if(node instanceof JCTree.JCMethodInvocation) {
            JCTree.JCMethodInvocation methodInvocation = (JCTree.JCMethodInvocation) node;
            if(methodInvocation.meth instanceof JCTree.JCFieldAccess) {
                JCTree.JCFieldAccess fullMethod = (JCTree.JCFieldAccess) methodInvocation.meth;
                if(fullMethod.selected.type != null && fullMethod.selected.type.toString().equals(CIRCUIT_TYPE)) {
                    if(!fullMethod.selected.toString().equals(currentCircuitName.name.toString())) {
                        throw new RuntimeException("Currently only one circuit allowed in parallel.");
                    }
                    Expr[][] unitary = null;
                    int qBit = -1;
                    if(fullMethod.name.toString().equals("u")) {
                        if(!(methodInvocation.args.get(1) instanceof JCTree.JCIdent)) {
                            if (methodInvocation.args.get(0) instanceof JCTree.JCIdent) {
                                int size = (int)Math.pow(2, methodInvocation.args.size() - 1);
                                unitary = TransUtils.getUnitaryFromIdent((JCTree.JCIdent) methodInvocation.args.get(0), size);
                            }
                            qBit = Integer.parseInt(methodInvocation.args.get(1).toString());
                        } else if(methodInvocation.args.size() >= 3) {
                            if (methodInvocation.args.get(0) instanceof JCTree.JCIdent && methodInvocation.args.get(1) instanceof JCTree.JCIdent) {
                                int size = (int)Math.pow(2, methodInvocation.args.size() - 2);
                                unitary = TransUtils.getUnitaryFromCompIdent((JCTree.JCIdent) methodInvocation.args.get(0), (JCTree.JCIdent) methodInvocation.args.get(1), size);
                            }
                            qBit = Integer.parseInt(methodInvocation.args.get(2).toString());
                        } else {
                            throw new RuntimeException("Illegal number of arguments for unitary matrix application.");
                        }
                    } else if(fullMethod.name.toString().equals("swap")) {
                        assert methodInvocation.args.size() == 2;

                        int qBit1 = Integer.parseInt(methodInvocation.args.get(0).toString());
                        int qBit2 = Integer.parseInt(methodInvocation.args.get(1).toString());
                        Utils.applySwap(qState, qBit1, qBit2);
                        newStatements = newStatements.appendList(TransUtils.updateState(qState, qStateVars));
                        return super.visitMethodInvocation(node, p);
                    } else if(fullMethod.name.toString().equals("measureMax")) {
                        Utils.anonymizeState(qState, qStateVars);
                        qBit = Integer.parseInt(methodInvocation.args.get(0).toString());
                        Expr[][] trueState = Utils.applyMeasurement(qState, qBit, true);
                        Expr[][] falseState = Utils.applyMeasurement(qState, qBit, false);
                        JCTree.JCExpression cond = TransUtils.makeMeasureMaxCondition(qState, qBit);
                        newStatements = newStatements.append(treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureVar" + ++measureVarCounter), currentSymbol, Position.NOPOS));
                        JCTree.JCIdent tmp = M.Ident("$$_tmp_measureVar" + measureVarCounter);
                        JCTree.JCBlock ifBlock = M.Block(0L, TransUtils.updateState(falseState, qStateVars).append(TransUtils.setCState(tmp,  true)));
                        JCTree.JCBlock elseBlock = M.Block(0L, TransUtils.updateState(trueState, qStateVars).append(TransUtils.setCState(tmp,  false)));
                        JCTree.JCIf jcIf = M.If(cond, ifBlock, elseBlock);
                        newStatements = newStatements.append(jcIf);
                        if(CLI.includePrintStatements) {
                            newStatements = newStatements.appendList(TransUtils.makePrintStatement(qStateVars));
                        }
                        Utils.anonymizeState(qState, qStateVars);

                        return tmp;
                    } else if(fullMethod.name.toString().equals("measure")) {
                        Utils.anonymizeState(qState, qStateVars);
                        qBit = Integer.parseInt(methodInvocation.args.get(0).toString());
                        Expr[][] trueState = Utils.applyMeasurement(qState, qBit, true);
                        Expr[][] falseState = Utils.applyMeasurement(qState, qBit, false);
                        JCTree.JCExpression cond = null;
                        if(CLI.useNondetFunctions) {
                            cond = TransUtils.makeNondetBoolean(currentSymbol);
                        } else {
                            cond = M.Ident("$$_tmp_measureParam" + paramVarCounter++);
                        }
                        newStatements = newStatements.append(treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureVar" + ++measureVarCounter), currentSymbol, Position.NOPOS));
                        JCTree.JCIdent tmp = M.Ident("$$_tmp_measureVar" + measureVarCounter);
                        JCTree.JCBlock ifBlock = M.Block(0L, TransUtils.updateState(trueState, qStateVars).append(TransUtils.setCState(tmp,  false)));
                        JCTree.JCBlock elseBlock = M.Block(0L, TransUtils.updateState(falseState, qStateVars).append(TransUtils.setCState(tmp,  true)));
                        JCTree.JCIf jcIf = M.If(cond, ifBlock, elseBlock);
                        newStatements = newStatements.append(jcIf);
                        if(CLI.includePrintStatements) {
                            newStatements = newStatements.appendList(TransUtils.makePrintStatement(qStateVars));
                        }
                        Utils.anonymizeState(qState, qStateVars);

                        return tmp;
                    } else {
                        unitary = Utils.getUnitaryForName(fullMethod.name.toString());
                        qBit = Integer.parseInt(methodInvocation.args.get(0).toString());
                    }
                    qState = Utils.apply(unitary, qBit, qState);
                    newStatements = newStatements.appendList(TransUtils.updateState(qState, qStateVars));
                    if(CLI.includePrintStatements) {
                        newStatements = newStatements.appendList(TransUtils.makePrintStatement(qStateVars));
                    }
                }
            }
        }
        return super.visitMethodInvocation(node, p);
    }

    @Override
    public JCTree visitExpressionStatement(ExpressionStatementTree node, Void p) {
        JCTree res = super.visitExpressionStatement(node, p);
        if(res instanceof JCTree.JCIdent) {
            return M.Exec(M.Assign((JCTree.JCExpression) res, (JCTree.JCExpression) res));
        }
        return res;
    }

    @Override
    public JCTree visitJmlBlock(JmlTree.JmlBlock that, Void p) {
        JCTree.JCBlock res = M.Block(0L, List.nil());
        for(JCTree.JCStatement st : that.stats) {
            newStatements = List.nil();
            JCTree.JCStatement statementCopy = super.copy(st);
            res.stats = res.stats.appendList(newStatements);
            if(statementCopy instanceof JCTree.JCExpressionStatement) {
                if(((JCTree.JCExpressionStatement) statementCopy).expr instanceof JCTree.JCMethodInvocation) {
                    if(((JCTree.JCMethodInvocation) ((JCTree.JCExpressionStatement) statementCopy).expr).meth instanceof JCTree.JCFieldAccess) {
                        if(((JCTree.JCFieldAccess) ((JCTree.JCMethodInvocation) ((JCTree.JCExpressionStatement) statementCopy).expr).meth).selected.type.toString().equals(CIRCUIT_TYPE)) {
                            continue;
                        }
                    }
                }
            }
            res.stats = res.stats.append(statementCopy);
        }
        newStatements = List.nil();
        return res;
    }

    @Override
    public JCTree visitIf(IfTree node, Void p) {
        JCTree.JCIf jcif = (JCTree.JCIf)node;
        if(currentCircuitName == null) {
            return jcif;
        }
        JCTree.JCExpression cond = super.copy(jcif.cond);
        JCTree.JCStatement ifBlock = super.copy(jcif.thenpart);
        Utils.anonymizeState(qState, qStateVars);
        JCTree.JCStatement elseBlock = super.copy(jcif.elsepart);
        Utils.anonymizeState(qState, qStateVars);
        return M.If(cond, ifBlock, elseBlock);
    }
}
