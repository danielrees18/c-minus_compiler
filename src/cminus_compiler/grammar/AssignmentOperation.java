package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;
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
        Operation lowLevelOperation = new Operation(Operation.OperationType.ASSIGN, function.getCurrBlock());
        return function;
    }
}
