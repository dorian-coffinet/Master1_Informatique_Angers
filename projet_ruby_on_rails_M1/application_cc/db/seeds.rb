User.delete_all

@cv_dc = Rack::Test::UploadedFile.new('db/files/cv_dc.pdf')
@cv_1 = Rack::Test::UploadedFile.new('db/files/cv_1.pdf')
@cv_2 = Rack::Test::UploadedFile.new('db/files/cv_2.pdf')
@cv_3 = Rack::Test::UploadedFile.new('db/files/cv_3.pdf')
@cv_4 = Rack::Test::UploadedFile.new('db/files/cv_4.pdf')

User.create!({ :nom => "Dorian Coffinet",
	:email => "dorian.coffinet@gmail.com",
	:dateNaissance => Date.new(1989,03,22),
	:poidsActuel => 60,
	:poidsIdeal => 55,
	:taille => 177,
	:estSportif => false,
	:souhaitePratiquerSport => true,
	:cvpdf => @cv_dc})

User.create!({ :nom => "Jean Dupont",
	:email => "jean.dupond@example.com",
	:dateNaissance => Date.new(2001,2,3),
	:poidsActuel => 70,
  :poidsIdeal => 65,
  :taille => 176,
  :estSportif => false,
  :souhaitePratiquerSport => true,
  :cvpdf => @cv_1})

User.create!({ :nom => "Paul Pierre",
	:email => "paul@dom.fr",
	:dateNaissance => Date.new(1989,8,9),
	:poidsActuel => 105,
	:poidsIdeal => 100,
	:taille => 190,
	:estSportif => true,
	:souhaitePratiquerSport => false,
	:cvpdf => nil})

User.create!({ :nom => "Pierre Lafeuille",
	:email => "pierre.lepape@mail.eu",
	:dateNaissance => Date.new(1977,6,5),
	:poidsActuel => 30,
	:poidsIdeal => 29,
	:taille => 165,
	:estSportif => false,
	:souhaitePratiquerSport => false,
	:cvpdf => @cv_2})

User.create!({ :nom => "Sophie Bois",
	:email => "soso.38@boite.org",
	:dateNaissance => Date.new(1947,2,12),
	:poidsActuel => 53,
	:poidsIdeal => 51,
	:taille => 173,
	:estSportif => false,
	:souhaitePratiquerSport => true,
	:cvpdf => @cv_3})

User.create!({ :nom => "Marie Du Pape",
	:email => "marie.help@help.com",
	:dateNaissance => Date.new(1967,4,25),
	:poidsActuel => 98,
	:poidsIdeal => 68,
	:taille => 177,
	:estSportif => false,
	:souhaitePratiquerSport => true,
	:cvpdf => nil})

User.create!({ :nom => "Julie Patoulatchi",
	:email => "juju.job@entreprise.de",
	:dateNaissance => Date.new(1953,12,16),
	:poidsActuel => 89,
	:poidsIdeal => 55,
	:taille => 152,
	:estSportif => true,
	:souhaitePratiquerSport => false,
	:cvpdf => @cv_4})

User.create!({ :nom => "Toto MaidIn",
	:email => "toto@faitdu.velo",
	:dateNaissance => Date.new(1998,10,17),
	:poidsActuel => 150,
	:poidsIdeal => 80,
	:taille => 191,
	:estSportif => false,
	:souhaitePratiquerSport => true,
	:cvpdf => nil})

User.create!({ :nom => "Benoit Martin",
	:email => "benoit.martin@test.eu",
	:dateNaissance => Date.new(1965,7,4),
	:poidsActuel => 78,
	:poidsIdeal => 75,
	:taille => 186,
	:estSportif => true,
	:souhaitePratiquerSport => false,
	:cvpdf => nil})

User.create!({ :nom => "Jean-Eude Camino",
	:email => "j.e@camino.el",
	:dateNaissance => Date.new(1945,9,26),
	:poidsActuel => 85,
	:poidsIdeal => 80,
	:taille => 165,
	:estSportif => false,
	:souhaitePratiquerSport => false,
	:cvpdf => nil})

