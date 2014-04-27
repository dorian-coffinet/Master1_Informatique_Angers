package model.dao;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.Query;

import model.org.persistence.GroupEntity;
import model.org.persistence.UserEntity;

/**
 * Surcouche afin de rendre plus propre les accès en base
 * Cette classe DAO concerne les transactions pour les users
 * @author mickael
 *
 */
public class UserDAO extends AbstractDAO<UserEntity> 
{
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * @param id
	 */
	public UserEntity getById(long id) 
	{
		return getById(id, "UserEntity.findById");
	}
	
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * 
	 */
	public List<UserEntity> getAll()
	{
		return getAll("UserEntity.findAll");
	}
	
	/**
	 * Methode permetant de récupérer un user avec email et password 
	 * Utile pour la connexion
	 * @param email
	 * @param password
	 */
	public UserEntity getUserByEmailAndPwd(String email, String password)
	{
		initEntityManager();
		UserEntity user = new UserEntity();
		user.setEmail(email);
		user.setPassword(password);
		try 
		{
			Query q=getEntityManager().createNamedQuery("UserEntity.findByEmailAndPwd")
					.setParameter("email", email).setParameter("pwd", password);
			user= (q.getResultList().size()!=0) ? (UserEntity) q.getResultList().get(0) : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return user;
	}
	
	/**
	 * Méthode permettant de récupérer une map <idGroup,nameGroup>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TreeMap<Long,String> getAllTeacherForMap()
	{
		initEntityManager();
		TreeMap<Long,String> allGroupName = new TreeMap<Long,String>();
		List<UserEntity> listAll;
		try 
		{
			GroupEntity group = new GroupEntity();
			group.setId(1);
			Query q=getEntityManager().createNamedQuery("UserEntity.findByGroup").setParameter("group", group);
			listAll= (q.getResultList().size()!=0) ? (List<UserEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		for (int i = 0; i < listAll.size(); i++) 
		{
			allGroupName.put(listAll.get(i).getId(), (listAll.get(i).getFirstName()+ " " +listAll.get(i).getName()));
		}			
		return allGroupName;
	}
	
	
	/**
	 * Méthode permettant de savoir si l'email existe
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean existEmailUser(String email)
	{
		boolean res = false;
		
		initEntityManager();
		List<UserEntity> user;
		try 
		{
			Query q=getEntityManager().createNamedQuery("UserEntity.findByEmail").setParameter("email", email);
			user= (q.getResultList().size()!=0) ? (List<UserEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		
		if(user!=null)
		{
			res=true;
		}
		return res;
	}
}