
public class QTeleportationInt {
    public void generationTest(int a, int b) {
        int[][] qStates = new int[3][2];
        //0 is phi
        qStates[0] = new int[]{a, b};
        qStates[1] = new int[]{10000, 0};
        qStates[2] = new int[]{10000, 0};
        CircuitMock c = new CircuitMock(3, qStates);
        //SymCircuit c = new SymCircuit(2, 0);
        //Create Bell-state for 1, 2
        c.h(1);
        c.cx(1, 2);
        c.cx(0, 1);
        c.h(0);
        boolean b0 = c.measurePos(0);
        boolean b1 = c.measurePos(1);
        if(b1) {
            c.x(2);
        }
        if(b0) {
            c.z(2);
        }
    }
}
