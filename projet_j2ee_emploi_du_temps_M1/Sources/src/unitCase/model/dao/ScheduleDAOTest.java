package unitCase.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.ScheduleDAO;
import model.org.persistence.ClassroomEntity;
import model.org.persistence.GroupEntity;
import model.org.persistence.ScheduleEntity;
import model.org.persistence.SubjectEntity;
import model.org.persistence.UserEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test du dao des horaires
 * 
 * @author mickael
 * 
 */
public class ScheduleDAOTest
{
	// déclaration de l'objet DAO afin d'appeler les méthodes à tester
	private ScheduleDAO scheduleDAO = new ScheduleDAO();

	@Before
	public void insertion()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ScheduleEntity s = new ScheduleEntity();
		s.setId(0);
		s.setName("test");
		s.setComment("junit test save");
		s.setDayStart(simpleDateFormat.format(new Date()));
		s.setDayEnd(simpleDateFormat.format(new Date()));
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.setId(1);
		s.setClassroom(classroom);
		GroupEntity group = new GroupEntity();
		group.setId(1);
		s.setGroup(group);
		SubjectEntity subject = new SubjectEntity();
		subject.setId(1);
		s.setSubject(subject);
		UserEntity user = new UserEntity();
		user.setId(1);
		s.setUserTeacher(user);
		scheduleDAO.save(s);
	}
	
	/**
	 * Test de la méthode du DAO permetant de récupérer un schedule par son id
	 */
	@Test
	public void testGetById()
	{
		ScheduleEntity s = scheduleDAO.getById(0);
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (s == null));
	}

	/**
	 * Test de la méthode du DAO permetant de faire un update sur un schedule
	 */
	@Test
	public void testUpdate()
	{
		ScheduleEntity s = scheduleDAO.getById(0);
		s.setComment("junit update");
		scheduleDAO.update(s);
	}

	/**
	 * Test de la méthode du DAO permetant supprimer un schedule
	 */
	@Test
	public void testDelete()
	{
		List<ScheduleEntity> list = scheduleDAO.getAll();
		ScheduleEntity s = scheduleDAO.getById(list.get(list.size()-1).getId());
		scheduleDAO.delete(s);
	}

	/**
	 * Test de la méthode du DAO permettant d'enregstrer un schedule
	 */
	@Test
	public void testSave()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ScheduleEntity s = new ScheduleEntity();
		s.setId(0);
		s.setName("test");
		s.setComment("junit test save");
		s.setDayStart(simpleDateFormat.format(new Date()));
		s.setDayEnd(simpleDateFormat.format(new Date()));
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.setId(1);
		s.setClassroom(classroom);
		GroupEntity group = new GroupEntity();
		group.setId(1);
		s.setGroup(group);
		SubjectEntity subject = new SubjectEntity();
		subject.setId(1);
		s.setSubject(subject);
		UserEntity user = new UserEntity();
		user.setId(1);
		s.setUserTeacher(user);
		scheduleDAO.save(s);
		testDelete();
	}

	/**
	 * Test de la méthode du DAO permettant de récupérer tous les schedules
	 */
	@Test
	public void testGetAll()
	{
		List<ScheduleEntity> s = scheduleDAO.getAll();
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (s == null));
	}

	/**
	 * Test de la méthode du DAO permettant de récupérer tous les schedules par
	 * group
	 */
	@Test
	public void testGetAllByGroup()
	{
		GroupEntity group = new GroupEntity();
		group.setId(1);
		List<ScheduleEntity> s = scheduleDAO.getAllByGroup(group);
		Assert.assertFalse("Soit la base est vide, soit la méthode ne fonctionne pas", (s == null));
	}
	
	@After
	public void rollBackFunction()
	{
		testDelete();
	}
}
