# encoding: UTF-8

# = Classe pour calculer l'imc
# == Ne se veut pas exceptionnelle mais elle seulement là pour tester la création d'une et l'utilisation de la RDoc

class Imc
	attr_accessor :taille_imc, :poids_imc
	
	#	Initialisation de la classe avec le poids et la taille pour le calcul de l'imc
	def initialize(taille, poids)
		@taille_imc = taille
		@poids_imc = poids
	end

	# Calcul de l'imc suivant le poids et la taille
	def calcul_imc
		if !(@taille_imc.is_a? Fixnum) || !(@poids_imc.is_a? Fixnum) then
			imc_status = "données insuffisantes"
		else
			taille_in_metters = (@taille_imc.to_f/100)
			imc = (@poids_imc / (taille_in_metters*taille_in_metters)).round(2)

			imc_status = case imc
				when 0...16 then "dénutrition"
				when 16...18 then "maigreur"
				when 18...25 then "normal"
				when 25...30 then "surpoids"
				when 30...35 then "obésité modérée"
				when 35...40 then "obésité sévère"
				else "obésité massive"	
				end
			imc_status += " : #{imc}"
		end
	end
end