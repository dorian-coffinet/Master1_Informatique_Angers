package bean;

/**
 * Bean Error
 * @author mickael
 *
 */
public class ErrorBean
{
	private String errorMessage;
	private String errorTraces;

	public ErrorBean() 
	{
		super();
		this.errorMessage="";
		this.errorTraces="";
	}

	

	/**
	 * Getters and Setters
	 */
	public String getErrorMessage() 
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) 
	{
		this.errorMessage = errorMessage;
	}

	public String getErrorTraces()
	{
		return errorTraces;
	}

	public void setErrorTraces(String errorTraces) 
	{
		this.errorTraces = errorTraces;
	}
}
