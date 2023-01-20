package QIn;

import QIn.Expressions.Expr;
import org.apache.commons.lang3.tuple.Pair;
import org.jmlspecs.annotation.In;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class qasm_listener extends QIn.QASMBaseListener {

    JavaCircuitWriter jv;
    private List<Integer> currentArguments = new ArrayList<Integer>();
    private List<QuantumRegister> quantumRegisters = new ArrayList<>();
    private List<Pair<Integer, Integer>> pendingSwaps = new ArrayList<>();

    public class QuantumRegister {
        String name;
        int size;
        int offset;

        public QuantumRegister(String name, int size, int offset) {
            this.name = name;
            this.size = size;
            this.offset = offset;
        }

        public int getGlobalIdx(int idx) {
            return offset + idx;
        }
    }

    public int getGlobalIdxForRegister(String registerName, int idx) {
        for(QuantumRegister register : quantumRegisters) {
            if(register.name.equals(registerName)) {
                return register.getGlobalIdx(idx);
            }
        }
        throw new RuntimeException("Unknown register: " + registerName);
    }

    @Override public void enterUop(QIn.QASMParser.UopContext ctx) {
        currentArguments = new ArrayList<>();
    }

    @Override
    public void exitUop(QIn.QASMParser.UopContext ctx) {
        if(ctx.getChild(0).getText().equals("swap")) {
            jv.applySwap(currentArguments.get(0), currentArguments.get(1));
            return;
        }

        Expr[][] unitary = Utils.getUnitaryForName(ctx.getChild(0).getText());
        int[] args = new int[currentArguments.size()];
        args[0] = currentArguments.get(0);
        for(int i = 1; i < currentArguments.size(); ++i) {
            if(currentArguments.get(i - 1) != currentArguments.get(i) - 1) {
                jv.applySwap(currentArguments.get(i), args[i - 1] + 1);
                pendingSwaps.add(Pair.of(currentArguments.get(i), args[i - 1] + 1));
            }
            args[i] = args[i - 1] + 1;
        }
        jv.applyGate(unitary, args);
        for(Pair<Integer, Integer> swap : pendingSwaps) {
            jv.applySwap(swap.getLeft(), swap.getRight());
        }
        pendingSwaps.clear();
    }


    @Override public void enterQop(QIn.QASMParser.QopContext ctx) {
        if(ctx.getChild(0).getText().equals("measure")){
            int qubitIdx = getGlobalIdxForRegister(ctx.argument(0).ID().getText(), Integer.parseInt(ctx.argument(0).INT().getText()));
            jv.measure(qubitIdx);
        } else if (ctx.getChild(0).getText().equals("reset")) {
            int qubitIdx = getGlobalIdxForRegister(ctx.argument(0).ID().getText(), Integer.parseInt(ctx.argument(0).INT().getText()));
            jv.applyReset(qubitIdx);
        }
    }

    @Override
    public void exitStatement(QIn.QASMParser.StatementContext ctx) {
        super.exitStatement(ctx);
        currentArguments.clear();
    }

    @Override public void enterDecl(QIn.QASMParser.DeclContext ctx) {

        if(ctx.getChild(0).getText().equals("qreg")){
            quantumRegisters.add(new QuantumRegister(ctx.ID().getText(), Integer.parseInt(ctx.INT().getText()),
                    quantumRegisters.stream().collect(Collectors.summingInt(quantumRegister -> quantumRegister.size))
                    ));

            QuantumRegister lastRegister = quantumRegisters.get(quantumRegisters.size() - 1);
            jv = new JavaCircuitWriter(lastRegister.offset + lastRegister.size);
        }

    }

    @Override
    public void enterMixedlist(QIn.QASMParser.MixedlistContext ctx) {
        for(QIn.QASMParser.ArgumentContext arg : ctx.argument()) {
            int qbitIdx = getGlobalIdxForRegister(arg.getChild(0).getText(), Integer.parseInt(arg.getChild(2).getText()));
            currentArguments.add(qbitIdx);
        }
    }
}
