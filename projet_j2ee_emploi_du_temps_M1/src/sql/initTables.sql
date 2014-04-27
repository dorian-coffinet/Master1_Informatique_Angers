-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 03 Avril 2014 à 09:49
-- Version du serveur: 5.5.35-0ubuntu0.13.10.2
-- Version de PHP: 5.5.3-1ubuntu2.2

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `projetEDT`
--
CREATE DATABASE IF NOT EXISTS `projetEDT` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `projetEDT`;

-- --------------------------------------------------------

--
-- Structure de la table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
CREATE TABLE IF NOT EXISTS `classroom` (
  `id_classroom` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`id_classroom`),
   UNIQUE (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `classroom`
--

INSERT INTO `classroom` (`id_classroom`, `name`) VALUES
(1, 'H006'),
(2, 'L201'),
(3, 'A005');

-- --------------------------------------------------------

--
-- Structure de la table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
CREATE TABLE IF NOT EXISTS `group_user` (
  `id_group_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`id_group_user`),
  UNIQUE (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `group_user`
--

INSERT INTO `group_user` (`id_group_user`, `name`) VALUES
(1, 'Enseignant'),
(2, 'Etudiant'),
(3, 'Invité'),
(4, 'M1 Informatique');

-- --------------------------------------------------------

--
-- Structure de la table `passwordTeacher`
--

DROP TABLE IF EXISTS `passwordTeacher`;
CREATE TABLE IF NOT EXISTS `passwordTeacher` (
  `id_password_teacher` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`id_password_teacher`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `passwordTeacher`
--

INSERT INTO `passwordTeacher` (`id_password_teacher`, `password`) VALUES
(1, '018c61431a981be22bc5c5c5e3881e8e');

-- --------------------------------------------------------

--
-- Structure de la table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE IF NOT EXISTS `schedule` (
  `id_schedule` int(11) NOT NULL AUTO_INCREMENT,
  `day_start` datetime NOT NULL,
  `day_end` datetime NOT NULL,
  `name` varchar(256) NOT NULL,
  `comment` text NOT NULL,
  `id_user_teacher` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `id_group_user` int(11) NOT NULL,
  PRIMARY KEY (`id_schedule`),
  KEY `id_subject` (`id_subject`),
  KEY `id_classroom` (`id_classroom`),
  KEY `id_group_user` (`id_group_user`),
  KEY `id_user_teacher` (`id_user_teacher`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `schedule`
--


INSERT INTO `schedule` (`id_schedule`, `day_start`, `day_end`, `name`, `comment`, `id_user_teacher`, `id_subject`, `id_classroom`, `id_group_user`) VALUES
(3, '2014-04-14 09:00:00', '2014-04-14 11:30:00', 'Optimisation Lineaire', '', 1, 2, 1, 4),
(4, '2014-04-14 14:00:00', '2014-04-14 16:00:00', 'Langages et Compilation', '', 2, 2, 1, 4),
(5, '2014-04-15 09:30:00', '2014-04-15 12:20:00', ' Résolution de problèmes', '', 1, 1, 1, 4),
(6, '2014-04-15 14:00:00', '2014-04-15 17:00:00', 'Développement Web', '', 2, 1, 1, 4),
(7, '2014-04-16 09:30:00', '2014-04-16 12:00:00', 'Résolution de problèmes', '', 1, 1, 1, 4),
(8, '2014-04-16 14:00:00', '2014-04-16 17:30:00', 'Optimisation combinatoire', '', 2, 2, 1, 4),
(9, '2014-04-17 09:00:00', '2014-04-17 11:00:00', 'Frameworks et langages', '', 1, 3, 1, 4),
(10, '2014-04-17 11:15:00', '2014-04-17 12:15:00', 'Présentation projets', '', 2, 3, 1, 4),
(11, '2014-04-17 14:00:00', '2014-04-17 15:30:00', 'Optimisation', '', 1, 3, 1, 4),
(12, '2014-04-17 16:00:00', '2014-04-17 17:00:00', 'Optimisation combinatoire', '', 2, 3, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `subject`
--

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `id_subject` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `short_name` varchar(256) NOT NULL,
  `color` varchar(256) NOT NULL,
  PRIMARY KEY (`id_subject`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `subject`
--

INSERT INTO `subject` (`id_subject`, `name`, `short_name`, `color`) VALUES
(1, 'Cours magistral', 'CM', '#FAF616'),
(2, 'Travaux pratiques', 'TP', '#FF771C'),
(3, 'Devoir surveillé', 'DS', '#FF0000'),
(4, 'Cours annulé', 'Annulé', '#BF00E6');


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `name` varchar(256) NOT NULL,
  `id_group_user` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `id_group_user` (`id_group_user`),
     UNIQUE (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id_user`, `email`, `password`, `first_name`, `name`, `id_group_user`) VALUES
(1, 'lefevre@univ-angers.fr', '69cc906c0ef009ec7eb2ef2d01f91202', 'Claire','Lefevre', 1),
(2, 'chhel@univ-angers.fr', '7a35ff13dfead8a7000cd9a9eca4911c', 'Fabien', 'Chhel', 1),
(3, 'dorian.coffinet@univ-angers.fr', '1ec08faa91dbf7fb97658852bd8e0e74', 'Dorian','Coffinet', 4),
(4, 'mickael.fardilha@univ-angers.fr', 'fada8b5a72e9dd2dd4632e049f0bda4c', 'Mickaël','Fardilha', 4),
(5, 'thibault.gauthier@univ-angers.fr', '81f414698132a935acd41b78e891174f', 'Thibault','Gauthier', 4),
(6, 'raphael.pillie@univ-angers.fr', '0252bde464e787ed008f91b415ed847c', 'Raphaël','Pillié', 4),
(7, 'noname@univ-angers.fr', '499ddaad9df107bf7107a3e2c0064800', 'No','Name', 3);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `fk_schedule_group_user` FOREIGN KEY (`id_group_user`) REFERENCES `group_user` (`id_group_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_schedule_classroom` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id_classroom`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_schedule_subject` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_schedule_user_teacher` FOREIGN KEY (`id_user_teacher`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_group_user` FOREIGN KEY (`id_group_user`) REFERENCES `group_user` (`id_group_user`) ON DELETE CASCADE ON UPDATE CASCADE;
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;