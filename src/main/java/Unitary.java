import java.util.ArrayList;
import java.util.Arrays;

public class Unitary extends Operation {
    private Expr[][] m;

    public Unitary(int qBit, Expr[][] m) {
        this.m = m;
        this.qBit = qBit;
    }

    @Override
    void apply(Expr[][] state, Boolean[] cstate) {
        resultingQStates = new ArrayList<>();
        resultingQStates.add(Utils.mult(m, state));

        resultingCStates = new ArrayList<>();
        Boolean[] newCstate = Arrays.copyOf(cstate, cstate.length);
        resultingCStates.add(newCstate);

    }
}
