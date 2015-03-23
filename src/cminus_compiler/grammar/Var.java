
package cminus_compiler.grammar;

import cminus_compiler.model.Token;

/** 
 *
 *
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
        builder.append("\n---");
        if(expression != null) {
            builder.append(expression.printTree(indent+1));
        }
        
        return builder.toString();
    }
}
