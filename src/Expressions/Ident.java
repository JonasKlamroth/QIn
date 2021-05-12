package Expressions;

public class Ident extends Expr {
    String name;

    public Ident(String name) {
        this.name = name;
    }

    public String toString() {
        return "(" + name + ")";
    }

    @Override
    public String simplify() {
        return "(" + name + ")";
    }
}
