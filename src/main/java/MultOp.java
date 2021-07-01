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

        Expr res = otherChildren.get(0).simplify();
        for(int i = 1; i < otherChildren.size(); ++i) {
            res = new MultOp(res, otherChildren.get(i).simplify());
        }
        if(constPart != 1.0f) {
            res = new MultOp(new Const(constPart), res);
        }
        return res;
    }

    @Override
    public JCTree.JCExpression getAST() {
        Expr simplified = this.simplify();
        if(simplified instanceof MultOp) {
            JCTree.JCBinary res = TransUtils.M.Binary(JCTree.Tag.PLUS, ((MultOp) simplified).right.getAST(), ((MultOp) simplified).left.getAST());
            res.type = res.lhs.type;
            return res;
        }
        return simplified.getAST();
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
