#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "tableSymboles.h"

typedef struct element_TS element_TS;
typedef element_TS* llistTS;
struct element_TS
{
    // int val;
    char* symb;
    char* type;
    struct element_TS *nxt;
    int occurence;
};


llistTS ajouterEnFinTS(llistTS table_symboles, char* symbole, char* type_symb)
{
    /* On crée un nouvel élément */
    element_TS* nouvelElement_TS = malloc(sizeof(element_TS));
 
    /* On assigne la valeur au nouvel élément */
    nouvelElement_TS->symb = symbole;

    /* On assigne le type au nouvel élément */
    nouvelElement_TS->type = type_symb;

    /* On met le nombre d'occurence à 1 */
    nouvelElement_TS->occurence = 1;
 
    /* On ajoute en fin, donc aucun élément ne va suivre */
    nouvelElement_TS->nxt = NULL;
 
    if(table_symboles == NULL)
    {
        /* Si la liste est videé il suffit de renvoyer l'élément créé */
        return nouvelElement_TS;
    }
    else
    {
        /* Sinon, on parcourt la liste à l'aide d'un pointeur temporaire et on
        indique que le dernier élément de la liste est relié au nouvel élément */
        element_TS* temp=table_symboles;
        while(temp->nxt != NULL)
        {
            temp = temp->nxt;
        }
        temp->nxt = nouvelElement_TS;
        return table_symboles;
    }
}


llistTS ajouterEnTeteTS(llistTS table_symboles, char* symbole)
{
    /* On crée un nouvel élément */
    element_TS* nouvelElement_TS = malloc(sizeof(element_TS));
 
    /* On assigne la valeur au nouvel élément */
    nouvelElement_TS->symb = symbole;
 
    /* On assigne l'adresse de l'élément suivant au nouvel élément */
    nouvelElement_TS->nxt = table_symboles;
 
    /* On retourne la nouvelle liste, i.e. le pointeur sur le premier élément */
    return nouvelElement_TS;
}

void afficherListeTS(llistTS table_symboles)
{
    element_TS *tmp = table_symboles;
    /* Tant que l'on n'est pas au bout de la liste */
    int i = 1;
    while(tmp != NULL)
    {

        /* On affiche */
        printf("Symbole %d : %s, Type : %s , occurence :%d \n", i, tmp->symb, tmp->type, tmp->occurence);
        // printf("%s \n", tmp->symb);
        /* On avance d'une case */
        tmp = tmp->nxt;
        i++;
    }

}

int estVideTS(llistTS table_symboles)
{
    return (table_symboles == NULL)? 1 : 0;
}

int rechercherElementTS(llistTS table_symboles, char* symbole)
{
    element_TS *tmp=table_symboles;
    /* Tant que l'on n'est pas au bout de la liste */
    while(tmp != NULL)
    {
        if(strcmp(tmp->symb, symbole)==0)
        {
            /* Si l'élément a la valeur recherchée, on renvoie son adresse */
            //problème si on le laisse ce free , a revoir
            // free(tmp);
            /* On augmente le nombre d'occurence de 1*/
            tmp->occurence +=1;
            return 1;
        }
        tmp = tmp->nxt;
    }
    free(tmp);
    return 0;
}

int verificationContexteTS(llistTS table_symboles, char* symbole){
    char* symb;
    symb = malloc(strlen(symbole) + 1);
    strcpy(symb, symbole);
    if(rechercherElementTS(table_symboles, symb)==0)
    {
        // printf("ERROR Le symbole %s n'existe pas\n", symb);
        return 0;
    }
    else
    {
        // printf("VALIDE Le symbole %s existe\n", symb);
        return 1;
    }
}

llistTS ajoutSymboleTS(llistTS table_symboles, char* symbole, char* type_symb)
{
    char* symb;
    char* typ;
    symb = malloc(strlen(symbole) + 1);
    strcpy(symb, symbole);
    typ = malloc(strlen(type_symb) + 1);
    strcpy(typ, type_symb);
    if (rechercherElementTS(table_symboles, symb)==0)
    {
        table_symboles = ajouterEnFinTS(table_symboles, symb, typ);
    } 
    else
    {
        free(symb);
    }
    return table_symboles;
}

llistTS ajoutListeSymboleTS(llistTS table_symboles, char* listeVariable, char* type_symb){
    char * pch;
    pch = strtok (listeVariable,",");
    while (pch != NULL)
    {
        table_symboles = ajoutSymboleTS(table_symboles, pch, type_symb);
        pch = strtok (NULL, ",");
    }
    return table_symboles;
}






llistTS supprimerElementEnTeteTS(llistTS liste)
{
    if(liste != NULL)
    {
        /* Si la liste est non vide, on se prépare à renvoyer l'adresse de
        l'élément en 2ème position */
        element_TS* aRenvoyer = liste->nxt;
        /* On libère le premier élément */
        free(liste->symb);
        free(liste);
        /* On retourne le nouveau début de la liste */
        return aRenvoyer;
    }
    else
    {
        return NULL;
    }
}

void liberationMemoireTS(llistTS liste)
{
    while(liste != NULL)
    {
        liste = supprimerElementEnTeteTS(liste);
    }
    free(liste);
}


char * concat_expressionTS(char * s1, char * op, char * s2) {
    char * s = malloc(sizeof(s1) + sizeof(op) + sizeof(s2)); 
    strcat(s,s1);
    strcat(s,op);
    strcat(s,s2);
    return s;  
}

char * concat_deux_chainesTS(char * s1, char * s2) {
    char * s = malloc(sizeof(s1) + sizeof(s2)); 
    strcat(s,s1);
    strcat(s,s2);
    return s;  
}
