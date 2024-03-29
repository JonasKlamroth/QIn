grammar QASM;

@header {
package QIn;
}

mainprog
  : version statement*
  ;

statement 
  : decl 
  | gatedecl goplist '}'
  | gatedecl '}'
  | 'opaque' ID idlist ';'
  | 'opaque' ID '(' ')' idlist ';'
  | 'opaque' ID '(' idlist ')' idlist ';'
  | qop
  | 'if' '(' ID '==' INT ')' qop
  | 'barrier' anylist ';'
  ;

version
  : 'OPENQASM' REAL ';'
  ;

decl
  : 'qreg' ID '[' INT ']' ';'
  | 'creg' ID '[' INT ']' ';'
  ;

gatedecl
  : 'gate' ID idlist '{'
  | 'gate' ID '(' ')' idlist '{'
  | 'gate' ID '(' idlist ')' idlist '{'
  ;

goplist 
  : (uop | 'barrier' idlist ';')+
  ;

qop
  : uop
  | 'measure' argument '->' argument ';'
  | 'reset' argument ';' 
  ;

uop
  : ID anylist ';'
  | ID '(' ')' anylist ';'
  | ID '(' explist ')' anylist ';'
  ;

anylist
  : idlist 
  | mixedlist
  ;

idlist 
  : (ID ',')* ID
  ;

mixedlist
  : argument (',' argument)*
  ;

argument 
  : ID '[' INT ']'
  ;

explist 
  : (exp ',')* exp 
  ;

exp
  : REAL
  | INT 
  | 'pi'
  | ID
  | exp '+' exp
  | exp '-' exp 
  | exp '*' exp
  | exp '/' exp
  | '-' exp 
  | exp '^' exp
  | '(' exp ')'
  | unaryop '(' exp ')' 
  ;

unaryop 
  : 'sin' | 'cos' | 'tan' | 'exp' | 'ln' | 'sqrt'
  ;

// Other / REGEX Tokens

ID
  : [a-z][A-Za-z0-9_]*
  ;

REAL
  :  INT '.' [0-9]+ ([eE][+-]? [0-9]+)?
  ;

INT
  : [+-]? [0-9]+
  ;

WS
  : [ \t\u000C\r\n]+ -> skip
  ;

INCLUDE
  : 'include' .*? '";' -> skip
  ;

COMMENT
  : '/*' .*? '*/' -> skip
  ;

LINE_COMMENT
  : '//' ~[\r\n]* -> skip
  ;

