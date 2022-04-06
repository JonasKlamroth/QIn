package QIn;

import QIn.Expressions.Expr;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Position;
import org.jmlspecs.openjml.*;

import java.io.StringWriter;


public class JavaCircuitWriter {


    private JmlTree.JmlClassDecl cl;
    private JCTree.JCMethodDecl methodDecl;
    private List<JCTree.JCVariableDecl> qStateVars;
    private Expr[][] qState;
    private final int numQbits;

    private final JmlTreeUtils treeutils;
    private final JmlTree.Maker M;
    private List<JCTree.JCStatement> statements;
    private int measureVarCounter;

    public JavaCircuitWriter(int numQbits) {
        IAPI api;
        try {
            api = Factory.makeAPI();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing JavaCircuitWriter.");
        }
        Context context = api.context();
        this.treeutils = JmlTreeUtils.instance(context);
        this.M = JmlTree.Maker.instance(context);
        TransUtils.init(context);

        this.qState = Utils.getInitialState(numQbits);
        this.numQbits = numQbits;

        initCircuit();
    }

    public JavaCircuitWriter(Expr[][] initialState) {
        IAPI api;
        try {
            api = Factory.makeAPI();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing JavaCircuitWriter.");
        }
        Context context = api.context();
        this.treeutils = JmlTreeUtils.instance(context);
        this.M = JmlTree.Maker.instance(context);
        TransUtils.init(context);

        this.qState = initialState;
        this.numQbits = Utils.log2(initialState.length);

        initCircuit();
    }

    private void initCircuit() {
        methodDecl = M.MethodDef(M.Modifiers(1L | 8L), M.Name("placeholder"), M.Type(new Type.JCVoidType()), List.nil(), List.nil(), List.nil(), M.Block(0L, List.nil()), null);
        cl = M.ClassDef(M.Modifiers(1L), M.Name("placeholder"), List.nil(), null, List.nil(), List.of(methodDecl));

        List<JCTree.JCExpression> init = TransUtils.makeArrayExpression(qState);
        qStateVars = List.nil();
        statements = List.nil();
        for(int i = 0; i < init.size(); ++i) {
            qStateVars = qStateVars.append(treeutils.makeVarDef(init.get(i).type, M.Name("q_state_" + i), methodDecl.sym, init.get(i)));
            if(i != init.size() - 1) {
                statements = statements.append(qStateVars.get(qStateVars.size() - 1));
            }
        }
    }


    public String prettyPrint(String className, String methodName) {
        List<JCTree.JCStatement> bodyList = statements;
        JCTree.JCBlock body = M.Block(0L, bodyList);
        cl.name = M.Name(className);
        methodDecl.name = M.Name(methodName);
        methodDecl.body = body;

        StringWriter sw = new StringWriter();
        JmlPretty cpp = new JmlPretty(sw, true);
        cl.accept(cpp);
        return sw.toString();
    }


    public void applyGate(Expr[][] gate, int... qbit) {
        if (1 << qbit.length != gate.length) {
            throw new RuntimeException("Cant apply gate of size " + gate.length + " to " + (1 << qbit.length) + " qbits.");
        }
        if (numQbits < qbit[0] + Utils.log2(gate.length)) {
            throw new RuntimeException("Cant apply gate of size " + gate.length + " to " + (qbit[0]) + "th qbit in a state with " + numQbits + " qbits.");
        }
        if (qbit[0] >= numQbits || qbit[0] < 0) {
            throw new RuntimeException("Cant apply gate to " + (qbit[0]) + "th qbit in a state with " + numQbits + " qbits.");
        }
        qState = Utils.apply(gate, qbit[0], qState);
        statements = statements.appendList(TransUtils.updateState(qState, qStateVars));
    }

    public void measure(int qbit) {
        if (qbit >= numQbits || qbit < 0) {
            throw new RuntimeException("Cant measure " + qbit + "th qbit in a state with " + numQbits + " qbits.");
        }
        Utils.anonymizeState(qState, qStateVars);
        Expr[][] trueState = Utils.applyMeasurement(qState, qbit, true);
        Expr[][] falseState = Utils.applyMeasurement(qState, qbit, false);
        JCTree.JCExpression cond = TransUtils.makeMeasureMaxCondition(qState, qbit);
        statements = statements.append(treeutils.makeVarDef(M.Literal(true).type, M.Name("$$_tmp_measureVar" + ++measureVarCounter), methodDecl.sym, Position.NOPOS));
        JCTree.JCIdent tmp = M.Ident("$$_tmp_measureVar" + measureVarCounter);
        JCTree.JCBlock ifBlock = M.Block(0L, TransUtils.updateState(falseState, qStateVars).append(TransUtils.setCState(tmp,  true)));
        JCTree.JCBlock elseBlock = M.Block(0L, TransUtils.updateState(trueState, qStateVars).append(TransUtils.setCState(tmp,  false)));
        JCTree.JCIf jcIf = M.If(cond, ifBlock, elseBlock);
        statements = statements.append(jcIf);
        Utils.anonymizeState(qState, qStateVars);
    }

    public void applySwap(int qBit1, int qBit2){
        Utils.applySwap(qState, qBit1, qBit2);
    }

    public static void main(String[] args) {
        JavaCircuitWriter jv = new JavaCircuitWriter(3);
        jv.applyGate(Utils.getUnitaryForName("x"), 0);
        jv.measure(0);
        System.out.println(jv.prettyPrint("test", "testM"));
    }
}
