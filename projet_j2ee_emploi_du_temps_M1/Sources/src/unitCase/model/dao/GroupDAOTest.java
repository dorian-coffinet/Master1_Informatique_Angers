package unitCase.model.dao;

import java.util.List;
import java.util.TreeMap;

import model.dao.GroupDAO;
import model.org.persistence.GroupEntity;

import org.junit.Assert;
import org.junit.Test;


/**
 * Classe de test du dao des groupes
 * @author mickael
 *
 */
public class GroupDAOTest 
{
	//déclaration de l'objet DAO afin d'appeler les méthodes à tester
	private GroupDAO groupDAO = new GroupDAO();

	/**
	 * Test de la méthode du DAO permetant de récupérer un groupe par son id
	 */
	@Test
	public void testGetById() 
	{
		GroupEntity g= groupDAO.getById(1);
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (g==null));
	}
	
	/**
	 * Test de la méthode du DAO permetant de faire un update sur un groupe
	 */
	@Test
	public void testUpdate() 
	{
		GroupEntity g= groupDAO.getById(4);
		g.setName("M1 Info");
		groupDAO.update(g);
	}
	
	/**
	 * Test de la méthode du DAO permetant supprimer un groupe
	 */
	@Test
	public void testDelete() 
	{
		GroupEntity g= groupDAO.getById(3);
		groupDAO.delete(g);
	}
	
	
	/**
	 * Test de la méthode du DAO permettant d'enregstrer un groupe
	 */
	@Test
	public void testSave() 
	{
		GroupEntity g = new GroupEntity() ;
		g.setName("L3 info");
		groupDAO.save(g);
	}
	

	/**
	 * Test de la méthode du DAO permettant de récupérer tous les users
	 */
	@Test
	public void testGetAll()
	{
		List<GroupEntity> g= groupDAO.getAll();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (g==null));
	}
	
	/**
	 * Test de la méthode du DAO permettant de trouver tous les noms des groupes
	 */
	@Test
	public void testGetAllGroupName() 
	{
		List<String> u= groupDAO.getAllGroupName();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (u==null));
	}
	
	/**
	 * Test de la méthode du DAO permettant  de récupérer une map <idGroup,nameGroup>
	 */
	@Test
	public void testExistNameGroup() 
	{
		boolean res = groupDAO.existNameGroup("Enseignant");
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (!res));
	}
	
	/**
	 * Test de la méthode du DAO permettant  de récupérer une map <idGroup,nameGroup>
	 */
	@Test
	public void testGetAllGroupForMap() 
	{
		TreeMap<Long, String> res = groupDAO.getAllGroupForMap();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (res==null));
	}
}
