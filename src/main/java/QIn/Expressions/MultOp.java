package QIn.Expressions;

import QIn.CLI;
import QIn.TransUtils;
import com.sun.tools.javac.tree.JCTree;
import java.util.ArrayList;
import java.util.List;

public class MultOp extends Expr {
    Expr left;
    Expr right;

    protected MultOp(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return this.left.simplify() + " * " + this.right.simplify();
    }

    @Override
    public Expr simplify() {
        List<Const> constChildren = new ArrayList<>();
        List<Expr> otherChildren = new ArrayList<>();
        for(Expr e : getRecOperants()) {
            e = e.simplify();
            if(e instanceof Const) {
                constChildren.add((Const) e);
            } else {
                otherChildren.add(e);
            }
        }

        Const constPart = null;
        if(!constChildren.isEmpty()) {
            constPart = constChildren.get(0);
            for (Const c : constChildren.subList(1, constChildren.size())) {
                constPart = (Const) constPart.mult(c);
            }
        }
        if(otherChildren.size() == 0) {
            return constPart;
        }
        if(constPart != null && constPart.isZero()) {
            return constPart;
        }

        Expr res = otherChildren.get(0);
        for(int i = 1; i < otherChildren.size(); ++i) {
            res = new MultOp(res, otherChildren.get(i));
        }
        if(constPart != null && !constPart.isOne()) {
            res = new MultOp(constPart, res);
        }
        return res;
    }

    @Override
    public com.sun.tools.javac.util.List<JCTree.JCExpression> getAST() {
        JCTree.JCBinary res = TransUtils.M.Binary(JCTree.Tag.MUL, left.getAST().get(0), right.getAST().get(0));
        res.type = res.lhs.type;
        if(!CLI.useFix) {
            res = TransUtils.M.Binary(JCTree.Tag.DIV, res, TransUtils.M.Literal((int)Math.pow(CLI.base, CLI.numDigits)));
            res.type = res.lhs.type;
        }
        return com.sun.tools.javac.util.List.of(res);
    }

    List<Expr> getRecOperants() {
        List<Expr> res = new ArrayList<>();
        if(left instanceof MultOp) {
            res.addAll(((MultOp) left).getRecOperants());
        } else {
            res.add(left);
        }
        if(right instanceof MultOp) {
            res.addAll(((MultOp) right).getRecOperants());
        } else {
            res.add(right);
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof MultOp) {
            return ((MultOp) o).left.equals(this.left) && ((MultOp) o).right.equals(this.right);
        }
        return false;
    }
}
