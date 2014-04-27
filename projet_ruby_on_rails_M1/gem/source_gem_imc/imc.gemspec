Gem::Specification.new do |s|
	s.name 									= "imc"
	s.summary 							= "Calcule de l'imc"
	s.description 					= File.read(File.join(File.dirname(__FILE__), 'README'))
	s.version 							= "0.0.1"
	s.author 								= "Dorian Coffinet"
	s.email 								= "dorian.coffinet@univ-angers.fr"
	s.homepage							= "http://forge.info.univ-angers.fr/~gh/"
	s.platform 							= Gem::Platform::RUBY
	s.required_ruby_version = '>=1.9'
	s.files 								= ["lib/imc.rb"]
	s.test_files						= ["tests/test_imc.rb"]
	s.has_rdoc 							= true
end