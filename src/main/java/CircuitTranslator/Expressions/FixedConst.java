package CircuitTranslator.Expressions;

import CircuitTranslator.CLI;

public class FixedConst extends Const {

    public FixedConst(int val) {
        super(val);
    }

    @Override
    public Expr add(Expr c) {
        if(c instanceof FixedConst) {
            int newVal = (int)this.val + (int) ((FixedConst) c).val;
            return new FixedConst(newVal);
        }
        throw new RuntimeException("Adding differently typed consts is not supported.");
    }

    @Override
    public Expr mult(Expr c) {
        if(c instanceof FixedConst) {
            int newVal = ((int)this.val * (int) ((FixedConst) c).val) / ((int)Math.pow(CLI.base, CLI.numDigits));
            return new FixedConst(newVal);
        }
        throw new RuntimeException("Multiplying differently typed consts is not supported.");
    }

    @Override
    public boolean isOne() {
        return (int)this.val == (int)Math.pow(CLI.base, CLI.numDigits);
    }

    @Override
    public boolean isZero() {
        return (int)val == 0;
    }
}
