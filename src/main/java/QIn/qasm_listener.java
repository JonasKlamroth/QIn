package QIn;

import QIn.Expressions.Expr;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.tools.javac.util.List.nil;

public class qasm_listener extends QIn.QASMBaseListener {

    JavaCircuitWriter jv;
    private List<Integer> currentArguments = new ArrayList<Integer>();
    private List<QuantumRegister> quantumRegisters = new ArrayList<>();
    private com.sun.tools.javac.util.List<Pair<Integer, Integer>> pendingSwaps = com.sun.tools.javac.util.List.nil();

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
        com.sun.tools.javac.util.List<Integer> args = nil();
        for(int i = 0; i < currentArguments.size(); ++i) {
            args = args.append(currentArguments.get(i));
        }
        com.sun.tools.javac.util.Pair<Integer, com.sun.tools.javac.util.List<com.sun.tools.javac.util.Pair<Integer, Integer>>> res = Utils.applyNecessarySwaps(jv.getqState(), args);
        pendingSwaps = res.snd;
        jv.applyGate(unitary, res.fst);
        for(com.sun.tools.javac.util.Pair<Integer, Integer> swap : pendingSwaps) {
            jv.applySwap(swap.fst, swap.snd);
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
                    quantumRegisters.stream().mapToInt(quantumRegister -> quantumRegister.size).sum()
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
