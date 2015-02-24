package cminus_compiler.tool;

import cminus_compiler.main.ScannerInterface;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** 
 *   Compiler class which runs the main scan and handles file I/O
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: Compiler.java
 * Created: Feb 2015
 *	
 * Description:  Compiler provides a clean interface to interact with the 
 * scanner scanner by handling any file I/O.
 */
public class Compiler {
    
    // Compiler Variables
    private File file;
    
    
    // Compiler Constructor
    public Compiler(File cFile) {
        this.file = cFile;
    }
    
    
    // Compiler Methods
    /**
     * Compile the c file passed to the constructor
     */
    public void compile() {
        ArrayList<Token> tokens = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            
//            ScannerInterface scanner = new Scanner(br);
            ScannerInterface scanner = new CMinusFlexScanner(br);
            
            // scan for tokens while we haven't reached the end of the file
            while (scanner.viewNextToken().getTokenType() != TokenType.EOF_TOKEN) {
                Token token = scanner.getNextToken();
                System.out.println("Token Found: " + token.getTokenType().toString());
                tokens.add(token);
            }
            
            br.close();
            
            // write tokens to output file
            writeTokensToFile(tokens);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write the array of tokens to the output file
     * @param tokens    -   ArrayList of tokens to write to file
     */
    private void writeTokensToFile(ArrayList<Token> tokens) {
        File fout = new File("tokens.txt");
        
        try{
            FileWriter writer = new FileWriter(fout);
            StringBuilder builder = new StringBuilder();
            for (Token token : tokens) {
                boolean write = false;
                TokenType type = token.getTokenType();
                builder.append(type.toString());
                
                Object tokenData = token.getTokenData();
                String tokenDataString = ": ";
                if (tokenData != null) {
                    builder.append(":(");
                    builder.append(tokenData.toString());
                    builder.append(") ");
                } else {
                    builder.append(" ");
                }
                
                if (type == TokenType.LCURLY_TOKEN
                        || type == TokenType.COMMA_TOKEN) {
                    builder.append("\n \t");
                    write = true;
                } else if (type == TokenType.RCURLY_TOKEN) {
                    builder.append("\n");
                } else if (type == TokenType.SEMICOLON_TOKEN) {
                    builder.append("\n \n");
                    write = true;
                }
                
                if(write) {
                    writer.write(builder.toString());
                    builder.setLength(0);
                }

            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
