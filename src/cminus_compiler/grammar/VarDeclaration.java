package cminus_compiler.grammar;

import cminus_compiler.model.Token;
import lowlevel.CodeItem;
import lowlevel.Data;
import lowlevel.Function;

/** 
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
    public VarDeclaration() {
        this(0, null);
    }
    
    public VarDeclaration (Num number, Token ID) {
        this(number.getValue(), (String)ID.getTokenData());
    }
    
    public VarDeclaration(int size, String name) {
        this.size = size;
        this.declarationName = name;
    }

    
    // Getters
    public int getSize() {
        return size;
    }

    
    // Setters
    public void setSize(int size) {
        this.size = size;
    }

    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        builder.append("int ");
        builder.append(declarationName);
        if(size > 0) {
            builder.append("[");
            builder.append(size);
            builder.append("]");
        }
        
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        Data data = new Data(Data.TYPE_INT, declarationName);
        
        if(function != null) {
            function.getTable().put(declarationName, function.getNewRegNum());
        }
        
        return data;
    }
}
