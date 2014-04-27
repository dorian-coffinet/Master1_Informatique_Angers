# encoding: UTF-8
# == Schema Information
#
# Table name: users
#
#  id                     :integer          not null, primary key
#  nom                    :string(255)
#  email                  :string(255)
#  created_at             :datetime         not null
#  updated_at             :datetime         not null
#  dateNaissance          :date
#  poidsActuel            :integer
#  poidsIdeal             :integer
#  estSportif             :boolean
#  souhaitePratiquerSport :boolean
#  taille                 :integer
#

require 'spec_helper'

describe User do
	before(:each) do
    @date = Date.today
    @attr = { :nom => "New User", :email => "user@example.com", :dateNaissance => @date, :poidsActuel => 60,
              :poidsIdeal => 55, :taille => 170, :estSportif => :true, :souhaitePratiquerSport => :false, :cvpdf => nil}
  end

  it "devrait créer une nouvelle instance dotée des attributs valides" do
    User.create!(@attr)
  end

  it "exige un nom" do
    bad_guy = User.new(@attr.merge(:nom => ""))
    bad_guy.should_not be_valid
  end

  it "exige une adresse email" do
    no_email_user = User.new(@attr.merge(:email => ""))
    no_email_user.should_not be_valid
  end

  it "devrait rejeter les noms trop longs" do
    long_nom = "a" * 51
    long_nom_user = User.new(@attr.merge(:nom => long_nom))
    long_nom_user.should_not be_valid
  end

  it "devrait accepter une adresse email valide" do
    adresses = %w[user@foo.com THE_USER@foo.bar.org first.last@foo.jp]
    adresses.each do |address|
      valid_email_user = User.new(@attr.merge(:email => address))
      valid_email_user.should be_valid
    end
  end

  it "devrait rejeter une adresse email invalide" do
    adresses = %w[user@foo,com user_at_foo.org example.user@foo.]
    adresses.each do |address|
      invalid_email_user = User.new(@attr.merge(:email => address))
      invalid_email_user.should_not be_valid
    end
  end

  it "devrait rejeter un email double" do
    # Place un utilisateur avec un email donné dans la BD.
    User.create!(@attr)
    user_with_duplicate_email = User.new(@attr)
    user_with_duplicate_email.should_not be_valid
  end

  it "devrait rejeter une adresse email invalide jusqu'à la casse" do
    upcased_email = @attr[:email].upcase
    User.create!(@attr.merge(:email => upcased_email))
    user_with_duplicate_email = User.new(@attr)
    user_with_duplicate_email.should_not be_valid
  end

  it "exige un poidsActuel supérieur au poidsIdeal" do
    bad_guy = User.new(@attr.merge(:poidsActuel=> 55, :poidsIdeal => 70))
    bad_guy.should_not be_valid
  end

  it "doit ajouter un cv en pdf" do
    User.new@attr.merge(:cvpdf => File.new(Rails.root + 'spec/fixtures/files/cv_exemple.pdf'))
  end

end
