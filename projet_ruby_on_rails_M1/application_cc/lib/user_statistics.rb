class UserStatistics

	attr_accessor :poids_ac, :poids_id, :datas, :poids_ac_plot, :poids_id_plot

	#initilisation des variables
	def initialize(users)
		@users = users
		@poids_ac = Array.new
		@poids_ac_plot = Array.new
		@poids_id_plot = Array.new
		@poids_id = Array.new		
		@datas = Array.new

		poids_ac_users
		poids_id_users
		poids_ac_plot_user
		poids_id_plot_user
		repartition_imc
  end

  #pour tous les utilisateurs on va chercher les poids actuels
  def poids_ac_users
  	@users.each do |user|
			@poids_ac << user.poidsActuel
		end

		@poids_ac
  end
	
  #pour tous les utilisateurs on va chercher les poids idéaux
	def poids_id_users
		@users.each do |user|
			@poids_id << user.poidsIdeal
		end

		@poids_id
  end

  def poids_ac_plot_user
  	user_ordinate = @poids_ac.sort

  	
		@poids_ac_plot << user_ordinate.first
		@poids_ac_plot << user_ordinate[user_ordinate.length/4]
		@poids_ac_plot << median(user_ordinate)
		@poids_ac_plot << user_ordinate[(user_ordinate.length*3)/4]
		@poids_ac_plot << user_ordinate.last

  end

  def poids_id_plot_user
  	user_ordinate = @poids_id.sort

  	
		@poids_id_plot << user_ordinate.first
		@poids_id_plot << user_ordinate[user_ordinate.length/4]
		@poids_id_plot << median(user_ordinate)
		@poids_id_plot << user_ordinate[(user_ordinate.length*3)/4]
		@poids_id_plot << user_ordinate.last
		
  end

# [30, 60, 78, 98, 150]
# [29, 55, 68, 80, 100]

	def median(user_ordinate)
	  len = user_ordinate.length
	  return (user_ordinate[(len - 1) / 2] + user_ordinate[len / 2]) / 2
	end

  #permet d'avoir la répartition des imc des utilisateurs 
  def repartition_imc
		repartition_imc = []
		#pour tous les utilisateurs on va chercher le status de l'imc
		@users.each do |user|
			repartition_imc << user.imc.split(/\s:\s/)[0]
		end

		#pour la répartiton on compte les occurences de chaque imc
		counts = Hash.new(0)
		for imc in repartition_imc
			counts[imc] += 1
		end

		#on formate les imc pour le graphique de highcharts de la forme [[key1,value1], [key2,value2]]
		counts.each do |k,v|
			@datas << [k,v]
		end

		@datas
  end
	
end