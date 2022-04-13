package QIn;

import com.sun.tools.javac.tree.JCTree;
import org.jmlspecs.openjml.JmlTreeScanner;

class ContainsCircuitVisitor extends JmlTreeScanner {
    private boolean containsCircuit = false;

    public ContainsCircuitVisitor() {
        super();
    }

    @Override
    public void visitIdent(JCTree.JCIdent tree) {
        if(tree.type.toString().equals(QCircuitVisitor.CIRCUIT_TYPE)) {
            containsCircuit = true;
        }
    }

    public static boolean containsCircuit(JCTree.JCStatement st) {
        ContainsCircuitVisitor ccv = new ContainsCircuitVisitor();
        ccv.scan(st);
        return ccv.containsCircuit;
    }
}

