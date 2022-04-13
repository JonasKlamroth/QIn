package QIn;

import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriptEngineWrapper {

    private static ScriptEngineWrapper instance = null;
    private Map<String, Integer> assignmentMap = null;


    private ScriptEngineWrapper() {
    }

    public static ScriptEngineWrapper getInstance() {
        if(instance == null) {
            instance = new ScriptEngineWrapper();
        }
        return instance;
    }

    public void applyAssignmentMap(Map<String, Integer> map) {
        assignmentMap = map;
    }


    public int eval(String expr) {
        Argument[] args = new Argument[assignmentMap.keySet().size()];
        int idx = 0;
        for(String k : assignmentMap.keySet()) {
            Argument a = new Argument(k + "=" + assignmentMap.get(k));
            args[idx] = a;
            idx++;
        }
        try {
            Expression e = new Expression(expr, args);
            if(Double.isNaN(e.calculate())) {
                throw new RuntimeException("Could not evaluate expression: " + e.getExpressionString());
            }
            return (int)e.calculate();
        } catch (Exception e) {
            throw new UnsupportedException("Unsupported expression evaluated: " + expr);
        }
    }
}
