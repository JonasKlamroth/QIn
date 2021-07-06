package CircuitTranslator.Expressions;

import CircuitTranslator.CLI;

public class FixedConst extends Const {

    public FixedConst(int val) {
        super(val);
    }

    @Override
    public Const add(Const c) {
        if(c instanceof FixedConst) {
            int newVal = (int)this.val + (int)c.val;
            return new FixedConst(newVal);
        }
        throw new RuntimeException("Adding differently typed consts is not supported.");
    }

    @Override
    public Const mult(Const c) {
        if(c instanceof FixedConst) {
            int newVal = ((int)this.val * (int)c.val) / ((int)Math.pow(10, CLI.numDigits));
            return new FixedConst(newVal);
        }
        throw new RuntimeException("Multiplying differently typed consts is not supported.");
    }

    @Override
    public boolean isOne() {
        return (int)this.val == 1;
    }

    @Override
    public boolean isZero() {
        return (int)val == 0;
    }
}
