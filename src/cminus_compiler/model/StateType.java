package cminus_compiler.model;

/** 
 *   ENUM of all possible states that could occur while scanning a c file
 *
 * @authors Daniel Rees, Nathan Kallman
 * @version 1.0
 * File: StateType.java
 * Created: Feb 2015	
 *
 * Description:  Exposes all available state types as ENUM values.
 */
public enum StateType {
    START,
    IN_NUM,
    IN_COMMENT,
    IN_ID,
    DONE;
}
