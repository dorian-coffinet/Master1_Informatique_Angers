package model.dao;

import java.util.List;
import java.util.TreeMap;


import javax.persistence.Query;

import model.org.persistence.ClassroomEntity;

/**
 * Surcouche afin de rendre plus propre les accès en base Cette classe DAO
 * concerne les transaction pour classroom
 * 
 * @author raphael
 * 
 */
public class ClassroomDAO extends AbstractDAO<ClassroomEntity>
{
	
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * @param id
	 */
	public ClassroomEntity getById(long id) 
	{
		return getById(id, "ClassroomEntity.findById");
	}
	
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * 
	 */
	public List<ClassroomEntity> getAll()
	{
		return getAll("ClassroomEntity.findAll");
	}
	
	/**
	 * Méthode permettant de récupérer la liste de tous les noms des salles
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllClassroomName()
	{
		initEntityManager();
		List<String> listClassroomName;
		try
		{
			Query q = getEntityManager().createNamedQuery("ClassroomEntity.findAllName");
			listClassroomName = (q.getResultList().size()!=0) ? (List<String>) q.getResultList() : null ;
		} finally
		{
			getEntityManager().close();
		}
		return listClassroomName;
	}

	/**
	 * Méthode permettant de récupérer une map <idClassroom,nameClassroom>
	 * 
	 */
	public TreeMap<Long,String> getAllClassroomForMap()
	{
		initEntityManager();
		TreeMap<Long,String> allClassroomName = new TreeMap<Long,String>();
		List<ClassroomEntity> listAll = getAll();
		for (int i = 0; i < listAll.size(); i++) 
		{
			allClassroomName.put(listAll.get(i).getId(), listAll.get(i).getName());
		}			
		return allClassroomName;
	}

	/**
	 * Méthode permettant de savoir si le nom de la classroom existe deja 
	 * @param name
	 * @return
	 */
	public boolean existNameClassroom(String name)
	{
		boolean res = false;
		
		List<String> listName= getAllClassroomName();
		for (int i = 0; i < listName.size(); i++) 
		{
			if(listName.get(i)!=null && listName.get(i).equals(name))
			{
				res=true;
			}
		}
		return res;
	}
}