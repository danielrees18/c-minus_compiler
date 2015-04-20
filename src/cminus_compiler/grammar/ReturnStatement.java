package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;

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
        return null;
    }
}
