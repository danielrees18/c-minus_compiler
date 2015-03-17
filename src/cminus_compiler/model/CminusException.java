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
    
    public CminusException(String message) {
        super(message);
    }  
    
    public CminusException(String expected, Token found) {
        super("Invalid token. Expected " + expected + ". Found " + found.toString());
    }
    
    public CminusException(Token found, TokenType... expectedTypes) {
        super();
        errorMessage = "Invalid token. Expected " + getExpectedTypesString(expectedTypes) + ". Found " + found.toString();
    }

    @Override
    public String getMessage() {
        if(errorMessage != null) {
            return errorMessage;
        }
        
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static String getExpectedTypesString(TokenType[] types) {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < types.length; i++) {
            builder.append(types[i].toString());
            if (types.length > 1 && i == types.length -1) {
                builder.append(" or ");
            } else {
                builder.append(", ");
            }
        }
        
        return builder.toString();
    }
}
