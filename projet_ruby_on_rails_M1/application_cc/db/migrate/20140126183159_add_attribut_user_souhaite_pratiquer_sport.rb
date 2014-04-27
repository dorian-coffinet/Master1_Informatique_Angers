class AddAttributUserSouhaitePratiquerSport < ActiveRecord::Migration
  def up
  	add_column :users, :souhaitePratiquerSport, :boolean
  end

  def down
  	remove_column :users, :souhaitePratiquerSport
  end
end
