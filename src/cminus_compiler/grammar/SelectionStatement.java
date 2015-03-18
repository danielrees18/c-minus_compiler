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
 * File: SelectionStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class SelectionStatement extends Statement {
    
    private Expression expression;
    private Statement primaryStatement;
    private Statement optionalStatement;
    
    
    // Constructors
    public SelectionStatement() {
        this(null, null, null);
    }

    public SelectionStatement(Expression expression, Statement primaryStatement, Statement optionalStatement) {
        this.expression = expression;
        this.primaryStatement = primaryStatement;
        this.optionalStatement = optionalStatement;
    }

    
    // Getters
    public Expression getExpression() {
        return expression;
    }

    public Statement getPrimaryStatement() {
        return primaryStatement;
    }

    public Statement getOptionalStatement() {
        return optionalStatement;
    }

    
    // Setters
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setPrimaryStatement(Statement primaryStatement) {
        this.primaryStatement = primaryStatement;
    }

    public void setOptionalStatement(Statement optionalStatement) {
        this.optionalStatement = optionalStatement;
    }
    
    
    // Public Methods
    @Override
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        builder.append(expression.printTree());
        builder.append(primaryStatement.printTree());
        if(optionalStatement != null) {
            builder.append(optionalStatement.printTree());
        }
        
        return builder.toString();
    }
}
