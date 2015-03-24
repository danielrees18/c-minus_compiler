package cminus_compiler.tool;

import cminus_compiler.grammar.Program;
import cminus_compiler.interfaces.ParserInterface;
import cminus_compiler.interfaces.ScannerInterface;
import cminus_compiler.model.Token;
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

            ScannerInterface scanner = new Scanner(br);

            // parse
            ParserInterface parser = new Parser(scanner);
            Program program = parser.parse();
            br.close();
            
            writeToFile(program, file.getName());
            
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
    private void writeToFile(Program program, String fileName) throws IOException {
        File fout = new File(fileName + "_ast.txt");
        
        FileWriter writer = new FileWriter(fout);
        writer.write("Filename: " + fileName);
        writer.write(program.printTree());

        writer.close();
        System.out.println("Finished writing program to " + fout.getName());

    }
}
