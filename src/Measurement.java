import Expressions.Const;
import Expressions.Expr;

import java.util.ArrayList;

public class Measurement extends Operation {
    public int cBit;
    public boolean considerAll;

    public Measurement(int qBit, int cBit, boolean considerAll) {
        this.qBit = qBit;
        this.cBit = cBit;
        this.considerAll = considerAll;
    }

    @Override
    void apply(Expr[][] qstate, Boolean[] cstate) {
        Expr[][] state0 = new Expr[qstate.length][1];
        Expr[][] state1 = new Expr[qstate.length][1];
        int shift = Utils.log2(qstate.length) - qBit - 1;
        for(int i = 0;  i < qstate.length; ++i) {
            if((i & (1 << shift)) == 0) {
                state0[i][0] = new Const(0.0f);
                state1[i][0] = qstate[i][0];
            } else {
                state0[i][0] = qstate[i][0];
                state1[i][0] = new Const(0.0f);
            }
        }
        resultingQStates = new ArrayList<>();
        resultingQStates.add(state0);
        resultingQStates.add(state1);

        Boolean[] newCState0 = new Boolean[cstate.length];
        Boolean[] newCState1 = new Boolean[cstate.length];
        for(int i = 0; i < cstate.length; ++i) {
            if(i == cBit) {
                newCState0[i] = false;
                newCState1[i] = true;
            } else {
                newCState0[i] = cstate[i];
                newCState1[i] = cstate[i];
            }
        }
        resultingCStates = new ArrayList<>();
        resultingCStates.add(newCState0);
        resultingCStates.add(newCState1);
    }
}
