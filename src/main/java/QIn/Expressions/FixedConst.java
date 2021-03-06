package QIn.Expressions;

import QIn.CLI;

public class FixedConst extends Const {

    public FixedConst(int val) {
        super(val);
    }

    public int getVal() {
        return (int)this.val;
    }

    @Override
    public Expr add(Expr c) {
        if(c instanceof FixedConst) {
            int newVal = getVal() + ((FixedConst) c).getVal();
            return new FixedConst(newVal);
        }
        return super.add(c);
    }

    @Override
    public Expr mult(Expr c) {
        if(c instanceof FixedConst) {
            int newVal = ((int)this.val * (int) ((FixedConst) c).val) / ((int)Math.pow(CLI.base, CLI.numDigits));
            return new FixedConst(newVal);
        }
        return super.mult(c);
    }

    @Override
    public boolean isOne() {
        return (int)this.val == (int)Math.pow(CLI.base, CLI.numDigits);
    }

    @Override
    public boolean isZero() {
        return (int)val == 0;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof FixedConst) {
            return ((FixedConst) o).val.equals(this.val);
        }
        return false;
    }

    @Override
    public Expr getAbs() {
        if(getVal() >= 0) {
            return this;
        }
        return new FixedConst(-getVal());
    }
}
