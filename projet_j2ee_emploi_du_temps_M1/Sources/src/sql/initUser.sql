/*******************************************************************************/
/************************** INITIALISATION DE LA BASE **************************/
/******** WARNING : Si je user existe déjà, ne pas executer sa création ********/
/*******************************************************************************/

/*************** Création du USER pour les connexions à distance ***************/
CREATE USER 'useredt'@'%' IDENTIFIED BY  '1234';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,FILE,INDEX,ALTER,CREATE TEMPORARY TABLES,CREATE VIEW,EVENT,TRIGGER,SHOW VIEW,CREATE ROUTINE,ALTER ROUTINE,
EXECUTE ON * . * TO  'useredt'@'%' IDENTIFIED BY  '1234' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

/***************** Création du USER pour les connexions local ******************/
CREATE USER 'useredt'@'localhost' IDENTIFIED BY  '1234';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,FILE,INDEX,ALTER,CREATE TEMPORARY TABLES,CREATE VIEW,EVENT,TRIGGER,SHOW VIEW,CREATE ROUTINE,ALTER ROUTINE,
EXECUTE ON * . * TO  'useredt'@'localhost' IDENTIFIED BY  '1234' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

/************************ Création de la base du projet ************************/
DROP DATABASE IF EXISTS projetEDT;
CREATE DATABASE projetEDT;
/******** Ajout des privilèges au users pour la base qui lui est dédiée ********/
GRANT ALL PRIVILEGES ON  projetEDT . * TO  'useredt@%' WITH GRANT OPTION ;
GRANT ALL PRIVILEGES ON  projetEDT . * TO  'useredt'@'localhost' WITH GRANT OPTION ;

/*********************** Selection de la base du projet ************************/
USE projetEDT;

