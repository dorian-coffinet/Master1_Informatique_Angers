class AddAttributsUsers < ActiveRecord::Migration
  def up
  	add_column :users, :dateNaissance, :date
  	add_column :users, :poidsActuel, :integer
  	add_column :users, :poidsIdeal, :integer
  	add_column :users, :estSportif, :date
  end

  def down
  	remove_column :users, :dateNaissance
  	remove_column :users, :poidsActuel
  	remove_column :users, :poidsIdeal
  	remove_column :users, :estSportif
  end
end
