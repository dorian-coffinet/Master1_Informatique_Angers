#ifndef TABS_H
#define TABS_H


typedef struct element_TS element_TS;
typedef element_TS* llistTS;

llistTS ajouterEnFinTS(llistTS table_symboles, char* symbole, char* type_symb);
llistTS ajouterEnTeteTS(llistTS table_symboles, char* symbole);
void afficherListeTS(llistTS table_symboles);
int estVideTS(llistTS table_symboles);
int rechercherElementTS(llistTS table_symboles, char* symbole);
int verificationContexteTS(llistTS table_symboles, char* symbole);
llistTS ajoutSymboleTS(llistTS table_symboles, char* symbole, char* type_symb);
llistTS ajoutListeSymboleTS(llistTS table_symboles, char* listeVariable, char* type_symb);
llistTS supprimerElementEnTeteTS(llistTS liste);
void liberationMemoireTS(llistTS liste);
char * concat_expressionTS(char * s1, char * op, char * s2);
char * concat_deux_chainesTS(char * s1, char * s2);
#endif