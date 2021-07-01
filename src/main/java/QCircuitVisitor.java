import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.NewClassTree;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeCopier;
import org.jmlspecs.openjml.JmlTreeUtils;

public class QCircuitVisitor extends JmlTreeCopier {
    private static final String CIRCUIT_TYPE = "SymCircuit";
    private final JmlTreeUtils treeutils;
    private List<JCTree.JCStatement> newStatements = List.nil();
    private final Symtab syms;
    private Symbol currentSymbol;
    int numQbits = -1;
    int numCbits = -1;
    private JCTree.JCVariableDecl qStateVar;
    private Expr[][] q_state;
    private int stateSize;
    private Expr[][] qState;

    public QCircuitVisitor(Context context, JmlTree.Maker maker) {
        super(context, maker);
        this.treeutils = JmlTreeUtils.instance(context);
        this.syms = Symtab.instance(context);
    }

    @Override
    public JCTree visitNewClass(NewClassTree node, Void p) {
        return super.visitNewClass(node, p);
    }

    @Override
    public JCTree visitJmlVariableDecl(JmlTree.JmlVariableDecl that, Void p) {
        if(that.type.toString().equals(CIRCUIT_TYPE)) {
            JCTree.JCNewClass clazz = null;
            if(that.init instanceof JCTree.JCNewClass) {
                clazz = (JCTree.JCNewClass) that.init;
            } else {
                System.out.println("Only SymCircuits created with constructor supported for now.");
                return super.visitJmlVariableDecl(that, p);
            }
            if(clazz.args == null || clazz.args.size() < 2) {
                System.out.println("Error creating circuit.");
                return super.visitJmlVariableDecl(that, p);
            }
            if(clazz.args.size() >= 3) {
                System.out.println("Translating circuits with initial states currently not supported.");
                return super.visitJmlVariableDecl(that, p);
            }
            numQbits = Integer.parseInt(clazz.args.get(0).toString());
            stateSize = (int)Math.pow(numQbits, 2);
            numCbits = Integer.parseInt(clazz.args.get(1).toString());
            qState = Utils.getInitialState(numQbits);
            JCTree.JCExpression init = TransUtils.makeArrayExpression(qState);
            qStateVar = treeutils.makeVarDef(init.type, M.Name("q_state"), currentSymbol, init);
            return qStateVar;
            //newStatements = newStatements.append(q_statedecl);
        }
        return super.visitJmlVariableDecl(that, p);
    }

    @Override
    public JCTree visitJmlMethodDecl(JmlTree.JmlMethodDecl that, Void p) {
        currentSymbol = that.sym;
        List<JCTree.JCStatement> body = that.body.stats;
        that.body.stats = List.nil();
        JmlTree.JmlMethodDecl copy = (JmlTree.JmlMethodDecl) super.visitJmlMethodDecl(that, p);
        for(JCTree.JCStatement st : body) {
            newStatements = List.nil();
            JCTree.JCStatement statementCopy = super.copy(st);
            if(newStatements.isEmpty()) {
                copy.body.stats = copy.body.stats.append(statementCopy);
            } else {
                copy.body.stats = copy.body.stats.appendList(newStatements);
            }
        }
        return copy;
    }

    @Override
    public JCTree visitMethodInvocation(MethodInvocationTree node, Void p) {
        if(node instanceof JCTree.JCMethodInvocation) {
            JCTree.JCMethodInvocation methodInvocation = (JCTree.JCMethodInvocation) node;
            if(methodInvocation.meth instanceof JCTree.JCFieldAccess) {
                JCTree.JCFieldAccess fullMethod = (JCTree.JCFieldAccess) methodInvocation.meth;
                if(fullMethod.selected.type.toString().equals(CIRCUIT_TYPE)) {
                    Expr[][] unitary = null;
                    int qBit = -1;
                    if(fullMethod.name.toString().equals("u")) {
                        assert methodInvocation.args.size() == 2;
                        if(methodInvocation.args.get(0) instanceof JCTree.JCIdent) {
                            unitary = TransUtils.getUnitaryFromIdent((JCTree.JCIdent) methodInvocation.args.get(0), stateSize);
                        }
                        qBit = Integer.parseInt(methodInvocation.args.get(1).toString());
                    } else if(fullMethod.name.toString().startsWith("measure")) {
                        return super.visitMethodInvocation(node, p);
                    } else {
                        unitary = Utils.getUnitaryForName(fullMethod.name.toString());
                        qBit = Integer.parseInt(methodInvocation.args.get(0).toString());
                    }
                    qState = Utils.apply(unitary, qBit, qState);
                    newStatements = newStatements.append(TransUtils.updateState(qState, qStateVar));
                }
            }
        }
        return super.visitMethodInvocation(node, p);
    }
}
