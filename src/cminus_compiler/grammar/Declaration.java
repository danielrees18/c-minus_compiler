package cminus_compiler.grammar;

import cminus_compiler.tool.IndentTool;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Declaration.java
 * Created: Feb 2015	
 *
 * Description:
 */
public abstract class Declaration {
    
    // Attributes
    protected String declarationName;
    
    
    // Getters
    public String getDeclarationName() {
        return declarationName;
    }

    
    // Setters
    public void setDeclarationName(String declarationName) {
        this.declarationName = declarationName;
    }
    
    public String indent(int indent) {
        return IndentTool.indent(indent);
    }
    
    
    // Abstract Methods
    public abstract String printTree(int indent);
    
}
