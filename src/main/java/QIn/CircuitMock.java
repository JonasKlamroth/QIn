package QIn;

public class CircuitMock {
    public CircuitMock(int numQbits) {
    }

    public CircuitMock(int numQbits, float[] initialStates) {
    }

    public CircuitMock(int numQbits, long[] initialStates) {
    }

    public CircuitMock(int numQbits, int[] initialStates) {
    }

    public CircuitMock(int numQbits, float[] initialStates, float[] initialCStates) {
    }

    public void h(int qbit) {
    }

    public void x(int qbit) {
    }

    public void z(int qbit) {
    }

    public void cx(int cqbit, int tqbit) {
    }

    public void cz(int cqbit, int tqbit) {
    }

    public void u(float[][] m, int... qbits) {
    }

    public void u(int[][] m, int... qbits) {
    }

    public boolean measure(int qBit) {
        return true;
    }

    public boolean measurePos(int qBit) {
        return true;
    }
}