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
    private TokenType[] firstOfStatement = {LPAREN_TOKEN, LCURLY_TOKEN, NUM_TOKEN, ID_TOKEN, IF_TOKEN, WHILE_TOKEN, RETURN_TOKEN, SEMICOLON_TOKEN};
    private TokenType[] firstOfExpressionStatement = { SEMICOLON_TOKEN, COMMA_TOKEN, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN };
 
        
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
        match(INT_TOKEN);
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(ID_TOKEN)) {
            Token ID = scanner.getNextToken();
            nextToken = scanner.viewNextToken();
            if (nextToken.equals(LBRACKET_TOKEN)) {
                match(LBRACKET_TOKEN);
                Num num = new Num(match(NUM_TOKEN));
                match(RBRACKET_TOKEN);
                declaration = new VarDeclaration(num, ID);
            }
            else {
                declaration = new VarDeclaration(new Num(), ID);
            }
            match(SEMICOLON_TOKEN);
        } else {
            //error
            throw new CminusException(nextToken, ID_TOKEN);
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
        match(LCURLY_TOKEN);
        ArrayList<VarDeclaration> localDeclarations = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        Token nextToken = scanner.viewNextToken();
        if (nextToken.equals(RCURLY_TOKEN)) {
            // this is a compound statement of do nothing
            compoundStatement = new CompoundStatement();
        }
        else if (nextToken.equals(INT_TOKEN)) {
            // there is at least one var-decl
            localDeclarations.add(parseVariableDeclaration());
            nextToken = scanner.viewNextToken();
            while (nextToken.equals(INT_TOKEN)) {
                localDeclarations.add(parseVariableDeclaration());
                nextToken = scanner.viewNextToken();
            }
            if (isInFirstOfStatement(nextToken)) {
                // there is at least one stmt
                statements = parseStatementList();
            }
            compoundStatement = new CompoundStatement(localDeclarations, statements);
        }
        // first set of stmt => (, NUM, ID, {, if, while, return, ; 
        else if (isInFirstOfStatement(nextToken)) {
            // there is at least one stmt
            statements = parseStatementList();
            compoundStatement = new CompoundStatement(localDeclarations, statements);
        }
        else {
            //error
            throw new CminusException(nextToken, ERROR);
        }
        
        match(RCURLY_TOKEN);
        return compoundStatement;
    }
    
    private ArrayList<Statement> parseStatementList() throws CminusException {
        ArrayList<Statement> list = new ArrayList<>();
        Token nextToken = scanner.viewNextToken();
        
        if (isInFirstOfStatement(nextToken)) {
            list.add(parseStatement());
            while (isInFirstOfStatement(scanner.viewNextToken())) {
                list.add(parseStatement());
            }
        }
        else {
            //error
            throw new CminusException(nextToken, ERROR);
        }
        
        
        return list;
    }
    
    // 9. stmt → expression-stmt | compound-stmt | selection-stmt | iteration-stmt | return-stmt
    private Statement parseStatement() throws CminusException {
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
    
    // 10. expression-stmt → [ expression ] ; 
    private ExpressionStatement parseExpressionStatement() throws CminusException {
        ExpressionStatement expressionStatement = null;
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(SEMICOLON_TOKEN)) {
            // End of line. Consume and return empty statement
            expressionStatement = new ExpressionStatement();
            match(SEMICOLON_TOKEN);
            
        } else if(isInFirstOfExpressionStatement(nextToken)) {
            expressionStatement = new ExpressionStatement(parseExpression());
            
        } else {
          throw new CminusException(nextToken, firstOfExpressionStatement);
        }
        
        return expressionStatement;
    }
    
    // 11. selection-stmt → if ( expression ) stmt [ else stmt ]
    private SelectionStatement parseSelectionStatement() throws CminusException {
        // SelectionStatement object to be returned
        SelectionStatement statement = new SelectionStatement();
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(IF_TOKEN)) {
            match(IF_TOKEN);
            match(LPAREN_TOKEN);
            statement.setExpression(parseExpression());
            match(RPAREN_TOKEN);
            statement.setPrimaryStatement(parseStatement());
            
            // Optional else statement. If not found, do not throw error
            if(scanner.viewNextToken().equals(ELSE_TOKEN)) {
                match(ELSE_TOKEN);
                statement.setOptionalStatement(parseStatement());
            }
        } else {
            throw new CminusException(nextToken, IF_TOKEN);
        }
        
        return statement;
    }
    
    // 12. iteration-stmt → while ( expression ) stmt
    private IterationStatement parseIterationStatement() throws CminusException {
        IterationStatement statement = new IterationStatement();
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(WHILE_TOKEN)) {
            match(WHILE_TOKEN);
            match(LPAREN_TOKEN);
            statement.setExpression(parseExpression());
            match(RPAREN_TOKEN);
            statement.setStatement(parseStatement());
        } else {
            throw new CminusException(nextToken, WHILE_TOKEN);
        }
        
        return statement;
    }
    
    // 13. return-stmt → return [ expression ] ;
    private ReturnStatement parseReturnStatement() throws CminusException {
        ReturnStatement statement = new ReturnStatement();
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(RETURN_TOKEN)) {
            match(RETURN_TOKEN);
           
            // Optional expression exists if semicolon is not next. If not found, do not throw error
            if(!scanner.viewNextToken().equals(SEMICOLON_TOKEN)) {
                statement.setExpression(parseExpression());
            }
            
            match(SEMICOLON_TOKEN);
        } else {
            throw new CminusException(nextToken, RETURN_TOKEN);
        }
        
        return statement;
    }
    
    // 14. expression → ( expression ) simple-exp-prime | NUM simple-exp-prime | ID exp-prime
    private Expression parseExpression() throws CminusException {
        Expression expression = null;
        
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            parseExpression();
            match(RPAREN_TOKEN);
            expression = parseSimpleExpressionPrime();
        } else if (nextToken.equals(NUM_TOKEN)) {
            Token numToken = match(NUM_TOKEN);
            expression = parseSimpleExpressionPrime(numToken);
        } else if (nextToken.equals(ID_TOKEN)) {
            Token idToken = match(ID_TOKEN);
            expression = parseExpressionPrime(idToken);
        } else {
            throw new CminusException(nextToken, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
        
        return expression;
    }
    
    // 15. exp-prime → = expression | simple-exp-prime | ( args ) simple-exp-prime | [ expression ] exp-doubleprime
    private Expression parseExpressionPrime(Token prevToken) throws CminusException {
        Expression expression = null;
        Token t = scanner.viewNextToken();
        if(t.equals(ASSIGN_TOKEN)) {
            match(ASSIGN_TOKEN);
            expression = parseExpression();
            
        } else if (t.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            // TODO: parse args. Return value?
            match(RPAREN_TOKEN);
            parseSimpleExpressionPrime(null); // TODO: pass a value?
            
        } else if (t.equals(LBRACKET_TOKEN)) {
            match(LBRACKET_TOKEN);
            // TODO: parse args. Return value?
            match(RBRACKET_TOKEN);
            parseExpressionDoublePrime(null);
            
        } else if (t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN) ) {
            parseSimpleExpressionPrime(null);
            
        // FollowSet of ExpressionPrime. Goes to epsilon
        } else if (t.equals(SEMICOLON_TOKEN) || t.equals(RPAREN_TOKEN) || t.equals(RBRACKET_TOKEN) || t.equals(COMMA_TOKEN)) {
            return null;
            
        } else {
            throw new CminusException(t, LPAREN_TOKEN, LBRACKET_TOKEN, MULT_TOKEN, DIV_TOKEN);
        }
        return expression;
    }
    
    // 16. exp-doubleprime → = expression | simple-exp-prime
    private Expression parseExpressionDoublePrime(Token prevToken) throws CminusException {
        Expression expression = null;
        Token t = scanner.viewNextToken();
        if(t.equals(ASSIGN_TOKEN)) {
            match(ASSIGN_TOKEN);
            expression = parseExpression();
        } else if (t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN) ) {
            expression = parseSimpleExpressionPrime(null); // TODO: Value?
            
        // FollowSet of ExpressionPrime. Goes to epsilon
        } else if (t.equals(SEMICOLON_TOKEN) || t.equals(RPAREN_TOKEN) || t.equals(RBRACKET_TOKEN) || t.equals(COMMA_TOKEN)) {
            return null;
            
        } else {
            throw new CminusException(t, ASSIGN_TOKEN, MULT_TOKEN, DIV_TOKEN);
        }
        return expression;
    }
    
    // 17. var → [ [ expression ] ]
    private Var parseVar() throws CminusException {
        Var var = null;
       
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(LBRACKET_TOKEN)) {
            match(LBRACKET_TOKEN);
            var = (Var) parseExpression();
            match(RBRACKET_TOKEN);
       
        } else if (isInFollowOfVar(nextToken)) {
            return null;
            
        } else {
            throw new CminusException(nextToken, LBRACKET_TOKEN);
        }
        
        return var;
    }
    
    // 18. simple-exp-prime → additive-exp-prime [ relop additive-exp ]
    private Expression parseSimpleExpressionPrime(Token prevToken) throws CminusException {
        Expression expression = null;
        
        Token t = scanner.viewNextToken();
        if(t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN)) {
            parseAdditiveExpressionPrime();
        
        } else if (t.equals(SEMICOLON_TOKEN) || t.equals(RPAREN_TOKEN) || t.equals(RBRACKET_TOKEN) || t.equals(COMMA_TOKEN)) {
            return null;
            
        } else {
            throw new CminusException(t, LBRACKET_TOKEN);
        }
        
        return expression;
    }
    
    
    private void parseAdditiveExpressionPrime() {
        
    }
    
    // 19. relop → <= | < | > | >= | == | !=
    // 22. addop → + | -
    // 25. mulop →  * | / 
    private BinaryOperation parseBinaryOperation(Expression lhs) throws CminusException {
        // TODO: we need to read what kind of operator it is...create a BinaryOperation object and add the lhs and parse whats next and add it as well
        return null;
    }
    private AssignmentOperation parseAssignmentOperation() throws CminusException {
        
        return null;
    }
    
    private Num parseNum() throws CminusException {
        Token num = match(NUM_TOKEN);
        return new Num(num);
    }
    // 19. relop → <= | < | > | >= | == | !=
    private Token parseRelop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == LESS_TOKEN || op.getTokenType() == LEQ_TOKEN 
                || op.getTokenType() == GREAT_TOKEN || op.getTokenType() == GEQ_TOKEN 
                || op.getTokenType() == NEQ_TOKEN || op.getTokenType() == EQUAL_TOKEN) {
            return op;
        }
        else {
            throw new CminusException(op, LESS_TOKEN,LEQ_TOKEN,GREAT_TOKEN,GEQ_TOKEN,NEQ_TOKEN, EQUAL_TOKEN);
        }
    }
    // 22. addop → + | -
    private Token parseAddop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == PLUS_TOKEN || op.getTokenType() == MINUS_TOKEN) {
            return op;
        }
        else {
            throw new CminusException(op, PLUS_TOKEN, MINUS_TOKEN);
        }
    }
    
    private void parseTerm() throws CminusException {
        
    }
    
    // 25. mulop →  * | / 
    private Token parseMulop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == MULT_TOKEN || op.getTokenType() == DIV_TOKEN) {
            return op;
        }
        else {
            throw new CminusException(op, MULT_TOKEN, DIV_TOKEN);
        }
    }
    // 26. factor → ( expression ) | varcall | NUM
    private Expression parseFactor() throws CminusException {
        Token token = scanner.viewNextToken();
        Expression expression = null;
        if (token.equals(NUM_TOKEN)) {
            expression = parseNum();
        }
        else if (token.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            expression = parseExpression();
            match(RPAREN_TOKEN);
        }
        else if (token.equals(ID_TOKEN)) {
            expression = parseVarCall();
        }
        else {
            throw new CminusException(token, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
        return expression;
    }
    // 27.  varcall → ID var | ID call
    private Expression parseVarCall() throws CminusException {
        Token ID = match(ID_TOKEN);
        Token nextToken = scanner.viewNextToken();
        
        return null;
    }
    
    // 28. call → ( args )
    private Call parseCall(Token ID) throws CminusException {
        Call call = null;
        
        Token t = scanner.viewNextToken();
        if(t.equals(LPAREN_TOKEN)) {
            parseArgs();
        } else {
            throw new CminusException(t, LPAREN_TOKEN);
        }
        
        return call;
    }
    
    // 29. args → [ expression { , expression } ]
    private void parseArgs() throws CminusException {
        
        Token t = scanner.viewNextToken();
        if(t.equals(LPAREN_TOKEN) || t.equals(NUM_TOKEN) || t.equals(ID_TOKEN)) {
            parseExpression();
            t = scanner.viewNextToken();
            if(t.equals(COMMA_TOKEN)) {
                parseExpression(); // Back to args?
            } else {
                // No new expressions. Don't throw err
            }
        } else {
            throw new CminusException(t, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
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
    
    /**
     * Evaluates the token.getTokenType and compares it to be the first set of statement.
     * stmt => (, NUM, ID, {, if, while, return, ; 
     * @param token -  Token being evaluated
     * @return -  True iff token.equals(LPAREN_TOKEN || NUM_TOKEN || ID_TOKEN || IF_TOKEN || WHILE_TOKEN || RETURN_TOKEN || SEMICOLON_TOKEN)
     */
    private boolean isInFirstOfStatement(Token token) {
        return token.equals(LPAREN_TOKEN) || token.equals(NUM_TOKEN) 
                || token.equals(ID_TOKEN) || token.equals(IF_TOKEN) 
                || token.equals(WHILE_TOKEN) || token.equals(RETURN_TOKEN) 
                || token.equals(SEMICOLON_TOKEN);
    }

    /**
     * Evaluates the token.getTokenType and compares it to be the follow set of Var.
     * var { *, /, +, - ,<= , < , > , >= , == , !=, ;, ), ], , }
     * @param token -  Token being evaluated
     * @return -  True iff token.equals(MULT_TOKEN || DIV_TOKEN || ID_TOKEN || IF_TOKEN || WHILE_TOKEN || RETURN_TOKEN || SEMICOLON_TOKEN)
     */
    private boolean isInFollowOfVar(Token token) {
        return token.equals(MULT_TOKEN) || token.equals(DIV_TOKEN) 
                || token.equals(PLUS_TOKEN) || token.equals(MINUS_TOKEN) 
                || token.equals(LEQ_TOKEN) || token.equals(LESS_TOKEN) 
                || token.equals(GREAT_TOKEN) || token.equals(GEQ_TOKEN)
                || token.equals(EQUAL_TOKEN) || token.equals(NEQ_TOKEN)
                || token.equals(SEMICOLON_TOKEN) || token.equals(RPAREN_TOKEN)
                || token.equals(RBRACKET_TOKEN) || token.equals(COMMA_TOKEN);
    }
    
    
}
