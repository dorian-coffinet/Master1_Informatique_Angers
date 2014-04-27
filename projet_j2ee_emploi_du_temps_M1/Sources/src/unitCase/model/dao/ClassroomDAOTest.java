package unitCase.model.dao;

import java.util.List;
import java.util.TreeMap;

import model.dao.ClassroomDAO;
import model.org.persistence.ClassroomEntity;

import org.junit.Assert;
import org.junit.Test;


/**
 * Classe de test du dao des salles
 * @author mickael
 *
 */
public class ClassroomDAOTest 
{

	//déclaration de l'objet DAO afin d'appeler les méthodes à tester
	private ClassroomDAO classroomDAO = new ClassroomDAO();
	
	/**
	 * Test de la méthode du DAO permetant de récupérer un user par son id
	 */
	@Test
	public void testGetById() 
	{
		ClassroomEntity u= classroomDAO.getById(1);
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (u==null));
	}
	
	/**
	 * Test de la méthode du DAO permetant de faire un update sur un user
	 */
	@Test
	public void testUpdate() 
	{
		ClassroomEntity c= classroomDAO.getById(1);
		c.setName("A009");
		classroomDAO.update(c);
	}
	
	/**
	 * Test de la méthode du DAO permetant supprimer un user
	 */
	@Test
	public void testDelete() 
	{
		ClassroomEntity c= classroomDAO.getById(2);
		classroomDAO.delete(c);
	}
	
	
	/**
	 * Test de la méthode du DAO permettant d'enregstrer un user
	 */
	@Test
	public void testSave() 
	{
		ClassroomEntity c = new ClassroomEntity();
		c.setName("C203");
		classroomDAO.save(c);
	}
	

	/**
	 * Test de la méthode du DAO permettant de récupérer tous les users
	 */
	@Test
	public void testGetAll()
	{
		List<ClassroomEntity> c= classroomDAO.getAll();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (c==null));
	}
	
	
	/**
	 * Test de la méthode du DAO permettant de trouver tous les noms des salles
	 */
	@Test
	public void testGetAllClassroomName() 
	{
		List<String> u= classroomDAO.getAllClassroomName();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (u==null));
	}
	
	
	/**
	 * Test de la méthode du DAO permettant de récupérer une map <idClassroom,nameClassroom>
	 */
	@Test
	public void testGetAllClassroomForMap() 
	{
		TreeMap<Long, String> u= classroomDAO.getAllClassroomForMap();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (u==null));
	}
	
	/**
	 * Test de la méthode du DAO permettant  de savoir si le nom d'une salle existe
	 */
	@Test
	public void testExistNameGroup() 
	{
		boolean res = classroomDAO.existNameClassroom("A005");
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (!res));
	}
}
