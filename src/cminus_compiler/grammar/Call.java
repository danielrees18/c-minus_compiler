package cminus_compiler.grammar;

import java.util.ArrayList;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Call.java
 * Created: March 2015	
 *
 * Description:
 */
public class Call extends Expression {
    
    private String callName;
    private ArrayList<Expression> args;

    
    // Constructors
    public Call() {
        this(null, new ArrayList<>());
    }

    public Call(String callName, ArrayList<Expression> args) {
        this.callName = callName;
        this.args = args;
    }

    
    // Getters
    public String getCallName() {
        return callName;
    }

    public ArrayList<Expression> getArgs() {
        return args;
    }

    
    // Setters
    public void setCallName(String callName) {
        this.callName = callName;
    }

    public void setArgs(ArrayList<Expression> args) {
        this.args = args;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(callName);
        for(Expression arg : args) {
            builder.append(arg.printTree(indent+1));
        }        
        return builder.toString();
    }
}
