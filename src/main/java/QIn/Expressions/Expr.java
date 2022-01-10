package QIn.Expressions;

import QIn.Utils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

public abstract class Expr {
    public abstract Expr simplify();

    public abstract List<JCTree.JCExpression> getAST();

    public static Expr parse(JCTree.JCExpression s) {
        try {
            float f = Float.parseFloat(s.toString());
            return Utils.getConst(f);
        } catch (NumberFormatException e) {
            //String[] splits = s.split("\\[\\+\\*\\]");
            return new SymbExpr(s);
        }
    }

    public List<JCTree.JCExpression> getSimplifiedAST() {
        return this.simplify().getAST();
    }

    public Expr mult(Expr e) {
        if(e instanceof ComplexExpression) {
            return e.mult(this);
        }
        return new MultOp(this, e);
    }

    public Expr add(Expr e) {
        if(e instanceof ComplexExpression) {
            return e.add(this);
        }
        return new AddOp(this, e);
    }

    abstract public Expr getAbs();
}
