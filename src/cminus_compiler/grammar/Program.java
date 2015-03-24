package cminus_compiler.grammar;

import java.util.ArrayList;

/** 
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
        builder.append("\n*** Begin Tree *** \nProgram");
        for(Declaration declaration : declarations) {
            builder.append(declaration.printTree(1));
        }
        
        return builder.toString();
    }
}
