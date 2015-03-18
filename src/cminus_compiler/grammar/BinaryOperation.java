/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

/** 
 *
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

    public BinaryOperation(Expression leftHandExpression, Expression rightHandExpression, String operation) {
        this.leftHandExpression = leftHandExpression;
        this.rightHandExpression = rightHandExpression;
        this.operation = operation;
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
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        builder.append(operation);
        builder.append("\n---");
        builder.append(leftHandExpression.printTree());
        builder.append("\n---");
        builder.append(rightHandExpression.printTree());
        
        return builder.toString();
    }
}
