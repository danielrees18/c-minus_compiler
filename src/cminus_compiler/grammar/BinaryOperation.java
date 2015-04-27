package cminus_compiler.grammar;

import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: BinaryOperation.java
 * Created: March 2015	
 *
 * Description:
 */
public class BinaryOperation extends Expression {
    
    private Expression leftHandExpression;
    private Expression rightHandExpression;
    private String operation;

    
    // Constructors
    public BinaryOperation() {
        this(null, null, null);
    }
    
    public BinaryOperation(Expression leftHandExpression, Expression rightHandExpression, String op) {
        this.leftHandExpression = leftHandExpression;
        this.rightHandExpression = rightHandExpression;
        this.operation = op;
    }

    
    // Getters
    public Expression getLeftHandExpression() {
        return leftHandExpression;
    }

    public Expression getRightHandExpression() {
        return rightHandExpression;
    }

    public String getOperation() {
        return operation;
    }

    
    // Setters
    public void setLeftHandExpression(Expression leftHandExpression) {
        this.leftHandExpression = leftHandExpression;
    }

    public void setRightHandExpression(Expression rightHandExpression) {
        this.rightHandExpression = rightHandExpression;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append(operation);
        builder.append(leftHandExpression.printTree(indent+1));
        builder.append(rightHandExpression.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        this.setRegNum(function.getNewRegNum());
        leftHandExpression.gencode(function);
        rightHandExpression.gencode(function);
        
        // Generate Operand sources and desitination for the binary operation
        Operand srcLeft = new Operand(Operand.OperandType.REGISTER, leftHandExpression.getRegNum());
        Operand srcRight = new Operand(Operand.OperandType.REGISTER, rightHandExpression.getRegNum());
        Operand dest = new Operand(Operand.OperandType.REGISTER, this.getRegNum());
        
        // Create the binary operation of specified type and set sources/destination
        Operation.OperationType operationType = convertToOperationType();
        Operation binaryOperation = new Operation(operationType, function.getCurrBlock());
        binaryOperation.setDestOperand(0, dest);
        binaryOperation.setSrcOperand(0, srcLeft);
        binaryOperation.setSrcOperand(1, srcRight);
        
        function.getCurrBlock().appendOper(binaryOperation);
        
        return null;
    }
    
    private Operation.OperationType convertToOperationType() {
        Operation.OperationType type = Operation.OperationType.UNKNOWN;
        
        switch(operation) {
            case "+":
                type = Operation.OperationType.ADD_I;
                break;
            case "-":
                type = Operation.OperationType.SUB_I;
                break;
            case "*":
                type = Operation.OperationType.MUL_I;
                break;
            case "/":
                type = Operation.OperationType.DIV_I;
                break;
            case "<":
                type = Operation.OperationType.LT;
                break;
            case "<=":
                type = Operation.OperationType.LTE;
                break;
            case ">":
                type = Operation.OperationType.GT;
                break;
            case ">=":
                type = Operation.OperationType.GTE;
                break;
            case "==":
                type = Operation.OperationType.EQUAL;
                break;    
            case "!=":
                type = Operation.OperationType.NOT_EQUAL;
                break;
            default:
                break;
        }
        return type;
    }
}
