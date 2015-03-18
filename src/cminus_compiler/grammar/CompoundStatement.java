
package cminus_compiler.grammar;

import java.util.ArrayList;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: CompoundStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class CompoundStatement extends Statement {
    
    private ArrayList<VarDeclaration> variableDeclartions;
    private ArrayList<Statement> statements;

    
    // Constructors
    public CompoundStatement() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public CompoundStatement(ArrayList<VarDeclaration> variableDeclartions, ArrayList<Statement> statements) {
        this.variableDeclartions = variableDeclartions;
        this.statements = statements;
    }

    
    // Getters
    public ArrayList<VarDeclaration> getVariableDeclartions() {
        return variableDeclartions;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    
    // Setters
    public void setVariableDeclartions(ArrayList<VarDeclaration> variableDeclartions) {
        this.variableDeclartions = variableDeclartions;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }
    
    
    // Public Methods
    @Override
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        
        for(VarDeclaration decl : variableDeclartions) {
            builder.append("\n---");
            builder.append(decl.printTree());
        }
        
        for(Statement stmt : statements) {
            builder.append("\n---");
            builder.append(stmt.printTree());
        }
        
        return builder.toString();
    }
}
