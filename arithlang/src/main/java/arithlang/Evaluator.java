package arithlang;
import static arithlang.AST.*;
import static arithlang.Value.*;

import java.io.IOException;
import java.util.List;

public class Evaluator implements Visitor<Value> {
    private NumVal record = new NumVal("0");
    Printer.Formatter ts = new Printer.Formatter();
	
    Value valueOf(Program p) {
        // Value of a program in this language is the value of the expression
        return (Value) p.accept(this);
    }
	
    @Override
    public Value visit(AddExp e) {
        List<Exp> operands = e.all();
        String result = "";
        if (operands.size() > 2) {
            return new NumVal("ERROR we can only have 2 operands");
        }
        NumVal b = (NumVal)operands.get(0).accept(this);
        NumVal a = (NumVal)operands.get(1).accept(this);
        if (a.v().equals("0")) {
            return new NumVal(b.v());
        } else if (a.v().equals("p")) {
            if (b.v().equals("0") || b.v().equals("p")) {
                return new NumVal("p");
            } else {
                return new NumVal("u");
            }
        } else if (a.v().equals("n")) {
            if (b.v().equals("0") || b.v().equals("n")) {
                return new NumVal("n");
            } else {
                return new NumVal("u");
            }
        } else {
            return new NumVal("u");
        }
    }


    public Value visit(SubExp e) {
        List<Exp> operands = e.all();
        String result = "";
        if (operands.size() > 2) {
            return new NumVal("ERROR we can only have 2 operands");
        }
        NumVal b = (NumVal)operands.get(0).accept(this);
        NumVal a = (NumVal)operands.get(1).accept(this);
        if (a.v().equals("0")) {
            return new NumVal(b.v());
        } else if (a.v().equals("p")) {
            if (b.v().equals("0") || b.v().equals("n")) {
                return new NumVal("n");
            } else {
                return new NumVal("u");
            }
        } else if (a.v().equals("n")) {
            if (b.v().equals("0") || b.v().equals("p")) {
                return new NumVal("p");
            } else {
                return new NumVal("u");
            }
        } else {
            return new NumVal("u");
        }
    }


    @Override
    public Value visit(NumExp e) {
        return new NumVal(e.v());
    }

    @Override
    public Value visit(MultExp e) {
        List<Exp> operands = e.all();
        String result = "";
        if (operands.size() > 2) {
            return new NumVal("ERROR we can only have 2 operands");
        }
        NumVal b = (NumVal)operands.get(0).accept(this);
        NumVal a = (NumVal)operands.get(1).accept(this);
        if (a.v().equals("0")|| b.v().equals("0")) {
            return new NumVal("0");
        } else if (a.v().equals("u") || b.v().equals("u")) {
            return new NumVal("u");
        } else if (a.v().equals(b.v())) {
            return new NumVal("p");
        } else {
            return new NumVal("n");
        }
    }

    @Override
    public Value visit(Program p) {
        return (Value) p.e().accept(this);
    }


}
