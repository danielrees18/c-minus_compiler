package cminus_compiler.grammar;

import cminus_compiler.model.Token;
import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *]
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Var.java
 * Created: March 2015	
 *
 * Description:
 */
public class Var extends Expression {
       
    private String variableName;
    private Expression expression;
 
    
    // Constructors
    public Var() {
    }
    
    public Var(Token ID, Expression expression) {
        this((String)ID.getTokenData(), expression);
    }

    public Var(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    
    // Getters
    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    
    // Setters
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(variableName);
        if(expression != null) {
            builder.append("[");
            builder.append(expression.printTree(indent+1));
            builder.append("]");
        }
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        Integer obj = (Integer) function.getTable().get(variableName);
        
        // Load from global table
        if(obj == null) {
            this.setRegNum(function.getNewRegNum());
        
//            Operation loadOp = new Operation(Operation.OperationType.LOAD_I, function.getCurrBlock());
//            
//            Operand srcLoad = new Operand(Operand.OperandType.STRING, this.variableName);
//            Operand destLoad = new Operand(Operand.OperandType.REGISTER, this.getRegNum());
//            
//            loadOp.setDestOperand(0, destLoad);
//            loadOp.setSrcOperand(0, srcLoad);
//            
//            function.getCurrBlock().appendOper(loadOp);
            
            
        } else {
            this.setRegNum(obj);
        }
        
        return function;
    }
    
    public boolean isGlobal(Function function) {
        Integer obj = (Integer) function.getTable().get(variableName);
        return obj == null;
    }
}
