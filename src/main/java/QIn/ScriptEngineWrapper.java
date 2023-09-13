package QIn;

import java.io.Reader;
import java.util.Map;
import javax.script.*;

public class ScriptEngineWrapper {
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

    private static ScriptEngineWrapper instance = null;
    private Map<String, Object> assignmentMap = null;


    private ScriptEngineWrapper() {
    }

    public static ScriptEngineWrapper getInstance() {
        if(instance == null) {
            instance = new ScriptEngineWrapper();
        }
        return instance;
    }

    public void applyAssignmentMap(Map<String, Object> map) {
        assignmentMap = map;
        for(String key : assignmentMap.keySet()) {
            try {
                if (assignmentMap.get(key) instanceof String) {
                    assignmentMap.put(key, Integer.parseInt((String) assignmentMap.get(key)));
                }
            } catch (NumberFormatException e) {
                if (assignmentMap.get(key) instanceof String) {
                    assignmentMap.put(key, Double.parseDouble((String) assignmentMap.get(key)));
                }
            }
        }
    }


    public int evalInt(String expr) {
        return new Double(eval(expr)).intValue();
    }

    public double eval(String expr) {
        Bindings args = new SimpleBindings();
        args.putAll(assignmentMap);

        //expr = expr.replaceAll("\\(double\\)", "");
        //expr = expr.replaceAll("\\(float\\)", "");
        //expr = expr.replaceAll("\\(int\\)", "");
        //expr = expr.replaceAll(">>", "@>>");
        //expr = expr.replaceAll("<<", "@<<");
        try {
            Object res = engine.eval(expr, args);
            if(res instanceof Double) {
                return (double)res;
            } else if(res instanceof Integer) {
                return (((Integer) res).doubleValue());
            } else {
                throw new UnsupportedException("Unsupported expression evaluated: " + expr + ". Did you forget to provide a constant?");
            }
        } catch (Exception e) {
            throw new UnsupportedException("Unsupported expression evaluated: " + expr + ". Did you forget to provide a constant?");
        }
    }
}
