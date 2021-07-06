package CircuitTranslator.Expressions;

import CircuitTranslator.TransUtils;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;

public class MultOp extends Expr {
    Expr left;
    Expr right;

    public MultOp(Expr left, Expr right) {
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
        float constPart = 1.f;
        for(Const c : constChildren) {
            constPart *= c.val;
        }
        if(otherChildren.size() == 0) {
            return new Const(constPart);
        }
        if(constPart == 0.0f) {
            return new Const(0.0f);
        }

        Expr res = otherChildren.get(0);
        for(int i = 1; i < otherChildren.size(); ++i) {
            res = new MultOp(res, otherChildren.get(i));
        }
        if(constPart != 1.0f) {
            res = new MultOp(new Const(constPart), res);
        }
        return res;
    }

    @Override
    public JCTree.JCExpression getAST() {
        JCTree.JCBinary res = TransUtils.M.Binary(JCTree.Tag.MUL, left.getAST(), right.getAST());
        res.type = res.lhs.type;
        return res;
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
}
