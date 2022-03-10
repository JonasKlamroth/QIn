package QIn;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import org.antlr.v4.runtime.tree.*;
import org.jmlspecs.openjml.Factory;
import org.jmlspecs.openjml.IAPI;
import org.jmlspecs.openjml.JmlTree;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

@CommandLine.Command(name = "CircTrans", header = "@|bold translate quantum circuits to plain java |@")
public class CLI implements Runnable {

    @CommandLine.Parameters(index="0", arity = "1", description = "The file to be translated")
    public String fileName = null;

    @CommandLine.Option(names = {"-v", "-verbose"}, description = "Include printout statements after each state update")
    public static boolean includePrintStatements = false;

    @CommandLine.Option(names = {"-nd", "-numDigits"}, description = "Number of digits used for fixed point representation.")
    public static int numDigits = 15;

    @CommandLine.Option(names = {"-b", "-base"}, description = "Base for fixedPoint arithmetic")
    public static int base = 2;

    @CommandLine.Option(names = {"-real", "-realCoefficients"}, description = "Use real coefficients instead of complex ones.")
    public static boolean useReals = false;

    @CommandLine.Option(names = {"-fixed", "-useFixedPointArithmetic"}, description = "Whether fixed or floating point arithmetic is used.")
    public static boolean useFix = false;

    @CommandLine.Option(names = {"-o", "-outputFile"}, description = "Provide a file to which the output is stored. If not provided output is printed to stdout")
    public static String outPath = null;

    @CommandLine.Option(names = {"-ndf", "-nondetFunctions"}, description = "Allow the use of JJBMCs nondet functions.")
    public static boolean useNondetFunctions;

    public static void main(String[] args) {
        System.setErr(new CostumPrintStream(System.err));
        System.setOut(new CostumPrintStream(System.out));
        CommandLine.run(new CLI(), args);
    }

    @Override
    public void run() {

        String[] apiArgs = new String[]{"-cp", new File(fileName).getParent()};
        String[] args = new String[]{fileName};
        IAPI api = null;
        try {
            api = Factory.makeAPI(apiArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating api: " + e.getMessage());
        }

        java.util.List<JmlTree.JmlCompilationUnit> cu = api.parseFiles(args);

        //parse qasm here
        try {
            parseQasm();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = 0;
        try {
            a = api.typecheck(cu);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error running typecheck: " + e.getMessage());
        }
        if(a > 0) {
            System.out.println("Error translating");
            return;
        }
        Context ctx = api.context();
        TransUtils.init(ctx);

        for (JmlTree.JmlCompilationUnit it : cu) {
            //log.info(api.prettyPrint(rewriteRAC(it, ctx)));
            JCTree t = rewriteAssert(it, ctx);
            try {
                String translation = api.prettyPrint(t);
                if(outPath != null) {
                    File outFile = new File(outPath);
                    if(outFile.exists()) {
                        Files.delete(outFile.toPath());
                    }
                    Files.write(outFile.toPath(), translation.getBytes(), StandardOpenOption.CREATE);
                    System.out.println("Output written to: " + outFile.getAbsolutePath());
                } else {
                    System.out.println(translation);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseQasm() throws IOException {
        QASMLexer qasmLexer = new QASMLexer(CharStreams.fromFileName("test_circuit.qasm"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(qasmLexer);

        QIn.QASMParser qasmParser = new QIn.QASMParser(commonTokenStream);

        ParseTree parseTree = qasmParser.mainprog();
        ParseTreeWalker.DEFAULT.walk((ParseTreeListener) new qasm_listener(), parseTree);

    }

    private JCTree rewriteAssert(JmlTree.JmlCompilationUnit it, Context ctx) {
        QCircuitVisitor qCircuitVisitor = new QCircuitVisitor(ctx, JmlTree.Maker.instance(ctx));
        ctx.dump();
        return it.accept(qCircuitVisitor, null);
    }
}
