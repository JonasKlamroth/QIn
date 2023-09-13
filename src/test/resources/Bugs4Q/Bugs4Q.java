public class Bugs4Q {
    //List of quantum bugs reported on online-platforms. List is a selection from: https://github.com/Z-928/Bugs4Q

    //https://stackoverflow.com/questions/63283443/my-qiskit-codes-output-differ-from-the-lecturer-ryan-o-donnell
    //@ ensures \result[0] == false && \result[1] == true && \result[2] == true;
    public static boolean[] reversedOrderBroken() {
        CircuitMock circuit = new CircuitMock(3);
        circuit.reset(0);
        circuit.reset(1);
        circuit.reset(2);
        circuit.x(0);
        circuit.x(1);
        circuit.ccx(0,1,2);
        return new boolean[]{circuit.measure(0), circuit.measure(1), circuit.measure(2)};
    }

    //@ ensures \result[0] == false && \result[1] == true && \result[2] == true;
    public static boolean[] reversedOrderFixed() {
        CircuitMock circuit = new CircuitMock(3);
        circuit.reset(0);
        circuit.reset(1);
        circuit.reset(2);
        circuit.x(2);
        circuit.x(1);
        circuit.ccx(0,1,2);
        return new boolean[]{circuit.measure(0), circuit.measure(1), circuit.measure(2)};
    }

    //https://stackoverflow.com/questions/60918011/implement-quantum-teleportation-in-qiskit
    public static void faultyQTeleportationBroken() {
        CircuitMock qc = new CircuitMock(3);

        qc.x(0); // #q -> 1

        qc.h(1);
        qc.cx(1, 2);
        // Next, apply the teleportation protocol.
        qc.cx(0, 1);
        qc.h(0);
        // We measure these qubits and use the classical results to perform an operation
        qc.measurePos(0);
        qc.measurePos(1);
        qc.cx(1, 2);
        qc.cz(0, 2);
        boolean res = qc.measurePos(2);
        assert res == false;
    }
    public static void faultyQTeleportationFixed() {
        CircuitMock qc = new CircuitMock(3);

        qc.x(0); // #q -> 1
        qc.h(1);
        qc.cx(1, 2);
        // Next, apply the teleportation protocol.
        qc.cx(0, 1);
        qc.h(0);
        // We measure these qubits and use the classical results to perform an operation
        qc.measurePos(0);
        qc.measurePos(1);
        qc.cx(1, 2);
        qc.cz(0, 2);
        boolean res = qc.measurePos(2);
        //the expected result should be 1 not 0
        assert res == true;
    }

    //https://github.com/Qiskit/qiskit-aer/issues/135
    //we proove the opposite of what we want to achieve here and show that we can verify what we want to be false
    public static void doulbeMeasurementBroken() {
        CircuitMock ghz = new CircuitMock(5);

        ghz.h(0);;
        ghz.cx(0,1);
        ghz.cx(1,2);
        ghz.cx(2,3);
        ghz.cx(3,4);
        for(int i = 0; i < 5; ++i) {
            ghz.measure(i);
        }
        assert !ghz.measurePos(0);
    }

    //without the measurements this proof is not possible anymore
    public static void doulbeMeasurementFixed() {
        CircuitMock ghz = new CircuitMock(5);

        ghz.h(0);
        ghz.cx(0,1);
        ghz.cx(1,2);
        ghz.cx(2,3);
        ghz.cx(3,4);
        assert !ghz.measurePos(0);;
    }

    //https://stackoverflow.com/questions/62661255/2-entangled-qubit-gives-all-states-with-25
    public static void bellStateCreationBroken() {
        CircuitMock qc = new CircuitMock(2);
        qc.h(0);  // |0> -> |+>
        qc.h(1);  // |0> -> |+>
        qc.cx(0,1);
        boolean b0 = qc.measurePos(0);
        boolean b1 = qc.measurePos(1);
        assert b0 != b1;
    }

    public static void bellStateCreationFixed() {
        CircuitMock qc = new CircuitMock(2);
        qc.h(0);  // |0> -> |+>
        qc.x(1);  // |0> -> |1>
        qc.cx(0,1);
        boolean b0 = qc.measurePos(0);
        boolean b1 = qc.measurePos(1);
        assert b0 != b1;
    }

    // https://quantumcomputing.stackexchange.com/questions/9246/quantum-phase-estimation-implementation
    float PI = 3.141592653f;
    private final int N = 3;


    //@ ensures \result == 3;
    public int computeQFTFixed() {
        float theta = 0.78f;
        CircuitMock c = new CircuitMock(N + 1);
        //create initial state
        c.x(N);

        //apply hadamards to ancillas
        for(int i = 0; i < N; ++i) {
            c.h(i);
        }

        //Applying U^(2^(n-i)) on qubit i
        for(int i = 0; i < N; ++i) {
            c.cp(i, N, 2* PI * theta * ((double)(1 << (N - i - 1))));
        }

        //Applying qft_dagger
        for(int i = 0; i < N / 2; ++i) {
            c.swap(i, N - i - 1);
        }
        for(int i = N - 1; i >= 0; i--) {
            c.h(i);
            for(int j = i - 1; j >= 0; j--) {
                c.cp(i , j, -2.0 * PI / ((double)(1 << (i - j  + 1))));
            }
        }

        int res = 0;
        for(int i = 0; i < N; i++) {
            res +=  c.measure(i) ? 1 << i : 0;
        }
        return res;
    }

    //@ ensures \result == 3;
    public int computeQFTBroken() {
        float theta = 0.78f;
        CircuitMock c = new CircuitMock(N + 1);
        //create initial state
        c.x(N);

        //apply hadamards to ancillas
        for(int i = 0; i < N; ++i) {
            c.h(i);
        }

        //Applying U^(2^(n-i)) on qubit i
        for(int i = 0; i < N; ++i) {
            c.cp(i, N, 2* PI * theta * ((double)(1 << (N - i - 1))));
        }

        //Applying qft_dagger
        for(int i = N - 1; i >= 0; i--) {
            for(int j = i - 1; j >= 0; j--) {
                c.cp(i + j - 1, i, -2.0 * PI / ((double)(1 << (i - j  + 1))));
            }
            c.h(i);
        }

        int res = 0;
        for(int i = 0; i < N; i++) {
            res +=  c.measure(i) ? 1 << i : 0;
        }
        return res;
    }

    //https://quantumcomputing.stackexchange.com/questions/9871/achieve-a-control-gate-with-2-hadamard-coins
    //@ ensures \result == (b0 && b1 && b2);
    public static boolean TaffoliGateBroken(boolean b0, boolean b1, boolean b2) {
        CircuitMock qc = new CircuitMock(4);
        if(b0) {
            qc.x(0);
        }
        if(b1) {
            qc.x(1);
        }
        if(b2) {
            qc.x(2);
        }
        qc.ccx(2, 3, 1);
        qc.cx(1, 0);
        qc.x(1);
        qc.ccx(2, 3, 1);
        return qc.measure(0);
    }


    //@ ensures \result == (b0 && b1 && b2);
    public static boolean TaffoliGateFixed(boolean b0, boolean b1, boolean b2) {
        CircuitMock qc = new CircuitMock(5);
        if(b0) {
            qc.x(0);
        }
        if(b1) {
            qc.x(1);
        }
        if(b2) {
            qc.x(2);
        }
        qc.ccx(0, 1, 3);
        qc.ccx(2, 3, 4);
        qc.ccx(0, 1, 3);
        return qc.measure(4);
    }

    //https://quantumcomputing.stackexchange.com/questions/18448/how-to-perform-a-plot-histogram-for-a-circuit
    //again we proof the opposite of what we expect to find the contradiction
    //@ ensures !\result;
    public static boolean mixedResultsBroken() {
        CircuitMock qc = new CircuitMock(4);
        qc.cx(3, 1);
        qc.cx(1, 0);
        qc.cx(0, 1);
        qc.ccx(3, 2, 1);
        qc.cx(1, 2);
        qc.cx(3, 2);
        boolean res = false;
        res |= qc.measurePos(0);
        res |= qc.measurePos(1);
        res |= qc.measurePos(2);
        res |= qc.measurePos(3);
        return res;
    }


    //@ ensures !\result;
    public static boolean mixedResultsFixed() {
        CircuitMock qc = new CircuitMock(4);
        for(int i = 0; i < 4; ++i) {
            qc.h(i);
        }
        qc.cx(3, 1);
        qc.cx(1, 0);
        qc.cx(0, 1);
        qc.ccx(3, 2, 1);
        qc.cx(1, 2);
        qc.cx(3, 2);
        boolean res = false;
        res |= qc.measurePos(0);
        res |= qc.measurePos(1);
        res |= qc.measurePos(2);
        res |= qc.measurePos(3);
        return res;
    }

    //https://quantumcomputing.stackexchange.com/questions/15925/q-sphere-representation-of-bell-states
    public static void bellStateCreation2Broken() {
        CircuitMock mycircuit = new CircuitMock(2);
        mycircuit.x(0);
        mycircuit.h(0);
        mycircuit.cx(0,1);
        assert mycircuit.measurePos(0) != mycircuit.measurePos(1);
    }

    public static void bellStateCreation2Fixed() {
        CircuitMock mycircuit = new CircuitMock(2);
        mycircuit.x(1);
        mycircuit.h(0);
        mycircuit.cx(0,1);
        assert mycircuit.measurePos(0) != mycircuit.measurePos(1);
    }

    //https://quantumcomputing.stackexchange.com/questions/15711/cirq-n-qubit-ghz-state
    public static void createGHZBroken(int maxN) {
        CircuitMock c = new CircuitMock(maxN);
        for(int i = 0; i < maxN - 1; ++i) {
            c.h(i);
            c.cx(i, i + 1);
        }
        boolean firstMeasurement = c.measurePos(0);
        for(int i = 0; i < maxN; ++i) {
            assert c.measurePos(i) == firstMeasurement;
        }
    }

    public static void createGHZFixed(int maxN) {
        CircuitMock c = new CircuitMock(maxN);
        c.h(0);
        for(int i = 0; i < maxN - 1; ++i) {
            c.cx(i, i + 1);
        }
        boolean firstMeasurement = c.measurePos(0);
        for(int i = 0; i < maxN; ++i) {
            assert c.measurePos(i) == firstMeasurement;
        }
    }
}