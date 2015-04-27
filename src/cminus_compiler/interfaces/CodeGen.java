/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cminus_compiler.interfaces;

import lowlevel.CodeItem;
import lowlevel.Function;

/**
 *
 * @author drees
 */
public interface CodeGen {
    
    public abstract CodeItem gencode(Function function);
    
}
