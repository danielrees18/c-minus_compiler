package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: AssignmentOperation.java
 * Created: March 2015	
 *
 * Description:
 */
public class AssignmentOperation extends Expression {
    
    private Var variable;
    private Expression rightHandExpression;
    private String operation = "=";

    
    // Constructors
    public AssignmentOperation() {
        
    }
    
    public AssignmentOperation(Var var, Expression rhs) {
        this.variable = var;
        this.rightHandExpression = rhs;
    }

    
    // Getters
    public Var getVariable() {
        return variable;
    }

    public Expression getRightHandExpression() {
        return rightHandExpression;
    }

    public String getOperation() {
        return operation;
    }

    
    // Setters
    public void setVariable(Var variable) {
        this.variable = variable;
    }

    public void setRightHandExpression(Expression rightHandExpression) {
        this.rightHandExpression = rightHandExpression;
    }
  
  
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(indent(indent));
        
        builder.append(operation);
        builder.append(variable.printTree(indent+1));
        builder.append(rightHandExpression.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        this.setRegNum(function.getNewRegNum());
        
        variable.gencode(function);
        rightHandExpression.gencode(function);
        
        Operation assignOperation = new Operation(Operation.OperationType.ASSIGN, function.getCurrBlock());
        
        //
        Operand dest = new Operand(Operand.OperandType.REGISTER, variable.getRegNum());
        Operand src = new Operand(Operand.OperandType.REGISTER, rightHandExpression.getRegNum());
        
        assignOperation.setDestOperand(0, dest);
        assignOperation.setSrcOperand(0, src);
        
        function.getCurrBlock().appendOper(assignOperation);
        
        // Store global variables if they changed
        if(variable.isGlobal(function)) {
            this.storeGlobalVariable(function);
        }
        
        return function;
    }
    
    private void storeGlobalVariable(Function function) {
        Operation storeOperation = new Operation(Operation.OperationType.STORE_I, function.getCurrBlock());
        
        Operand srcZero = new Operand(Operand.OperandType.REGISTER, rightHandExpression.getRegNum());
        Operand srcOne = new Operand(Operand.OperandType.STRING, variable.getVariableName());
        
        storeOperation.setSrcOperand(0, srcZero);
        storeOperation.setSrcOperand(1, srcOne);
        
        function.getCurrBlock().appendOper(storeOperation);
    }
}
