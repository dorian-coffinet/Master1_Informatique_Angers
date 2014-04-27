# encoding: UTF-8
require 'spec_helper'

describe PagesController do
  render_views

  before(:each) do
    @base_title = "Simple App du Tutoriel Ruby on Rails"
    @date = Date.new(2001,2,3)
    @cv_exemple = Rack::Test::UploadedFile.new('spec/fixtures/files/cv_exemple.pdf')

    first  = User.create!({ :nom => "New User1", :email => "user1@example.com", :dateNaissance => @date, :poidsActuel => 70,
            :poidsIdeal => 65, :taille => 176, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => @cv_exemple})
    second = User.create!({ :nom => "New User2", :email => "user2@example.com", :dateNaissance => @date, :poidsActuel => 105,
            :poidsIdeal => 100, :taille => 190, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => nil})
    third  = User.create!({ :nom => "New User3", :email => "user3@example.com", :dateNaissance => @date, :poidsActuel => 60,
            :poidsIdeal => 55, :taille => 170, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => nil})

    @users = [first, second, third]
  end

  describe "GET 'home'" do
    it "devrait réussir" do
      get 'home'
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get 'home'
      response.should have_selector("title",
                        :content => @base_title + " | Accueil")
    end
  end

  describe "GET 'contact'" do
    it "devrait réussir" do
      get 'contact'
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get 'contact'
      response.should have_selector("title",
                        :content => @base_title + " | Contact")
    end
  end

  describe "GET 'about'" do
    it "devrait réussir" do
      get 'about'
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get 'about'
      response.should have_selector("title",
                        :content => @base_title + " | À Propos")
    end
  end

  describe "GET 'help'" do
    it "devrait réussir" do
      get 'help'
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get 'help'
      response.should have_selector("title",
                        :content => @base_title + " | Aide")
    end
  end

  describe "GET 'statistiques'" do
    it "devrait réussir" do
      get 'statistiques'
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get 'statistiques'
      response.should have_selector("title",
                        :content => @base_title + " | Statistiques")
    end

    it "devrait avoir le graphique poids ac/id" do
      get 'statistiques'
      response.should have_selector("div", :id => "graphique_repartition_poids_ac_ic")
    end

    it "devrait avoir le graphique repartition imc" do
      get 'statistiques'
      response.should have_selector("div", :id => "graphique_repartition_imc")
    end

    it "devrait avoir le graphique repartition imc" do
      get 'statistiques'
      response.should have_selector("div", :id => "graphique_box_plot_poids")
    end

  end
end
