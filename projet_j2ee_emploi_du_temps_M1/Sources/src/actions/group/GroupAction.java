package actions.group;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import model.dao.GroupDAO;
import model.org.persistence.GroupEntity;
import bean.GroupBean;
import actions.abstractAction.AbstractAction;

/**
 * Action sur les groupes
 * @author mickael
 *
 */
public class GroupAction extends AbstractAction
{
	//Serialization
	private static final long serialVersionUID = 1L;

	//bean de formulaire permettant le transfere des informations
	private GroupBean groupBean;
	private ArrayList<GroupBean> listGroupBean;

	//déclaration et initialisation des DAO
	private GroupDAO gdao = new GroupDAO();
	
	//permet de récup l'id pour la suppression et l'affichage en modification
	private long id;
	
	/**
	 * Execution de l'ajout d'un groupe
	 */
	public String execute()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			GroupEntity g = new GroupEntity();
			g.setName(groupBean.getName());
			gdao.save(g);
		}
		catch(Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	
	/**
	 * Méthode permettant d'afficher la liste des groupes
	 * @return
	 */
	@SkipValidation 
	public String showGroup()
	{
		forward = FORWARD_SUCCESS;
		listGroupBean = new ArrayList<GroupBean>();
		try
		{
			List<GroupEntity> listGroupEntity = gdao.getAll();
			for (GroupEntity groupEntity : listGroupEntity)
			{
				GroupBean groupBean = new GroupBean();
				groupBean.setId(groupEntity.getId());
				groupBean.setName(groupEntity.getName());
				listGroupBean.add(groupBean);
			}
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	
	/**
	 * Méthode permettant de supprimer un groupe
	 * @return
	 */
	@SkipValidation 
	public String deleteGroup()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			GroupEntity groupEntity = gdao.getById(id);
			gdao.delete(groupEntity);
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de récupérer le groupe selectionné pour l'afficher en mode modification
	 * 
	 */
	@SkipValidation 
	public String getGroup()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			GroupEntity groupEntity = (GroupEntity) gdao.getById(this.id);
			this.groupBean = new GroupBean();
			this.groupBean.setId(groupEntity.getId());
			this.groupBean.setName(groupEntity.getName());
			session.put(OLD_VALUE, groupEntity.getName());
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}
	
	/**
	 * Méthode permettant d'update un groupe
	 */
	public String updateGroup()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			GroupEntity groupEntity=gdao.getById(this.groupBean.getId());
			groupEntity.setName(groupBean.getName());
			gdao.update(groupEntity);
		} 
		catch (Exception e)
		{
			forward = generateError(e);
		}
		return forward;
	}
	

	/**
	 * Méthode permettant la validation des champs 
	 */
	public void validate()
	{
		if (groupBean != null)
		{
			if(groupBean.getName().isEmpty())
			{
				addFieldError("error.name", getText("validator.field.empty"));
			}

			if(!groupBean.getName().equals(session.get(OLD_VALUE)))
			{
				if(gdao.existNameGroup(groupBean.getName()))
					addFieldError("error.name",  getText("validator.group.exist"));
			}
		}
	}
	
	/**
	 * Getters and setters
	 *
	 */
	public GroupBean getGroupBean() 
	{
		return groupBean;
	}

	public void setGroupBean(GroupBean groupBean) 
	{
		this.groupBean = groupBean;
	}

	public GroupDAO getGroupDAO() 
	{
		return gdao;
	}

	public void setGroupDAO(GroupDAO groupDAO) 
	{
		this.gdao = groupDAO;
	}

	public ArrayList<GroupBean> getListGroupBean() 
	{
		return listGroupBean;
	}

	public void setListGroupBean(ArrayList<GroupBean> listGroupBean) 
	{
		this.listGroupBean = listGroupBean;
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
