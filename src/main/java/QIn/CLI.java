package QIn;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.jmlspecs.openjml.Factory;
import org.jmlspecs.openjml.IAPI;
import org.jmlspecs.openjml.JmlTree;
import picocli.CommandLine;

@CommandLine.Command(name = "CircTrans", header = "@|bold translate quantum circuits to plain java |@")
public class CLI implements Runnable {

    @CommandLine.Parameters(index = "0", arity = "1", description = "The file to be translated")
    public String fileName = null;

    @CommandLine.Option(names = {"-p", "-printStates"}, description = "Include printout statements after each state update")
    public static boolean includePrintStatements = false;

    @CommandLine.Option(names = {"-nd", "-numDigits"}, description = "Number of digits used for fixed point representation.")
    public static int numDigits = 15;

    @CommandLine.Option(names = {"-b", "-base"}, description = "Base for fixedPoint arithmetic")
    public static int base = 2;

    @CommandLine.Option(names = {"-real", "-realCoefficients"}, description = "Use real coefficients instead of complex ones.")
    public static boolean useReals = false;

    @CommandLine.Option(names = {"-fixed", "-useFixedPointArithmetic"}, description = "Whether fixed or floating point arithmetic is used.")
    public static boolean useFix = false;

    @CommandLine.Option(names = {"-o", "-outputFile"},
        description = "Provide a file to which the output is stored. If not provided output is printed to stdout")
    public static String outPath = null;

    @CommandLine.Option(names = {"-ndf", "-nondetFunctions"}, description = "Allow the use of JJBMCs nondet functions.")
    public static boolean useNondetFunctions;

    @CommandLine.Option(names = {"-m", "-mockCircuit"}, description = "Create a mock circuit java file")
    public static boolean createMockCircuit;

    @CommandLine.Option(names = {"--mxLicencseName"}, interactive = true)
    private static String mxLicencseName = null;

    @CommandLine.Option(names = {"-v", "-variableAssignment"}, description = "Assign values to variables (only ints and floats supported for now)")
    public static Map<String, Object> variableAssignments = new HashMap<>();
    private final Path MockFilepath = new File("." + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "CircuitMock.java").toPath();



    public static void main(String[] args) {
        System.setErr(new CostumPrintStream(System.err));
        System.setOut(new CostumPrintStream(System.out));
        CommandLine.run(new CLI(), args);
    }

    @Override
    public void run() {
        ScriptEngineWrapper.getInstance().applyAssignmentMap(variableAssignments);

        String[] apiArgs = new String[]{"-cp", new File(fileName).toPath().toAbsolutePath().getParent().toString()};
        String[] args = new String[]{fileName};
        IAPI api = null;
        try {
            api = Factory.makeAPI(apiArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating api: " + e.getMessage());
        }

        if (fileName.endsWith(".qasm")) {
            //parse qasm
            try {
                parseQasm(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error parsing qasm " + e.getMessage());
            }
        } else if (fileName.endsWith(".java")) {
            copyMock();
            //parse java
            java.util.List<JmlTree.JmlCompilationUnit> cu = api.parseFiles(args);
            int a = 0;
            try {
                a = api.typecheck(cu);
            } catch (IOException e) {
                e.printStackTrace();
                deleteMock();
                throw new RuntimeException("Error running typecheck: " + e.getMessage());
            }
            if (a > 0) {
                System.out.println("Error translating");
                deleteMock();
                return;
            }
            Context ctx = api.context();
            TransUtils.init(ctx);

            for (JmlTree.JmlCompilationUnit it : cu) {
                //log.info(api.prettyPrint(rewriteRAC(it, ctx)));
                JCTree t = rewriteAssert(it, ctx);
                try {
                    String translation = api.prettyPrint(t);
                    if (outPath != null) {
                        File outFile = new File(outPath);
                        if (outFile.exists()) {
                            Files.delete(outFile.toPath());
                        }
                        Files.write(outFile.toPath(), translation.getBytes(), StandardOpenOption.CREATE);
                        System.out.println("Output written to: " + outFile.getAbsolutePath());
                    } else {
                        System.out.println(translation);
                    }
                } catch (IOException e) {
                    deleteMock();
                    e.printStackTrace();
                }
            }
            deleteMock();
        } else {
            throw new RuntimeException("unknown file format!");
        }
    }

    private void deleteMock() {
        File folder = new File(fileName).getParentFile();
        File mock = new File(folder, "CircuitMock.java");
        if(mock.exists()) {
            try {
                Files.delete(mock.toPath());
            } catch (IOException e) {
                System.out.println("Error trying to delete the QIn.CircuitMock.");
                throw new RuntimeException(e);
            }
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void copyMock() {
        File folder = new File(fileName).getParentFile();
        File mock = new File(folder, "CircuitMock.java");
        //if(mock.exists()) {
            //throw new RuntimeException("QIn.CircuitMock.java is already existing in the folder of the given file. Please make sure this is not the case.");
        //}
        try {
            InputStream is = CLI.class.getResourceAsStream("/QIn/CircuitMock.java");
            String content = convertStreamToString(is);
            Files.write(mock.toPath(), content.getBytes());
        } catch (IOException e) {
            System.out.println("Error copying QIn.CircuitMock.");
            throw new RuntimeException(e);
        }
    }

    public void parseQasm(String fileName) throws IOException {
        QIn.QASMLexer qasmLexer = new QIn.QASMLexer(CharStreams.fromFileName(fileName));
        CommonTokenStream commonTokenStream = new CommonTokenStream(qasmLexer);

        QIn.QASMParser qasmParser = new QIn.QASMParser(commonTokenStream);

        ParseTree parseTree = qasmParser.mainprog();

        String translation = "";
        if (createMockCircuit) {
            translation += "public class test { \n" + "public static void testM() {\n";

            qasm2mock_listener q = new qasm2mock_listener();
            ParseTreeWalker.DEFAULT.walk((ParseTreeListener) q, parseTree);
            translation += q.statements;

            translation += "} \n" + "}\n";

        } else {
            qasm_listener q = new qasm_listener();
            ParseTreeWalker.DEFAULT.walk((ParseTreeListener) q, parseTree);
            translation = q.jv.prettyPrint("test", "testM");
        }
        //save file
        try {
            if (outPath != null) {
                File outFile = new File(outPath);
                if (outFile.exists()) {
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

    private JCTree rewriteAssert(JmlTree.JmlCompilationUnit it, Context ctx) {
        QCircuitVisitor qCircuitVisitor = new QCircuitVisitor(ctx, JmlTree.Maker.instance(ctx));
        ctx.dump();
        return it.accept(qCircuitVisitor, null);
    }
}
