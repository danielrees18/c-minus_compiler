package cminus_compiler.grammar;

import java.util.ArrayList;
import lowlevel.CodeItem;

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
    
    public CodeItem genLLCode() {
        CodeItem nextItem = declarations.get(0).gencode(null);
        CodeItem firstItem = nextItem; 
        for(int i = 1; i < declarations.size(); i++) {
            nextItem.setNextItem(declarations.get(i).gencode(null));
            nextItem = nextItem.getNextItem();
        }
        
        return firstItem;
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
