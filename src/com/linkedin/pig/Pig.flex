package com.linkedin.pig;
 
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.linkedin.pig.psi.PigTypes;
import com.intellij.psi.TokenType;
 
%%
 
%class PigLexer
%implements FlexLexer
%unicode
%caseless
%function advance
%type IElementType
%eof{  return;
%eof}

/* main character classes */
DIGIT=[0-9]
OCTAL_DIGIT=[0-7]
HEX_DIGIT=[0-9A-Fa-f]
WHITE_SPACE=([\ \n\r\t\f])+

/* identifiers */
IDENTIFIER = [:jletter:][:jletterdigit:]*

/* comments */
C_STYLE_COMMENT=("/*"[^"*"]{COMMENT_TAIL})|"/*"
DOC_COMMENT="/*""*"+("/"|([^"/""*"]{COMMENT_TAIL}))?
COMMENT_TAIL=([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT="-""-"[^\r\n]*

/* literals */
INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}|{HEX_INTEGER_LITERAL}|{OCTAL_INTEGER_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))
HEX_INTEGER_LITERAL=0[Xx]({HEX_DIGIT})*
OCTAL_INTEGER_LITERAL=0({OCTAL_DIGIT})+
LONG_LITERAL=({INTEGER_LITERAL})[Ll]

FLOAT_LITERAL=(({FLOATING_POINT_LITERAL1})[Ff])|(({FLOATING_POINT_LITERAL2})[Ff])|(({FLOATING_POINT_LITERAL3})[Ff])|(({FLOATING_POINT_LITERAL4})[Ff])
DOUBLE_LITERAL=(({FLOATING_POINT_LITERAL1})[Dd]?)|(({FLOATING_POINT_LITERAL2})[Dd]?)|(({FLOATING_POINT_LITERAL3})[Dd]?)|(({FLOATING_POINT_LITERAL4})[Dd])
FLOATING_POINT_LITERAL1=({DIGIT})+"."({DIGIT})*({EXPONENT_PART})?
FLOATING_POINT_LITERAL2="."({DIGIT})+({EXPONENT_PART})?
FLOATING_POINT_LITERAL3=({DIGIT})+({EXPONENT_PART})
FLOATING_POINT_LITERAL4=({DIGIT})+
EXPONENT_PART=[Ee]["+""-"]?({DIGIT})*

EXEC_LITERAL="`"([^\\`\r\n]|{ESCAPE_SEQUENCE})*("`"|\\)?
STRING_LITERAL=\'([^\\\'\r\n]|{ESCAPE_SEQUENCE})*(\'|\\)?
ESCAPE_SEQUENCE=\\[^\r\n]

%%

<YYINITIAL> {WHITE_SPACE} { return TokenType.WHITE_SPACE; }

<YYINITIAL> {C_STYLE_COMMENT} { return PigTypes.C_STYLE_COMMENT; }
<YYINITIAL> {END_OF_LINE_COMMENT} { return PigTypes.END_OF_LINE_COMMENT; }
<YYINITIAL> {DOC_COMMENT} { return PigTypes.DOC_COMMENT; }

<YYINITIAL> {LONG_LITERAL} { return PigTypes.LONG_LITERAL; }
<YYINITIAL> {INTEGER_LITERAL} { return PigTypes.INTEGER_LITERAL; }
<YYINITIAL> {FLOAT_LITERAL} { return PigTypes.FLOAT_LITERAL; }
<YYINITIAL> {DOUBLE_LITERAL} { return PigTypes.DOUBLE_LITERAL; }

<YYINITIAL> {EXEC_LITERAL} { return PigTypes.EXEC_LITERAL; }
<YYINITIAL> {STRING_LITERAL} { return PigTypes.STRING_LITERAL; }

<YYINITIAL> "TRUE" { return PigTypes.BOOLEAN_TRUE; }
<YYINITIAL> "FALSE" { return PigTypes.BOOLEAN_FALSE; }

<YYINITIAL> "NULL" { return PigTypes.NULL_KEYWORD; }

 /* keywords */
<YYINITIAL> "ALL" { return PigTypes.ALL_KEYWORD; }
<YYINITIAL> "ANY" { return PigTypes.ANY_KEYWORD; }
<YYINITIAL> "AS" { return PigTypes.AS_KEYWORD; }
<YYINITIAL> "ASC" { return PigTypes.ASC_KEYWORD; }
<YYINITIAL> "BAG" { return PigTypes.BAG_KEYWORD; }
<YYINITIAL> "BY" { return PigTypes.BY_KEYWORD; }
<YYINITIAL> "CACHE" { return PigTypes.CACHE_KEYWORD; }
<YYINITIAL> "CHARARRAY" { return PigTypes.CHARARRAY_KEYWORD; }
<YYINITIAL> "COGROUP" { return PigTypes.COGROUP_KEYWORD; }
<YYINITIAL> "CROSS" { return PigTypes.CROSS_KEYWORD; }
<YYINITIAL> "DEFINE" { return PigTypes.DEFINE_KEYWORD; }
<YYINITIAL> "DESC" { return PigTypes.DESC_KEYWORD; }
<YYINITIAL> "DISTINCT" { return PigTypes.DISTINCT_KEYWORD; }
<YYINITIAL> "DOUBLE" { return PigTypes.DOUBLE_KEYWORD; }
<YYINITIAL> "FILTER" { return PigTypes.FILTER_KEYWORD; }
<YYINITIAL> "FLATTEN" { return PigTypes.FLATTEN_KEYWORD; }
<YYINITIAL> "FLOAT" { return PigTypes.FLOAT_KEYWORD; }
<YYINITIAL> "FOREACH" { return PigTypes.FOREACH_KEYWORD; }
<YYINITIAL> "FULL" { return PigTypes.FULL_KEYWORD; }
<YYINITIAL> "GENERATE" { return PigTypes.GENERATE_KEYWORD; }
<YYINITIAL> "GROUP" { return PigTypes.GROUP_KEYWORD; }
<YYINITIAL> "IF" { return PigTypes.IF_KEYWORD; }
<YYINITIAL> "IMPORT" { return PigTypes.IMPORT_KEYWORD; }
<YYINITIAL> "IN" { return PigTypes.IN_KEYWORD; }
<YYINITIAL> "INNER" { return PigTypes.INNER_KEYWORD; }
<YYINITIAL> "INPUT" { return PigTypes.INPUT_KEYWORD; }
<YYINITIAL> "INT" { return PigTypes.INT_KEYWORD; }
<YYINITIAL> "INTO" { return PigTypes.INTO_KEYWORD; }
<YYINITIAL> "IS" { return PigTypes.IS_KEYWORD; }
<YYINITIAL> "JOIN" { return PigTypes.JOIN_KEYWORD; }
<YYINITIAL> "LEFT" { return PigTypes.LEFT_KEYWORD; }
<YYINITIAL> "LIMIT" { return PigTypes.LIMIT_KEYWORD; }
<YYINITIAL> "LOAD" { return PigTypes.LOAD_KEYWORD; }
<YYINITIAL> "LONG" { return PigTypes.LONG_KEYWORD; }
<YYINITIAL> "MAP" { return PigTypes.MAP_KEYWORD; }
<YYINITIAL> "MAPREDUCE" { return PigTypes.MAPREDUCE_KEYWORD; }
<YYINITIAL> "ONSCHEMA" { return PigTypes.ONSCHEMA_KEYWORD; }
<YYINITIAL> "ORDER" { return PigTypes.ORDER_KEYWORD; }
<YYINITIAL> "OTHERWISE" { return PigTypes.OTHERWISE_KEYWORD; }
<YYINITIAL> "OUTER" { return PigTypes.OUTER_KEYWORD; }
<YYINITIAL> "OUTPUT" { return PigTypes.OUTPUT_KEYWORD; }
<YYINITIAL> "PARALLEL" { return PigTypes.PARALLEL_KEYWORD; }
<YYINITIAL> "PARTITION" { return PigTypes.PARTITION_KEYWORD; }
<YYINITIAL> "RETURNS" { return PigTypes.RETURNS_KEYWORD; }
<YYINITIAL> "RIGHT" { return PigTypes.RIGHT_KEYWORD; }
<YYINITIAL> "SAMPLE" { return PigTypes.SAMPLE_KEYWORD; }
<YYINITIAL> "SET" { return PigTypes.SET_KEYWORD; }
<YYINITIAL> "SHIP" { return PigTypes.SHIP_KEYWORD; }
<YYINITIAL> "SPLIT" { return PigTypes.SPLIT_KEYWORD; }
<YYINITIAL> "STDERR" { return PigTypes.STDERROR_KEYWORD; }
<YYINITIAL> "STDIN" { return PigTypes.STDIN_KEYWORD; }
<YYINITIAL> "STDOUT" { return PigTypes.STDOUT_KEYWORD; }
<YYINITIAL> "STORE" { return PigTypes.STORE_KEYWORD; }
<YYINITIAL> "STREAM" { return PigTypes.STREAM_KEYWORD; }
<YYINITIAL> "THROUGH" { return PigTypes.THROUGH_KEYWORD; }
<YYINITIAL> "TUPLE" { return PigTypes.TUPLE_KEYWORD; }
<YYINITIAL> "UNION" { return PigTypes.UNION_KEYWORD; }
<YYINITIAL> "USING" { return PigTypes.USING_KEYWORD; }
<YYINITIAL> "VOID" { return PigTypes.VOID_KEYWORD; }
<YYINITIAL> "AND" { return PigTypes.AND_KEYWORD; }
<YYINITIAL> "NOT" { return PigTypes.NOT_KEYWORD; }
<YYINITIAL> "OR" { return PigTypes.OR_KEYWORD; }
<YYINITIAL> "EQ" { return PigTypes.STR_OP_EQ; }
<YYINITIAL> "GT" { return PigTypes.STR_OP_GT; }
<YYINITIAL> "LT" { return PigTypes.STR_OP_LT; }
<YYINITIAL> "GTE" { return PigTypes.STR_OP_GTE; }
<YYINITIAL> "LTE" { return PigTypes.STR_OP_LTE; }
<YYINITIAL> "NEQ" { return PigTypes.STR_OP_NE; }
<YYINITIAL> "MATCHES" { return PigTypes.STR_OP_MATCHES; }

<YYINITIAL> {IDENTIFIER} { return PigTypes.IDENTIFIER; }

  /* separators */
<YYINITIAL> "(" { return PigTypes.LPARENTH; }
<YYINITIAL> ")" { return PigTypes.RPARENTH; }
<YYINITIAL> "{" { return PigTypes.LBRACE; }
<YYINITIAL> "}" { return PigTypes.RBRACE; }
<YYINITIAL> "[" { return PigTypes.LBRACK; }
<YYINITIAL> "]" { return PigTypes.RBRACK; }
<YYINITIAL> ";" { return PigTypes.SEMICOLON; }
<YYINITIAL> "," { return PigTypes.COMMA; }
<YYINITIAL> "." { return PigTypes.DOT; }

  /* operators */
<YYINITIAL> "=" { return PigTypes.EQUAL; }
<YYINITIAL> ">" { return PigTypes.GT; }
<YYINITIAL> "<" { return PigTypes.LT; }
<YYINITIAL> "?" { return PigTypes.QMARK; }
<YYINITIAL> ":" { return PigTypes.COLON; }
<YYINITIAL> "==" { return PigTypes.EQEQ; }
<YYINITIAL> "<=" { return PigTypes.LTEQ; }
<YYINITIAL> ">=" { return PigTypes.GTEQ; }
<YYINITIAL> "!=" { return PigTypes.NOTEQ; }
<YYINITIAL> "+" { return PigTypes.PLUS; }
<YYINITIAL> "-" { return PigTypes.MINUS; }
<YYINITIAL> "*" { return PigTypes.STAR; }
<YYINITIAL> "/" { return PigTypes.DIV; }
<YYINITIAL> "#" { return PigTypes.POUND; }
<YYINITIAL> "%" { return PigTypes.PERCENT; }
<YYINITIAL> ".." { return PigTypes.DOUBLE_PERIOD; }
<YYINITIAL> "\\$" { return PigTypes.DOLLAR; }
<YYINITIAL> "::" { return PigTypes.DCOLON; }

<YYINITIAL> . { return TokenType.BAD_CHARACTER; }
