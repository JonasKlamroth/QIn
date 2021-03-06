package QIn.Expressions;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

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
    public List<JCTree.JCExpression> getAST() {
        return List.of(expression);
    }

    @Override
    public Expr getAbs() {
        return new MultOp(this, this);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof SymbExpr) {
            return ((SymbExpr) o).expression.equals(this.expression);
        }
        return false;
    }
}
