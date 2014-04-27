%{
	#include<stdio.h>
	#include<string.h>
	#include"translate.h"
	#include"tableSymboles.h"
	#include"tableSymbolesSecondaires.h"
	int yylex();
	void yyerror(char const* s);
	extern FILE *yyin;
	llistTS table_sym = NULL;
	llistTS table_sym_global = NULL;
	llistSecond table_sym_second = NULL;
%}

%token <t_string> TBEGIN DO DIV TEND FUNCTION PROCEDURE IF MOD PROGRAM THEN ELSE VAR WHILE FOR TO
%token <t_string> INTEGER STRING REAL BOOLEAN CHAR
%token <t_string> ASSIGNATION POINT DEUXPOINTS VIRGULE POINTVIRGULE
%token <t_string> EGAL SUPERIEUREGAL SUPERIEUR INFERIEUREGAL INFERIEUR DIFFERENT
%token <t_string> ADDITION SOUSTRACTION MULTIPLICATION DIVISION
%token <t_string> PARENTHESEGAUCHE PARENTHESEDROITE
%token <t_string> APOSTROPHE
%token <t_string> NOMBRE
%token <t_string> IDENTIFIANT
%token <t_string> WRITELN
%token <t_string> CHAINE

%left ADDITION SOUSTRACTION
%left MULTIPLICATION DIVISION
%left INFERIEUR SUPERIEUR
%left EGAL
%right THEN ELSE

%error-verbose

%union {
	int t_int;
	float t_float;
	char * t_string;
}

%type <t_string> programme;
%type <t_string> programme_entete;
%type <t_string> bloc;
%type <t_string> declaration_variable;
%type <t_string> liste_variables;
%type <t_string> declaration_variables;
%type <t_string> liste_identifiants;
%type <t_string> declaration_fonction;
%type <t_string> declaration_procedure;
%type <t_string> liste_fonctions;
%type <t_string> liste_procedures;
%type <t_string> declaration_fonctions;
%type <t_string> declaration_procedures;
%type <t_string> fonction_entete;
%type <t_string> procedure_entete;
%type <t_string> parametres;
%type <t_string> liste_parametres;
%type <t_string> instruction;
%type <t_string> instruction_list;
%type <t_string> declaration;
%type <t_string> declaration_close;
%type <t_string> declaration_ouverte;
%type <t_string> while;
%type <t_string> if;
%type <t_string> for;
%type <t_string> instruction_assignement;
%type <t_string> expressions;
%type <t_string> comparaison;
%type <t_string> expression;
%type <t_string> type_variable;
%type <t_string> write;
%type <t_string> interieur_write;
%type <t_string> appel_procedure;

%%

programme:
	programme_entete bloc POINT 
	;

programme_entete:
	PROGRAM IDENTIFIANT POINTVIRGULE { ajouterEnFin($2); }
	;

bloc: declaration_variable
	declaration_fonction
	declaration_procedure
	instruction
	;

declaration_variable: VAR liste_variables POINTVIRGULE { 
							ajouterEnFin("declaration_variables ");
							ajouterEnFin($2);
							}
	|
	;

liste_variables: liste_variables POINTVIRGULE declaration_variables { $$ = concat_trois_chaines($1,$2,$3); }
	| declaration_variables { $$ = $1; }
	;

declaration_variables: liste_identifiants DEUXPOINTS type_variable { 
							$$ = concat_trois_chaines($1,$2,$3);
							table_sym = ajoutListeSymboleTS(table_sym, $1, $3);
							}
	;

liste_identifiants: liste_identifiants VIRGULE IDENTIFIANT { $$ = concat_trois_chaines($1,$2,$3); }
	| IDENTIFIANT { $$ = $1; }
	;

declaration_fonction: liste_fonctions POINTVIRGULE declaration_variable
	|
	;

declaration_procedure: liste_procedures POINTVIRGULE declaration_variable 
	|
	;

liste_fonctions: liste_fonctions POINTVIRGULE declaration_fonctions 
	| declaration_fonctions 
	;

liste_procedures: liste_procedures POINTVIRGULE declaration_procedures 
	| declaration_procedures 
	;

declaration_fonctions: fonction_entete bloc {
							ajouterEnFin(concat_deux_chaines("fin_fonction ",$1));
							llistTS table_sym_b = NULL;
							table_sym_second = ajouterEnFinSecondaire(table_sym_second, table_sym_b); /* probleme de copie */
							// afficherListeTS(table_sym);
							printf("\n");
							table_sym = table_sym_b;
						}
	;

declaration_procedures: procedure_entete bloc { ajouterEnFin(concat_deux_chaines("fin_procedure ",$1)); }
	;

fonction_entete: FUNCTION IDENTIFIANT parametres DEUXPOINTS type_variable POINTVIRGULE { 
				ajouterEnFin(concat_trois_chaines($1," ",$2)); 
				ajouterEnFin(concat_deux_chaines("param ",$3));
				ajouterEnFin(concat_deux_chaines("type_return ", $5));
				ajouterEnFin(concat_deux_chaines("fin_declaration ",$2));
				$$ = $2;
				table_sym = ajoutSymboleTS(table_sym, $2, concat_deux_chainesTS("fonction", $5));
				table_sym_global = ajoutSymboleTS(table_sym_global, $2, concat_deux_chainesTS("fonction", $5));
				}
	;

procedure_entete: PROCEDURE IDENTIFIANT POINTVIRGULE { 
														ajouterEnFin(concat_trois_chaines($1," ",$2));
														$$ = $2;
														table_sym = ajoutSymboleTS(table_sym, $2, "void");
														table_sym_global = ajoutSymboleTS(table_sym_global, $2, "void");
													 }
	;

parametres: PARENTHESEGAUCHE liste_parametres PARENTHESEDROITE { $$ = $2; }
	;

liste_parametres: liste_parametres VIRGULE declaration_variables { $$ = concat_trois_chaines($1,$2,$3); }
	| declaration_variables 
	;

instruction: 	TBEGIN 				{ ajouterEnFin("begin"); }
				instruction_list 	
				TEND				{ ajouterEnFin("end"); }
	;

instruction_list: instruction_list POINTVIRGULE declaration
	| declaration
	;

declaration: declaration_ouverte
	| declaration_close
	;

declaration_close: instruction_assignement
	| instruction
	|
	;

declaration_ouverte: while 
	| if 
	| for
	| write 
	| appel_procedure
	;

while: 	WHILE 
		expressions 
		DO 				{ 
							char * temporaire = concat_trois_chaines($1," ",$2);
	 						char * temporaire2 = concat_trois_chaines(temporaire," ",$3);
							ajouterEnFin(temporaire2);
						}
		declaration 
	;

if: IF 				
	expressions 
	THEN 			{ 
	 					char * temporaire = concat_trois_chaines($1," ",$2);
	 					char * temporaire2 = concat_trois_chaines(temporaire," ",$3);
						ajouterEnFin(temporaire2); 
					}
	declaration 
	ELSE 			{ ajouterEnFin("else"); }
	declaration 
	;

for: FOR IDENTIFIANT ASSIGNATION expression TO expression DO declaration {
		if(verificationContexteTS(table_sym, $2)==0)
			printf("ERROR Le symbole %s n'existe pas\n", $2);
		else 
			printf("VALIDE Le symbole %s existe\n", $2);/*vérification de l'identifiant si il est déclaré*/}
	;

write: WRITELN 
		PARENTHESEGAUCHE 
		interieur_write
		PARENTHESEDROITE 	{
			char * temporaire = concat_trois_chaines("affichage ",$1,$2);
			char * temporaire2 = concat_trois_chaines(temporaire,$3,$4);
			ajouterEnFin(temporaire2);
		}
		declaration
	;

interieur_write: 	IDENTIFIANT 
					PARENTHESEGAUCHE 
					expression 
					PARENTHESEDROITE { char * temporaire = concat_trois_chaines($1,$2,$3);
										char * temporaire2 = concat_deux_chaines(temporaire,$4);
										$$ = temporaire2;
										if(verificationContexteTS(table_sym_global, $1) == 1){
											printf("VALIDE Le symbole %s existe\n", $1);
										}else if(verificationContexteTS(table_sym, $1) ==1 ){
											printf("VALIDE Le symbole %s existe\n", $1);
										} else {
											printf("ERROR Le symbole %s n'existe pas\n", $1);
										}
										}
					|
					expression { $$ = $1; }
;

instruction_assignement: IDENTIFIANT ASSIGNATION interieur_write { 
						char * temporaire = concat_deux_chaines("assignation ",$1);
						ajouterEnFin(concat_trois_chaines(temporaire,$2,$3));
						if(verificationContexteTS(table_sym_global, $1) == 1){
							printf("VALIDE Le symbole %s existe\n", $1);
						}else if(verificationContexteTS(table_sym, $1) ==1 ){
							printf("VALIDE Le symbole %s existe\n", $1);
						} else {
							printf("ERROR Le symbole %s n'existe pas\n", $1);
						}
						}
	;

appel_procedure: IDENTIFIANT POINTVIRGULE { ajouterEnFin(concat_deux_chaines("appel_procedure ",$1)); }
	declaration {
		if(verificationContexteTS(table_sym, $1)==0)
			printf("ERROR Le symbole %s n'existe pas\n", $1);
		else 
			printf("VALIDE Le symbole %s existe\n", $1);/*vérification de l'identifiant si il est déclaré*/}
	; 

expressions: comparaison 	{ $$ = $1; } 	 	
	|
	NOMBRE 					{ $$ = $1; }	
	;

comparaison: expression INFERIEUREGAL expression 	{ $$ = concat_trois_chaines($1,$2,$3); }	
	| 
	expression INFERIEUR expression 				{ $$ = concat_trois_chaines($1,$2,$3); }				
	|
	expression EGAL expression 						{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	expression SUPERIEUR expression 				{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	expression SUPERIEUREGAL expression 			{ $$ = concat_trois_chaines($1,$2,$3); }
	;

expression: expression MULTIPLICATION expression 	{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	expression ADDITION expression 					{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	expression SOUSTRACTION expression 				{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	expression DIVISION expression 					{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	PARENTHESEGAUCHE expression PARENTHESEDROITE 	{ $$ = concat_trois_chaines($1,$2,$3); }
	|
	NOMBRE 											{ $$ = $1; }
	|
	IDENTIFIANT 									{ $$ = $1; 
		if(verificationContexteTS(table_sym, $1)==0)
			printf("ERROR Le symbole %s n'existe pas\n", $1);
		else 
			printf("VALIDE Le symbole %s existe\n", $1);/*vérification de l'identifiant si il est déclaré*/}
	|
	CHAINE				{ $$ = $1; }
	;

type_variable : STRING 	{ $$ = $1; }
	| INTEGER 			{ $$ = $1; }
	| REAL 				{ $$ = $1; }
	| BOOLEAN 			{ $$ = $1; }
	| CHAR 				{ $$ = $1; }
	;

%%

int main(int argc, char* argv[]){
	FILE *f = NULL;
	if(argc >1) {
		f = fopen(argv[1],"r");
			if(f==NULL){
				return -1;
			}
			yyin=f;
	}
	yyparse();
	//impression();
	traduction();
	if(f!=NULL)
		fclose(f);
}

void yyerror(char const* s){
	fprintf(stderr, "Erreur %s\n", s);
}