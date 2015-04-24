package cminus_compiler.grammar;

import java.util.ArrayList;
import lowlevel.Attribute;
import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
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

    public Call() {
        this(null, new ArrayList<>());
    }

    public Call(String callName, ArrayList<Expression> args) {
        this.callName = callName;
        this.args = args;
    }

    public String getCallName() {
        return callName;
    }

    public ArrayList<Expression> getArgs() {
        return args;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public void setArgs(ArrayList<Expression> args) {
        this.args = args;
    }
    
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
    
    @Override
    public CodeItem gencode(Function function) {
        // Pass Operations
        ArrayList<Operation> passOperations = new ArrayList<>();
        int count = 0;
        for(Expression arg : args) {
            arg.gencode(function);
            
            Operand src = new Operand(Operand.OperandType.REGISTER, arg.getRegNum());
            Operation passOperation = new Operation(Operation.OperationType.PASS, function.getCurrBlock());
            passOperation.setSrcOperand(0, src);
            
            String pos = Integer.toString(count);
            Attribute attribute = new Attribute("PARAM_NUM", pos);
            passOperation.addAttribute(attribute);
            count++;

            passOperations.add(passOperation);
        }
        
        for(Operation passOp : passOperations) {
            function.getCurrBlock().appendOper(passOp);
        }
        
        
        // Call Operation
        
        String size = Integer.toString(args.size());
        Attribute attribute = new Attribute("numParams", size);
        Operand callSrc = new Operand(Operand.OperandType.STRING, this.callName);
        Operation callOperation = new Operation(Operation.OperationType.CALL, function.getCurrBlock());
        callOperation.setSrcOperand(0, callSrc);
        callOperation.addAttribute(attribute);
        
        function.getCurrBlock().appendOper(callOperation);
        
        // RetReg Operation
        int destRegNum = function.getNewRegNum();
        this.setRegNum(destRegNum);
        Operand src = new Operand(Operand.OperandType.MACRO, "RetReg");
        Operand dest = new Operand(Operand.OperandType.REGISTER, destRegNum);
        Operation assignOperation = new Operation(Operation.OperationType.ASSIGN, function.getCurrBlock());
        assignOperation.setDestOperand(0, dest);
        assignOperation.setSrcOperand(0, src);
        
        function.getCurrBlock().appendOper(assignOperation);
        
        
        return null;
    }
}
