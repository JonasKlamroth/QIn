public class Bugs4Q {

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
        circuit.x(0);
        circuit.x(1);
        circuit.ccx(2,1,0);
        return new boolean[]{circuit.measure(0), circuit.measure(1), circuit.measure(2)};
    }

}