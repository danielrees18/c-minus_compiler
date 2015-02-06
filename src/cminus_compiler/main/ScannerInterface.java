package cminus_compiler.main;

import cminus_compiler.model.Token;

/** 
 *   Interface for a C-Minus scanner tool
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: ScannerInterface.java
 * Created: Feb 2015	
 *
 * Description:  Exposes methods to consume and view tokens in a file
 */
public interface ScannerInterface {
 
    public Token getNextToken();
    public Token viewNextToken();   
}
