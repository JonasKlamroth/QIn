package Expressions;

public class MultOp extends Expr {
    Expr left;
    Expr right;

    public MultOp(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return this.left.simplify() + " * " + this.right.simplify();
    }

    @Override
    public String simplify() {
        try {
            float left = Float.parseFloat(this.left.simplify());
            float right = Float.parseFloat(this.right.simplify());
            return (left * right) + "f";
        } catch (NumberFormatException ex) {
            try{
                float left = Float.parseFloat(this.left.simplify());
                if(left == 1.0f) {
                    return right.simplify();
                }
                if(left == 0.0f) {
                    return "0.0f";
                }
            } catch (NumberFormatException ignored) {
            }
            try{
                float right = Float.parseFloat(this.right.simplify());
                if(right == 1.0f) {
                    return left.simplify();
                }
                if(right == 0.0f) {
                    return "0.0f";
                }
            } catch (NumberFormatException ignored) {
            }
            return toString();
        }
    }
}
