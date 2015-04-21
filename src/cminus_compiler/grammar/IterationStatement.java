package cminus_compiler.grammar;

import lowlevel.BasicBlock;
import lowlevel.CodeItem;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/** 
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: IterationStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class IterationStatement extends Statement {
    
    private Expression expression;
    private Statement statement;
    
    public IterationStatement() {
        this(null, null);
    }

    public IterationStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }
    
    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append("while");
        builder.append(expression.printTree(indent+1));
        builder.append(statement.printTree(indent+1));
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
        // 1. Gencode expression
        expression.gencode(function);
        
        // 2. Make 2 blocks
        BasicBlock thenBlock = new BasicBlock(function);
        BasicBlock postBlock = new BasicBlock(function);
        
        // 3. create the branch operation to based on the condition given in while expression
        Operation branchOperation = 
                getBranchOperation(Operation.OperationType.BEQ, postBlock, function.getCurrBlock());
        function.getCurrBlock().appendOper(branchOperation);
        function.appendToCurrentBlock(thenBlock);
        function.setCurrBlock(thenBlock);
        
        // 6. gencode statement
        statement.gencode(function);
        
        // Recheck condition
        expression.gencode(function);
        Operation recheckOperation = getBranchOperation(Operation.OperationType.BEQ, postBlock, function.getCurrBlock());
        function.getCurrBlock().appendOper(recheckOperation);
        
        // Loop Condition
        Operation bneBranchOperation = 
                getBranchOperation(Operation.OperationType.BNE, thenBlock, function.getCurrBlock());
        function.getCurrBlock().appendOper(bneBranchOperation);
        function.appendToCurrentBlock(postBlock);         
        function.setCurrBlock(postBlock);
        
        return function;
    }
    
    private Operation getBranchOperation(Operation.OperationType type, BasicBlock block, BasicBlock cur) {
        Operation branchOperation = new Operation(type, cur);
        Operand srcOne = new Operand(Operand.OperandType.REGISTER, expression.getRegNum());
        Operand srcConst = new Operand(Operand.OperandType.INTEGER, 0);
        Operand srcTarget = new Operand(Operand.OperandType.BLOCK, block.getBlockNum());
        
        branchOperation.setSrcOperand(0, srcOne);
        branchOperation.setSrcOperand(1, srcConst);
        branchOperation.setSrcOperand(2, srcTarget);
        
        return branchOperation;
    }
}
