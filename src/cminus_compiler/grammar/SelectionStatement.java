package cminus_compiler.grammar;

/** 
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

    public SelectionStatement(Expression expression, Statement primary, Statement optional) {
        this.expression = expression;
        this.primaryStatement = primary;
        this.optionalStatement = optional;
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
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append("if");
        builder.append(expression.printTree(indent+1));
        builder.append(primaryStatement.printTree(indent+1));
        
        if(optionalStatement != null) {
            builder.append(indent(indent));
            builder.append("else");
            builder.append(optionalStatement.printTree(indent+1));
        }
        
        return builder.toString();
    }
}
