package CircuitTranslator.Expressions;

public class FloatConst extends Const {
    public FloatConst(float val) {
        super(val);
    }

    public Const add(Const a) {
        if(a instanceof FloatConst) {
            return new FloatConst((float) a.val + (float)this.val);
        }
        throw new RuntimeException("Unsupported addition of different types.");
    }

    public Const mult(Const a) {
        if(a instanceof FloatConst) {
            return new FloatConst(((float) a.val) * (float)this.val);
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
}
