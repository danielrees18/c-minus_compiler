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
 * File: SelectionStatement.java
 * Created: Feb 2015	
 *
 * Description:
 */
public class SelectionStatement extends Statement {
    
    private Expression expression;
    private Statement primaryStatement;
    private Statement optionalStatement;
    
    
    // Constructors
    public SelectionStatement() {
        this(null, null, null);
    }

    public SelectionStatement(Expression expression, Statement primary, Statement optional) {
        this.expression = expression;
        this.primaryStatement = primary;
        this.optionalStatement = optional;
    }

    
    // Getters
    public Expression getExpression() {
        return expression;
    }

    public Statement getPrimaryStatement() {
        return primaryStatement;
    }

    public Statement getOptionalStatement() {
        return optionalStatement;
    }

    
    // Setters
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setPrimaryStatement(Statement primaryStatement) {
        this.primaryStatement = primaryStatement;
    }

    public void setOptionalStatement(Statement optionalStatement) {
        this.optionalStatement = optionalStatement;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(indent));
        
        builder.append("if");
        builder.append(expression.printTree(indent+1));
        builder.append(primaryStatement.printTree(indent+1));
        
        if(optionalStatement != null) {
            builder.append(indent(indent));
            builder.append("else");
            builder.append(optionalStatement.printTree(indent+1));
        }
        
        return builder.toString();
    }
    
    @Override
    public CodeItem gencode(Function function) {
       
        // 1. Gencode expression
        expression.gencode(function);
        
        // 2. Make 2/3 blocks
        BasicBlock thenBlock = new BasicBlock(function);
        BasicBlock postBlock = new BasicBlock(function);
        BasicBlock elseBlock = null;
        
        // 3. Branch to else/post
        int blockNum = -1;
        if (optionalStatement != null) {
            // Branch to elseBlock
            elseBlock = new BasicBlock(function);
            blockNum = elseBlock.getBlockNum();
        } else {
            // Branch to postBlock
            blockNum = postBlock.getBlockNum();
        }
        Operation branchOperation = new Operation(Operation.OperationType.BEQ, function.getCurrBlock());
        Operand srcOne = new Operand(Operand.OperandType.REGISTER, expression.getRegNum());
        Operand srcConst = new Operand(Operand.OperandType.INTEGER, 0);
        Operand srcTarget = new Operand(Operand.OperandType.BLOCK, blockNum);
        
        branchOperation.setSrcOperand(0, srcOne);
        branchOperation.setSrcOperand(1, srcConst);
        branchOperation.setSrcOperand(2, srcTarget);
        
        function.getCurrBlock().appendOper(branchOperation);
        
        // 4. Append 'then' block
        function.appendToCurrentBlock(thenBlock);
        
        // 5. CB = THEN
        function.setCurrBlock(thenBlock);
        
        // 6. gencode then
        primaryStatement.gencode(function);
        
        // 7. append post
        function.appendToCurrentBlock(postBlock);
        
        
        if(optionalStatement != null) {
            // 8. CB = Else
            function.setCurrBlock(elseBlock);
            
            // 9. gencode else
            optionalStatement.gencode(function);
            
            // 10. JMP to POST
            Operation jmp = new Operation(Operation.OperationType.JMP, function.getCurrBlock());
            Operand jmpSrc = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
            jmp.setSrcOperand(0, jmpSrc);
            
            function.getCurrBlock().appendOper(jmp);
            
            // 11. Else to unconnected Chain
            function.appendUnconnectedBlock(elseBlock);
        }
        
        // 12. 
        function.setCurrBlock(postBlock);
        
        return function;
    }
}
