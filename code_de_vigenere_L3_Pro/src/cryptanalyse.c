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

#define N 26

void initTab(int t[N]);
void nbLettres(char *entree, int t[N], int *tailleText, int tailleCle);
double indiceCoincidence(int t[N], int nblettres);
unsigned int longueurCle(char *entree, int t[N]);
int lettreMotCle(int t[N]);
void dechiffrement(char *entree, char *sortie,unsigned int *clef,unsigned int n);
void motClef(char *entree, int t[N], unsigned int *cle, int tailleCle);

void initTab(int t[N])
{
   int i;
   for(i=0; i<N; i++)
       t[i]=0;
}
   
//renvoie le nombre de lettres totales dans le texte et compte le nombre de chaque lettres du texte
void motClef(char *entree, int t[N], unsigned int *cle, int tailleCle)
{
   FILE *f_in;
	 int lettretmp;
   int compteurLongeurCle=1;
   int c;
   int j=0;
   
   int i=0;
   //valeur pour sortir du while principal quand on atteint la fin de fichier
   int val=0;
   //valeur pour lire obligatoirement le premier caractere du fichier source et ne pas tout decaler
   int test=0;
   if ((f_in = fopen(entree, "r")) == NULL)
       {
     fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", entree);
     return;
       }
       
while (compteurLongeurCle!=tailleCle+1){
  val=0;
  test=0;
  initTab(t);
 while (val==0)
   {
    
    if(test==0)
    {
     
      fseek(f_in,j,SEEK_SET);
      c = fgetc(f_in);
      test=1;
    }
    else
    {  
       for(i=0;i<tailleCle;i++) //boucle permettant d'avancer le caractere lu en fonction de la longeur de la clef testee
       {

         if( (c==32) || ( (c>=48) && (c<=57) ) )
         {
           c = fgetc(f_in);
           i--;
         }
         else
	   			c = fgetc(f_in);

         if(c== EOF)
            val=1;
      	 
			  }
     }
     

     if((c>=65) && (c<=90)) //si la lettre est majuscule
     {
       t[c-65]=t[c-65]+1; /*retire 65 a la lettre pour qu'elle corresponde a sa case das le tableau (0 pour A, 1 our B etc..) et 
													*ajoute +1 a chaque fois que la lettre 'c' est rencontrée*/
     }

   } //fin while secondaire

   lettretmp=lettreMotCle(t);
   cle[compteurLongeurCle-1]=lettretmp;
   compteurLongeurCle++;
   j++;
  
	}//fin while principal
	fclose(f_in);
}



//renvoie le nombre de lettres totales dans le texte et compte le nombre de chaque lettres du texte
void nbLettres(char *entree, int t[N], int *tailleText, int tailleCle)
{
   FILE *f_in;
   unsigned int nbLettresTotal=0;
   int c;
   int i=0;
   //valeur pour sortir du while principal quand on atteint la fin de fichier
   int val=0;
   //valeur pour lire obligatoirement le premier caractere du fichier source et ne pas tout decaler
   int test=0;
   if ((f_in = fopen(entree, "r")) == NULL)
       {
     fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", entree);
     return;
       }

 while (val==0)
   {
    /*
     * lit obligatoirement le premier caractere, pour eviter davoir le texte decaler
     * 
     * A remplacer par fseek(f_in,1 , SEEK_SET);
     * 
     */
    if(test==0)
    {
     c = fgetc(f_in);
     test=1;
    }
    else
    {  
       for(i=0;i<tailleCle;i++) //boucle permettant d'avancer le caractere lu en fonction de la longeur de la clef testee
       {

         if( (c==32) || ( (c>=48) && (c<=57) ) )
         {
           c = fgetc(f_in);
           i--;
         }
         else
     			 c = fgetc(f_in);
					
         if(c== EOF)
            val=1;
        
			 }
     }
     
     if((c>=65) && (c<=90)) //si la lettre est majuscule
     {
       t[c-65]=t[c-65]+1; /*retire 65 a la lettre pour qu'elle corresponde a sa case das le tableau (0 pour A, 1 our B etc..) et 																*ajoute +1 a chaque fois que la lettre 'c' est rencontrée*/
       nbLettresTotal++;
     }
   } //fin while

   *tailleText=nbLettresTotal;
   fclose(f_in);
}

double indiceCoincidence(int t[N], int nblettres)
{
   int i;
   double indice=0;
   double indice2=0;
   for(i=0; i<N; i++)
   {
       indice=indice+(t[i]*(t[i]-1));
       indice=indice/(nblettres*(nblettres-1));
       indice2+=indice;
   }
   return indice2 ;
}


unsigned int longueurCle(char *entree, int t[N])
{
	int tailleCle, nblettre;
	double indice=0;

	  for(tailleCle=1; indice<0.0700; tailleCle++)
   {
       initTab(t);
       nblettre=0;
       indice=0;
       nbLettres(entree, t, &nblettre, tailleCle);
       indice=indiceCoincidence(t, nblettre);
   }
	
	return(tailleCle-1);
}


//retourne la case du tableau qui est 4 cases avant la lettre la plus présente dans le tableau.
int lettreMotCle(int t[N])
{
	int i, j, plusGrand;
	plusGrand=j=0;

	for(i=0; i<N; i++)
	{
		if(t[i]>=plusGrand)
		{
			plusGrand=t[i];
			j=i;
		}		
	}
	return (((j-4)+26)%26);
}




void dechiffrement(char *entree, char *sortie,unsigned int *clef,unsigned int n)
{
  FILE *f_in, *f_out ;
  int c;
  int chiffre=0 ;
  unsigned int i = 0 ;

  if ((f_in = fopen(entree, "r")) == NULL)
    {
      fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", entree) ;
      return ;
    }
    
  if ((f_out = fopen(sortie, "w")) == NULL)
    {
      fprintf(stderr,"Erreur ds l’ouverture du fichier %s\n", sortie) ;
      return ;
    }

	while ((c = fgetc(f_in)) != EOF)
    {

			if( (c==32) || (c>=48) && (c<=57) )
				chiffre=32;
	
		 	else
			{
					chiffre = ((((c-65) - clef[i%n])+26) % 26)+ 65 ;
					i++ ;
			}
		 
		 
		 	fprintf(f_out,"%c",chiffre) ;
		
		}


  fclose(f_in) ;
  fclose(f_out) ;
  return ;
}


int main(int argc, char *argv[0])
{
   int t[N];
	 unsigned int *cle;
	 unsigned int tailleCle;
	 int i;

	 if (argc != 3)											//tester le nombre d'arguments, exit si nombre incorrect 
   {
      fprintf(stderr, "\nErreur : nombre d’arguments invalide\n");
      fprintf(stderr, "Syntaxe : %s fichier_entree fichier_sortie\n", argv[0]);
      return(EXIT_FAILURE) ;
   }

	 tailleCle=longueurCle(argv[1], t);
	 cle=calloc(tailleCle,sizeof(unsigned int));
	 motClef(argv[1],t,cle,tailleCle);
	 dechiffrement(argv[1], argv[2], cle, tailleCle);
	 printf("la clé est : ");
	 for(i=0; i<tailleCle; i++)
		printf("%c", cle[i]+65);

	 printf("\n");
	 free(cle);
   
  return 0;
}
