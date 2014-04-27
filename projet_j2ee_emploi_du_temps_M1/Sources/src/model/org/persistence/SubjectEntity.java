package model.org.persistence;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity correspondant à la table subject
 * @author raphael
 *
 */
@Entity
@Table(name = "subject")
@NamedQueries
({
    @NamedQuery(name = "SubjectEntity.findById", query = "SELECT s FROM SubjectEntity s where s.id = :id"),
    @NamedQuery(name = "SubjectEntity.findAllName", query = "SELECT s.name FROM SubjectEntity s"),
    @NamedQuery(name = "SubjectEntity.findAll", query = "SELECT s FROM SubjectEntity s ")
})
public class SubjectEntity implements Serializable 
{
	//Serialization
	private static final long serialVersionUID = 1L;

	//Définition des colonnes de la table 
	@Id
	@Column(name="id_subject")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="short_name")
	private String shortName;
	@Column(name="color")
	private String color;
	
	
	/**
	 * Getters and setters
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

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
	
}