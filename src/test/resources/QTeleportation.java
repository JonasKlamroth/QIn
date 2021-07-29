
public class QTeleportation {
    public void generationTest(float a, float b) {
        float[][] qStates_r = new float[3][2];
        float[][] qStates_c = new float[3][2];
        //0 is phi
        qStates_r[0] = new float[]{a, b};
        qStates_c[0] = new float[]{0.0f, 0.0f};
        qStates_r[1] = new float[]{1.0f, 0.0f};
        qStates_c[1] = new float[]{0.0f, 0.0f};
        qStates_r[2] = new float[]{1.0f, 0.0f};
        qStates_c[2] = new float[]{0.0f, 0.0f};
        CircuitMock c = new CircuitMock(3, qStates_r, qStates_c);
        //SymCircuit c = new SymCircuit(2, 0);
        //Create Bell-state for 1, 2
        c.h(1);
        c.cx(1, 2);
        c.cx(0, 1);
        c.h(0);
        boolean b0 = c.measure(0);
        boolean b1 = c.measure(1);
        if(b1) {
            c.x(2);
        }
        if(b0) {
            c.z(2);
        }
    }
}
