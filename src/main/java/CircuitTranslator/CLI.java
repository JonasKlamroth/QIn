package CircuitTranslator;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import org.jmlspecs.openjml.Factory;
import org.jmlspecs.openjml.IAPI;
import org.jmlspecs.openjml.JmlTree;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

@CommandLine.Command(name = "CircTrans", header = "@|bold translate quantum circuits to plain java |@")
public class CLI implements Runnable {

    @CommandLine.Parameters(index="0", arity = "1", description = "The file to be translated")
    public String fileName = null;

    @CommandLine.Option(names = {"-v", "-verbose"}, description = "Include printout statements after each state update")
    public static boolean includePrintStatements = false;

    @CommandLine.Option(names = {"-nd", "-numDigits"}, description = "Number of digits used for fixed point representation.")
    public static int numDigits = 4;

    @CommandLine.Option(names = {"-float", "-useFloatingPointArithmetic"}, description = "Whether fixed or floating point arithmetic is used.")
    public static boolean useFloat = false;


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
                System.out.println(translation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JCTree rewriteAssert(JmlTree.JmlCompilationUnit it, Context ctx) {
        QCircuitVisitor qCircuitVisitor = new QCircuitVisitor(ctx, JmlTree.Maker.instance(ctx));
        ctx.dump();
        return it.accept(qCircuitVisitor, null);
    }
}
