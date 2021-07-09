package CircuitTranslator.Expressions;

import CircuitTranslator.TransUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

import java.lang.reflect.Type;

public abstract class Const extends Expr {
    Object val;

    public Const(Object val) {
        this.val = val;
    }

    public String toString() {
        return val.toString();
    }

    @Override
    public Expr simplify() {
        return this;
    }

    @Override
    public List<JCTree.JCExpression> getAST() {
        return List.of(TransUtils.M.Literal(val));
    }

    public abstract boolean isOne();

    public abstract boolean isZero();
}
