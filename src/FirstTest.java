public class FirstTest {
    public static void main(String[] args) {
        SymCircuit c = new SymCircuit(2, 1);
        //c.x(0);
        c.h(0);
        c.cnot(0, 1);
        c.measureMax(0, 0);
        System.out.println(c.getTranslation());
    }
}
