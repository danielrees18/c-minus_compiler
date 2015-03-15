package cminus_compiler.tool;

import cminus_compiler.main.ScannerInterface;
import cminus_compiler.model.StateType;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import java.io.BufferedReader;
import java.io.IOException;

/** 
 *   Scanner class implements the ScannerInterface, providing support for
 * scanning a buffered reader for tokens that are recognized in the C- language
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Scanner.java
 * Created: Feb 2015
 *	
 *
 * Description:  Class provides functionality of the ScannerInterface which
 * exposes two methods to consume or view the next token in a .c file
 */

public class Scanner implements ScannerInterface {
    
    // Scanner Variables
    private BufferedReader reader;
    private Token nextToken;
    private char nextChar;
    private boolean isEOF = false;

    
    // Scanner Constructor
    public Scanner(BufferedReader br) {
        this.reader = br;
        this.nextChar = (char)scanCharacter();
        this.nextToken = scanToken();
    }

    
    // Scanner Methods
    /**
     * Consume the next token from the file
     * @return  -   Next Token from the file
     */
    @Override
    public Token getNextToken() {
        Token returnToken = nextToken;
        // get the next token
        if(nextToken.getTokenType() != TokenType.EOF_TOKEN) {
            nextToken = scanToken();
        }
        
        return returnToken;
    }
    
    /**
     * View the next token from the file, does not consume token
     * @return  -   Next Token from the file
     */
    @Override
    public Token viewNextToken() {
        return nextToken;
    }
    
    // Scan the file for the next token 
    private Token scanToken() {
        Token token = new Token();
        StateType state = StateType.START;
        String tokenData = "";
        boolean saveCharacter;
        
        // Loop until a token is found
        while(state != StateType.DONE) {
            // If at the EOF, set EOF_TOKEN.
            if(isEOF) {
                state = StateType.DONE;
                token.setTokenType(TokenType.EOF_TOKEN);
            }
            
            char c = viewNextChar();
            saveCharacter = true;
            switch(state) {
                case START:
                    c = getNextChar();
                    // Looking for numbers
                    if (Character.isDigit(c)) {
                        state = StateType.IN_NUM;
                        
                    // Looing for identifiers    
                    } else if (Character.isAlphabetic(c)) {
                        state = StateType.IN_ID;
                        
                    // Looking for whitespace to ignore    
                    } else if (c == ' ' || c == '\n' || c == '\t') {
                        saveCharacter = false;
                    
                    // Looking for symbols
                    } else {
                        saveCharacter = false;
                        state = StateType.DONE;
                        switch(c) {
                            case '+':
                                token.setTokenType(TokenType.PLUS_TOKEN);
                                break;
                            case '-':
                                token.setTokenType(TokenType.MINUS_TOKEN);
                                break;
                            case '*':
                                token.setTokenType(TokenType.MULT_TOKEN);
                                break;
                            case '/':
                                state = StateType.IN_COMMENT_OPEN;
                                break;
                            case '<':
                                state = StateType.IN_LEQ;
                                break;
                            case '>':
                                state = StateType.IN_GREQ;
                                break;
                            case '=':
                                state = StateType.IN_EQUALS;
                                break;
                            case '!':
                                state = StateType.IN_NEQ;
                                break;
                            case ';':
                                token.setTokenType(TokenType.SEMICOLON_TOKEN);
                                break;
                            case ',':
                                token.setTokenType(TokenType.COMMA_TOKEN);
                                break;
                            case '(':
                                token.setTokenType(TokenType.LPAREN_TOKEN);
                                break;
                            case ')':
                                token.setTokenType(TokenType.RPAREN_TOKEN);
                                break;
                            case '[':
                                token.setTokenType(TokenType.LBRACKET_TOKEN);
                                break;
                            case ']':
                                token.setTokenType(TokenType.RBRACKET_TOKEN);
                                break;
                            case '{':
                                token.setTokenType(TokenType.LCURLY_TOKEN);
                                break;
                            case '}':
                                token.setTokenType(TokenType.RCURLY_TOKEN);
                                break;
                        }
                    }
                    break;
                    
                
                case IN_COMMENT_OPEN:
                    saveCharacter = false;
                    if(viewNextChar() == '*') {
                        // /* comment state
                        state = StateType.IN_COMMENT;
                        saveCharacter = false;
                    } else {
                        // Not a star, division op
                        token.setTokenType(TokenType.DIV_TOKEN);
                        state = StateType.DONE;
                    }
                    
                    break;
                    
                case IN_COMMENT:
                    saveCharacter = false;
                    if(getNextChar() == '*' && viewNextChar() == '/') {
                        state = StateType.START;
                        getNextChar(); // Consume next char
                    }
                    break;
                    
                case IN_EQUALS:
                    state = StateType.DONE;
                    saveCharacter = false;
                    // ==
                    if(viewNextChar() == '=') {
                        token.setTokenType(TokenType.EQUAL_TOKEN);
                        getNextChar(); // Consume
                    // =
                    } else {
                        token.setTokenType(TokenType.ASSIGN_TOKEN);
                    }
                    break;
                    
                case IN_NEQ:
                    if (viewNextChar() == '=') {
                        state = StateType.DONE;
                        token.setTokenType(TokenType.NEQ_TOKEN);
                        getNextChar(); // Consume
                    } 
                    else {
                        state = StateType.IN_ERROR;
                        saveCharacter = true;
                        getNextChar();
                    }
                    break;
                    
                case IN_GREQ:
                    state = StateType.DONE;
                    saveCharacter = false;
                    // >=
                    if(viewNextChar() == '=') {
                        token.setTokenType(TokenType.GEQ_TOKEN);
                        getNextChar(); // Consume
                    // >
                    } else {
                        token.setTokenType(TokenType.GREAT_TOKEN);
                    }          
                    break;
                    
                case IN_LEQ:
                    state = StateType.DONE;
                    saveCharacter = false;
                    // <=
                    if(viewNextChar() == '=') {
                        token.setTokenType(TokenType.LEQ_TOKEN);
                        getNextChar(); // Consume
                    // <
                    } else {
                        token.setTokenType(TokenType.LESS_TOKEN);
                    }
                    break;
                    
                case IN_NUM:
                    // 1cat is invalid
                    if(Character.isAlphabetic(c)) {
                        state = StateType.IN_ERROR;
                        getNextChar();
                        
                    } else if(!Character.isDigit(c)) {
                        state = StateType.DONE;
                        saveCharacter = false;
                        token.setTokenType(TokenType.NUM_TOKEN);
                    } else {
                        getNextChar(); // Consume
                    }
                    break;
                
                case IN_ID:
                    // cat3 is invalid
                    if(Character.isDigit(c)) {
                        state = StateType.IN_ERROR;
                        getNextChar();
                        
                    } else if(!Character.isAlphabetic(c)) {
                        state = StateType.DONE;
                        saveCharacter = false;
                        token.setTokenType(TokenType.ID_TOKEN);
                    } else {
                        getNextChar(); // Consume
                    }
                    break;

                case IN_ERROR:
                    if(viewNextChar() != ' ' && viewNextChar() != '\n' && viewNextChar() != '\t') {
                        saveCharacter = true;
                        getNextChar();
                    } else {
                        state = StateType.DONE;
                        token.setTokenType(TokenType.ERROR);
                        saveCharacter = false;
                    }
                    break;    
                    
                case DONE:
                default: // Should this happen, you're toast.
                    System.err.println("Your scanner sux: " + c);
                    state = StateType.DONE;
            }
            
            // Store c into string  
            if(saveCharacter) {
                tokenData += c;
            }
            
            // Create token if done
            if(state == StateType.DONE) {
                if(token.getTokenType() == TokenType.ID_TOKEN) {
                    token = checkIfWordIsKeyword(token, tokenData);
                } else {
                    if (tokenData != null && tokenData.isEmpty()) {
                        tokenData = null;
                    }
                    token.setTokenData(tokenData);
                }
            }
        }
        return token;
    }
    
    
    /**
     * Consume the next character in the buffered reader
     * @return  -   The next character from the reader
     */
    private char getNextChar() {
        char returnChar = nextChar;
     
        // Prepare next char
        nextChar = (char) scanCharacter();
        
        return returnChar;
    }
    
    /**
     * Gets the next character from the reader, accounting for EOF
     * @return  -   Integer value of the next character from the reader
     */
    private int scanCharacter() {
        int c = -1;
        try { 
            c  = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // reader.read() returns -1 when reading end of file.
        if(c == -1) {
            isEOF = true;
        }
        
        return c;
    }
    
    /**
     * View the next character in the buffered reader without consuming it
     * @return  -   The next character from the reader 
     */
    private char viewNextChar() {
        return nextChar;   
    }
    
    private String[] keywords = {"else", "if", "int", "while", "void","return"};
    /**
     * Checks if the argument is a keyword
     * @param word
     * @return 
     */
    private Token checkIfWordIsKeyword(Token token, String word) {
        if(keywords[0].equals(word)) {
            token.setTokenType(TokenType.ELSE_TOKEN);
        } else if(keywords[1].equals(word)) {
            token.setTokenType(TokenType.IF_TOKEN);
        } else if(keywords[2].equals(word)) {
            token.setTokenType(TokenType.INT_TOKEN);
        } else if(keywords[3].equals(word)) {
            token.setTokenType(TokenType.WHILE_TOKEN);
        } else if(keywords[4].equals(word)) {
            token.setTokenType(TokenType.VOID_TOKEN);
        } else if(keywords[5].equals(word)) {
            token.setTokenType(TokenType.RETURN_TOKEN);
        } else {
            token.setTokenData(word);
        }
        
        return token;
    }
}