package QIn;

public class qasm_listener extends QIn.QASMBaseListener {

    @Override public void enterMainprog(QASMParser.MainprogContext ctx) {
        System.out.println("enterMainprog!");
        System.out.println(ctx.getText());
    }

}
