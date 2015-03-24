/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.tool;

/**
 *
 * @author drees
 */
public class IndentTool {
    
    public static String indent(int indentCount) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for(int i = 0; i < indentCount; i++) {
            builder.append("---");
            if(i == indentCount - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
    
}
