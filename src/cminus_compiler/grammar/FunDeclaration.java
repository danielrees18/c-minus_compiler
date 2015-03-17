
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
 
    private String returnType;
    private ArrayList<Param> params;
    private CompoundStatement compoundStatement;
    
    public FunDeclaration() {
        
    }
    
    
    @Override
    public String printTree() {
        String treeOutput = "FunDecl";
        
        
        return treeOutput;
    }
}
