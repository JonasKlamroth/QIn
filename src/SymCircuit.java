import Expressions.Const;
import Expressions.Expr;
import Expressions.Ident;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymCircuit {
   private int numQbits;
    private int numCBits;
    private int numfloatVars = 0;
    private int numBoolVars = 0;
    private int stateSize;
    private int currentStateIdx = 0;
    private int currentCStateIdx = 0;
    private final Expr[][] initialState;
    private List<Operation> operations = new ArrayList<>();

    public SymCircuit(int numQbits, int numCBits) {
        this.numQbits = numQbits;
        this.numCBits = numCBits;
        this.stateSize = (int)Math.pow(2, numQbits);
        this.initialState = new Expr[stateSize][1];
        for(int i = 0; i < stateSize; ++i) {
            this.initialState[i] = new Expr[]{new Const(0.0f)};
        }
        this.initialState[0][0] = new Const(1.0f);
    }

    public SymCircuit(int numQbits, int numCBits, List<String[]> initialStates) {
        this.numQbits = numQbits;
        this.numCBits = numCBits;
        this.stateSize = (int) Math.pow(2, numQbits);
        assert initialStates.size() == numQbits;
        Expr[][] state = arrayToState(initialStates.get(0));
        for(int i = 1; i < initialStates.size(); ++i) {
            state = Utils.tensorProd(state, arrayToState(initialStates.get(i)));
        }
        this.initialState = state;
    }

    public SymCircuit(int numQbits, int numCBits, String[] initialState) {
        this.numQbits = numQbits;
        this.numCBits = numCBits;
        this.stateSize = (int)Math.pow(2, numQbits);
        assert initialState.length == stateSize;
        this.initialState = new Expr[stateSize][1];
        for(int i = 0; i < stateSize; ++i) {
            this.initialState[i] = new Expr[]{Expr.parse(initialState[i])};
        }

    }

    Expr[][] arrayToState(String[] a) {
        Expr[][] state = new Expr[a.length][1];
        for(int i = 0; i < a.length; ++i) {
            state[i][0] = Expr.parse(a[i]);
        }
        return state;
    }

    private String newVarFromCState(Boolean[] cstate) {
        StringBuilder sb = new StringBuilder();
        sb.append("boolean[] c_").append(++currentCStateIdx).append(" = new boolean[]");
        sb.append(Arrays.toString(cstate).replace('[', '{').replace(']', '}').replace("null", "false"));
        sb.append(";\n");
        return sb.toString();
    }

    private String newVarFromState(Expr[][] state) {
        StringBuilder sb = new StringBuilder();
        sb.append("float[] q_").append(++currentStateIdx).append(" = new float[]");
        sb.append(getStateArray(state));
        sb.append(";\n");
        for(int i = 0; i < stateSize; ++i) {
            state[i][0] = new Ident("q_" + currentStateIdx + "[" + i + "]");
        }
        return sb.toString();
    }

    private Expr[][] newState() {
        Expr[][] res = new Expr[stateSize][1];
        res[0][0] = new Const(1.0f);
        for(int i = 1; i < stateSize; ++i) {
            res[i][0] = new Const(0.0f);
        }
        return res;
    }

    public void h(int qbit) {
        u(Utils.H, qbit);
    }

    public void x(int qbit) {
        u(Utils.X, qbit);
    }

    public void z(int qbit) {
        u(Utils.Z, qbit);
    }

    public void cnot(int cqbit, int tqbit) {
        u(Utils.CX, cqbit, tqbit);
    }

    public void u(String[][] m, int... qbits) {
        assert m.length > 1 && m.length <= stateSize;
        assert m.length == m[0].length;
        assert Math.pow(2, qbits.length) == m.length;
        for(int i = 1; i < qbits.length; ++i) {
            assert qbits[i] == qbits[i - 1] + 1;
        }
        Expr[][] u = new Expr[m.length][m.length];
        for(int i = 0; i < m.length; ++i) {
            for(int j = 0; j < m.length; j++) {
                u[i][j] = Expr.parse(m[i][j]);
            }
        }
        u(u, qbits);
    }
    public void u(Expr[][] m, int... qbits) {
        assert m.length > 1 && m.length <= stateSize;
        assert m.length == m[0].length;
        assert Math.pow(2, qbits.length) == m.length;
        for(int i = 1; i < qbits.length; ++i) {
            assert qbits[i] == qbits[i - 1] + 1;
        }
        Expr[][] m1 = Utils.adapt(m, qbits[0], numQbits);
        operations.add(new Unitary(qbits[0], m1));
    }

    public void measureMax(int qBit, int cBit) {
        operations.add(new Measurement(qBit, cBit, false));
    }

    public void measure(int qBit, int cBit) {
        operations.add(new Measurement(qBit, cBit, true));
    }

    public String getTranslation() {
        String res = "float[] q_res = null;\n";
        res += "boolean[] c_res = null;\n";
        res += newVarFromState(initialState);
        return res + getTranslation(operations, initialState, new Boolean[numCBits]);
    }

    private String getTranslation(List<Operation> operations, Expr[][] qstate, Boolean[] cstate) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < operations.size(); ++i) {
            Operation o = operations.get(i);
            o.apply(qstate, cstate);
            if(o instanceof Measurement) {
                String cond = "";
                if(((Measurement) o).considerAll) {
                    sb.append("boolean b_" + ++numBoolVars + " = CProver.nondetBoolean();\n");
                    cond = "b_" + numBoolVars;
                } else {
                    String val0 = "";
                    String val1 = "";
                    int shift = numQbits - o.qBit - 1;
                    for(int j = 0; j < stateSize; ++j) {
                        if((j & (1 << shift)) == 0) {
                            val0 += "(" + qstate[j][0] + " * " + qstate[j][0] + ") + ";
                        } else {
                            val1 += "(" + qstate[j][0] + " * " + qstate[j][0] + ") + ";
                        }
                    }
                    val0 += "0.0f;\n";
                    val1 += "0.0f;\n";

                    sb.append("float dvar_" + ++numfloatVars + " = " + val0);
                    sb.append("float dvar_" + ++numfloatVars + " = " + val1);
                    cond = "dvar_" + (numfloatVars - 1) + " < " + "dvar_" + numfloatVars;
                }
                sb.append("if(" + cond + ") {\n");
                sb.append(newVarFromState(o.resultingQStates.get(0)));
                sb.append(newVarFromCState(o.resultingCStates.get(0)));
                if(i + 1 < operations.size()) {
                    sb.append(getTranslation(operations.subList(i + 1, operations.size()), o.resultingQStates.get(0), o.resultingCStates.get(0)));
                }
                if(i == operations.size() - 1) {
                    sb.append("q_res = q_" + currentStateIdx + ";\n");
                    sb.append("c_res = c_" + currentCStateIdx + ";\n");
                }
                sb.append("} else {\n");
                sb.append(newVarFromState(o.resultingQStates.get(1)));
                sb.append(newVarFromCState(o.resultingCStates.get(1)));
                if(i + 1 < operations.size()) {
                    sb.append(getTranslation(operations.subList(i + 1, operations.size()), o.resultingQStates.get(1), o.resultingCStates.get(1)));
                }
                if(i == operations.size() - 1) {
                    sb.append("q_res = q_" + currentStateIdx + ";\n");
                    sb.append("c_res = c_" + currentCStateIdx + ";\n");
                }
                sb.append("}\n");
                break;
            } else {
                qstate = o.resultingQStates.get(0);
                sb.append(newVarFromState(qstate));
                if(i == operations.size() - 1) {
                    sb.append("q_res = q_" + currentStateIdx + ";\n");
                    sb.append("c_res = c_" + currentCStateIdx + ";\n");
                }
            }
        }

        return sb.toString();
    }

    private void setQBit(int qBit, boolean value) {

    }

    public String getStateArray(Expr[][] state) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Expr[] exp : state) {
            assert exp.length == 1;
            sb.append(exp[0].simplify());
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

}
