package cminus_compiler.grammar;

import java.util.ArrayList;
import lowlevel.CodeItem;
import lowlevel.Function;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: FunDeclaration.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class FunDeclaration extends Declaration {
 
    private String returnType;  // TODO: Can we avoid having returnType as a String?
    private ArrayList<Param> params;
    private CompoundStatement compoundStatement;
    
    
    // Constructors
    public FunDeclaration() {
        this(null, new ArrayList<>(), null, null);
    }

    public FunDeclaration(String type, ArrayList<Param> params, CompoundStatement stmt, String name) {
        this.returnType = type;
        this.params = params;
        this.compoundStatement = stmt;
        this.declarationName = name;
    }
    
    
    // Getters
    public String getReturnType() {
        return returnType;
    }

    public ArrayList<Param> getParams() {
        return params;
    }

    public CompoundStatement getCompoundStatement() {
        return compoundStatement;
    }

    
    // Setters
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParams(ArrayList<Param> params) {
        this.params = params;
    }

    public void setCompoundStatement(CompoundStatement compoundStatement) {
        this.compoundStatement = compoundStatement;
    }

    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(returnType);
        builder.append(" ");
        builder.append(declarationName);
        for(Param param : params) {
            builder.append(param.printTree(indent+1));
        }
        
        builder.append(compoundStatement.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        return null;
    }
}
