package QIn;

import org.mariuszgromada.math.mxparser.*;

import java.util.Map;

public class ScriptEngineWrapper {

    private static ScriptEngineWrapper instance = null;
    private Map<String, String> assignmentMap = null;


    private ScriptEngineWrapper() {
    }

    public static ScriptEngineWrapper getInstance() {
        if(instance == null) {
            instance = new ScriptEngineWrapper();
        }
        return instance;
    }

    public void applyAssignmentMap(Map<String, String> map) {
        assignmentMap = map;
    }


    public int evalInt(String expr) {
        return new Double(eval(expr)).intValue();
    }

    public double eval(String expr) {
        Argument[] args = new Argument[assignmentMap.keySet().size()];
        int idx = 0;
        for(String k : assignmentMap.keySet()) {
            Argument a = new Argument(k + "=" + assignmentMap.get(k));
            args[idx] = a;
            idx++;
        }
        expr = expr.replaceAll("\\(double\\)", "");
        expr = expr.replaceAll("\\(float\\)", "");
        expr = expr.replaceAll("\\(int\\)", "");
        expr = expr.replaceAll(">>", "@>>");
        expr = expr.replaceAll("<<", "@<<");
        try {
            Expression e = new Expression(expr, args);
            double res = e.calculate();
            if(Double.isNaN(res)) {
                throw new RuntimeException("Could not evaluate expression: " + e.getExpressionString());
            }
            return res;
        } catch (Exception e) {
            throw new UnsupportedException("Unsupported expression evaluated: " + expr);
        }
    }
}
