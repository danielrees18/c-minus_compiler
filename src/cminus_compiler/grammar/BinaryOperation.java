package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: BinaryOperation.java
 * Created: March 2015	
 *
 * Description:
 */
public class BinaryOperation extends Expression {
    
    private Expression leftHandExpression;
    private Expression rightHandExpression;
    private String operation;

    
    // Constructors
    public BinaryOperation() {
        this(null, null, null);
    }
    
    public BinaryOperation(Expression leftHandExpression, Expression rightHandExpression, String op) {
        this.leftHandExpression = leftHandExpression;
        this.rightHandExpression = rightHandExpression;
        this.operation = op;
    }

    
    // Getters
    public Expression getLeftHandExpression() {
        return leftHandExpression;
    }

    public Expression getRightHandExpression() {
        return rightHandExpression;
    }

    public String getOperation() {
        return operation;
    }

    
    // Setters
    public void setLeftHandExpression(Expression leftHandExpression) {
        this.leftHandExpression = leftHandExpression;
    }

    public void setRightHandExpression(Expression rightHandExpression) {
        this.rightHandExpression = rightHandExpression;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(operation);
        builder.append(leftHandExpression.printTree(indent+1));
        builder.append(rightHandExpression.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        return null;
    }
}
