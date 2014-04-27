package model.org.persistence;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
@NamedQueries
({
    @NamedQuery(name = "ClassroomEntity.findAll", query = "SELECT cr FROM ClassroomEntity cr order by cr.name"),
    @NamedQuery(name = "ClassroomEntity.findAllName", query = "SELECT cr.name FROM ClassroomEntity cr"),
    @NamedQuery(name = "ClassroomEntity.findById", query = "SELECT cr FROM ClassroomEntity cr WHERE cr.id = :id")
})
public class ClassroomEntity implements Serializable 
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe
	 */
	public ClassroomEntity() 
	{
	}

	//Définition des colonnes de la table 
	@Id
	@Column(name="id_classroom")
	private long id;
	@Column(name="name")
	private String name;
	
	/**
	 * Getters et setters de cette entité classroom
	 * 
	 */
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}