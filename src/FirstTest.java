public class FirstTest {
    public static void main(String args[]) {
        Circuit c = new Circuit(2);
        c.h(0);
        c.cnot(0, 1);
        System.out.println(c.getRes());
    }
}
