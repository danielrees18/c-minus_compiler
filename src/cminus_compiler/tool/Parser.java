package cminus_compiler.tool;

import cminus_compiler.grammar.*;
import cminus_compiler.interfaces.ParserInterface;
import cminus_compiler.interfaces.ScannerInterface;
import cminus_compiler.model.CminusException;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import static cminus_compiler.model.TokenType.*;
import com.sun.org.apache.xpath.internal.functions.FunctionDef1Arg;
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
    private TokenType[] firstOfStatement = {LPAREN_TOKEN, LCURLY_TOKEN, NUM_TOKEN, ID_TOKEN, IF_TOKEN, WHILE_TOKEN, RETURN_TOKEN, SEMICOLON_TOKEN};
 
        
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
            declaration = parseDeclarationPrime(match(ID_TOKEN));
        } else {
            throw new CminusException(nextToken, VOID_TOKEN, INT_TOKEN);
        }
        return declaration;
    }
        
    //3. decl-prime → [ [ NUM ] ] ; |  ( params ) compound-stmt
    private Declaration parseDeclarationPrime(Token ID) throws CminusException {
        Declaration declaration = null;
        Token nextToken = scanner.viewNextToken();
        if (nextToken.equals(LBRACKET_TOKEN)) {
            match(LBRACKET_TOKEN);
            Num number = new Num(match(NUM_TOKEN));
            match(RBRACKET_TOKEN);
            declaration = new VarDeclaration(number, ID);
        }
        else if (nextToken.equals(SEMICOLON_TOKEN)) {
            declaration = new VarDeclaration(new Num(), ID);
        }
        else if (nextToken.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            ArrayList<Param> params = parseParams();
            match(RPAREN_TOKEN);
            CompoundStatement compound_statement = parseCompoundStatement();
            declaration = new FunDeclaration("int", params, compound_statement);
        }
        else {
            //error
            throw new CminusException(nextToken, LBRACKET_TOKEN, SEMICOLON_TOKEN, LPAREN_TOKEN);
        }
        
        return declaration;
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
        Token nextToken = scanner.viewNextToken();
        if (nextToken.equals(VOID_TOKEN)) {
            // just pass up an empty arraylist
        }
        else {
            params.add(parseParam());
            nextToken = scanner.viewNextToken();
            while (!nextToken.equals(RPAREN_TOKEN)) {
                match(COMMA_TOKEN);
                params.add(parseParam());
            }
        }
        
        return params;
    }
    
    // 7. param → int ID [ [ ] ]
    private Param parseParam() throws CminusException {
        match(INT_TOKEN);
        Token ID = scanner.getNextToken();
        boolean isArray = false;
        if (scanner.viewNextToken().equals(RBRACKET_TOKEN)) {
            match(RBRACKET_TOKEN);
            match(LBRACKET_TOKEN);
            isArray = true;
        }
        
        Param param = new Param(ID, isArray);
        
        return param;
    }
    
    // 8. compound-stmt → { { var-decl } { stmt } }
    private CompoundStatement parseCompoundStatement() throws CminusException {
        CompoundStatement compoundStatement = null;
        match(RCURLY_TOKEN);
        ArrayList<VarDeclaration> localDeclarations = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        Token nextToken = scanner.viewNextToken();
        if (nextToken.equals(LCURLY_TOKEN)) {
            // this is a compound statement of do nothing
            compoundStatement = new CompoundStatement();
        }
        else if (nextToken.equals(INT_TOKEN)) {
            // there is at least one var-decl
        }
        // first set of stmt => (, NUM, ID, {, if, while, return, ; 
        else if (nextToken.equals(LPAREN_TOKEN) || nextToken.equals(NUM_TOKEN) 
                || nextToken.equals(ID_TOKEN) || nextToken.equals(IF_TOKEN) 
                || nextToken.equals(WHILE_TOKEN) || nextToken.equals(RETURN_TOKEN) 
                || nextToken.equals(SEMICOLON_TOKEN)) {
            // there is at least one stmt
        }
        else {
            //error
            throw new CminusException(nextToken, ERROR);
        }
        
        match(LCURLY_TOKEN);
        return compoundStatement;
    }
    
    // 9. stmt → expression-stmt | compound-stmt | selection-stmt | iteration-stmt | return-stmt
    private Statement parseStmt() throws CminusException {
        // Statement to be returned
        Statement statement = null;
        
        // Look ahead to the next token
        Token nextToken = scanner.viewNextToken();
        if(this.isInFirstOfExpressionStatement(nextToken)) {
            // Parse expression statement
            statement = parseExpressionStatement();
        } else if (nextToken.equals(LCURLY_TOKEN)) {
            // Parse Compound statement
            statement = parseCompoundStatement();
        } else if (nextToken.equals(IF_TOKEN)) {
            // Parse selection statement
            statement = parseSelectionStatement();
        } else if (nextToken.equals(WHILE_TOKEN)) {
            // Parse iteration statement
            statement = parseIterationStatement();
        } else if (nextToken.equals(RETURN_TOKEN)) {
            // Parse return statement
            statement = parseReturnStatement();
        } else {
            // error
            throw new CminusException(nextToken, firstOfStatement);
        }
        
        return statement;
    }
    private ExpressionStatement parseExpressionStatement() throws CminusException {
        
        return null;
    }
    private SelectionStatement parseSelectionStatement() throws CminusException {
        
        return null;
    }
    private IterationStatement parseIterationStatement() throws CminusException {
        
        return null;
    }
    private ReturnStatement parseReturnStatement() throws CminusException {
        
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
    private Token match(TokenType expectedType) throws CminusException {
        Token token = scanner.getNextToken();
        if(!token.equals(expectedType)) {
            throw new CminusException(token, expectedType);
        }
        
        return token;
    }
    
    /**
     * Evaluates the token.getTokenType and compares it to the first set of expression-statement.
     * expression-statement =>  ; , (, NUM, ID
     * @param token -   Token being evaluated
     * @return  -   True if token.equals(SEMICOLON || COMMA || LPAREN || NUM || ID). False otherwise
     */
    private boolean isInFirstOfExpressionStatement(Token token) {
        return token.equals(SEMICOLON_TOKEN) || token.equals(COMMA_TOKEN) || token.equals(LPAREN_TOKEN) 
                    || token.equals(NUM_TOKEN) || token.equals(ID_TOKEN);
    }
}
