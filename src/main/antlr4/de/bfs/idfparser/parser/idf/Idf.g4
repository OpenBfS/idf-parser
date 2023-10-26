grammar Idf;

idfdatei : idf_header (measure_block | location_block | text_block)+ ENDOFIDF;

idf_header : STRING STRING STRING STRING NEWLINE;
measure_block : (data_line (measure_line)+);
location_block : (site_line (location_line)+);
text_block : TEXT NEWLINE (STRING | NEWLINE)*;

data_line : BLOCK_PREFIX? DATA NEWLINE;
site_line : BLOCK_PREFIX? SITE NEWLINE;

comment: COMMENT_PREFIX (STRING)*;
measure_line : STRING STRING STRING STRING STRING STRING (STRING)* (STRING)* (STRING)* (STRING)* (STRING)* (STRING)* (STRING)* (STRING)* comment? NEWLINE;
location_line : STRING STRING (STRING)* (STRING)* (STRING)* (STRING|STRING_ESC)* (STRING|STRING_ESC)* comment? NEWLINE;

BLOCK_PREFIX: ATTN | TEST;

DATA: 'DATA';
SITE: 'SITE';
TEXT: 'TEXT';

ATTN: 'ATTN';
TEST: 'TEST';

COMMENT_PREFIX : '!';
WS : (' ' | '\t') -> skip;
NEWLINE : ('\r\n' | '\r' | '\n') ;
STRING_ESC : ('"'(~('"'))*'"') ;
STRING : C+ ;
C : CHAR ;
fragment CHAR : ~[ !\t"\r\n] ;
ENDOFIDF : 'ZZZZZ' NEWLINE? EOF;
