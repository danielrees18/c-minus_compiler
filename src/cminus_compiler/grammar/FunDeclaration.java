package cminus_compiler.grammar;

import java.util.ArrayList;
import lowlevel.BasicBlock;
import lowlevel.CodeItem;
import lowlevel.Data;
import lowlevel.FuncParam;
import lowlevel.Function;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: FunDeclaration.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class FunDeclaration extends Declaration {
 
    private String returnType;  // TODO: Can we avoid having returnType as a String?
    private ArrayList<Param> params;
    private CompoundStatement compoundStatement;
    
    
    // Constructors
    public FunDeclaration() {
        this(null, new ArrayList<>(), null, null);
    }

    public FunDeclaration(String type, ArrayList<Param> params, CompoundStatement stmt, String name) {
        this.returnType = type;
        this.params = params;
        this.compoundStatement = stmt;
        this.declarationName = name;
    }
    
    
    // Getters
    public String getReturnType() {
        return returnType;
    }

    public ArrayList<Param> getParams() {
        return params;
    }

    public CompoundStatement getCompoundStatement() {
        return compoundStatement;
    }

    
    // Setters
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParams(ArrayList<Param> params) {
        this.params = params;
    }

    public void setCompoundStatement(CompoundStatement compoundStatement) {
        this.compoundStatement = compoundStatement;
    }

    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(returnType);
        builder.append(" ");
        builder.append(declarationName);
        for(Param param : params) {
            builder.append(param.printTree(indent+1));
        }
        
        builder.append(compoundStatement.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        
        // Convert all of the function parameters into FuncParams to pass to the Function object. Need
        // to maintain a pointer to the front of the linked list, while still adding to the linked list
        FuncParam firstParam = new FuncParam();
        FuncParam tempParam = new FuncParam();
        int i = 0;
        for(Param param : params) {
            if(i == 0) {
                tempParam = param.gencode();
                firstParam = tempParam;
            } else {
                tempParam.setNextParam(param.gencode());
                tempParam = tempParam.getNextParam();
            }
            i++;
        }
        
        // Generating the function and compound statement of the function
        int type = convertReturnType();
        Function topFunction = new Function(type, declarationName, firstParam);
        
        // Create BB0
        topFunction.createBlock0();
        
        // Make BB
        BasicBlock bbOne = new BasicBlock(topFunction);
        
        // Append BB
        topFunction.appendBlock(bbOne);
        
        // Set CB = BB
        topFunction.setCurrBlock(bbOne);
        
        // gencode { }
        this.compoundStatement.gencode(topFunction);
        
        // append return block
        BasicBlock returnBlock = topFunction.getReturnBlock();
        topFunction.appendBlock(returnBlock);
        
        // append Unconnected chain
        BasicBlock unconnectedChainBlock = topFunction.getFirstUnconnectedBlock();
        topFunction.appendBlock(unconnectedChainBlock);
        
        // Return CodeItem
        return topFunction;
    }
    
    private int convertReturnType() {
        if(this.returnType.equalsIgnoreCase("void")) {
            return Data.TYPE_VOID;
        } else {
            return Data.TYPE_INT;
        }
    }
}
