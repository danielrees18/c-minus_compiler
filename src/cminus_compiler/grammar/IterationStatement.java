
package cminus_compiler.grammar;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: IterationStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class IterationStatement extends Statement {
    
    private Expression expression;
    private Statement statement;
    
    
    // Constructors
    public IterationStatement() {
        this(null, null);
    }

    public IterationStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }
    
    
    // Getters
    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return statement;
    }

    
    // Setters
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    
    // Public Methods
    @Override
    public String printTree() {
        String treeOutput = "";
        
        
        return treeOutput;
    }
}
