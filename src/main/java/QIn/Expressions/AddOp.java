package QIn.Expressions;

import QIn.TransUtils;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;

public class AddOp extends Expr {
    public Expr left;
    public Expr right;

    protected AddOp(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "(" + this.left.simplify() + " + " + this.right.simplify() + ")";
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
                constPart = (Const) constPart.add(c);
            }
        }
        if(otherChildren.size() == 0) {
            return constPart;
        }

        Expr res = otherChildren.get(0);
        for(int i = 1; i < otherChildren.size(); ++i) {
            res = new AddOp(res, otherChildren.get(i));
        }
        if(constPart != null && !constPart.isZero() ) {
            res = new AddOp(constPart, res);
        }
        return res;
    }

    @Override
    public com.sun.tools.javac.util.List<JCTree.JCExpression> getAST() {
        JCTree.JCBinary res = TransUtils.M.Binary(JCTree.Tag.PLUS, left.getAST().get(0), right.getAST().get(0));
        res.type = res.lhs.type;
        return com.sun.tools.javac.util.List.of(res);
    }

    List<Expr> getRecOperants() {
        List<Expr> res = new ArrayList<>();
        if(left instanceof AddOp) {
            res.addAll(((AddOp) left).getRecOperants());
        } else {
            res.add(left);
        }
        if(right instanceof AddOp) {
            res.addAll(((AddOp) right).getRecOperants());
        } else {
            res.add(right);
        }
        return res;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof AddOp) {
            return ((AddOp) o).left.equals(this.left) && ((AddOp) o).right.equals(this.right);
        }
        return false;
    }
}