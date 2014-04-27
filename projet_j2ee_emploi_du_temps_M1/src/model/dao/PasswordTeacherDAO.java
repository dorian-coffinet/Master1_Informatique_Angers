package model.dao;

import java.util.List;

import javax.persistence.Query;

import model.org.persistence.PasswordTeacherEntity;


/**
 * Surcouche afin de rendre plus propre les accès en base
 * Cette classe DAO concerne les transaction pour le passwordTeacher
 * @author mickael
 *
 */
public class PasswordTeacherDAO extends AbstractDAO<PasswordTeacherEntity>
{
	/**
	 * Methode permetant de récupérer le passwordTeacher
	 * Utile enregistrer un user du groupe enseignant
	 */
	public String getPasswordTeacher()
	{
		initEntityManager();
		String pwd="";
		try 
		{
			Query q=getEntityManager().createNamedQuery("PasswordTeacherEntity.get");
			pwd=  (q.getResultList().size()!=0) ? (String) q.getResultList().get(0) : null ;
		} finally 
		{
			getEntityManager().close();
		}
		return pwd;
	}
	
	public List<PasswordTeacherEntity>getAll()
	{
		return getAll("PasswordTeacherEntity.findAll");
	}
	
	/**
	 * Methode permetant de récupérer le passwordTeacher
	 * sous forme d'entity pour l'update
	 */
	public PasswordTeacherEntity get()
	{
		return getAll().get(0);
	}
	
	/**
	 * redéfinition de la méthode delete pour eviter la suppression
	 */
	public void delete(PasswordTeacherEntity object) {}
	
	/**
	 * redéfinition de la méthode save pour eviter la création
	 */
	public void save(PasswordTeacherEntity object){}
}