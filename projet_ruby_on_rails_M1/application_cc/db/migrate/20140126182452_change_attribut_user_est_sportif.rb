class ChangeAttributUserEstSportif < ActiveRecord::Migration
  def up
  	change_column :users, :estSportif, :boolean
  end

  def down
  	rails ActiveRecord::IrreversibleMigration
  end
end
