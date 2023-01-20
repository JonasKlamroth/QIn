package QIn;

public class qasm2mock_listener extends QIn.QASMBaseListener {

    public String statements ="";

    @Override public void enterUop(QASMParser.UopContext ctx) {

        if(ctx.getChild(0).getText().equals("h")){
            statements += " c.h(" + ctx.getChild(1).getChild(0).getChild(2).getText() + ");" + "\n";
        } else if (ctx.getChild(0).getText().equals("x")){
            statements += " c.x(" + ctx.getChild(1).getChild(0).getChild(2).getText() + ");" + "\n";
        } else if(ctx.getChild(0).getText().equals("cx")){
            statements += "c.cx(" + ctx.getChild(1).getChild(0).getChild(2).getText() + "," + ctx.getChild(1).getChild(0).getChild(7).getText() + ");" + "\n";
        } else if(ctx.getChild(0).getText().equals("swap")){
            statements += " c.swap(" + ctx.getChild(1).getChild(0).getChild(2).getText() + "," + ctx.getChild(1).getChild(0).getChild(7).getText() + ");" + "\n";
        } else if(ctx.getChild(0).getText().equals("rx")){
            statements += "c.rx(" + ctx.getChild(2).getChild(0).getChild(0).getText() + "," + ctx.getChild(4).getChild(0).getChild(2).getText() + ");" + "\n";
        } else if(ctx.getChild(0).getText().equals("rz")){
            statements += "c.rz(" + ctx.getChild(2).getChild(0).getChild(0).getText() + "," + ctx.getChild(4).getChild(0).getChild(2).getText() + ");" + "\n";
        } else if(ctx.getChild(0).getText().equals("ry")){
            statements += "c.ry(" + ctx.getChild(2).getChild(0).getChild(0).getText() + "," + ctx.getChild(4).getChild(0).getChild(2).getText() + ");" + "\n";
        } else {
            throw new RuntimeException("Unkown gate type :" + ctx.getChild(0).getText());
        }

    }

    @Override public void enterQop(QASMParser.QopContext ctx) {
        if(ctx.getChild(0).getText().equals("measure")){
            statements += " c.measure(" + ctx.getChild(1).getChild(2).getText() + ");" + "\n";
        }

    }

    @Override public void enterDecl(QASMParser.DeclContext ctx) {

        if(ctx.getChild(0).getText().equals("qreg")){
            statements += "CircuitMock c = new CircuitMock(" + ctx.getChild(3).getText() + ");" + "\n";
        }

    }

}
