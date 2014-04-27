################################
#     Étudiant du Groupe       #
################################

Dorian COFFINET

##################################
#      Environnement requis      #
##################################

ruby-1.9.3-p448

##################################
#     Installation de la gem     #
##################################

1) Soit directement installer la gem présente dans le dossier gem/imc-0.0.1 avec la commande
	gem install imc-0.0.1

2) Soit compiler les sources en allant dans le dossier imc/ et lancer la commande 
	gem build imc.gemspec
un message de succès doit s’afficher, il faut ensuite faire le 1)

##################################
# Mise en place de l’application #
##################################

1) Aller dans le dossier application_cc et lancer : bundle install
2) Faire la migration : rake db:migrate
2) Pour remplir la base lancer rake db:seed
3) Lancer le serveur