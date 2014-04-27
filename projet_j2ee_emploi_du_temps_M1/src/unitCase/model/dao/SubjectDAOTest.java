package unitCase.model.dao;


import java.util.List;
import java.util.TreeMap;

import model.dao.SubjectDAO;
import model.org.persistence.SubjectEntity;

import org.junit.Assert;
import org.junit.Test;


/**
 * Classe de test du dao des sujet
 * @author mickael
 *
 */
public class SubjectDAOTest 
{
	//déclaration des objets DAO afin d'appeler les méthodes à tester
	private SubjectDAO sdao = new SubjectDAO();
	
	/**
	 * Test de la méthode du DAO permetant de récupérer un user par son id
	 */
	@Test
	public void testGetById() 
	{
		SubjectEntity u= sdao.getById(3);
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (u==null));
	}
	
	/**
	 * Test de la méthode du DAO permetant de faire un update sur un sujet
	 */
	@Test
	public void testUpdate() 
	{
		SubjectEntity s= sdao.getById(3);
		s.setShortName("Partiel");
		sdao.update(s);
	}
	
	/**
	 * Test de la méthode du DAO permetant supprimer un sujet
	 */
	@Test
	public void testDelete() 
	{
		SubjectEntity s= sdao.getById(2);
		sdao.delete(s);
	}
	
	
	/**
	 * Test de la méthode du DAO permettant d'enregstrer un sujet
	 */
	@Test
	public void testSave() 
	{
		SubjectEntity s = new SubjectEntity() ;
		s.setColor("#BF00E6");
		s.setName("Travaux dirigés");
		s.setShortName("TD");
		sdao.save(s);
	}
	
	

	/**
	 * Test de la méthode du DAO permettant de récupérer tous les users
	 */
	@Test
	public void testGetAll()
	{
		List<SubjectEntity> s= sdao.getAll();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (s==null));
	}
	
	/**
	 * Méthode permettant de récupérer une map <idSubject,nameSubject>
	 * 
	 */
	@Test
	public void testGetAllTeacherForMap() 
	{
		TreeMap<Long, String> res = sdao.getAllSubjectForMap();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (res==null));
	}
}
