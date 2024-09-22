
public class QTeleportation {

    public static /*@ pure @*/ boolean isClose(float val, float to) {
        //float roundError = 1.1920929E-7f;
        float roundError = 1.0E-5f;
        //float roundError = 0.01f;
        return val < to + roundError && val > to - roundError;
    }



    /*@ requires isClose(a*a + b*b, 1.0f);
      @ ensures a < b == \result;
      @*/
    public static boolean generationTest(float a, float b) {
        float[] qStates_r = new float[]{a, b, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        float[] qStates_c = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        //0 is phi
        CircuitMock c = new CircuitMock(3, qStates_r, qStates_c);
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
