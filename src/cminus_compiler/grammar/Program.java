/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

import java.util.ArrayList;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Program.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class Program {
    
    // Program variables
    private ArrayList<Declaration> declarations;
    
    
    // Program constructor
    public Program() {
        declarations = new ArrayList<>();
    }
    
       
    // Program Methods
    public void addDeclaration(Declaration declaration) {
        declarations.add(declaration);
    }
    
    public String printTree() {
        StringBuilder builder = new StringBuilder();
        
        for(Declaration declaration : declarations) {
            builder.append(declaration.printTree());
        }
        
        return builder.toString();
    }
}
