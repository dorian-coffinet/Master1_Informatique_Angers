#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "tableSymbolesSecondaires.h"
#include "tableSymboles.h"

typedef struct element_TSS element_TSS;
typedef element_TSS* llistSecond;
struct element_TSS
{
    llistTS *listeSecond;
    struct element_TSS *nxt;
};

llistSecond ajouterEnFinSecondaire(llistSecond table_symboles, llistTS listeSecondaire)
{
    /* On crée un nouvel élément */
    element_TSS* nouvelElement_TSS = malloc(sizeof(element_TSS));
 
    /* On assigne la valeur au nouvel élément */
    nouvelElement_TSS->listeSecond = &listeSecondaire;

    /* On ajoute en fin, donc aucun élément ne va suivre */
    nouvelElement_TSS->nxt = NULL;
 
    if(table_symboles == NULL)
    {
        /* Si la liste est videé il suffit de renvoyer l'élément créé */
        return nouvelElement_TSS;
    }
    else
    {
        /* Sinon, on parcourt la liste à l'aide d'un pointeur temporaire et on
        indique que le dernier élément de la liste est relié au nouvel élément */
        element_TSS* temp=table_symboles;
        while(temp->nxt != NULL)
        {
            temp = temp->nxt;
        }
        temp->nxt = nouvelElement_TSS;
        return table_symboles;
    }
    return NULL;
}

void afficherListeSecondaire(llistSecond table_symboles)
{
    // element_TSS *tmp = table_symboles;
    // /* Tant que l'on n'est pas au bout de la liste */
    // int i = 1;
    // while(tmp != NULL)
    // {
    //     llist* toto = tmp->listeSecond;
    //     afficherListe((*toto));
    //     tmp = tmp->nxt;
    //     printf("%d\n", i);
    //     i++;
    // }
}

