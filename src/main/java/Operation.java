import java.util.ArrayList;
import java.util.List;

public abstract class Operation {
    List<Expr[][]> resultingQStates = new ArrayList<>();
    List<Boolean[]> resultingCStates = new ArrayList<>();
    protected int qBit;

    abstract void apply(Expr[][] state, Boolean[] cstate);

}
