package Expressions;

public class AddOp extends Expr {
    Expr left;
    Expr right;

    public AddOp(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "(" + this.left.simplify() + " + " + this.right.simplify() + ")";
    }

    @Override
    public String simplify() {
        try {
            float left = Float.parseFloat(this.left.simplify());
            float right = Float.parseFloat(this.right.simplify());
            return (left + right) + "f";
        } catch (NumberFormatException ex) {
            try{
                float left = Float.parseFloat(this.left.simplify());
                if(left == 0.0f) {
                    return right.simplify();
                }
            } catch (NumberFormatException ignored) {
            }
            try{
                float right = Float.parseFloat(this.right.simplify());
                if(right == 0.0f) {
                    return left.simplify();
                }
            } catch (NumberFormatException ignored) {
            }
            return toString();
        }
    }
}
