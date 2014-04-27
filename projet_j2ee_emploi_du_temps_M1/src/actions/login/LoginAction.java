package actions.login;


import model.dao.UserDAO;
import model.org.persistence.UserEntity;
import actions.abstractAction.AbstractAction;
import bean.UserBean;

/**
 * Action de connexion
 * @author mickael
 *
 */
public class LoginAction extends AbstractAction
{
	//Serialization
	private static final long serialVersionUID = 1L;
	//bean de formulaire permettant le transfere des informations
	private UserBean userBean;
	//déclaration et initialisation du DAO
	private UserDAO udao = new UserDAO();

	/**
	 * Execution l'inscription en sauvegardant le user
	 */
	public String execute()
	{
		forward=FORWARD_SUCCESS;
		UserEntity user=udao.getUserByEmailAndPwd(userBean.getEmail(), md5(userBean.getPassword()));
		
		//Si le user n'existe pas c'est qu'il y a une erreur d'authentification
		if(user==null)
		{
			addActionError(getText("validator.not.register"));
			forward=FORWARD_INPUT;
		}
		else
		{
			this.userBean.convertEntityToBean(user);
			session.put(USER, userBean);
			session.put(USER_GROUP, user.getGroupe().getName());
		}
		return forward;
	}


	/**
	 * Méthode permetant la validation des champs 
	 */
	public void validate()
	{
		if("".equals(userBean.getEmail()))
		{
			addFieldError("error.email", getText("validator.field.empty"));
		}
		else if(!emailValidator(userBean.getEmail()))
		{
			addFieldError("error.email", getText("validator.mail.false"));
		}

		if("".equals(userBean.getPassword()))
		{
			addFieldError("error.password", getText("validator.field.empty"));
		}
		
		
	}


	/**
	 * Getters and setters
	 * @return
	 */
	public UserBean getUserBean() 
	{
		return userBean;
	}

	public void setUserBean(UserBean userBean) 
	{
		this.userBean = userBean;
	}

}
