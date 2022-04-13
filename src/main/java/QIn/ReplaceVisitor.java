package QIn;


import com.sun.source.tree.IdentifierTree;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import org.jmlspecs.openjml.JmlTree;
import org.jmlspecs.openjml.JmlTreeCopier;

public class ReplaceVisitor extends JmlTreeCopier {
    Symbol.VarSymbol oldVar = null;
    int value = 0;


    public ReplaceVisitor(Context context, JmlTree.Maker maker) {
        super(context, maker);
    }

    @Override
    public JCTree visitIdentifier(IdentifierTree node, Void p) {
        JCTree.JCIdent t = (JCTree.JCIdent) super.visitIdentifier(node, p);
        if(t.sym != null && t.sym.equals(oldVar)) {
            return M.Literal(value);
        }
        return t;
    }

    @Override
    public JCTree visitJmlVariableDecl(JmlTree.JmlVariableDecl that, Void p) {
        JCTree.JCVariableDecl vd = (JCTree.JCVariableDecl) super.visitJmlVariableDecl(that, p);
        if(vd.sym.equals(oldVar)) {
            return M.Literal(value);
        }
        return vd;
    }

    public static JCTree.JCStatement replace(Symbol.VarSymbol oldVar, int value, JCTree.JCStatement st, JmlTree.Maker maker) {
        ReplaceVisitor rv = new ReplaceVisitor(maker.context, maker);
        rv.value = value;
        rv.oldVar = oldVar;
        return rv.copy(st);
    }
}

