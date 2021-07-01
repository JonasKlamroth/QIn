import com.sun.tools.javac.tree.JCTree;

public class Const extends Expr {
    float val;

    public Const(float val) {
        this.val = val;
    }

    public String toString() {
        return val + "f";
    }

    @Override
    public Expr simplify() {
        return this;
    }

    @Override
    public JCTree.JCExpression getAST() {
        return TransUtils.M.Literal(val);
    }
}
