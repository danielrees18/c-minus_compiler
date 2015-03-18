/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: AssignmentOperation.java
 * Created: March 2015	
 *
 * Description:
 */
public class AssignmentOperation extends Expression {
    
    private Var variable;
    private Expression rightHandExpression;
    private String operation = "=";
    
    
    // Constructors
    public AssignmentOperation() {
        
    }
    
    public AssignmentOperation(Var var, Expression rhs) {
        this.variable = var;
        this.rightHandExpression = rhs;
    }

    
    // Getters
    public Var getVariable() {
        return variable;
    }

    public Expression getRightHandExpression() {
        return rightHandExpression;
    }

    public String getOperation() {
        return operation;
    }

    
    // Setters
    public void setVariable(Var variable) {
        this.variable = variable;
    }

    public void setRightHandExpression(Expression rightHandExpression) {
        this.rightHandExpression = rightHandExpression;
    }
  
  
    // Public Methods
    @Override
    public String printTree() {
        String treeOutput = "";
        
        
        return treeOutput;
    }
}
