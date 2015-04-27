package cminus_compiler.grammar;

import cminus_compiler.model.Token;
import cminus_compiler.tool.IndentTool;
import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Num.java
 * Created: March 2015	
 *
 * Description:
 */
public class Num extends Expression {

    private int value;
    
    // Constructors
    public Num() {
        this(0);
    }
    
    public Num(Token token) {
       this(Integer.parseInt(token.data()));
    }

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(IndentTool.indent(indent));
        builder.append(toString());
        return builder.toString();
    }   

    @Override
    public String toString() {
        return Integer.toString(value);
    }
    
    @Override
    public CodeItem gencode(Function function) {
        
        int regNum = function.getNewRegNum();
        this.setRegNum(regNum);
        
        Operand src = new Operand(Operand.OperandType.INTEGER, this.value);
        Operand dest = new Operand(Operand.OperandType.REGISTER, regNum);
        
        Operation operation = new Operation(Operation.OperationType.ASSIGN, function.getCurrBlock());
        operation.setDestOperand(0, dest);
        operation.setSrcOperand(0, src);
        
        function.getCurrBlock().appendOper(operation);
        
        return function;
    }
    
}
