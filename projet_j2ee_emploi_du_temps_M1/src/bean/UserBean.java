package bean;

import java.util.TreeMap;

import model.org.persistence.UserEntity;

/**
 * Bean User
 * @author mickael
 *
 */
public class UserBean
{
	private long id;
	private long idGroup;
	private String firstName;
	private String name;
	private String email;
	private String password;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String passwordTeacher;
	private TreeMap<Long, String> mapGroup;

	/**
	 * Contructeur
	 */
	public UserBean() 
	{
		super();
		this.id=0;
		this.idGroup=0;
		this.firstName="";
		this.name="";
		this.email="";
		this.password="";
		this.currentPassword="";
		this.newPassword="";
		this.confirmPassword="";
		this.passwordTeacher="";
	}
	
	/**
	 * Méthode de conversion avec un userEntity en entrée et un userBean en sortie
	 * @param UserEntity
	 */
	public void convertEntityToBean(UserEntity userToConvert)
	{
		setId(userToConvert.getId());
		setEmail(userToConvert.getEmail());
		setFirstName(userToConvert.getFirstName());
		setName(userToConvert.getName());
		setIdGroup(userToConvert.getGroupe().getId());
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

	public String getConfirmPassword() 
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}

	public String getPasswordTeacher()
	{
		return passwordTeacher;
	}

	public void setPasswordTeacher(String passwordTeacher)
	{
		this.passwordTeacher = passwordTeacher;
	}

	public String getCurrentPassword() 
	{
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) 
	{
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() 
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword) 
	{
		this.newPassword = newPassword;
	}

	public long getIdGroup() 
	{
		return idGroup;
	}

	public void setIdGroup(long idGroup) 
	{
		this.idGroup = idGroup;
	}

	public TreeMap<Long, String> getMapGroup() 
	{
		return mapGroup;
	}

	public void setMapGroup(TreeMap<Long, String> mapGroup) 
	{
		this.mapGroup = mapGroup;
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
