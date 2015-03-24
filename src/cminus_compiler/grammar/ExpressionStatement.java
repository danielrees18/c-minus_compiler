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
 * File: ExpressionStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class ExpressionStatement extends Statement {
    
    private Expression expression;
    
    // Constructors
    public ExpressionStatement() {
        this(null);
    }

    public ExpressionStatement(Expression expression) {
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
        String output = "";
        if(expression != null) {
            output = expression.printTree(indent+1);
        }
        return output;
    }
}
