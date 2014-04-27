package model.dao;

import java.util.List;
import java.util.TreeMap;

import model.org.persistence.SubjectEntity;

/**
 * Surcouche afin de rendre plus propre les accès en base Cette classe DAO
 * concerne les transaction pour le subject
 * 
 * @author raphael
 * 
 */
public class SubjectDAO extends AbstractDAO<SubjectEntity>
{
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * @param id
	 */
	public SubjectEntity getById(long id) 
	{
		return getById(id, "SubjectEntity.findById");
	}
	
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * 
	 */
	public List<SubjectEntity> getAll()
	{
		return getAll("SubjectEntity.findAll");
	}

	
	/**
	 * Méthode permettant de récupérer une map <idSubject,nameSubject>
	 * 
	 */
	public TreeMap<Long,String> getAllSubjectForMap()
	{
		initEntityManager();
		TreeMap<Long,String> allGroupName = new TreeMap<Long,String>();
		List<SubjectEntity> listAll = getAll();
		for (int i = 0; i < listAll.size(); i++) 
		{
			allGroupName.put(listAll.get(i).getId(), listAll.get(i).getName());
		}			
		return allGroupName;
	}
}