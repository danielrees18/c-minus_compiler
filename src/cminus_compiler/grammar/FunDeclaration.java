
package cminus_compiler.grammar;

import java.util.ArrayList;

/** 
 *
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
        this(null, new ArrayList<Param>(), null, null);
    }

    public FunDeclaration(String returnType, ArrayList<Param> params, CompoundStatement compoundStatement, String name) {
        this.returnType = returnType;
        this.params = params;
        this.compoundStatement = compoundStatement;
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
            builder.append("\n");
            builder.append(param.printTree(indent+1));
        }
        
        builder.append("\n");
        builder.append(compoundStatement.printTree(indent+1));
        
        return builder.toString();
    }
}
