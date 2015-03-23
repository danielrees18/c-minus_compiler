/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.grammar;

import cminus_compiler.model.Token;

/** 
 *
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Num.java
 * Created: March 2015	
 *
 * Description:
 */
public class Num extends Expression {

    private int value;
    
    
    // Constructors
    public Num() {
        this(0);
    }
    
    public Num(Token token) {
       this(Integer.parseInt(token.data()));
   }

    public Num(int value) {
        this.value = value;
    }

    
    // Getters
    public int getValue() {
        return value;
    }

    
    // Setters
    public void setValue(int value) {
        this.value = value;
    }
    
    
    // Public Methods
    @Override
    public String printTree(int indent) {
        return toString();
    }   

    @Override
    public String toString() {
        return Integer.toString(value);
    }
    
    
}
