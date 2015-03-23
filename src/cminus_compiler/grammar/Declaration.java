/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

/** 
 *
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
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < indent; i++) {
            builder.append("---");
        }
        return builder.toString();
    }
    
    
    // Abstract Methods
    public abstract String printTree(int indent);
    
}
