/*
##################################################
#			                         #
#        Projet LicPro Info 2012/2013            #
#            Le code de Vigenere                 #
#                                                #
# Romain Prieur,Lassalle Anthony,Coffinet Dorian #  
#			                         #
##################################################
*/

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define VRAI 1
#define FAUX 0 

/**en-tête de la fonction de dechiffrement**/
void dechiffrement(char *, char *, unsigned int *,unsigned int) ;
int accent(int c);

/******fonction de dechiffrement ( pointeur ,pointeur , pointeur, taille de la clef)******/
void dechiffrement(char *entree, char *sortie,unsigned int *clef,unsigned int n)
{
  FILE *f_in, *f_out ;//pointeur sur les fichiers d'entree et de sortie
  int c; //code ASCII du caractère de lecture du fichier source
  int chiffre=0 ;//code ASCII du caractère de lecture du fichier de sortie
  unsigned int i = 0 ;//incrémente la clef pour passer au caractère suivant

  /**ouverture du fichier source**/
  if ((f_in = fopen(entree, "r")) == NULL)
    {
      fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", entree) ;
      return ;
    }
  /**ouverture du fichier de sortie**/  
  if ((f_out = fopen(sortie, "w")) == NULL)
    {
      fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", sortie) ;
      return ;
    }
/**boucle sur le caractère du fichier source jusqu'à la fin de fichier**/
	while ((c = fgetc(f_in)) != EOF)
    {

			if( (c>=48) && (c<=57) ) //la variable "c" est comprise entre 48 et 57 qui sont les codes ascii des chiffres 0 à 9
				fprintf(f_out,"%c",c);
			else
			{
			if (c==32)
				chiffre=32;
	
		 	else
			{
					chiffre = ((((c-65) - clef[i%n])+26) % 26)+ 65 ;
					i++ ;
			}
		 
		 
		 	fprintf(f_out,"%c",chiffre) ;
			}
		}

  /**fermeture des fichiers source et sortie**/
  fclose(f_in) ;
  fclose(f_out) ;
  return ;
}

/******Fonction de test sur les accents******/
int accent(int c)
{
	switch (c) 
		{
				case 169 : c = 'e'; break;	//caractère 'é'
				case 168 : c = 'e'; break;	//caractère 'è'
				case 170 : c = 'e'; break;	//caractère 'ê'
				case 171 : c = 'e'; break;	//caractère 'ë'
				case 160 : c = 'a'; break;	//caractère 'à'
				case 162 : c = 'a'; break;	//caractère 'â'
				case 185 : c = 'u'; break;	//caractère 'ù'
				case 188 : c = 'u'; break;	//caractère 'ü'
				case 187 : c = 'u'; break;	//caractère 'û'
				case 167 : c = 'c'; break;	//caractère 'ç'
				case 180 : c = 'o'; break;	//caractère 'ô'
				case 175 : c = 'i'; break;	//caractère 'ï'
		}

		return c;		
}

/******Main******/
int main(int argc, char *argv[0])//on utilise les arguments pour l'ouverture d'un fichier spécifique, ainsi que l'écriture dans un fichier spécifique
{
 
  int i,c;//code ASCII du caractère
  unsigned int longueur_clef;//longueur de la clef de codage
  unsigned int *clef ;//pointeur sur la valeur de la clef de codage
  int booleen = FAUX;//variable booléen initialisée à faux
   if (argc != 3)//on teste le nombre d'arguments, exit si nombre incorrect (./a texte.txt resultat.txt) --> (execution texte resultat_codage) 
    {
      fprintf(stderr, "\nErreur : nombre d’arguments invalide\n") ;
      fprintf(stderr, "Syntaxe : %s fichier_entree fichier_sortie\n", argv[0]) ;
      return(EXIT_FAILURE) ;
    }
   
  printf("\nEntrez la longueur de la clef sans espace (exemple : texte sans espace = 15 )\t") ;
  scanf("%u", &longueur_clef) ;
  clef = malloc(longueur_clef * sizeof(unsigned int)) ;//on attribut de l'espace mémoire de la taille d'un unsigned int * par la longueur de la clef
  
  
  printf("Entrez un mot clé ou une phrase clé de %d caractères (sans compter les espaces) : ",longueur_clef) ;
  //lire une fois le caractère pour lire entrée
  c = getchar() ;
  i=0;

/**traitement de la clef de cryptage**/
  while(i< longueur_clef)//i est l'indice de la case du tableau clef[]
	{
      c = getchar() ;

      if (c == 195)//test sur les caractères spéciaux
				booleen = VRAI;

			else
			 if (booleen == VRAI)
				{
					c = accent(c);
					booleen = FAUX;
					clef[i] = c - 97 ;//affecte des résultats entre 0 et 25 sur les lettre minuscules, 0 à 25 corresponds aux 26 lettres de l'alphabet français
					i++ ;
				} 
				else
				{
					       	if((c!=32))		//si 'c' n'est pas un espace et n'est pas une lettre majuscule
				{ 
					if( (c<65) || (c>90) )	
						{
							clef[i] = c - 97 ; 							//on utilise alors les résultats dans la fonction chiffrement
							i++;
						}
					else 
						{
						clef[i] = c-65;//65 correspond au nombre ascii de A majuscule
						i++;
						}
			 	}
			}
		}	

  if(i==longueur_clef)//lorsque la longueur de la clef correspond à la deniere case du tableau
  {
    dechiffrement(argv[1], argv[2], clef, longueur_clef); //on utilise la fonction dechiffrement décrite plus haut avec les paramètres suivant : texte_entré,texte_resultat,clef,longueur
  }else{
    printf("veuillez rentrer un clef égale à votre longueur de clef\n");//lorsque l'utilisateur entre un clef trop petite ou trop grande par rapport à longueur clef
  }

	free(clef);

	return 0;
}
