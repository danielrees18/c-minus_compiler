package cminus_compiler.tool;

import cminus_compiler.grammar.*;
import cminus_compiler.interfaces.ParserInterface;
import cminus_compiler.interfaces.ScannerInterface;
import cminus_compiler.model.CminusException;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import static cminus_compiler.model.TokenType.*;
import java.util.ArrayList;

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
    
    
    /*
        PARSE GRAMMAR METHODS
    */
    
    // 1. program → decl {decl}
    private Program parseProgram() throws CminusException {
        Program program = new Program();
        while(!scanner.viewNextToken().equals(EOF_TOKEN)) {
            program.addDeclaration(parseDeclaration());
        }
        return program;
    }
    
    // 2. decl → void ID fun-decl-prime | int ID decl-prime
    private Declaration parseDeclaration() throws CminusException {
        Declaration declaration = null;
        Token nextToken = scanner.getNextToken();
        
        if(nextToken.equals(VOID_TOKEN)) {
            declaration = parseFunctionDeclarationPrime("void");
        } else if (nextToken.equals(INT_TOKEN)) {
            declaration = parseVariableDeclaration();
        } else {
            throw new CminusException(nextToken, VOID_TOKEN, INT_TOKEN);
        }
        return declaration;
    }
        
    //3. decl-prime → [ [ NUM ] ] ; |  ( params ) compound-stmt
    private Declaration parseDeclarationPrime() throws CminusException {
        return null;
    }
    
    // 4. var-decl → int ID [ [ NUM ] ] ;
    private VarDeclaration parseVariableDeclaration() throws CminusException {
        VarDeclaration declaration = null;
        Token nextToken = scanner.viewNextToken();
        
        if(nextToken.equals(ID_TOKEN)) {
            
        } else {
            
        }
        
        return declaration;
    }
     
    // 5. fun-decl-prime → ( params ) compound-stmt
    private FunDeclaration parseFunctionDeclarationPrime(String returnType) throws CminusException {
        FunDeclaration declaration = null;
        Token nextToken = scanner.viewNextToken();
        
        if (nextToken.equals(LPAREN_TOKEN)) {
            this.match(LPAREN_TOKEN);
            ArrayList<Param> params = parseParams();
            this.match(RPAREN_TOKEN);
            
            CompoundStatement compoundStatement = parseCompoundStatement();
            declaration = new FunDeclaration(returnType, params, compoundStatement);
        } else {
        }
        return declaration;
    }
    
    // 6. params → param { , param } | void
    private ArrayList<Param> parseParams() throws CminusException {
        ArrayList<Param> params = new ArrayList<>();
        
        return params;
    }
    
    // 7. param → int ID [ [ ] ]
    private Param parseParam() throws CminusException {
        Param param = null;
        
        return param;
    }
    
    private CompoundStatement parseCompoundStatement() throws CminusException {
        
        return null;
    }
    
    private Statement parseStmt() throws CminusException {
        
        return null;
    }
    private ExpressionStatement parseExpressionStmt() throws CminusException {
        
        return null;
    }
    private SelectionStatement parseSelectionStmt() throws CminusException {
        
        return null;
    }
    private IterationStatement parseIterationStmt() throws CminusException {
        
        return null;
    }
    private ReturnStatement parseReturnStmt() throws CminusException {
        
        return null;
    }
    private Expression parseExpression() throws CminusException {
        
        return null;
    }
    private BinaryOperation parseBinaryOperation() throws CminusException {
        
        return null;
    }
    private AssignmentOperation parseAssignmentOperation() throws CminusException {
        
        return null;
    }
    private Var parseVar() throws CminusException {
        return null;
    }
    
    private Num parseNum() throws CminusException {
        
        return null;
    }
    private void parseRelop() throws CminusException {
        
    }
    
    private void parseAddop() throws CminusException {
        
    }
    private void parseTerm() throws CminusException {
        
    }
    private void parseMulop() throws CminusException {
        
    }
    private void parseFactor() throws CminusException {
        
    }
    private Call parseCall() throws CminusException {
        return null;   
    }
    private void parseArgs() throws CminusException {
        
    }
    
    
    
    // Parsing Helping Methods
    
    /**
     * Consumes the next token in the scanner and matches it to the expected type.
     * @param expectedType  -   Type expected to receive
     * @return  -   True if the types match. Throws otherwise
     * @throws CminusException  -   Throws if the next token type doesn't equal the expected type
     */
    private boolean match(TokenType expectedType) throws CminusException {
        Token token = scanner.getNextToken();
        if(!token.equals(expectedType)) {
            throw new CminusException(token, expectedType);
        }
        
        return true;
    }
}
