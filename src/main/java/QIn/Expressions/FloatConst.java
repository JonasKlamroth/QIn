package QIn.Expressions;

public class FloatConst extends Const {
    public FloatConst(float val) {
        super(val);
    }

    public float getVal() {
        return (float)this.val;
    }

    @Override
    public Expr add(Expr a) {
        if(a instanceof FloatConst) {
            return new FloatConst(((FloatConst) a).getVal() + getVal());
        }
        return super.add(a);
    }

    @Override
    public Expr mult(Expr a) {
        if(a instanceof FloatConst) {
            return new FloatConst(((float) ((FloatConst) a).val) * (float)this.val);
        }
        return super.mult(a);
    }

    @Override
    public boolean isOne() {
        return (float)val == 1.0f;
    }

    @Override
    public boolean isZero() {
        return (float)val == 0.0f;
    }

    @Override
    public String toString() {
        return this.val + "f";
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof FloatConst) {
            return ((FloatConst) o).val.equals(this.val);
        }
        return false;
    }

    @Override
    public Expr getAbs() {
        if(getVal() >= 0.f) {
            return this;
        }
        return new FloatConst(-getVal());
    }
}
