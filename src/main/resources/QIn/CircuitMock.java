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
    public void hhh(int qbit) {
    }
    public void hh(int qbit) {
    }

    public void x(int qbit) {
    }

    public void z(int qbit) {
    }

    public void rx(int qbit, float theta) {
    }

    public void ry(int qbit, float theta) {
    }

    public void rz(int qbit, float theta) {
    }

    public void cx(int cqbit, int tqbit) {
    }

    public void cz(int cqbit, int tqbit) {
    }

    public void cp(int cqbit, int tqbit, double theta) {

    }

    public void ccx(int cqbit1, int cqbit2, int tqbit) {
    }

    public void ccz(int cqbit1, int cqbit2, int tqbit) {
    }

    public void cccx(int cqbit1, int cqbit2, int cqbit3, int tqbit) {
    }

    public void cswap(int cqbit, int qbit1, int qbit2) {
    }

    public void reset(int qbit1) {
    }

    public void u(int[][] m, int qbit, int numQbits) {
    }
    public void u(int[][] m, int[][]m_i, int qbits, int numQbits) {
    }
    public void u(float[][] m, int qbit, int numQbits) {
    }
    public void u(float[][] m, float[][]m_i, int qbits, int numQbits) {
    }

    public void swap(int qbit1, int qbit2) {}

    public boolean measure(int qBit) {
        return true;
    }

    public boolean measurePos(int qBit) {
        return true;
    }

    public int measureAll() {
        return 0;
    }
}
