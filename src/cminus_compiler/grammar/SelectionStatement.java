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
 * File: SelectionStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class SelectionStatement extends Statement {
    
    private Expression expression;
    private Statement primaryStatement;
    private Statement optionalStatement;
    
    
    @Override
    public String printTree() {
        String treeOutput = "";
        
        
        return treeOutput;
    }
}
