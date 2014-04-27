package actions.logout;

import actions.abstractAction.AbstractAction;

/**
 * Action de déconnexion
 * @author mickael
 *
 */
public class LogoutAction extends AbstractAction
{
	//Serialization
	private static final long serialVersionUID = 1L;

	/**
	 * Execution la deconnexion 
	 */
	public String execute()
	{
		forward=FORWARD_SUCCESS;
		session.remove(USER);	
		session.remove(USER_GROUP);
		return forward;
	}
}
