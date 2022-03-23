package QIn;

public class qasm2mock_listener extends QIn.QASMBaseListener {

    @Override public void enterUop(QASMParser.UopContext ctx) {

        if(ctx.getChild(0).getText().equals("h")){
            System.out.println(" c.h(" + ctx.getChild(1).getChild(0).getChild(2).getText() + ");");
        }

        if(ctx.getChild(0).getText().equals("x")){
            System.out.println(" c.x(" + ctx.getChild(1).getChild(0).getChild(2).getText() + ");");
        }

        if(ctx.getChild(0).getText().equals("cx")){
            System.out.println(" c.cx(" + ctx.getChild(1).getChild(0).getChild(2).getText() + "," + ctx.getChild(1).getChild(0).getChild(7).getText() + ");");
        }

        if(ctx.getChild(0).getText().equals("swap")){
            System.out.println(" c.swap(" + ctx.getChild(1).getChild(0).getChild(2).getText() + "," + ctx.getChild(1).getChild(0).getChild(7).getText() + ");");
        }

        if(ctx.getChild(0).getText().equals("rx")){
            System.out.println("c.rx(" + ctx.getChild(2).getChild(0).getChild(0).getText() + "," + ctx.getChild(4).getChild(0).getChild(2).getText() + ");");
        }

        if(ctx.getChild(0).getText().equals("rz")){
            System.out.println("c.rz(" + ctx.getChild(2).getChild(0).getChild(0).getText() + "," + ctx.getChild(4).getChild(0).getChild(2).getText() + ");");
        }

    }

    @Override public void enterQop(QASMParser.QopContext ctx) {
        if(ctx.getChild(0).getText().equals("measure")){
            System.out.println(" c.measure(" + ctx.getChild(1).getChild(2).getText() + ");");
        }

    }

    @Override public void enterDecl(QASMParser.DeclContext ctx) {

        if(ctx.getChild(0).getText().equals("qreg")){
            System.out.println("CircuitMock c = new CircuitMock(" + ctx.getChild(3).getText() + ");");
        }

    }

}
