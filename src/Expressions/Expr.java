package Expressions;

public abstract class Expr {
    public abstract String simplify();

    public static Expr parse(String s) {
        try {
            float f = Float.parseFloat(s);
            return new Const(f);
        } catch (NumberFormatException e) {
            //String[] splits = s.split("\\[\\+\\*\\]");
            return new Ident(s);
        }
    }

}
