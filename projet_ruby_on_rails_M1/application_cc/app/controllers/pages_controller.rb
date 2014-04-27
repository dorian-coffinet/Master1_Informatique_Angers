# encoding: UTF-8
require 'user_statistics'
class PagesController < ApplicationController
 def home
    @titre = "Accueil"
  end

  def contact
    @titre = "Contact"
  end

  def about
    @titre = "À Propos"
  end

  def help
    @titre = "Aide"
  end

  def statistiques
    @titre = "Statistiques"
    @users = User.all
    @user_statistics = UserStatistics.new(@users)
  end
end
