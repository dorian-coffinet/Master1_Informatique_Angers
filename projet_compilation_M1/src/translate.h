#ifndef TRANSLATE_H
#define TRANSLATE_H


typedef struct element element;
typedef element * llist;

llist affichage;

void ajouterEnFin(char* chaine);
void afficherListe();
int estVide();
llist supprimerElementEnTete();
void impression();
void liberationMemoire();
char * concat_deux_chaines(char * s1, char * s2);
char * concat_trois_chaines(char * s1, char * op, char * s2);
char * alloc_yytext(char * yytext);
void traduction();
char * conversionType(char * type);
char * replace_str(char *str, char *orig, char *rep);

#endif