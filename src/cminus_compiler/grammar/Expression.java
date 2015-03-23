/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

import cminus_compiler.tool.IndentTool;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Expression.java
 * Created: Feb 2015	
 *
 * Description:
 */
public abstract class Expression {
    
    public String indent(int indent) {
        return IndentTool.indent(indent);
    }
    
    public abstract String printTree(int indent);
    
}
