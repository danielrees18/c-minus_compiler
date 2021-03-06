package cminus_compiler.tool;

import cminus_compiler.grammar.*;
import cminus_compiler.interfaces.ParserInterface;
import cminus_compiler.interfaces.ScannerInterface;
import cminus_compiler.model.CminusException;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import static cminus_compiler.model.TokenType.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Lorem Ipsum Dolor Sit Amet.
 *
 * @author Nathan Kallman, Daniel Rees
 * @version 1.0
 * Created: March 2015
 * Summary of Modifications:
 *
 * Description:
 */
public class Parser implements ParserInterface {
    
    // First Sets
    private final TokenType[] firstOfStatement = { 
        LPAREN_TOKEN, LCURLY_TOKEN, NUM_TOKEN, ID_TOKEN, 
        IF_TOKEN, WHILE_TOKEN, RETURN_TOKEN, SEMICOLON_TOKEN };
    
    private final TokenType[] firstOfExpressionStatement = { 
        SEMICOLON_TOKEN, COMMA_TOKEN, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN };
    
    private final TokenType[] firstOfRelop = {
        LEQ_TOKEN, LESS_TOKEN, GREAT_TOKEN, GEQ_TOKEN, EQUAL_TOKEN, NEQ_TOKEN };
    
    private final TokenType[] firstOfSimpleExpPrime = {
        MULT_TOKEN, DIV_TOKEN, LEQ_TOKEN, LESS_TOKEN, GREAT_TOKEN, GEQ_TOKEN, 
        EQUAL_TOKEN, NEQ_TOKEN, PLUS_TOKEN, MINUS_TOKEN };
    
    // Follow Sets
    private final TokenType[] followOfVar = { 
        MULT_TOKEN, DIV_TOKEN, PLUS_TOKEN, MINUS_TOKEN, LEQ_TOKEN, LESS_TOKEN, GREAT_TOKEN, GEQ_TOKEN, 
        EQUAL_TOKEN, NEQ_TOKEN, SEMICOLON_TOKEN, RPAREN_TOKEN, RBRACKET_TOKEN, COMMA_TOKEN };
    
    private final TokenType[] followOfExpressionPrime = { 
        SEMICOLON_TOKEN, RPAREN_TOKEN, RBRACKET_TOKEN, COMMA_TOKEN };
    
    private final TokenType[] followOfTermPrime = { 
        PLUS_TOKEN, MINUS_TOKEN, LEQ_TOKEN, LESS_TOKEN, GREAT_TOKEN, GEQ_TOKEN, 
        EQUAL_TOKEN, NEQ_TOKEN, RPAREN_TOKEN, RBRACKET_TOKEN, COMMA_TOKEN };
  
    
    
    // Parse exception
    public static class CodeGenerationException extends Exception {

        public CodeGenerationException(String message) {
            super(message);
        }
        
    }
    
    
    // Variables
    private final ScannerInterface scanner;
        
    // Constructor
    public Parser(ScannerInterface scanner) {
        this.scanner = scanner;
    }
    
    public Parser(String filename) {
        ScannerInterface temp = null;
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            temp = new Scanner(br);            
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.scanner = temp;
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
     *   PARSE GRAMMAR METHODS
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
            declaration = parseFunctionDeclarationPrime(match(ID_TOKEN));
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
            match(SEMICOLON_TOKEN);
            declaration = new VarDeclaration(new Num(), ID);
        }
        else if (nextToken.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            ArrayList<Param> params = parseParams();
            match(RPAREN_TOKEN);
            CompoundStatement compound_statement = parseCompoundStatement();
            declaration = new FunDeclaration("int", params, compound_statement, ID.data());
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
    private FunDeclaration parseFunctionDeclarationPrime(Token idToken) throws CminusException {
        FunDeclaration declaration = null;
        Token nextToken = scanner.viewNextToken();
        
        if (nextToken.equals(LPAREN_TOKEN)) {
            this.match(LPAREN_TOKEN);
            ArrayList<Param> params = parseParams();
            this.match(RPAREN_TOKEN);
            
            CompoundStatement compoundStatement = parseCompoundStatement();
            declaration = new FunDeclaration("void", params, compoundStatement, idToken.data());
        } else {
            //error
            throw new CminusException(nextToken, LPAREN_TOKEN);
        }
        return declaration;
    }
    
    // 6. params → param { , param } | void
    private ArrayList<Param> parseParams() throws CminusException {
        ArrayList<Param> params = new ArrayList<>();
        Token nextToken = scanner.viewNextToken();
        if (nextToken.equals(VOID_TOKEN)) {
            // just pass up an empty arraylist
            match(VOID_TOKEN);
        }
        else {
            params.add(parseParam());
            nextToken = scanner.viewNextToken();
            while (!nextToken.equals(RPAREN_TOKEN)) {
                match(COMMA_TOKEN);
                params.add(parseParam());
                nextToken = scanner.viewNextToken();
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
            if (isInSet(nextToken, firstOfStatement)) {
                // there is at least one stmt
                statements = parseStatementList();
            }
            compoundStatement = new CompoundStatement(localDeclarations, statements);
        }
        // first set of stmt => (, NUM, ID, {, if, while, return, ; 
        else if (isInSet(nextToken, firstOfStatement)) {
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
        
        if (isInSet(nextToken, firstOfStatement)) {
            list.add(parseStatement());
            while (isInSet(scanner.viewNextToken(), firstOfStatement)) {
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
        if(isInSet(nextToken, firstOfExpressionStatement)) {
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
            
        } else if(isInSet(nextToken, firstOfExpressionStatement)) {
            expressionStatement = new ExpressionStatement(parseExpression());
            match(SEMICOLON_TOKEN);
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
            Statement primarStatement = parseStatement();
            statement.setPrimaryStatement(primarStatement);
            
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
            Expression lhs = parseExpression();
            match(RPAREN_TOKEN);
            expression = parseSimpleExpressionPrime(lhs);
            
        } else if (nextToken.equals(NUM_TOKEN)) {
            Token numToken = match(NUM_TOKEN);
            Num num = new Num(numToken);
            expression = parseSimpleExpressionPrime(num);
            
        } else if (nextToken.equals(ID_TOKEN)) {
            Token idToken = match(ID_TOKEN);
            expression = parseExpressionPrime(idToken);
            
        } else if (nextToken.equals(RPAREN_TOKEN)) {
            // do nothing, expression will return null and be handled in parse args
            
        } else {
            throw new CminusException(nextToken, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
        
        return expression;
    }
    
    // 15. exp-prime → = expression | simple-exp-prime | ( args ) simple-exp-prime 
    //      | [ expression ] exp-doubleprime
    private Expression parseExpressionPrime(Token prevToken) throws CminusException {
        Expression expression = null;

        Token t = scanner.viewNextToken();
        if(t.equals(ASSIGN_TOKEN)) {
            Var var = new Var(prevToken, null);
            expression = parseAssignmentOperation(var);
            
        } else if (t.equals(LPAREN_TOKEN)) {
            match(LPAREN_TOKEN);
            ArrayList<Expression> args = parseArgs();
            match(RPAREN_TOKEN);
            Call call = new Call(prevToken.data(), args);
            expression = parseSimpleExpressionPrime(call);
            
        } else if (t.equals(LBRACKET_TOKEN)) {
            match(LBRACKET_TOKEN);
            Expression index = parseExpression();
            match(RBRACKET_TOKEN);
            Var arrayVar = new Var(prevToken.data(), index);
            
            expression = parseExpressionDoublePrime(arrayVar);
            
        } else if (isInSet(t, firstOfSimpleExpPrime)) {
            expression = parseSimpleExpressionPrime(varOrId(prevToken));
            
        } else if (isInSet(t, followOfExpressionPrime)) {
            expression = varOrId(prevToken);
            
        } else {
            throw new CminusException(t, LPAREN_TOKEN, LBRACKET_TOKEN, MULT_TOKEN, DIV_TOKEN);
        }
        return expression;
    }
    
    // 16. exp-doubleprime → = expression | simple-exp-prime
    private Expression parseExpressionDoublePrime(Var lhs) throws CminusException {
        Expression expression = null;
        
        Token t = scanner.viewNextToken();
        if(t.equals(ASSIGN_TOKEN)) {
            expression = parseAssignmentOperation(lhs);
            
        } else if (isInSet(t, firstOfSimpleExpPrime)) {
            expression = parseSimpleExpressionPrime(lhs); 
            
        // FollowSet of ExpressionPrime. Goes to epsilon
        } else if (isInSet(t, followOfExpressionPrime)) {
            return lhs;
            
        } else {
            throw new CminusException(t, ASSIGN_TOKEN, MULT_TOKEN, DIV_TOKEN);
        }
        return expression;
    }
    
    // 17. var → [ [ expression ] ]
    private Var parseVar(Token ID) throws CminusException {
        Var var = null;
       
        Token nextToken = scanner.viewNextToken();
        if(nextToken.equals(LBRACKET_TOKEN)) {
            match(LBRACKET_TOKEN);
            Expression index = parseExpression();
            match(RBRACKET_TOKEN);
            var = new Var(ID, index);
        } else if (isInSet(nextToken, followOfVar)) {
            var = new Var(ID, null);
        } else {
            throw new CminusException(nextToken, LBRACKET_TOKEN);
        }
        
        return var;
    }
    
    // 18. simple-exp-prime → additive-exp-prime [ relop additive-exp ]
    private Expression parseSimpleExpressionPrime(Expression lhs) throws CminusException {
        Expression expression = null;
        
        Token t = scanner.viewNextToken();
        if(t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN) || t.equals(PLUS_TOKEN) || t.equals(MINUS_TOKEN)) {
            expression = parseAdditiveExpressionPrime(lhs);
            
            t = scanner.viewNextToken();
            if(isInSet(t, firstOfRelop)) {
                BinaryOperation relop = parseBinaryOperation(expression);
            }
        
        } else if (isInSet(t, firstOfRelop)) {    
            expression = parseBinaryOperation(lhs);
            
        // Has same followset of expression prime    
        } else if (isInSet(t, followOfExpressionPrime)) {
            expression = lhs;
            
        } else {
            throw new CminusException(t, LBRACKET_TOKEN);
        }
        
        return expression;
    }
    
    // 21. additive-exp-prime → term-prime { addop term }
    private Expression parseAdditiveExpressionPrime(Expression lhs) throws CminusException {
        TokenType[] mathOps = { PLUS_TOKEN, MINUS_TOKEN, MULT_TOKEN, DIV_TOKEN };
        Expression returnExpression = null;
        
        Token t = scanner.viewNextToken();
        if(isInSet(t, mathOps) || t.equals(LPAREN_TOKEN) || t.equals(NUM_TOKEN) || t.equals(ID_TOKEN)) {
            returnExpression = parseTermPrime(lhs);
            
            t = scanner.viewNextToken();
            while(isInSet(t, mathOps)) {
                returnExpression = parseBinaryOperation(returnExpression);
                t = scanner.viewNextToken();
            }
        } else {
            throw new CminusException(t, mathOps);
        }
        return returnExpression;
    }
    
    // 19. relop → <= | < | > | >= | == | !=
    // 22. addop → + | -
    // 25. mulop →  * | / 
    private BinaryOperation parseBinaryOperation(Expression lhs) throws CminusException {
        Token op = scanner.viewNextToken();
        Expression rhs = null;
        String operator = "";
        if (isInSet(op, firstOfRelop)) {
            // relop
            operator = parseRelop();
            rhs = parseAdditiveExpressionPrime(null);
        }
        else if (op.getTokenType() == PLUS_TOKEN || op.getTokenType() == MINUS_TOKEN) {
            // addop
            operator = parseAddop();
            rhs = parseTerm();
        }
        else if (op.getTokenType() == MULT_TOKEN || op.getTokenType() == DIV_TOKEN) {
            // mulop
            operator = parseMulop();
            rhs = parseFactor();
        }
        return new BinaryOperation(lhs, rhs, operator);
    }
    
    private AssignmentOperation parseAssignmentOperation(Var lhs) throws CminusException {
        Token t = scanner.viewNextToken();
        if(t.equals(ASSIGN_TOKEN)) {
            match(ASSIGN_TOKEN);
            Expression rhs = parseExpression();
            return new AssignmentOperation(lhs, rhs);
        
        } else {
            throw new CminusException(t, EQUAL_TOKEN);
        }
    }
    
    private Num parseNum() throws CminusException {
        Token num = match(NUM_TOKEN);
        return new Num(num);
    }
    // 19. relop → <= | < | > | >= | == | !=
    private String parseRelop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == LESS_TOKEN) {
            return "<";
        }
        else if (op.getTokenType() == LEQ_TOKEN){
            return "<=";
        }
        else if (op.getTokenType() == GREAT_TOKEN) {
            return ">";
        }
        else if (op.getTokenType() == GEQ_TOKEN) {
            return ">=";
        }
        else if (op.getTokenType() == NEQ_TOKEN) {
            return "!=";
        }
        else if (op.getTokenType() == EQUAL_TOKEN) {
            return "==";
        }
        else {
            throw new CminusException(op, LESS_TOKEN,LEQ_TOKEN,GREAT_TOKEN,GEQ_TOKEN,NEQ_TOKEN, EQUAL_TOKEN);
        }
    }
    // 22. addop → + | -
    private String parseAddop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == PLUS_TOKEN) {
            return "+";
        }
        else if (op.getTokenType() == MINUS_TOKEN) {
            return "-";
        }
        else {
            throw new CminusException(op, PLUS_TOKEN, MINUS_TOKEN);
        }
    }
    
    // 23. term → factor { mulop factor }
    private Expression parseTerm() throws CminusException {
        Expression returnExpression = null;
        Token t = scanner.viewNextToken();
        if(t.equals(LPAREN_TOKEN) || t.equals(NUM_TOKEN) || t.equals(ID_TOKEN)) {
            Expression factor = parseFactor();
            returnExpression = factor;
            
            t = scanner.viewNextToken();
            if(t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN)) {
                returnExpression = parseBinaryOperation(factor);
            }
        } else {
            throw new CminusException(t, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
        return returnExpression;
    }
    
    // 24. term-prime → { mulop factor }
    private Expression parseTermPrime(Expression lhs) throws CminusException {
        Expression returnExpression = lhs;
        
        if (lhs == null) {
            return parseTerm();
        }
        
        Token t = scanner.viewNextToken();
        if(t.equals(MULT_TOKEN) || t.equals(DIV_TOKEN) || t.equals(PLUS_TOKEN) || t.equals(MINUS_TOKEN)) {
            returnExpression = parseBinaryOperation(lhs);
            
           
        // goes to empty
        } else if (isInSet(t, followOfTermPrime)) {
            returnExpression = parseTerm();
            
        } else {
            throw new CminusException(t, MULT_TOKEN, DIV_TOKEN);
        }
        return returnExpression;
    }
    
    // 25. mulop →  * | / 
    private String parseMulop() throws CminusException {
        Token op = scanner.getNextToken();
        if (op.getTokenType() == MULT_TOKEN) {
            return "*";
        }
        else if (op.getTokenType() == DIV_TOKEN) {
            return "/";
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
        Expression varcall = null;
        if (nextToken.equals(LPAREN_TOKEN)) {
            varcall = parseCall(ID);
        }
        else if (nextToken.equals(RBRACKET_TOKEN) || isInSet(nextToken, followOfVar)) {
            varcall = parseVar(ID);
        }
        else {
            throw new CminusException(nextToken, followOfVar);
        }
        
        return varcall;
    }
    
    // 28. call → ( args )
    private Call parseCall(Token ID) throws CminusException {
        Call call = null;
        
        Token t = scanner.viewNextToken();
        if(t.equals(LPAREN_TOKEN)) {
            call = new Call(ID.data(), parseArgs());
        } else {
            throw new CminusException(t, LPAREN_TOKEN);
        }
        
        return call;
    }
    
    // 29. args → [ expression { , expression } ]
    private ArrayList<Expression> parseArgs() throws CminusException {
        ArrayList<Expression> args = new ArrayList<>();

        Token t = scanner.viewNextToken();
        if(t.equals(LPAREN_TOKEN) || t.equals(NUM_TOKEN) || t.equals(ID_TOKEN)) {
            args.add(parseExpression());
            t = scanner.viewNextToken();
            
            while(t.equals(COMMA_TOKEN)) {
                match(COMMA_TOKEN);
                args.add(parseExpression());
                t = scanner.viewNextToken();
            } 
        } else {
            throw new CminusException(t, LPAREN_TOKEN, NUM_TOKEN, ID_TOKEN);
        }
        
        return args;
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
     * Walks a given set array checking if the toke exists in the set  
     * @param token -   Token that is being checked
     * @param set   -   Set of token types
     * @return  -   Returns true if the token type exists in the set. False otherwise
     */
    private boolean isInSet(Token token, TokenType[] set) {
        for(TokenType type : set) {
            if(token.equals(type) == true) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Creates a new var or num depending on the value of token
     * @param token -   Token that is var or num
     * @return  -   Num or Var result
     */
    private Expression varOrId(Token token) {
        Expression lhs;
            
        if(token.equals(ID_TOKEN)) {
            lhs = new Var(token, null);
        } else {
            lhs = new Num(token);
        }
            
        return lhs;
    }   
}