package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;

/** 
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
    
    public ExpressionStatement() {
        this(null);
    }

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    public Expression getExpression() {
        return expression;
    }

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
    
    @Override
    public CodeItem gencode(Function function) {
        if(expression != null) {
            expression.gencode(function);
        }
        return function;
    }
}
