package QIn;

import QIn.Expressions.Expr;
import com.sun.source.tree.*;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Position;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeCopier;
import org.jmlspecs.openjml.JmlTreeScanner;
import org.jmlspecs.openjml.JmlTreeUtils;

public class QCircuitVisitor extends JmlTreeCopier {
    public static final String CIRCUIT_TYPE = "CircuitMock";
    private final JmlTreeUtils treeutils;
    public static org.jmlspecs.openjml.Utils utils;
    private List<JCTree.JCStatement> newStatements = List.nil();
    private Symbol currentSymbol;
    int numQbits = -1;
    private List<JCTree.JCVariableDecl> qStateVars;
    private Expr[][] qState;
    private int measureVarCounter = 0;
    private int paramVarCounter = 0;
    private JmlTree.JmlVariableDecl currentCircuitName = null;
    public static List<JmlTree.JmlVariableDecl> classFinalVars = List.nil();
    public static List<JmlTree.JmlVariableDecl> localFinalVars = List.nil();
    private List<JCTree.JCStatement> pendingStateChange = List.nil();

    public QCircuitVisitor(Context context, JmlTree.Maker maker) {
        super(context, maker);
        this.treeutils = JmlTreeUtils.instance(context);
        utils = org.jmlspecs.openjml.Utils.instance(context);
    }

    @Override
    public JCTree visitJmlCompilationUnit(JmlTree.JmlCompilationUnit that, Void p) {
        JmlTree.JmlCompilationUnit cu = (JmlTree.JmlCompilationUnit) super.visitJmlCompilationUnit(that, p);
        if(CLI.useNondetFunctions) {
            cu.defs = cu.defs.prepend(M.Import(M.Ident(M.Name("org.cprover.CProver")), false));
        }
        return cu;
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
            numQbits = ScriptEngineWrapper.getInstance().evalInt(clazz.args.get(0).toString());
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
                qStateVars = qStateVars.append(treeutils.makeVarDef(init.get(i).type, M.Name("q" + i), currentSymbol, init.get(i)));
                newStatements = newStatements.append(qStateVars.get(qStateVars.size() - 1));
            }
            return null;
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
            JCTree.JCVariableDecl newParam = treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureParam_" + i), currentSymbol.owner, Position.NOPOS);
            if(copy.params.stream().noneMatch(param -> param.name.toString().equals(newParam.name.toString()))) {
                copy.params = copy.params.append(newParam);
            }
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
                    List<Pair<Integer, Integer>> necessarySwaps = List.nil();
                    Expr[][] unitary = null;
                    int qBit = -1;
                    int numQbits = 1;
                    if(Utils.anonymizePartialState(qState, qStateVars)) {
                        newStatements = newStatements.appendList(pendingStateChange);
                        pendingStateChange = List.nil();
                    }
                    if(fullMethod.name.toString().equals("u")) {
                        if(methodInvocation.args.size() == 3) {
                            qBit = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(1).toString());
                            numQbits = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(2).toString());
                            if (methodInvocation.args.get(0) instanceof JCTree.JCIdent) {
                                int size = (int) Math.pow(2, numQbits);
                                unitary = TransUtils.getUnitaryFromIdent((JCTree.JCIdent) methodInvocation.args.get(0), size);
                            }
                        } else if(methodInvocation.args.size() == 4) {
                            qBit = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(2).toString());
                            numQbits = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(3).toString());
                            if (methodInvocation.args.get(0) instanceof JCTree.JCIdent && methodInvocation.args.get(1) instanceof JCTree.JCIdent) {
                                int size = (int)Math.pow(2, numQbits);
                                unitary = TransUtils.getUnitaryFromCompIdent((JCTree.JCIdent) methodInvocation.args.get(0), (JCTree.JCIdent) methodInvocation.args.get(1), size);
                            }
                        } else {
                            throw new RuntimeException("Illegal number of arguments for unitary matrix application.");
                        }
                    } else if(fullMethod.name.toString().equals("swap")) {
                        assert methodInvocation.args.size() == 2;

                        int qBit1 = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(0).toString());
                        int qBit2 = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(1).toString());
                        Utils.applySwap(qState, qBit1, qBit2);
                        newStatements = newStatements.appendList(TransUtils.updateState(qState, qStateVars));
                        return null;
                    } else if(fullMethod.name.toString().equals("reset")) {
                        assert methodInvocation.args.size() == 1;

                        int qBit1 = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(0).toString());
                        Utils.applyReset(qState, qBit1);
                        newStatements = newStatements.appendList(TransUtils.updateState(qState, qStateVars));
                        return null;
                    } else if(fullMethod.name.toString().equals("measure")) {
                        newStatements = newStatements.appendList(pendingStateChange);
                        pendingStateChange = List.nil();
                        Utils.anonymizeState(qState, qStateVars);
                        qBit = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(0).toString());
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
                    } else if(fullMethod.name.toString().equals("measurePos")) {
                        newStatements = newStatements.appendList(pendingStateChange);
                        pendingStateChange = List.nil();
                        Utils.anonymizeState(qState, qStateVars);
                        qBit = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(0).toString());
                        Expr[][] trueState = Utils.applyMeasurement(qState, qBit, true);
                        Expr[][] falseState = Utils.applyMeasurement(qState, qBit, false);
                        JCTree.JCExpression random = null;
                        if(CLI.useNondetFunctions) {
                            random = TransUtils.makeNondetBoolean();
                        } else {
                            random = M.Ident("$$_tmp_measureParam_" + paramVarCounter++);
                        }
                        JCTree.JCExpression condTrue = TransUtils.makeMeasurePosCondition(qState, qBit, true);
                        JCTree.JCExpression condFalse = TransUtils.makeMeasurePosCondition(qState, qBit, false);
                        JCTree.JCIf measureTrueGuard = M.If(condTrue, M.Exec(TransUtils.makeAssume(M.Literal(false))), null);
                        JCTree.JCIf measureFalseGuard = M.If(condFalse, M.Exec(TransUtils.makeAssume(M.Literal(false))), null);
                        if(!CLI.useNondetFunctions) {
                            measureTrueGuard.thenpart = TransUtils.makeRuntimeException("Impossible measurement result.");
                            measureFalseGuard.thenpart = TransUtils.makeRuntimeException("Impossible measurement result.");
                        }
                        newStatements = newStatements.append(treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureVar" + ++measureVarCounter), currentSymbol, Position.NOPOS));
                        JCTree.JCIdent tmp = M.Ident("$$_tmp_measureVar" + measureVarCounter);
                        JCTree.JCBlock ifBlock = M.Block(0L, List.of((JCTree.JCStatement)measureTrueGuard).
                                appendList(TransUtils.updateState(trueState, qStateVars)).
                                append(TransUtils.setCState(tmp,  false)));
                        JCTree.JCBlock elseBlock = M.Block(0L, List.of((JCTree.JCStatement)measureFalseGuard).
                                appendList(TransUtils.updateState(falseState, qStateVars)).
                                append(TransUtils.setCState(tmp,  true)));
                        JCTree.JCIf jcIf = M.If(random, ifBlock, elseBlock);
                        newStatements = newStatements.append(jcIf);
                        if(CLI.includePrintStatements) {
                            newStatements = newStatements.appendList(TransUtils.makePrintStatement(qStateVars));
                        }
                        Utils.anonymizeState(qState, qStateVars);

                        return tmp;
                    } else {
                        List<Integer> qBits = List.nil();
                        for (int i = 0; i < methodInvocation.args.size(); ++i) {
                            if(!methodInvocation.args.get(i).type.hasTag(TypeTag.INT)) {
                                unitary = Utils.getUnitaryForName(fullMethod.name.toString(), methodInvocation.args.get(i));
                                break;
                            }
                            int nextQBit = ScriptEngineWrapper.getInstance().evalInt(methodInvocation.args.get(i).toString());
                            qBits = qBits.append(nextQBit);
                        }
                        Pair<Integer, List<Pair<Integer, Integer>>> res = Utils.applyNecessarySwaps(qState, qBits);
                        necessarySwaps = res.snd;
                        qBit = res.fst;

                        if(unitary == null) {
                            unitary = Utils.getUnitaryForName(fullMethod.name.toString());
                        }

                    }
                    qState = Utils.apply(unitary, qBit, qState);
                    Utils.applySwaps(qState, necessarySwaps);
                    pendingStateChange = TransUtils.updateState(qState, qStateVars);
                    //newStatements = newStatements.appendList(TransUtils.updateState(qState, qStateVars));
                    if(CLI.includePrintStatements) {
                        newStatements = newStatements.appendList(TransUtils.makePrintStatement(qStateVars));
                    }
                    return null;
                }
            }
        }
        return super.visitMethodInvocation(node, p);
    }

    @Override
    public JCTree visitExpressionStatement(ExpressionStatementTree node, Void p) {
        JCTree.JCExpressionStatement res = (JCTree.JCExpressionStatement) super.visitExpressionStatement(node, p);
        if(res.expr instanceof JCTree.JCIdent) {
            return null;
        }
        return res;
    }

    @Override
    public JCTree visitJmlBlock(JmlTree.JmlBlock that, Void p) {
        List<JCTree.JCStatement> tmp = newStatements;
        JCTree.JCBlock res = M.Block(0L, List.nil());
        for(JCTree.JCStatement st : that.stats) {
            newStatements = List.nil();
            JCTree.JCStatement statementCopy = super.copy(st);
            res.stats = res.stats.appendList(newStatements);
            if(statementCopy != null && !statementCopy.toString().contains("/*missing*/")) {
                res.stats = res.stats.append(statementCopy);
            }
        }
        newStatements = tmp;
        return res;
    }

    @Override
    public JCTree visitIf(IfTree node, Void p) {
        newStatements = newStatements.appendList(pendingStateChange);
        pendingStateChange = List.nil();
        JCTree.JCIf jcif = (JCTree.JCIf)node;
        if(currentCircuitName == null) {
            return jcif;
        }
        JCTree.JCExpression cond = super.copy(jcif.cond);
        JCTree.JCBlock ifBlock = M.Block(0L, List.of(super.copy(jcif.thenpart)));
        ifBlock.stats = ifBlock.stats.appendList(pendingStateChange);
        pendingStateChange = List.nil();
        JCTree.JCBlock elseBlock = null;
        if(jcif.elsepart != null) {
            elseBlock = M.Block(0L, List.of(super.copy(jcif.elsepart)));
            elseBlock.stats = elseBlock.stats.appendList(pendingStateChange);
            pendingStateChange = List.nil();
        }
        Utils.anonymizeState(qState, qStateVars);
        return M.If(cond, ifBlock, elseBlock);
    }

    @Override
    public JCTree visitJmlForLoop(JmlTree.JmlForLoop that, Void p) {
        //Check if its a loop effecting a circuit
        if(ContainsCircuitVisitor.containsCircuit(that.body)) {
            newStatements = List.nil();
            if (that.init == null || that.init.length() != 1 || !(that.init.get(0) instanceof JCTree.JCVariableDecl)) {
                throw new UnsupportedException("Loop initialization: " + that.init + " not supported.");
            }
            JCTree.JCVariableDecl loopVar = (JCTree.JCVariableDecl) that.init.get(0);
            RangeExtractor re = new RangeExtractor(M, loopVar.sym);
            re.extractRange(that.cond);
            JCTree.JCExpression lowerExpr = re.getMin();
            JCTree.JCExpression upperExpr = re.getMax();
            boolean upwards = true;
            if(lowerExpr == null && that.step != null && that.step.size() == 1) {
                JCTree.JCExpressionStatement step = that.step.get(0);
                if(step != null && step.expr instanceof JCTree.JCUnary) {
                    JCTree.JCUnary u = (JCTree.JCUnary) step.expr;
                    if(u.getTag().toString().equals("PREINC") || u.getTag().toString().equals("POSTINC")) {
                        if(loopVar.init != null) {
                            lowerExpr = loopVar.init;
                        }

                    }

                }
            }
            if(upperExpr == null && that.step != null && that.step.size() == 1) {
                JCTree.JCExpressionStatement step = that.step.get(0);
                if(step != null && step.expr instanceof JCTree.JCUnary) {
                    JCTree.JCUnary u = (JCTree.JCUnary) step.expr;
                    if(u.getTag().toString().equals("POSTDEC") || u.getTag().toString().equals("PREDEC")) {
                        upwards = false;
                        if(loopVar.init != null) {
                            upperExpr = loopVar.init;
                        }
                    }
                }
            }
            if(upperExpr == null || lowerExpr == null) {
                throw new UnsupportedException("Could not extract range from expr: " + that.cond);
            }
            int lower = ScriptEngineWrapper.getInstance().evalInt(lowerExpr.toString());
            int upper = ScriptEngineWrapper.getInstance().evalInt(upperExpr.toString());

            for (int i = (upwards ? lower : upper); i <= upper && i >= lower; i += (upwards ?  1 : - 1)) {
                //replace loop var in statements
                JCTree.JCBlock newBody = M.Block(0L, List.nil());
                JCTree.JCStatement replacedVar = ReplaceVisitor.replace(loopVar.sym, i, that.body, M);
                JCTree.JCStatement copy = this.copy(replacedVar);
                newBody.stats = newBody.stats.append(copy);
                newStatements = newStatements.append(newBody);
            }
            return M.Exec(null);
        }
       return super.visitJmlForLoop(that, p);
    }



    static class RangeExtractor extends JmlTreeScanner {
        private JCTree.JCExpression minResult;
        private JCTree.JCExpression maxResult;
        private final Symbol ident;

        private final JmlTree.Maker M;

        public RangeExtractor(JmlTree.Maker maker, Symbol ident) {
            this.ident = ident;
            this.M = maker;
        }

        @Override
        public void visitBinary(JCTree.JCBinary tree) {
            if(tree.getKind() == Tree.Kind.GREATER_THAN) {
                if(tree.getLeftOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getLeftOperand()).name.equals(ident.name)) {
                    if(minResult != null) {
                        throw new UnsupportedException("Ambiguous lower bound in range: ");
                    }
                    minResult = M.Binary(JCTree.Tag.PLUS, tree.getRightOperand(), M.Literal(1));
                    return;
                }
                if(tree.getRightOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getRightOperand()).name.equals(ident.name)) {
                    if(maxResult != null) {
                        throw new UnsupportedException("Ambiguous upper bound in range: ");
                    }
                    maxResult = M.Binary(JCTree.Tag.MINUS, tree.getLeftOperand(), M.Literal(1));
                    return;
                }
                throw new UnsupportedException("Error extracting range from quantifier: ");
            }
            else if(tree.getKind() == Tree.Kind.LESS_THAN) {
                if(tree.getLeftOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getLeftOperand()).name.equals(ident.name)) {
                    if(maxResult != null) {
                        throw new UnsupportedException("Ambiguous upper bound in range: ");
                    }
                    maxResult = M.Binary(JCTree.Tag.MINUS, tree.getRightOperand(), M.Literal(1));
                    return;
                }
                if(tree.getRightOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getRightOperand()).name.equals(ident.name)) {
                    if(minResult != null) {
                        throw new UnsupportedException("Ambiguous lower bound in range: ");
                    }
                    minResult = M.Binary(JCTree.Tag.PLUS, tree.getLeftOperand(), M.Literal(1));
                    return;
                }
                throw new UnsupportedException("Error extracting range from quantifier: ");
            }
            else if(tree.getKind() == Tree.Kind.GREATER_THAN_EQUAL) {
                if(tree.getLeftOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getLeftOperand()).name.equals(ident.name)) {
                    if(minResult != null) {
                        throw new UnsupportedException("Ambiguous lower bound in range: ");
                    }
                    minResult = tree.getRightOperand();
                    return;
                }
                if(tree.getRightOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent)tree.getRightOperand()).name.equals(ident.name)) {
                    if(maxResult != null) {
                        throw new UnsupportedException("Ambiguous upper bound in range: ");
                    }
                    maxResult = tree.getLeftOperand();
                    return;
                }
                throw new UnsupportedException("Error extracting range from quantifier: ");
            }
            else if(tree.getKind() == Tree.Kind.LESS_THAN_EQUAL) {
                if (tree.getLeftOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent) tree.getLeftOperand()).name.equals(ident.name)) {
                    if(maxResult != null) {
                        throw new UnsupportedException("Ambiguous upper bound in range: ");
                    }
                    maxResult = tree.getRightOperand();
                    return;
                }
                if (tree.getRightOperand().getKind() == Tree.Kind.IDENTIFIER && ((JCTree.JCIdent) tree.getRightOperand()).name.equals(ident.name)) {
                    if(minResult != null) {
                        throw new UnsupportedException("Ambiguous lower bound in range: ");
                    }
                    minResult = tree.getLeftOperand();
                    return;
                }
                throw new UnsupportedException("Error extracting range from quantifier: ");
            }
            else if(tree.getKind() == Tree.Kind.AND || tree.getKind() == Tree.Kind.CONDITIONAL_AND) {
                super.visitBinary(tree);
                return;
            }
            throw new UnsupportedException("Error extracting range from quantifier: " + tree);
        }


        public JCTree.JCExpression getMin() {
            return minResult;
        }

        public JCTree.JCExpression getMax() {
            return maxResult;
        }

        public void extractRange(JCTree tree) {
            try{
                maxResult = null;
                minResult = null;
                super.scan(tree);
            } catch (UnsupportedException e) {
                throw new UnsupportedException(e.getInnerMessage() + tree);
            }
        }
    }
}
