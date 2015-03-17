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
 * File: VarDeclaration.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class VarDeclaration extends Declaration {

    int size;
    
    
    // Constructors
    public VarDeclaration(int size, String name) {
        this.size = size;
        this.declarationName = name;
    }

    
    // Getters
    public int getSize() {
        return size;
    }

    public String getDeclarationName() {
        return declarationName;
    }

    
    // Setters
    public void setSize(int size) {
        this.size = size;
    }

    public void setDeclarationName(String declarationName) {
        this.declarationName = declarationName;
    }
    
    
    // Public Methods
    @Override
    public String printTree() {
        String treeOutput = "VarDecl";
        
        
        return treeOutput;
    }
}
