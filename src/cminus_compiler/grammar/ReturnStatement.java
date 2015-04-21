package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: ReturnStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class ReturnStatement extends Statement {
    
    private Expression expression;

    // Constructors
    public ReturnStatement() {
        this(null);
    }

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }
    
    
    // Getters
    public Expression getExpression() {
        return expression;
    }

    
    // Setters
    public void setExpression(Expression expression) {
        this.expression = expression;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append("return");
        if(expression != null) {
            builder.append(expression.printTree(indent+1));
        }
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        int returnRegNum;
        if(expression != null) {
            expression.gencode(function);
            returnRegNum = expression.getRegNum();
        } else {
            returnRegNum = function.getNewRegNum();
        }
        
        // Source to retReg operation
        Operand src = new Operand(Operand.OperandType.REGISTER, returnRegNum);
        Operand dest = new Operand(Operand.OperandType.MACRO, "RetReg");
       
        Operation op = new Operation(Operation.OperationType.ASSIGN, function.getCurrBlock());
        op.setDestOperand(0, dest);
        op.setSrcOperand(0, src);
        
        // Jump operation to return block
        Operand jmpSrc = new Operand(Operand.OperandType.BLOCK, function.getReturnBlock().getBlockNum());
                
        Operation jmp = new Operation(Operation.OperationType.JMP, function.getCurrBlock());
        jmp.setSrcOperand(0, jmpSrc);
        
        
        // Append blocks
        function.getCurrBlock().appendOper(op);
        function.getCurrBlock().appendOper(jmp);
        
        return function;
    }
}
