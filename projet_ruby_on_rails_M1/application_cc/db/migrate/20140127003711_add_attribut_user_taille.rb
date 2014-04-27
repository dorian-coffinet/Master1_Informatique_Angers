class AddAttributUserTaille < ActiveRecord::Migration
  def up
  	add_column :users, :taille, :integer
  end

  def down
  	remove_column :users, :taille
  end
end
