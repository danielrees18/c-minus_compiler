
package cminus_compiler.tool;

import java_cup.runtime.*;
import JFlex.sym;
import cminus_compiler.main.ScannerInterface;
import cminus_compiler.model.Token;
import cminus_compiler.model.TokenType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

%%

%public
%class CMinusFlexScanner 
%implements ScannerInterface

%unicode

%line
%column

%cup
%cupdebug

%{
	private Token nextToken;

  @Override
  public Token getNextToken() {
    Token returnToken = nextToken;
    // get the next token
    if(nextToken.getTokenType() != TokenType.EOF_TOKEN) {
        nextToken = scanToken();
    }
    
    return returnToken;
  }

  @Override
  public Token viewNextToken() {
    if(nextToken == null) {
        nextToken = scanToken();
    }
    return nextToken;
  }
  
  private Token scanToken() {
      Token token = new Token();
      try {
        Symbol sym = next_token();
        token.setTokenType(parseNewSymbol(sym));
        token.setTokenData(sym.value);
      } catch (IOException ex) {
        Logger.getLogger(CMinusFlexScanner.class.getName()).log(Level.SEVERE, null, ex);
      }
      return token;
  }

  private TokenType parseNewSymbol(Symbol s) {
    for(TokenType type : TokenType.values()) {
      if(type.getValue() == s.sym) {
          return type;
      }
    }
    return TokenType.ERROR;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"


/* identifiers */
Identifier = [:jletter:][:jletter:]*

IdentifierError = [:jletterdigit:]+[:jletter:]+|[:jletter:]+[:jletterdigit:]+

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*


%%

<YYINITIAL> {

  /* keywords */
  "else"                         { return new Symbol(TokenType.ELSE_TOKEN.getValue()); }  
  "int"                          { return new Symbol(TokenType.INT_TOKEN.getValue()); }
  "if"                           { return new Symbol(TokenType.IF_TOKEN.getValue()); }
  "return"                       { return new Symbol(TokenType.RETURN_TOKEN.getValue()); }
  "void"                         { return new Symbol(TokenType.VOID_TOKEN.getValue()); }
  "while"                        { return new Symbol(TokenType.WHILE_TOKEN.getValue()); }
  
  /* separators */
  "("                            { return new Symbol(TokenType.LPAREN_TOKEN.getValue()); }
  ")"                            { return new Symbol(TokenType.RPAREN_TOKEN.getValue()); }
  "{"                            { return new Symbol(TokenType.LCURLY_TOKEN.getValue()); }
  "}"                            { return new Symbol(TokenType.RCURLY_TOKEN.getValue()); }
  "["                            { return new Symbol(TokenType.LBRACKET_TOKEN.getValue()); }
  "]"                            { return new Symbol(TokenType.RBRACKET_TOKEN.getValue()); }
  ";"                            { return new Symbol(TokenType.SEMICOLON_TOKEN.getValue()); }
  ","                            { return new Symbol(TokenType.COMMA_TOKEN.getValue()); }
  
  /* operators */
  "="                            { return new Symbol(TokenType.ASSIGN_TOKEN.getValue()); }
  ">"                            { return new Symbol(TokenType.GREAT_TOKEN.getValue()); }
  "<"                            { return new Symbol(TokenType.LESS_TOKEN.getValue()); }
  "=="                           { return new Symbol(TokenType.EQUAL_TOKEN.getValue()); }
  "<="                           { return new Symbol(TokenType.LEQ_TOKEN.getValue()); }
  ">="                           { return new Symbol(TokenType.GEQ_TOKEN.getValue()); }
  "!="                           { return new Symbol(TokenType.NEQ_TOKEN.getValue()); }
  "+"                            { return new Symbol(TokenType.PLUS_TOKEN.getValue()); }
  "-"                            { return new Symbol(TokenType.MINUS_TOKEN.getValue()); }
  "*"                            { return new Symbol(TokenType.MULT_TOKEN.getValue()); }
  "/"                            { return new Symbol(TokenType.DIV_TOKEN.getValue()); }
  
  {DecIntegerLiteral}            { return new Symbol(TokenType.NUM_TOKEN.getValue(), new Integer(yytext())); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return new Symbol(TokenType.ID_TOKEN.getValue(), yytext()); }  

  /* 1cat cat2 */ 
  {IdentifierError}              { return new Symbol(TokenType.ERROR.getValue(), yytext()); } 
}

<<EOF>>                          { return new Symbol(TokenType.EOF_TOKEN.getValue()); }