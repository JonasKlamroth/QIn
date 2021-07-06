public class CircuitMock {
    public CircuitMock(int numQbits) {
    }

    public CircuitMock(int numQbits, float[][] initialStates) {
    }
    public void h(int qbit) {
    }

    public void x(int qbit) {
    }

    public void z(int qbit) {
    }

    public void cx(int cqbit, int tqbit) {
    }

    public void u(float[][] m, int... qbits) {
    }

    public void u(int[][] m, int... qbits) {
    }

    public boolean measureMax(int qBit) {
        return true;
    }

    public boolean measure(int qBit) {
        return true;
    }
}
