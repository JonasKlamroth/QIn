import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QTeleportation {
    @Test
    public void generationTest() {
        List<String[]> qStates = new ArrayList<>();
        //0 is phi
        qStates.add(new String[]{"a", "b"});
        qStates.add(new String[]{"1.0f", "0.0f"});
        qStates.add(new String[]{"1.0f", "0.0f"});
        SymCircuit c = new SymCircuit(3, 2, qStates);
        //SymCircuit c = new SymCircuit(2, 0);
        //Create Bell-state for 1, 2
        c.h(1);
        c.cnot(1, 2);
        c.cnot(0, 1);
        c.h(0);
        c.measure(0, 0);
        c.measure(1, 1);
        c.x(2);
        c.z(2);
        System.out.println(c.getTranslation());
    }
}
