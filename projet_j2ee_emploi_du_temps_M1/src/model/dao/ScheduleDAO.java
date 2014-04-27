package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.org.persistence.ClassroomEntity;
import model.org.persistence.GroupEntity;
import model.org.persistence.ScheduleEntity;
import model.org.persistence.UserEntity;

/**
 * Surcouche afin de rendre plus propre les accès en base
 * Cette classe DAO concerne les transactions pour les horaires
 * @author thibault
 * 
 */
public class ScheduleDAO extends AbstractDAO<ScheduleEntity>
{
	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * @param id
	 */
	public ScheduleEntity getById(long id)
	{
		return getById(id, "ScheduleEntity.findById");
	}

	/**
	 * Surchage de la méthode abstraite pour lui préciser dans quel table trouver l'info
	 * 
	 */
	public List<ScheduleEntity> getAll()
	{
		return getAll("ScheduleEntity.findAll");
	}
	
	/**
	 * Methode getByGroup, permet de rechercher tout les schedules pour un group donnée
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> getAllByGroup(GroupEntity group)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findByGroup").setParameter("group",group);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	/**
	 * Methode getByGroup, permet de rechercher tout les schedules pour un group donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> getAllByGroupAndDay(GroupEntity group, String dayStart, String dayEnd)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findByGroupAndDay").setParameter("group",group).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	/**
	 * Methode getByGroup, permet de rechercher tout les schedules pour un group donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> getAllByClassroomAndDay(ClassroomEntity room, String dayStart, String dayEnd)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findByClassroomAndDay").setParameter("classroom",room).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	
	/**
	 * Methode getByGroup, permet de rechercher tout les schedules pour un group donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> getAllByGroupAndDayWithEtu(GroupEntity group, GroupEntity groupEtu, String dayStart, String dayEnd)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findByGroupAndDayWithEtu")
					.setParameter("group",group).setParameter("groupEtu",groupEtu).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	/**
	 * Methode findIfClassroomExist, permet de rechercher tout les schedules pour un classroom donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> findIfClassroomExist(ClassroomEntity classroom, String dayStart, String dayEnd, long id)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findIfClassroomExist").setParameter("classroom",classroom).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd).setParameter("id", id);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	/**
	 * Methode findIfUserTeacherExist, permet de rechercher tout les schedules pour un user donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> findIfUserTeacherExist(UserEntity user, String dayStart, String dayEnd, long id)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findIfUserTeacherExist").setParameter("userTeacher",user).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd).setParameter("id", id);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
	
	/**
	 * Methode findIfGroupExist, permet de rechercher tout les schedules pour un user donnée,date début et fin
	 * @return List<ScheduleEntity>
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntity> findIfGroupExist(GroupEntity group, String dayStart, String dayEnd, long id)
	{
		initEntityManager();
		List<ScheduleEntity> all = new ArrayList<ScheduleEntity>();
		try 
		{
			Query q=getEntityManager().createNamedQuery("ScheduleEntity.findIfGroupExist").setParameter("group",group).setParameter("dayStart", dayStart).setParameter("dayEnd",dayEnd).setParameter("id", id);
			all= (q.getResultList().size()!=0) ? (List<ScheduleEntity>) q.getResultList() : null ;
		} 
		finally 
		{
			getEntityManager().close();
		}
		return all;
	}
}
