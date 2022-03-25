package QIn;

public class qasm_listener extends QIn.QASMBaseListener {

    JavaCircuitWriter jv;

    @Override public void enterUop(QASMParser.UopContext ctx) {

        if(ctx.getChild(0).getText().equals("h")){
            jv.applyGate(Utils.getUnitaryForName("h"),
                    Integer.parseInt(ctx.getChild(1).getChild(0).getChild(2).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("x")){
            jv.applyGate(Utils.getUnitaryForName("x"),
                    Integer.parseInt(ctx.getChild(1).getChild(0).getChild(2).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("cx")){
            jv.applyGate(Utils.getUnitaryForName("cx"),
                    Integer.parseInt(ctx.getChild(1).getChild(0).getChild(2).getText()),
                            Integer.parseInt(ctx.getChild(1).getChild(0).getChild(7).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("swap")){
            jv.applySwap(
                    Integer.parseInt(ctx.getChild(1).getChild(0).getChild(2).getText()),
                    Integer.parseInt(ctx.getChild(1).getChild(0).getChild(7).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("rx")){
            jv.applyGate(Utils.getUnitaryForName("rx", Float.parseFloat(ctx.getChild(2).getChild(0).getChild(0).getText())),
                    Integer.parseInt(ctx.getChild(4).getChild(0).getChild(2).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("rz")){
            jv.applyGate(Utils.getUnitaryForName("rz", Float.parseFloat(ctx.getChild(2).getChild(0).getChild(0).getText())),
                    Integer.parseInt(ctx.getChild(4).getChild(0).getChild(2).getText())
            );
        }

        if(ctx.getChild(0).getText().equals("ry")){
            jv.applyGate(Utils.getUnitaryForName("ry", Float.parseFloat(ctx.getChild(2).getChild(0).getChild(0).getText())),
                    Integer.parseInt(ctx.getChild(4).getChild(0).getChild(2).getText())
            );
        }

    }

    @Override public void enterQop(QASMParser.QopContext ctx) {
        if(ctx.getChild(0).getText().equals("measure")){
            jv.measure(Integer.parseInt(ctx.getChild(1).getChild(2).getText()));
        }
    }

    @Override public void enterDecl(QASMParser.DeclContext ctx) {

        if(ctx.getChild(0).getText().equals("qreg")){
            jv = new JavaCircuitWriter(
                    Integer.parseInt(ctx.getChild(3).getText())
            );
        }

    }

}
