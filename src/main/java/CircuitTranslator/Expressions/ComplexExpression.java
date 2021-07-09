package CircuitTranslator.Expressions;

import CircuitTranslator.CLI;
import CircuitTranslator.Utils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

public class ComplexExpression extends Expr {

    private Expr img;
    private Expr real;

    public ComplexExpression(Expr real, Expr img) {
        this.real = real;
        this.img = img;
    }

    @Override
    public Expr simplify() {
        img = img.simplify();
        real = real.simplify();
        return this;
    }

    @Override
    public List<JCTree.JCExpression> getAST() {
        return List.of(real.getAST().get(0), img.getAST().get(0));
    }

    @Override
    public Expr mult(Expr e) {
        if(e instanceof ComplexExpression) {
            ComplexExpression other = (ComplexExpression)e;
            Expr newReal = this.real.mult(other.real).add(this.img.mult(other.img).mult(Utils.getRealConst(-1.0f)));
            Expr newImg = this.real.mult(other.img).add(this.img.mult(other.real));
            return new ComplexExpression(newReal, newImg);
        } else {
            return this.mult(getComplex(e));
        }
    }

    @Override
    public Expr add(Expr e) {
        if(e instanceof ComplexExpression) {
            ComplexExpression other = (ComplexExpression)e;
            Expr newReal = this.real.add(other.real);
            Expr newImg = this.img.add(other.img);
            return new ComplexExpression(newReal, newImg);
        } else {
            return this.add(getComplex(e));
        }
    }

    public Expr getAbs() {
        return this.real.mult(this.real).add(this.img.mult(this.img));
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ComplexExpression) {
            return ((ComplexExpression) o).real.equals(this.real) && ((ComplexExpression) o).img.equals(this.img);
        }
        return false;
    }

    @Override
    public String toString() {
        return real.toString() + " + " + img.toString() + "i";
    }

    public ComplexExpression getComplex(Expr e) {
        if(e instanceof ComplexExpression) {
            return (ComplexExpression) e;
        }
        if(CLI.useFloat) {
            return new ComplexExpression(e, new FloatConst(0.0f));
        } else {
            return new ComplexExpression(e, new FixedConst(0));
        }
    }
}
