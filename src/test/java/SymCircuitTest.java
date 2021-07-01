import org.junit.jupiter.api.Test;

public class SymCircuitTest {

    @Test
    public void test() {
        SymCircuit c = new SymCircuit(2, 1);
        c.x(0);
        c.z(0);
        //c.h(0);
        //c.cnot(0, 1);
        //c.measureMax(0, 1);
        System.out.println(c.getTranslation());
    }
}
