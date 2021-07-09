package CircuitTranslator.Expressions;

public class FloatConst extends Const {
    public FloatConst(float val) {
        super(val);
    }

    @Override
    public Expr add(Expr a) {
        if(a instanceof FloatConst) {
            return new FloatConst((float) ((FloatConst) a).val + (float)this.val);
        }
        throw new RuntimeException("Unsupported addition of different types.");
    }

    @Override
    public Expr mult(Expr a) {
        if(a instanceof FloatConst) {
            return new FloatConst(((float) ((FloatConst) a).val) * (float)this.val);
        }
        throw new RuntimeException("Unsupported addition of different types.");
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
}
