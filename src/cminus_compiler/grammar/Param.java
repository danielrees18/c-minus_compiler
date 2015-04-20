package cminus_compiler.grammar;

import cminus_compiler.model.Token;
import cminus_compiler.tool.IndentTool;
import lowlevel.CodeItem;
import lowlevel.Function;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Param.java
 * Created: March 2015	
 *
 * Description:
 */
public class Param {
    
    private String paramName;
    private boolean isArray;
    
    public Param() {
        this("", false);
    }
    
    public Param(Token ID, boolean isArray) {
        this((String)ID.getTokenData(), isArray);
    }

    public Param(String paramName, boolean isArray) {
        this.paramName = paramName;
        this.isArray = isArray;
    }
    
    
    // Getters

    public String getParamName() {
        return paramName;
    }

    public boolean isArray() {
        return isArray;
    }

    
    // Setters
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }
    
    
    // Public Methods
    public String printTree(int indent) {
        
        StringBuilder builder = new StringBuilder();
        builder.append(IndentTool.indent(indent));
        
        builder.append(paramName);
        builder.append(" is array: ");
        builder.append(isArray);
        
        return builder.toString();
    }
    
    
    public CodeItem gencode(Function function) {
        return null;
    }

    
}
