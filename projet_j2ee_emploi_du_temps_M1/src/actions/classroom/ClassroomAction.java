package actions.classroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import model.dao.ClassroomDAO;
import model.org.persistence.ClassroomEntity;
import actions.abstractAction.AbstractAction;
import bean.ClassroomBean;


/**
 * Action sur les salles
 * @author mickael
 *
 */
public class ClassroomAction extends AbstractAction
{
	//Serialization
	private static final long serialVersionUID = 1L;
	//déclaration et initialisation des DAO
	private ClassroomDAO cdao = new ClassroomDAO();
	// bean de formulaire permettant le transfere des informations
	private ClassroomBean classroomBean;
	private ArrayList<ClassroomBean> listClassroomBean;
	
	//permet de récup l'id pour la suppression et l'affichage en modification
	private long id;

	/**
	 * Execution la sauvegarde d'une salle 
	 */
	public String execute()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			ClassroomEntity c = new ClassroomEntity();
			c.setName(classroomBean.getName());
			cdao.save(c);
		} 
		catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}
	
	/**
	 * Méthode permettant d'update une salle 
	 */
	public String updateClassroom()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			ClassroomEntity classroomEntity = cdao.getById(this.classroomBean.getId());
			classroomEntity.setName(this.classroomBean.getName());
			cdao.update(classroomEntity);
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}


	/**
	 * Méthode permettant de supprimer une salle
	 */
	@SkipValidation 
	public String deleteClassroom()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			ClassroomEntity classroomEntity = cdao.getById(this.id);
			cdao.delete(classroomEntity);
		} catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}
	
		
	/**
	 * Méthode permettant d'afficher la liste des salles 
	 * @return
	 */
	@SkipValidation 
	public String showClassroom()
	{
		forward = FORWARD_SUCCESS;
		this.listClassroomBean = new ArrayList<ClassroomBean>();
		try
		{
			List<ClassroomEntity> listClassroomEntity = cdao.getAll();
			for (ClassroomEntity classroomEntity : listClassroomEntity)
			{
				ClassroomBean classroomBean = new ClassroomBean();
				classroomBean.setId(classroomEntity.getId());
				classroomBean.setName(classroomEntity.getName());
				this.listClassroomBean.add(classroomBean);
			}
		} catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de récupérer la salle selectionné pour l'afficher en mode modification
	 * 
	 */
	@SkipValidation 
	public String getClassroom()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			ClassroomEntity classroomEntity = cdao.getById(this.id);
			this.classroomBean = new ClassroomBean();
			this.classroomBean.setId(classroomEntity.getId());
			this.classroomBean.setName(classroomEntity.getName());
			session.put(OLD_VALUE, classroomEntity.getName());
		} 
		catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permetant la validation des champs
	 */
	public void validate()
	{
		if (classroomBean != null)
		{
			if (classroomBean.getName().isEmpty())
			{
				addFieldError("error.name", getText("validator.field.empty"));
			}

			if(!classroomBean.getName().equals(session.get(OLD_VALUE)))
			{
				if(cdao.existNameClassroom(classroomBean.getName()))
					addFieldError("error.name",  getText("validator.classroom.exist"));
			}
			
		}
	}

	/**
	 * Getters and setters
	 * 
	 * @return
	 */
	public ClassroomBean getClassroomBean()
	{
		return classroomBean;
	}

	public void setClassroomBean(ClassroomBean classroomBean)
	{
		this.classroomBean = classroomBean;
	}

	public ArrayList<ClassroomBean> getListClassroomBean()
	{
		return listClassroomBean;
	}

	public void setListClassroomBean(ArrayList<ClassroomBean> listClassroomBean)
	{
		this.listClassroomBean = listClassroomBean;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
}
