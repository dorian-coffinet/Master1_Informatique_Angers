package model.dao;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.Query;

import model.org.persistence.GroupEntity;

/**
 * Surcouche afin de rendre plus propre les accès en base
 * Cette classe DAO concerne les transactions pour les groupes
 * @author mickael
 *
 */
public class GroupDAO extends AbstractDAO<GroupEntity>
{
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * @param id
	 */
	public GroupEntity getById(long id) 
	{
		return getById(id, "GroupEntity.findById");
	}
	
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * 
	 */
	public List<GroupEntity> getAll()
	{
		return getAll("GroupEntity.findAll");
	}
	
	/**
	 * Methode permetant de récupérer les noms de tous les groupes
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllGroupName()
	{
		initEntityManager();
		List<String> listGroupName;
		try
		{
			Query q = getEntityManager().createNamedQuery("GroupEntity.findAllName");
			listGroupName = (q.getResultList().size()!=0) ? (List<String>) q.getResultList() : null;
		} finally
		{
			getEntityManager().close();
		}
		return listGroupName;
	}
	
	/**
	 * Méthode permettant de récupérer une map <idGroup,nameGroup>
	 * 
	 */
	public TreeMap<Long,String> getAllGroupForMap()
	{
		initEntityManager();
		TreeMap<Long,String> allGroupName = new TreeMap<Long,String>();
		List<GroupEntity> listAll = getAll();
		for (int i = 0; i < listAll.size(); i++) 
		{
			allGroupName.put(listAll.get(i).getId(), listAll.get(i).getName());
		}			
		return allGroupName;
	}
	
	/**
	 * Méthode permettant de savoir si le nom du groupe existe deja 
	 * @param name
	 * @return
	 */
	public boolean existNameGroup(String name)
	{
		boolean res = false;
		
		List<String> listName= getAllGroupName();
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
