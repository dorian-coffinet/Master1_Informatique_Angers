package model.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Classe abstraire des DAO evitant la duplication du code et des méthode et 
 * permettant également l'homogénéisation du code
 * @author mickael
 *
 * @param <T>
 */
public class AbstractDAO<T>
{
	//nom de la database
	private final static String JPA_DATABASE = "ProjetEDT";
	//Cette entité permet d'acceder aux tables
	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * Méthode permettant de récupérer tous les elements de la table
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected T getById(long id, String tableMethode) 
	{
		T entity;
		initEntityManager();
		try
		{
			entityManager = Persistence.createEntityManagerFactory(JPA_DATABASE).createEntityManager();
			Query q = entityManager.createNamedQuery(tableMethode).setParameter("id", id);
			entity = (q.getResultList().size()!=0) ? (T) q.getResultList().get(0) : null;
		} finally
		{
			entityManager.close();
		}
		return entity;
	}

	
	/**
	 * Méthode permettant de récupérer toutes les occurences
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(String tableMethode)
	{
		initEntityManager();
		List<T> all = new ArrayList<T>();
		try 
		{
			Query q=getEntityManager().createNamedQuery(tableMethode);
			all= (q.getResultList().size()!=0) ? (List<T>) q.getResultList() : null ;
		} 
		finally 
		{
			entityManager.close();
		}
		return all;
	}
	
	
	
	/**
	 * Methode permetant de sauvegarder un objet
	 * @param email
	 * @param password
	 */
	public void save(T object)
	{
		initEntityManager();
		try 
		{
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
		} 
		finally 
		{
			entityManager.close();
		}
	}
	
	/**
	 * Permet de supprimer un objet
	 * @param object 
	 */
	public void delete(T object) 
	{
		initEntityManager();
		try 
		{
			entityManager.getTransaction().begin();
			object = entityManager.merge(object);
			entityManager.remove(object);
			entityManager.getTransaction().commit();
		} 
		finally 
		{
			entityManager.close();
		}
	}
		
	/**
	 * Permet d'update un objet
	 * @param object
	 */
	public void update(T object) 
	{
		initEntityManager();
		try 
		{
			entityManager.getTransaction().begin();
			object = entityManager.merge(object);
			entityManager.getTransaction().commit();
		} 
		finally 
		{
			entityManager.close();
		}
	}


	protected void initEntityManager()
	{
		entityManager = Persistence.createEntityManagerFactory(JPA_DATABASE).createEntityManager();
	}
	
	/**
	 * Getters and Setters
	 * 
	 */

	public EntityManager getEntityManager() 
	{
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) 
	{
		this.entityManager = entityManager;
	}
}
