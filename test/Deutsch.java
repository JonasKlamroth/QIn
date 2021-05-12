import org.junit.jupiter.api.Test;

public class Deutsch {
    @Test
    public void generateDeutsch() {
        String[][] m = new String[][]{
                new String[]{"!f[0] ? 1.0f : 0.0f", "f[0] ? 1.0f :0.0f", "0.f", "0.f"},
                new String[]{"f[0] ? 1.0f : 0.0f", "!f[0] ? 1.0f :0.0f", "0.f", "0.f"},
                new String[]{"0.f", "0.f", "!f[1] ? 1.0f : 0.0f", "f[1] ? 1.0f :0.0f"},
                new String[]{"0.f", "0.f", "f[1] ? 1.0f : 0.0f", "!f[1] ? 1.0f :0.0f"}
        };
        SymCircuit c = new SymCircuit(2, 1);
        c.x(1);
        c.h(1);
        c.h(0);
        c.u(m, 0, 1);
        c.h(0);
        c.measureMax(0, 0);
        System.out.println(c.getTranslation());

    }
}
