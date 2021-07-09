package CircuitTranslator.Expressions;

import CircuitTranslator.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestComplexExpressions {

    private ComplexExpression getComplexExpression(float r, float i) {
        return new ComplexExpression(Utils.getConst(r), Utils.getConst(i));
    }

    @Test
    public void testArith() {
        ComplexExpression a = getComplexExpression(1.0f, 0.0f);
        ComplexExpression b = getComplexExpression(1.0f, 0.0f);
        Expr c = a.mult(b);
        assertTrue(c instanceof ComplexExpression);
        assertEquals(a, c);

        c = a.add(b);
        assertTrue(c instanceof ComplexExpression);
        assertEquals(getComplexExpression(2.0f, 0.0f), c);

        c = getComplexExpression(0.0f, 1.0f).mult(getComplexExpression(0.0f, 1.0f));
        assertTrue(c instanceof ComplexExpression);
        assertEquals(getComplexExpression(-1.0f, 0.0f), c);

        c = getComplexExpression(1.0f, 1.0f).mult(getComplexExpression(1.0f, 1.0f));
        assertTrue(c instanceof ComplexExpression);
        assertEquals(getComplexExpression(0.0f, 2.0f), c);


        c = getComplexExpression(1.0f, 1.0f).add(getComplexExpression(1.0f, 1.0f));
        assertTrue(c instanceof ComplexExpression);
        assertEquals(getComplexExpression(2.0f, 2.0f), c);
    }
}
