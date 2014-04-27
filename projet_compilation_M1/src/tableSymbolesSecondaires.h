#ifndef TABSS_H
#define TABSS_H
#include "tableSymboles.h"

typedef struct element_TSS element_TSS;
typedef element_TSS* llistSecond;

llistSecond ajouterEnFinSecondaire(llistSecond table_symboles, llistTS listeSecondaire);
void afficherListeSecondaire(llistSecond table_symboles);
#endif