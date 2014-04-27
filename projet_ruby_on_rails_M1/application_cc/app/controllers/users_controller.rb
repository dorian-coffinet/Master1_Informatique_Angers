# encoding: UTF-8
require 'user_statistics'

class UsersController < ApplicationController

  def new
    @user = User.new
    @titre = "Inscription"
  end

  def show
    @user = User.find(params[:id])
  end

  def index
    @titre = "Liste des utilisateurs"
    @users = User.all
    
    respond_to do |format|
      format.html
      format.pdf
    end
  end

	def create
    @user = User.new(params[:user])
    if @user.save
      flash[:success] = "Inscription de l'utilisateur réussie!"
      redirect_to @user
    else
      @titre = "Inscription"
      render 'new'
    end
  end

  def nonsportif
    @users = User.where(estSportif: false, souhaitePratiquerSport: true)
    @titre = "Liste des utilisateurs non sportif"
    render 'index'
  end

end
