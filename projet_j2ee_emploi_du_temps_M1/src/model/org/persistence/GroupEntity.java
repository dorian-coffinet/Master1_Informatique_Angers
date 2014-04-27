package model.org.persistence;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity correspondant à la table group_user
 * @author mickael
 *
 */
@Entity
@Table(name="group_user")
@NamedQueries
({
	@NamedQuery(name = "GroupEntity.findAll", query = "Select g From GroupEntity g order by g.name"),
	@NamedQuery(name = "GroupEntity.findAllName", query = "Select g.name From GroupEntity g order by g.name"),
	@NamedQuery(name = "GroupEntity.findById", query= "Select g FROM GroupEntity g WHERE g.id = :id")
})
public class GroupEntity implements Serializable
{
	//Serialization
	private static final long serialVersionUID = 1L;
	
	//Définition des colonnes de la table
	@Id
	@Column(name="id_group_user")
	private long id;
	@Column(name="name")
	private String name;
	
	
	/**
	 * Constructeur par defaut
	 */
	public GroupEntity() 
	{
		super();
	}

	/**
	 * Constructeur
	 * @param g
	 */
	public GroupEntity(GroupEntity g) 
	{
		super();
		this.name=g.getName();
	}

	/**
	 * Equals de l'entity groupe
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupEntity other = (GroupEntity) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * Getters and Setters
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
