package cminus_compiler.grammar;

import cminus_compiler.interfaces.CodeGen;
import cminus_compiler.tool.IndentTool;
import lowlevel.CodeItem;
import lowlevel.Function;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Expression.java
 * Created: Feb 2015	
 *
 * Description:
 */
public abstract class Expression implements CodeGen {
    
    public String indent(int indent) {
        return IndentTool.indent(indent);
    }
    
    public abstract String printTree(int indent);
    @Override
    public abstract CodeItem gencode(Function function);
    
}
