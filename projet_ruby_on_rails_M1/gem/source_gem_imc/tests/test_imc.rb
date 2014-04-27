# encoding: UTF-8
require 'test/unit'
require 'shoulda'
require_relative '../lib/imc'

class TestImc < Test::Unit::TestCase

	context "vérification des calculs bons de l'imc" do

		setup do
			@imc_denutrition = Imc.new(165,30)
			@imc_maigreur = Imc.new(173,53)
			@imc_normal = Imc.new(176,70)
			@imc_surpoids = Imc.new(177,89)
			@imc_obesite_moderee = Imc.new(177,98)
			@imc_obesite_severe = Imc.new(152,89)
			@imc_obesite_massive = Imc.new(191,150)
			
			@imc_null = Imc.new(nil,nil)
			@imc_error = Imc.new("toto",89)
		end

		should "retoune l'imc d'une personne en dénutrition" do
			assert_equal "dénutrition : 11.02", @imc_denutrition.calcul_imc
		end

		should "retoune l'imc d'une personne en maigreur" do
			assert_equal "maigreur : 17.71", @imc_maigreur.calcul_imc
		end

		should "retoune l'imc d'une personne normale" do
			assert_equal "normal : 22.6", @imc_normal.calcul_imc
		end

		should "retoune l'imc d'une personne en surpoids" do
			assert_equal "surpoids : 28.41", @imc_surpoids.calcul_imc
		end

		should "retoune l'imc d'une personne en obésité modérée" do
			assert_equal "obésité modérée : 31.28", @imc_obesite_moderee.calcul_imc
		end

		should "retoune l'imc d'une personne en obésité sévère" do
			assert_equal "obésité sévère : 38.52", @imc_obesite_severe.calcul_imc
		end

		should "retoune l'imc d'une personne en obésité massive" do
			assert_equal "obésité massive : 41.12", @imc_obesite_massive.calcul_imc
		end
	end

	context "vérification de de calculs faux de l'imc" do

		setup do	
			@imc_null = Imc.new(nil,nil)
			@imc_error = Imc.new("toto",89)
		end

		should "retoune un message d'erreur si le poids ou la taille ont une valeur null" do
			assert_equal "données insuffisantes", @imc_null.calcul_imc
		end

		should "retoune un message d'erreur si le poids ou la taille ont une valeur différente d'un nombre" do
			assert_equal "données insuffisantes", @imc_error.calcul_imc
		end
	end

end