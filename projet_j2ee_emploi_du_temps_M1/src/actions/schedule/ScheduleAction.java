package actions.schedule;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import model.dao.ClassroomDAO;
import model.dao.GroupDAO;
import model.dao.ScheduleDAO;
import model.dao.SubjectDAO;
import model.dao.UserDAO;
import model.org.persistence.ClassroomEntity;
import model.org.persistence.GroupEntity;
import model.org.persistence.ScheduleEntity;
import model.org.persistence.UserEntity;

import org.apache.struts2.interceptor.validation.SkipValidation;

import actions.abstractAction.AbstractAction;
import bean.ScheduleBean;

public class ScheduleAction extends AbstractAction
{
	// Serialization
	private static final long serialVersionUID = 1L;
	private long id;

	// bean de formulaire permettant le transfere des informations
	private ScheduleBean scheduleBean;
	private ArrayList<ScheduleBean> listScheduleBean;

	// déclaration et initialisation des DAO
	private GroupDAO gdao = new GroupDAO();
	private ClassroomDAO cdao = new ClassroomDAO();
	private UserDAO udao = new UserDAO();
	private SubjectDAO sbdao = new SubjectDAO();
	private ScheduleDAO sdao = new ScheduleDAO();
	
	//map d'affichage des listes déroulantes
	private TreeMap<Long, String> mapGroup;
	private TreeMap<Long, String> mapClassroom;
	private TreeMap<Long, String> mapTeacher;
	private TreeMap<Long, String> mapSubject;
	

	/**
	 * Execution de l'ajout d'un horaire
	 */
	public String execute()
	{
		forward = FORWARD_SUCCESS;
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		try
		{
			ScheduleEntity scheduleEntity = new ScheduleEntity();
			convertCurrentBeanToEntity(scheduleEntity);
			
			scheduleDAO.save(scheduleEntity);
		} catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant d'update une horaire
	 * 
	 */
	public String updateSchedule()
	{
		forward = FORWARD_SUCCESS;
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		try
		{
			ScheduleEntity scheduleEntity = new ScheduleEntity();
			scheduleEntity.setId(this.scheduleBean.getId());
			convertCurrentBeanToEntity(scheduleEntity);
			scheduleDAO.update(scheduleEntity);
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de supprimer un horaire
	 * 
	 */
	@SkipValidation
	public String deleteSchedule()
	{
		// Sauf si il y a erreur, le traitement est considéré comme étant un
		// succès
		forward = FORWARD_SUCCESS;
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		try
		{
			ScheduleEntity scheduleEntity = scheduleDAO.getById(this.id);
			scheduleDAO.delete(scheduleEntity);
		} catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant d'afficher les horaires
	 * 
	 * @return
	 */
	@SkipValidation
	public String showSchedule()
	{
		forward = FORWARD_SUCCESS;
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		this.listScheduleBean = new ArrayList<ScheduleBean>();
		try
		{
			List<ScheduleEntity> listScheduleEntity = scheduleDAO.getAll();
			if (listScheduleEntity == null)
			{
				listScheduleEntity = new ArrayList<>();
			}
			for (ScheduleEntity scheduleEntity : listScheduleEntity)
			{
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.convertEntityToBean(scheduleEntity);
				scheduleBean.setMapGroup(gdao.getAllGroupForMap());
				scheduleBean.setMapClassroom(cdao.getAllClassroomForMap());
				scheduleBean.setMapSubject(sbdao.getAllSubjectForMap());
				scheduleBean.setMapTeacher(udao.getAllTeacherForMap());
				this.listScheduleBean.add(scheduleBean);
			}
		} catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de lancer la page d'ajout d'horaire en initialisant la
	 * liste des groupes
	 * 
	 * @return
	 */
	@SkipValidation
	public String openScheduleForm()
	{
		forward = FORWARD_SUCCESS;
		this.mapGroup = this.gdao.getAllGroupForMap();
		this.mapClassroom = this.cdao.getAllClassroomForMap();
		this.mapTeacher = this.udao.getAllTeacherForMap();
		this.mapSubject = this.sbdao.getAllSubjectForMap();
		return forward;
	}

	/**
	 * Méthode permettant de récupérer l'horaire selectionné pour l'afficher en mode modification
	 * 
	 */
	@SkipValidation
	public String getSchedule()
	{
		forward = FORWARD_SUCCESS;

		try
		{
			ScheduleEntity se = sdao.getById(this.getId());
			this.scheduleBean = new ScheduleBean();
			this.scheduleBean.convertEntityToBean(se);
			scheduleBean.setMapGroup(gdao.getAllGroupForMap());
			scheduleBean.setMapClassroom(cdao.getAllClassroomForMap());
			scheduleBean.setMapSubject(sbdao.getAllSubjectForMap());
			scheduleBean.setMapTeacher(udao.getAllTeacherForMap());
			this.mapGroup = this.gdao.getAllGroupForMap();
			this.mapClassroom = this.cdao.getAllClassroomForMap();
			this.mapTeacher = this.udao.getAllTeacherForMap();
			this.mapSubject = this.sbdao.getAllSubjectForMap();
		} catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de valider les champs du formulaire
	 */
	public void validate()
	{
		if (scheduleBean != null)
		{
			if ("".equals(scheduleBean.getName()))
			{
				addFieldError("error.name", getText("validator.field.empty"));
			}
			try
			{
				if (scheduleBean.getDayStart() == null || "".equals(scheduleBean.getDayStart()))
				{
					addFieldError("error.dayStart", getText("validator.field.empty"));
				}
			} catch (ParseException e)
			{
				addFieldError("error.dayStart", getText("validator.field.date.wrong"));
			}
			try
			{
				if (scheduleBean.getDayEnd() == null || "".equals(scheduleBean.getDayEnd()))
				{
					addFieldError("error.dayEnd", getText("validator.field.empty"));
				} else
				{
					if (scheduleBean.getDayStart() != null && !"".equals(scheduleBean.getDayStart()))
					{
						String dayStart = scheduleBean.getDayStart();
						String dayEnd = scheduleBean.getDayEnd();
						Date dayStartDate = SIMPLE_DATE_FORMAT.parse(dayStart);
						Date dayEndDate = SIMPLE_DATE_FORMAT.parse(dayEnd);
						if (!dayStartDate.before(dayEndDate))
						{
							addFieldError("error.dayEnd", getText("validator.field.date.EndError"));
						}
					}
				}
			} catch (ParseException e)
			{
				addFieldError("error.dayEnd", getText("validator.field.date.wrong"));
			}
			// findIfClassroomExist
			try
			{
				if (!(scheduleBean.getDayEnd() == null || "".equals(scheduleBean.getDayEnd())) && !(scheduleBean.getDayEnd() == null || "".equals(scheduleBean.getDayEnd())))
				{
					String dayStart = scheduleBean.getDayStart();
					String dayEnd = scheduleBean.getDayEnd();
					ClassroomEntity classroom = cdao.getById(this.scheduleBean.getIdClassroom());
					GroupEntity group = gdao.getById(this.scheduleBean.getIdGroup());
					UserEntity user = udao.getById(this.scheduleBean.getIdTeacher());
					
					List<ScheduleEntity> listClassroom = sdao.findIfClassroomExist(classroom, dayStart, dayEnd, this.scheduleBean.getId());
					List<ScheduleEntity> listGroup = sdao.findIfGroupExist(group, dayStart, dayEnd, this.scheduleBean.getId());
					List<ScheduleEntity> listUser = sdao.findIfUserTeacherExist(user, dayStart, dayEnd, this.scheduleBean.getId());
					if(this.scheduleBean.getIdGroup()>2)
					{
						GroupEntity groupEtu = gdao.getById(GROUP_ID_ETU);
						List<ScheduleEntity> listEtud = sdao.findIfGroupExist(groupEtu, dayStart, dayEnd, this.scheduleBean.getId());
						if (listEtud != null && listEtud.size() != 0)
						{
							addFieldError("error.group", getText("validator.group.already.use.byEtu"));
						}
					}
					if (listClassroom != null && listClassroom.size() != 0)
					{
						addFieldError("error.classroom", getText("validator.classromm.already.use"));
					}
					if (listGroup != null && listGroup.size() != 0)
					{
						addFieldError("error.group", getText("validator.group.already.use"));
					}
					if (listUser != null && listUser.size() != 0)
					{
						addFieldError("error.userTeacher", getText("validator.userTeacher.already.use"));
					}
				}
			} catch (ParseException e)
			{
			}

		}

		this.mapGroup = this.gdao.getAllGroupForMap();
		this.mapClassroom = this.cdao.getAllClassroomForMap();
		this.mapTeacher = this.udao.getAllTeacherForMap();
		this.mapSubject = this.sbdao.getAllSubjectForMap();
		scheduleBean.setMapGroup(gdao.getAllGroupForMap());
		scheduleBean.setMapClassroom(cdao.getAllClassroomForMap());
		scheduleBean.setMapSubject(sbdao.getAllSubjectForMap());
		scheduleBean.setMapTeacher(udao.getAllTeacherForMap());
	}
	
	
	/**
	 * Méthode de conversion avec un ScheduleEntity en entrée et le bean de la classe
	 * @param UserEntity
	 * 
	 */
	public void convertCurrentBeanToEntity(ScheduleEntity se) throws ParseException
	{
		se.setUserTeacher(this.udao.getById(this.scheduleBean.getIdTeacher()));
		se.setSubject(this.sbdao.getById(this.scheduleBean.getIdSubject()));
		se.setClassroom(this.cdao.getById(this.scheduleBean.getIdClassroom()));
		se.setGroup(this.gdao.getById(this.scheduleBean.getIdGroup()));
		se.setDayStart(this.scheduleBean.getDayStart());
		se.setDayEnd(this.scheduleBean.getDayEnd());
		se.setName(this.scheduleBean.getName());
		se.setComment(this.scheduleBean.getComment());
	}
	
	
	/**
	 * Getters and setters
	 * 
	 */
	public ScheduleBean getScheduleBean()
	{
		return scheduleBean;
	}

	public void setScheduleBean(ScheduleBean scheduleBean)
	{
		this.scheduleBean = scheduleBean;
	}

	public ArrayList<ScheduleBean> getListScheduleBean()
	{
		return listScheduleBean;
	}

	public void setListScheduleBean(ArrayList<ScheduleBean> listScheduleBean)
	{
		this.listScheduleBean = listScheduleBean;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public TreeMap<Long, String> getMapClassroom() 
	{
		return mapClassroom;
	}

	public void setMapClassroom(TreeMap<Long, String> mapClassroom) 
	{
		this.mapClassroom = mapClassroom;
	}

	public TreeMap<Long, String> getMapGroup() 
	{
		return mapGroup;
	}

	public void setMapGroup(TreeMap<Long, String> mapGroup) 
	{
		this.mapGroup = mapGroup;
	}

	public TreeMap<Long, String> getMapTeacher()
	{
		return mapTeacher;
	}

	public void setMapTeacher(TreeMap<Long, String> mapTeacher) 
	{
		this.mapTeacher = mapTeacher;
	}

	public TreeMap<Long, String> getMapSubject()
	{
		return mapSubject;
	}

	public void setMapSubject(TreeMap<Long, String> mapSubject) 
	{
		this.mapSubject = mapSubject;
	}
}
