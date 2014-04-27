package actions.user;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import model.dao.GroupDAO;
import model.dao.UserDAO;
import model.org.persistence.UserEntity;
import actions.abstractAction.AbstractAction;
import bean.UserBean;

/**
 * Action sur les users
 * @author mickael
 *
 */
public class UserAction extends AbstractAction
{
	//Serialization
	private static final long serialVersionUID = 1L;

	//bean de formulaire permettant le transfere des informations
	private ArrayList<UserBean> listUserBean;
	private UserBean userBean;

	//déclaration et initialisation des DAO
	private UserDAO uDao = new UserDAO();
	private GroupDAO gdao = new GroupDAO();
	
	//récupération de l'id pour afficher en mode modification un user
	private long id;

	
	/**
	 * Méthode permettant d'afficher la liste des users 
	 * @return
	 */
	@SkipValidation 
	public String showUser()
	{
		forward = FORWARD_SUCCESS;
		this.listUserBean = new ArrayList<UserBean>();
		try
		{
			List<UserEntity> listUserEntity = uDao.getAll();
			for (UserEntity userEntity : listUserEntity)
			{
				UserBean userBean=new UserBean();
				userBean.convertEntityToBean(userEntity);
				userBean.setMapGroup(gdao.getAllGroupForMap());
				this.listUserBean.add(userBean);
			}
		} 
		catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}

	/**
	 * Méthode permettant de récupérer le user selectionné pour l'afficher en mode modification
	 * 
	 */
	@SkipValidation 
	public String getUser()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			UserEntity userEntity = (UserEntity) uDao.getById(this.id);
			this.userBean=new UserBean();
			this.userBean.convertEntityToBean(userEntity);
			this.userBean.setMapGroup(gdao.getAllGroupForMap());
			session.put(OLD_VALUE, userBean.getEmail());
		} 
		catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}
	
	/**
	 * Méthode permettant de supprimer un user
	 */
	@SkipValidation 
	public String deleteUser()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			UserEntity userEntity=uDao.getById(id);
			uDao.delete(userEntity);
		} catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}
	
	/**
	 * Méthode permettant d'update un user
	 */
	public String updateUser()
	{
		forward = FORWARD_SUCCESS;
		try
		{
			UserEntity userEntity=uDao.getById(userBean.getId());
			convertCurrentBeanToEntity(userEntity);
			uDao.update(userEntity);
		} 
		catch (Exception e)
		{
			forward=generateError(e);
		}
		return forward;
	}
	
	
	/**
	 * Méthode permettant la validation des champs 
	 */
	public void validate()
	{
		if(userBean!=null)
		{
			//test si le nom est renseigné
			if(userBean.getFirstName().isEmpty())
			{
				addFieldError("error.firstname", getText("validator.field.empty"));
			}

			//test si le nom est renseigné
			if(userBean.getName().isEmpty())
			{
				addFieldError("error.name", getText("validator.field.empty"));
			}

			//test si le mail est renseigné
			if(userBean.getEmail().isEmpty())
			{
				addFieldError("error.email", getText("validator.field.empty"));
			}
			//test si l'email est correctement renseigné
			else if(!emailValidator(userBean.getEmail()))
			{
				addFieldError("error.email", getText("validator.mail.false"));
			}

			//test du changement de mot de passe
			if(!userBean.getNewPassword().isEmpty())
			{
				if(userBean.getConfirmPassword().isEmpty())
				{
					addFieldError("error.newpassword", getText("validator.pwd.confirm.part1"));
					addFieldError("error.confirmpassword", getText("validator.pwd.confirm.part2"));
				}
				else if(!userBean.getNewPassword().equals(userBean.getConfirmPassword()))
				{
					addFieldError("error.newpassword", getText("validator.pwd.confirm.part1"));
					addFieldError("error.confirmpassword", getText("validator.pwd.confirm.part2"));
				}
			}
			else
			{
				if(!userBean.getConfirmPassword().isEmpty())
				{
					addFieldError("error.newpassword", getText("validator.pwd.confirm.part1"));
					addFieldError("error.confirmpassword", getText("validator.pwd.confirm.part2"));
				}
			}

			if(!userBean.getEmail().equals(session.get(OLD_VALUE)))
			{
				if(uDao.existEmailUser(userBean.getEmail()))
					addFieldError("error.email",  getText("validator.user.exist"));
			}
		}		

		//rechargement de la liste des groupe pour réafficher la page des inscriptions
		userBean.setMapGroup(gdao.getAllGroupForMap());
	}

	
	/**
	 * Méthode de conversion avec un userEntity en entrée et le bean de la classe
	 * @param UserEntity
	 * @return UserBean
	 */
	private void convertCurrentBeanToEntity(UserEntity userResult)
	{
		userResult.setEmail(this.userBean.getEmail());
		userResult.setName(this.userBean.getName());
		userResult.setGroupe(gdao.getById(this.userBean.getIdGroup()));
		if(!this.userBean.getConfirmPassword().isEmpty())
			userResult.setPassword(md5(this.userBean.getConfirmPassword()));
	}
	
	/**
	 * Getters and setters
	 * @return
	 */
	public ArrayList<UserBean> getListUserBean() 
	{
		return listUserBean;
	}
	
	public void setListUserBean(ArrayList<UserBean> listUserBean) 
	{
		this.listUserBean = listUserBean;
	}

	public UserBean getUserBean() 
	{
		return userBean;
	}

	public void setUserBean(UserBean userBean) 
	{
		this.userBean = userBean;
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
