/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.model;

/**
 *
 * @author drees
 */
public class CminusException extends Exception {

    private String errorMessage;
    
    // Constructors
    public CminusException(String message) {
        super(message);
    }  
    
    public CminusException(String expected, Token found) {
        super("Invalid token. Expected " + expected + ". Found " + found.toString());
    }
    
    /**
     * Constructor builds an error message that converts found tokens and expected types into nice English
     * @param found -   Token found from the parser
     * @param expectedTypes     -   Token types that the parser was expecting
     */
    public CminusException(Token found, TokenType... expectedTypes) {
        super();
        errorMessage = generateErrorMessage(expectedTypes, found);
    }

    // Fancy error logging
    @Override
    public String getMessage() {
        if(errorMessage != null) {
            return errorMessage;
        }
        
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String generateErrorMessage(TokenType[] types, Token found) {
        StringBuilder builder = new StringBuilder();
        builder.append("Invalid token. Expected ");
        for(int i = 0; i < types.length; i++) {
            builder.append(types[i].toString()); 
            if(i < types.length - 2) {
                builder.append(", ");
            } else if (i == types.length - 2) {
                builder.append(" or ");
            } 
        }
        
        builder.append(". Found ");
        builder.append(found.toString());
        
        return builder.toString();
    }
}
