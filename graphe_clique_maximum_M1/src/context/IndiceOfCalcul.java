package context;

/**
 * Class permettant de découper les algorithmes pour les threads
 *
 */
public class IndiceOfCalcul 
{
	private int debut;
	private int fin;

	public IndiceOfCalcul(int d, int f) 
	{
		debut=d;
		fin=f;
	}

	public int getDebut() 
	{
		return debut;
	}

	public void setDebut(int debut)
	{
		this.debut = debut;
	}

	public int getFin()
	{
		return fin;
	}

	public void setFin(int fin) 
	{
		this.fin = fin;
	}

}
