package QIn;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;


public class Testqasm_listener {

    @Test
    public void testHGate(){

        QIn.QASMLexer qasmLexer = new QIn.QASMLexer(CharStreams.fromString(
                "OPENQASM 2.0;\n" +
                        "qreg q[1];\n" +
                        "h q[0];\n" + "measure q[0];\n"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(qasmLexer);

        QIn.QASMParser qasmParser = new QIn.QASMParser(commonTokenStream);

        ParseTree parseTree = qasmParser.mainprog();

        qasm_listener q = new qasm_listener();
        ParseTreeWalker.DEFAULT.walk((ParseTreeListener) q, parseTree);


    }
}
