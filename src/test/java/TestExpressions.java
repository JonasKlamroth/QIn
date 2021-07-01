import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressions {

    @Test
    public void testSimplification() {
        Expr e = new MultOp(new MultOp(new SymbExpr("a"), new Const(0.5f)), new Const(0.5f));
        assertEquals("0.25f * (a)", e.simplify());
    }

    @Test
    public void testSimplification1() {
        Expr e = new AddOp(new AddOp(new SymbExpr("a"), new Const(0.5f)), new Const(0.5f));
        assertEquals("(1.0f + (a))", e.simplify());
    }

    @Test
    public void testSimplification2() {
        Expr e = new MultOp(new SymbExpr("a"), new Const(0.f));
        assertEquals("0.0f", e.simplify());
    }

    @Test
    public void testSimplification3() {
        Expr e = new MultOp(new SymbExpr("a"), new Const(1.0f));
        assertEquals("(a)", e.simplify());
    }


    @Test
    public void testSimplification4() {
        Expr e = new AddOp(new SymbExpr("a"), new Const(0.0f));
        assertEquals("(a)", e.simplify());
    }
}
