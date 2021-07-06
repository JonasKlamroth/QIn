package CircuitTranslator.Expressions;

import CircuitTranslator.CLI;
import CircuitTranslator.TransUtils;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;

public class AddOp extends Expr {
    public Expr left;
    public Expr right;

    public AddOp(Expr left, Expr right) {
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
                constPart = constPart.add(c);
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
    public JCTree.JCExpression getAST() {
        JCTree.JCBinary res = TransUtils.M.Binary(JCTree.Tag.PLUS, left.getAST(), right.getAST());
        res.type = res.lhs.type;
        return res;
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
}