class AddAttachmentCvpdfToUsers < ActiveRecord::Migration
  def self.up
    change_table :users do |t|
      t.attachment :cvpdf
    end
  end

  def self.down
    drop_attached_file :users, :cvpdf
  end
end
