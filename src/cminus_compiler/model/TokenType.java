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
    ELSE_TOKEN,
    IF_TOKEN,
    INT_TOKEN,
    RETURN_TOKEN,
    VOID_TOKEN,
    WHILE_TOKEN,
    PLUS_TOKEN,
    MINUS_TOKEN,
    MULT_TOKEN,
    DIV_TOKEN,
    LESS_TOKEN,
    LEQ_TOKEN,
    GREAT_TOKEN,
    GEQ_TOKEN,
    EQUAL_TOKEN,
    NEQ_TOKEN,
    ASSIGN_TOKEN,
    SEMICOLON_TOKEN,
    COMMA_TOKEN,
    LPAREN_TOKEN,
    RPAREN_TOKEN,
    LBRACKET_TOKEN,
    RBRACKET_TOKEN,
    LCURLY_TOKEN,
    RCURLY_TOKEN,
    ID_TOKEN,
    NUM_TOKEN,
    EOF_TOKEN,
    ERROR;
}
