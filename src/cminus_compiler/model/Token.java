package cminus_compiler.model;

/** 
 *   Object to store TokenType and TokenData for all tokens found in a file
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Token.java
 * Created: Feb 2015	
 *
 * Description:  Data object with two attributes which allows for modeling a
 * token data type for the C-Minus compiler.
 */
public class Token {
    
    // Token attributes
    private TokenType tokenType;
    private Object tokenData;

    
    // Token Constructor
    public Token() {
        this(null, null);
    }
    
    public Token(TokenType type) {
        this(type, null);
    }
    
    public Token (TokenType type, Object data) {
        this.tokenType = type;
        this.tokenData = data;
    }
    
    
    // Public Methods
    public boolean equals(TokenType type) {
        return this.tokenType == type;
    }

    @Override
    public String toString() {
        return "Token{" + "tokenType=" + tokenType + ", tokenData=" + tokenData + '}';
    }
    
    
    // Getters
    public TokenType getTokenType() {
        return tokenType;
    }

    public Object getTokenData() {
        return tokenData;
    }


    // Setters
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
    
    public void setTokenData(Object tokenData) {
        this.tokenData = tokenData;
    }   
}