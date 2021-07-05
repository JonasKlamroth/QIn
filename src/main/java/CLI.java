import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import org.jmlspecs.openjml.Factory;
import org.jmlspecs.openjml.IAPI;
import org.jmlspecs.openjml.JmlTree;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "CircTrans", header = "@|bold translate quantum circuits to plain java |@")
public class CLI implements Runnable {

    @CommandLine.Parameters(index="0", arity = "1", description = "The file to be translated")
    public String fileName = null;

    @CommandLine.Option(names = {"-v", "-verbose"}, description = "Include printout statements after each state update")
    public static boolean includePrintStatements = false;

    public static final void main(String[] args) throws Exception {
        CommandLine.run(new CLI(), args);
    }

    @Override
    public void run() {

        String[] apiArgs = new String[]{};
        String[] args = new String[]{fileName};
        IAPI api = null;
        try {
            api = Factory.makeAPI(apiArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.util.List<JmlTree.JmlCompilationUnit> cu = api.parseFiles(args);
        int a = 0;
        try {
            a = api.typecheck(cu);
        } catch (IOException e) {
            e.printStackTrace();
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
