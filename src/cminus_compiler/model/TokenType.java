package cminus_compiler.model;

/** 
 *   ENUM of all possible tokens allowed in the C-Minus language
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: TokenType.java
 * Created: Feb 2015	
 *
 * Description:  Exposes all available token types as ENUM values.
 */
public enum TokenType {
    ELSE_TOKEN(0),
    IF_TOKEN(1),
    INT_TOKEN(2),
    RETURN_TOKEN(3),
    VOID_TOKEN(4),
    WHILE_TOKEN(5),
    PLUS_TOKEN(6),
    MINUS_TOKEN(7),
    MULT_TOKEN(8),
    DIV_TOKEN(9),
    LESS_TOKEN(10),
    LEQ_TOKEN(11),
    GREAT_TOKEN(12),
    GEQ_TOKEN(13),
    EQUAL_TOKEN(14),
    NEQ_TOKEN(15),
    ASSIGN_TOKEN(16),
    SEMICOLON_TOKEN(17),
    COMMA_TOKEN(18),
    LPAREN_TOKEN(19),
    RPAREN_TOKEN(20),
    LBRACKET_TOKEN(21),
    RBRACKET_TOKEN(22),
    LCURLY_TOKEN(23),
    RCURLY_TOKEN(24),
    ID_TOKEN(25),
    NUM_TOKEN(26),
    EOF_TOKEN(27),
    ERROR(28);
    
    private int value;
    
    private TokenType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
