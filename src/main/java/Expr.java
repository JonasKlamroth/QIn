import com.sun.tools.javac.tree.JCTree;

public abstract class Expr {
    public abstract Expr simplify();

    public abstract JCTree.JCExpression getAST();

    public static Expr parse(JCTree.JCExpression s) {
        try {
            float f = Float.parseFloat(s.toString());
            return new Const(f);
        } catch (NumberFormatException e) {
            //String[] splits = s.split("\\[\\+\\*\\]");
            return new SymbExpr(s);
        }
    }

    public JCTree.JCExpression getSimplifiedAST() {
        return this.simplify().getAST();
    }
}
