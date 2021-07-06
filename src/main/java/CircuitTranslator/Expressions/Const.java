package CircuitTranslator.Expressions;

import CircuitTranslator.TransUtils;
import com.sun.tools.javac.tree.JCTree;

import java.lang.reflect.Type;

public abstract class Const extends Expr {
    Object val;

    public Const(Object val) {
        this.val = val;
    }


    public abstract Const add(Const c);

    public abstract Const mult(Const c);

    public String toString() {
        return val.toString();
    }

    @Override
    public Expr simplify() {
        return this;
    }

    @Override
    public JCTree.JCExpression getAST() {
        return TransUtils.M.Literal(val);
    }

    public abstract boolean isOne();

    public abstract boolean isZero();
}
