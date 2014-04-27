package model.org.persistence;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity correspondant à la table user
 * @author mickael
 *
 */
@Entity
@Table(name = "user")
@NamedQueries
({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByEmailAndPwd", query = "SELECT u FROM UserEntity u WHERE u.email = :email and u.password = :pwd"),
    @NamedQuery(name = "UserEntity.findByGroup", query = "SELECT u FROM UserEntity u WHERE u.group = :group")
})
public class UserEntity implements Serializable 
{
	//Serialization
	private static final long serialVersionUID = 1L;

	//Définition des colonnes de la table 
	@Id
	@Column(name="id_user")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="first_name")
	private String firstName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@ManyToOne
	@JoinColumn(name="id_group_user")
	private GroupEntity group;

	/**
	 * Constructeur par defaut
	 */
	public UserEntity() 
	{
		super();
	}

	/**
	 * Constructeur avec params
	 * @param u
	 */
	public UserEntity(UserEntity u) 
	{
		super();
		this.name = u.getName();
		this.firstName = u.getFirstName();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.group=new GroupEntity(u.getGroupe());
	}
	
	
	/**
	 * Getters et setters de cette entité user
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
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public GroupEntity getGroupe()
	{
		return group;
	}

	public void setGroupe(GroupEntity groupe)
	{
		this.group = groupe;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
}