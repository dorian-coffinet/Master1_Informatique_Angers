%{
	#include<stdio.h>
  #include "translate.h"
	#include "grammaire.tab.h"
  #include "tableSymboles.h"
  #include "tableSymbolesSecondaires.h"
	int line_no = 1;
%}
A [aA]
B [bB]
C [cC]
D [dD]
E [eE]
F [fF]
G [gG]
H [hH]
I [iI]
J [jJ]
K [kK]
L [lL]
M [mM]
N [nN]
O [oO]
P [pP]
Q [qQ]
R [rR]
S [sS]
T [tT]
U [uU]
V [vV]
W [wW]
X [xX]
Y [yY]
Z [zZ]

CHIFFRE [0-9]
LETTRE [a-zA-Z]
APOSTROPHE [']

%%

{B}{E}{G}{I}{N}             { yylval.t_string = alloc_yytext("begin"); return(TBEGIN); }
{D}{O}                      { yylval.t_string = alloc_yytext("do"); return(DO); }
{D}{I}{V}                   { yylval.t_string = alloc_yytext("div"); return(DIV); }
{E}{N}{D}                   { yylval.t_string = alloc_yytext("end"); return(TEND); }
{F}{U}{N}{C}{T}{I}{O}{N}    { yylval.t_string = alloc_yytext("function"); return(FUNCTION); }
{P}{R}{O}{C}{E}{D}{U}{R}{E} { yylval.t_string = alloc_yytext("procedure"); return(PROCEDURE); }
{I}{F}                      { yylval.t_string = alloc_yytext("if"); return(IF); }
{M}{O}{D}                   { yylval.t_string = alloc_yytext("mod"); return(MOD); }
{P}{R}{O}{G}{R}{A}{M}       { yylval.t_string = alloc_yytext("program"); return(PROGRAM); }
{T}{H}{E}{N}                { yylval.t_string = alloc_yytext("then"); return(THEN); }
{E}{L}{S}{E}                { yylval.t_string = alloc_yytext("else"); return(ELSE); }
{V}{A}{R}                   { yylval.t_string = alloc_yytext("var"); return(VAR); }
{W}{H}{I}{L}{E}             { yylval.t_string = alloc_yytext("while"); return(WHILE); }
{F}{O}{R}                   { yylval.t_string = alloc_yytext("for"); return(FOR); }
{T}{O}                      { yylval.t_string = alloc_yytext("to"); return(TO); }
{I}{N}{T}{E}{G}{E}{R}       { yylval.t_string = alloc_yytext("integer"); return(INTEGER); }
{S}{T}{R}{I}{N}{G}          { yylval.t_string = alloc_yytext("string"); return(STRING); }
{R}{E}{A}{L}                { yylval.t_string = alloc_yytext("real"); return(REAL); }
{B}{O}{O}{L}{E}{A}{N}       { yylval.t_string = alloc_yytext("boolean"); return(BOOLEAN); }
{C}{H}{A}{R}                { yylval.t_string = alloc_yytext("char"); return(CHAR); }
{W}{R}{I}{T}{E}{L}{N}       { yylval.t_string = alloc_yytext("writeln"); return(WRITELN); }

":="                        { yylval.t_string = alloc_yytext(yytext); return(ASSIGNATION); }
"."                         { yylval.t_string = alloc_yytext(yytext); return(POINT); }
":"                         { yylval.t_string = alloc_yytext(yytext); return(DEUXPOINTS); }
","                         { yylval.t_string = alloc_yytext(yytext); return(VIRGULE); }
";"                         { yylval.t_string = alloc_yytext(yytext); return(POINTVIRGULE); }

"="                         { yylval.t_string = alloc_yytext(yytext); return(EGAL); }
">="                        { yylval.t_string = alloc_yytext(yytext); return(SUPERIEUREGAL); }
">"                         { yylval.t_string = alloc_yytext(yytext); return(SUPERIEUR); }
"<="                        { yylval.t_string = alloc_yytext(yytext); return(INFERIEUREGAL); }
"<"                         { yylval.t_string = alloc_yytext(yytext); return(INFERIEUR); }
"<>"                        { yylval.t_string = alloc_yytext(yytext); return(DIFFERENT); }

"+"                         { yylval.t_string = alloc_yytext(yytext); return(ADDITION); }
"-"                         { yylval.t_string = alloc_yytext(yytext); return(SOUSTRACTION); }
"*"                         { yylval.t_string = alloc_yytext(yytext); return(MULTIPLICATION); }
"/"                         { yylval.t_string = alloc_yytext(yytext); return(DIVISION); }

"("                         { yylval.t_string = alloc_yytext(yytext); return(PARENTHESEGAUCHE); }
")"                         { yylval.t_string = alloc_yytext(yytext); return(PARENTHESEDROITE); }

"'"                         { yylval.t_string = alloc_yytext(yytext); return(APOSTROPHE); }

{LETTRE}({LETTRE}|{CHIFFRE})* {
  yylval.t_string = alloc_yytext(yytext);
	return IDENTIFIANT;
}

{CHIFFRE}{CHIFFRE}*(.{CHIFFRE}{CHIFFRE}*)? {
  yylval.t_string = alloc_yytext(yytext);
  return NOMBRE;
}

{APOSTROPHE}(.)*{APOSTROPHE} {
  yylval.t_string = alloc_yytext(yytext);
  return CHAINE;
}

"{"   { register int c;
        while ((c = input()))
        {
          if (c == '}')
            break;
          else if (c == '*')
          {
            if ((c = input()) == ')')
              break;
            else
              unput (c);
          }          
          else if (c == '\n')
            line_no++;
          else if (c == 0)
            commenteof();
        }
    }

[\t] {}

[\n] {line_no++;}

" " {}

%%
commenteof()
{
  fprintf (stderr, "unexpected EOF inside comment at line %d\n", line_no);
  exit (1);
}