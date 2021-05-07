public class FirstTest {
    public static void main(String[] args) {
        SymCircuit c = new SymCircuit(2);
        //c.x(0);
        c.h(0);
        c.cnot(0, 1);
        System.out.println(c.getRes());
    }
}
