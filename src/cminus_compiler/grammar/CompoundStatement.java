package cminus_compiler.grammar;

import java.util.ArrayList;
import lowlevel.CodeItem;
import lowlevel.Function;

/** 
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

    public CompoundStatement(ArrayList<VarDeclaration> varDecls, ArrayList<Statement> statements) {
        this.variableDeclartions = varDecls;
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
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        
        for(VarDeclaration decl : variableDeclartions) {
            builder.append(decl.printTree(indent+1));
        }
        
        for(Statement stmt : statements) {
            builder.append(stmt.printTree(indent+1));
        }
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        
        for(VarDeclaration decl : variableDeclartions) {
            decl.gencode(function);
        }
        
        for(Statement stmt : statements) {
            stmt.gencode(function);
        }
        
        return function;
    }
}
