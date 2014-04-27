prawn_document() do |pdf|

	pdf.text "Export de la liste des utilisateurs enregistrés", :size => 20, :align => :center

	pdf.move_down 20

	pdf.text "Créé le : " +  Time.now.strftime('%d-%m-%Y à %H:%M'), :style => :italic, :align => :center

	pdf.move_down 70

  @users.each do |user|
  		sportif = user.estSportif ? "oui" : "non"
  		souhaiteSport = user.souhaitePratiquerSport  ? "oui" : "non" 


			pdf.text "Je m'appelle " + user.nom + ", vous pouvez me contacter ici : <u><link href=\"mailto:" +
								user.email + ">" + user.email + "</link></u>. Je suis né(e) le " + user.dateNaissance.to_s +
								" et j'ai " + user.age.to_s + " ans.", :inline_format => true

			pdf.text "Je mesure " + user.taille.to_s + " cm, pour un poids de " + user.poidsActuel.to_s +
								" kg, mon imc est : " + user.imc.to_s + "."

			pdf.text "Je pratique un sport et je souhaite en faire d'autre. " if sportif == "oui" && souhaiteSport == "oui"
			pdf.text "Je pratique un sport et je ne souhaite pas en faire d'autre. " if sportif == "oui" && souhaiteSport == "non"
			pdf.text "Je ne pratique pas un sport et je souhaite en pratiquer un. " if sportif == "non" && souhaiteSport == "oui"
			pdf.text "Je ne pratique pas un sport et je ne souhaite pas en pratiquer. " if sportif == "non" && souhaiteSport == "non"
			
			pdf.move_down 20

	end
end