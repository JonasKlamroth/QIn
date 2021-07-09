package CircuitTranslator.Expressions;

import CircuitTranslator.*;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.util.Context;
import org.jmlspecs.openjml.Factory;
import org.jmlspecs.openjml.IAPI;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressions {


    private static JmlTree.Maker M;
    private static JmlTreeUtils treeutils;
    private static Symtab syms;

    @BeforeAll
    private static void setUp() throws Exception {
        CLI.useFloat = true;
        IAPI api = Factory.makeAPI(new String[]{});
        Context context = api.context();
        treeutils = JmlTreeUtils.instance(context);
        syms = Symtab.instance(context);
        M = JmlTree.Maker.instance(context);
        TransUtils.init(context);
    }

    @Test
    public void testSimplification() {
        Expr e = new MultOp(new MultOp(new SymbExpr(M.Ident("a")), Utils.getConst(0.5f)), Utils.getConst(0.5f));
        assertEquals("0.25F * a", e.getSimplifiedAST().toString());
    }

    @Test
    public void testSimplification1() {
        Expr e = new AddOp(new AddOp(new SymbExpr(M.Ident("a")), Utils.getConst(0.5f)), Utils.getConst(0.5f));
        assertEquals("1.0F + a", e.getSimplifiedAST().toString());
    }

    @Test
    public void testSimplification2() {
        Expr e = new MultOp(new SymbExpr(M.Ident("a")), Utils.getConst(0.f));
        assertEquals("0.0F", e.getSimplifiedAST().toString());
    }

    @Test
    public void testSimplification3() {
        Expr e = new MultOp(new SymbExpr(M.Ident("a")), Utils.getConst(1.0f));
        assertEquals("a", e.getSimplifiedAST().toString());
    }


    @Test
    public void testSimplification4() {
        Expr e = new AddOp(new SymbExpr(M.Ident("a")), Utils.getConst(0.0f));
        assertEquals("a", e.getSimplifiedAST().toString());
    }
}
