package cminus_compiler.tool;

import cminus_compiler.grammar.*;
import cminus_compiler.interfaces.ParserInterface;
import cminus_compiler.interfaces.ScannerInterface;
import cminus_compiler.model.CminusException;
import cminus_compiler.model.Token;
import static cminus_compiler.model.TokenType.EOF_TOKEN;
import static cminus_compiler.model.TokenType.INT_TOKEN;
import static cminus_compiler.model.TokenType.VOID_TOKEN;

/**
 * Lorem Ipsum Dolor Sit Amet.
 *
 * @author Nathan
 * @version 1.0
 * Created:
 * Summary of Modifications:
 *
 * Description:
 */
public class Parser implements ParserInterface {
    
    private ScannerInterface scanner;
 
    // Constructor
    public Parser(ScannerInterface scanner) {
        this.scanner = scanner;
    }
    
    
    // Interface Methods
    @Override
    public Program parse() {
        Program program = null;
        try {
            program = parseProgram();
        } catch (CminusException e) {
            e.printStackTrace();
        }
        return program;
    }
    
    
    // Private Helper Methods
    public Program parseProgram() throws CminusException {
        Program program = new Program();
        while(!scanner.viewNextToken().equals(EOF_TOKEN)) {
            program.addDeclaration(parseDecl());
        }
        return program;
    }
    
    public Declaration parseDecl() throws CminusException {
        Declaration declaration = null;
        Token nextToken = scanner.getNextToken();
        if(nextToken.equals(VOID_TOKEN)) {
            // something voidy
        } else if (nextToken.equals(INT_TOKEN)) {
            // something inty
        } else {
            // error
            throw new CminusException("void or int", nextToken);
        }
        return declaration;
    }
    public VarDeclaration parseVarDecl() {
        
        return null;
    }
    public void parseTypeSpecifier() {
        
    }
    public FunDeclaration parseFunDecl() {
        
        return null;
    }
    public void parseParams() {
        
    }
    public void parseParam() {
        
    }
    public CompoundStatement parseCompoundStmt() {
        
        return null;
    }
    public Statement parseStmt() {
        
        return null;
    }
    public ExpressionStatement parseExpressionStmt() {
        
        return null;
    }
    public SelectionStatement parseSelectionStmt() {
        
        return null;
    }
    public IterationStatement parseIterationStmt() {
        
        return null;
    }
    public ReturnStatement parseReturnStmt() {
        
        return null;
    }
    public Expression parseExpression() {
        
        return null;
    }
    public BinaryOperation parseBinaryOperation() {
        
        return null;
    }
    public AssignmentOperation parseAssignmentOperation() {
        
        return null;
    }
    public Var parseVar() {
        return null;
    }
    
    public Num parseNum() {
        
        return null;
    }
    public void parseRelop() {
        
    }
    
    public void parseAddop() {
        
    }
    public void parseTerm() {
        
    }
    public void parseMulop() {
        
    }
    public void parseFactor() {
        
    }
    public Call parseCall() {
        return null;   
    }
    public void parseArgs() {
        
    }
    
}
