package Expressions;

public class Const extends Expr {
    float val;

    public Const(float val) {
        this.val = val;
    }

    public String toString() {
        return val + "f";
    }

    @Override
    public String simplify() {
        return toString();
    }
}
