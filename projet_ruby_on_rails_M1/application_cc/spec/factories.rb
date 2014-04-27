Factory.define :user do |user|

	@date = Date.new(2001,2,3)
	@cv_1 = Rack::Test::UploadedFile.new('db/files/cv_1.pdf')

  user.nom                    "Michael Hartl"
  user.email                  "mhartl@example.com"
  user.dateNaissance     		  @date    
  user.poidsActuel					  90
  user.poidsIdeal						  80
  user.taille                 190
  user.estSportif						  false
  user.souhaitePratiquerSport true
  user.cvpdf                  @cv_1

end