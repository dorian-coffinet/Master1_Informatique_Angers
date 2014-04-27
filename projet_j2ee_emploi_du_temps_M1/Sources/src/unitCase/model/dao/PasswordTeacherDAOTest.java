package unitCase.model.dao;

import java.util.List;

import model.dao.PasswordTeacherDAO;
import model.org.persistence.PasswordTeacherEntity;

import org.junit.Assert;
import org.junit.Test;


/**
 * Classe de test du dao des users
 * @author mickael
 *
 */
public class PasswordTeacherDAOTest 
{
	//déclaration des objets DAO afin d'appeler les méthodes à tester
	private PasswordTeacherDAO pdao = new PasswordTeacherDAO();
	
	/**
	 * Test de la méthode du DAO permetant de faire un update sur le password teacher
	 */
	@Test
	public void testUpdate() 
	{
		PasswordTeacherEntity p= pdao.get();
		p.setPassword("new_password");
		pdao.update(p);
	}
	
	/**
	 * Test de la méthode du DAO afin de vérifier que l'on ne peut pas
	 * sauvegarder de nouveau mot de passe prof
	 */
	@Test
	public void testSave() 
	{
		PasswordTeacherEntity p= new PasswordTeacherEntity();
		p.setPassword("new_password");
		pdao.save(p);
		List<PasswordTeacherEntity> l=pdao.getAll();
		Assert.assertFalse("Erreur si il y a eu un enregistrement supplémentaire", (l.size()!=1));
	}
	
	/**
	 * Test de la méthode du DAO afin de vérifier que l'on ne peut pas
	 * supprimer de nouveau mot de passe prof
	 */
	@Test
	public void testDelete() 
	{
		PasswordTeacherEntity p= pdao.get();
		pdao.delete(p);
		List<PasswordTeacherEntity> l=pdao.getAll();
		Assert.assertFalse("Erreur si il y a eu une suppression d'enregistrement", (l.size()!=1));
	}

	/**
	 * Test de la méthode du DAO permettant de récupérer le pwd teacher
	 * sous forme de string
	 */
	@Test
	public void testGetPasswordTeacher() 
	{
		String res = pdao.getPasswordTeacher();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (res==null || res.isEmpty()));
	}
	
	/**
	 * Test de la méthode du DAO permettant de récupérer le pwd teacher
	 * sous forme de DTO
	 */
	@Test
	public void testGet() 
	{
		PasswordTeacherEntity p = pdao.get();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (p==null));
	}
}
