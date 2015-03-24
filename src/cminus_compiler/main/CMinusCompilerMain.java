
package cminus_compiler.main;

import java.io.File;
import cminus_compiler.tool.Compiler;

/** 
 *   Compiler main class
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: CMinusCompilerMain.java
 * Created: Feb 2015	
 *
 * Description:  Loads file, runs compiler.
 */
public class CMinusCompilerMain {

    public static void main(String[] args) {
        
//        File file = new File("./src/test.c");
        File file = new File("./src/parse.c");
//        File file = new File("./src/ben.c");
//        File file = new File("./src/heavymath.c");
//        File file = new File("./src/testcode.c");
 
        Compiler compiler = new Compiler(file);
        compiler.compile();
    }    
}
