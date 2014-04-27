package actions.abstractAction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.interceptor.SessionAware;

import bean.ErrorBean;

import com.opensymphony.xwork2.ActionSupport;


/**
 * Encapsulation des actions 
 * @author mickael
 *
 */
public class AbstractAction extends ActionSupport implements SessionAware
{
	//Serialization
	private static final long serialVersionUID = 1L;
	//forward pour rediriger vers la bonne page
	protected String forward;
	//variable de session
	protected Map<String, Object> session;
	//pattern de vérification d'un email
	protected static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	//variable d'affichage du type calendrier
	protected static final long NORMAL=0;
	protected static final long COMPACT=1;
	protected static final long RESUME=2;
	
	//id de l'enseignant
	protected static final long GROUP_ID_TEACHER=1;
	//id de l'etudiant
	protected static final long GROUP_ID_ETU=2;
	//variable afin de mettre en session une ancienne valeur dans le cadre des test de validation des modules
	protected static final String OLD_VALUE="oldValue";
	//variable afin de mettre en session le user courant
	protected static final String USER="user";
	//variable afin de mettre en session le nom du groupe du user courant
	protected static final String USER_GROUP="userGroup";
	//format date de l'application
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//valeurs de forward
	protected static final String FORWARD_SUCCESS = "SUCCESS";
	protected static final String FORWARD_ERROR = "ERROR";
	protected static final String FORWARD_INPUT = "input";
	
	//permet de remonter l'erreur
	protected ErrorBean error; 
	
	
	public String execute()
	{
		forward=FORWARD_SUCCESS;
		return forward;
	}


	public void validate(){}
	
	/**
	 * Permet de valider un email selon la regex officiel
	 * @param email
	 * @return
	 */
	protected boolean emailValidator(String email) 
	{
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * Méthode qui va mettre en forme les erreurs pour les afficher comme il se doit
	 * 
	 */
	protected String generateError(Exception e)
	{
		error = new ErrorBean();
		String traceTmp="";
		for (int i = 0; i < e.getStackTrace().length; i++)
		{
			traceTmp+=e.getStackTrace()[i];
			traceTmp+="\n";
		}
		error.setErrorTraces(traceTmp);
		error.setErrorMessage(e.getMessage());
		
		return FORWARD_ERROR;
	}
	

	/**
	 * Méthode permettant le hashage des mots de passe
	 * @param password
	 * @return
	 */
	protected String md5(String password)
	{
		byte[] uniqueKey = password.getBytes();
		byte[] hash      = null;

		try
		{
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		}
		catch (NoSuchAlgorithmException e)
		{
			generateError(e);
		}

		StringBuilder hashString = new StringBuilder();
		for (int i = 0; i < hash.length; i++)
		{
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1)
			{
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}
			else
				hashString.append(hex.substring(hex.length() - 2));
		}
		return hashString.toString();
	}

	/**
	 * Objet de session
	 */
	@Override
	public void setSession(Map<String, Object> session) 
	{
		this.session=session;
	}

	public Map<String, Object> getSession() 
	{
		return session;
	}

	/**
	 * container des erreurs
	 * 
	 */
	public ErrorBean getError() 
	{
		return error;
	}


	public void setError(ErrorBean error) 
	{
		this.error = error;
	}
}
