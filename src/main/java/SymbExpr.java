import com.sun.tools.javac.tree.JCTree;

public class SymbExpr extends Expr {
    JCTree.JCExpression expression;

    public SymbExpr(JCTree.JCExpression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "(" + expression + ")";
    }

    @Override
    public Expr simplify() {
        return this;
    }

    @Override
    public JCTree.JCExpression getAST() {
        return expression;
    }
}
