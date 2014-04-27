package algorithme;

import context.IndiceOfCalcul;
import fileRW.ReadFile;


/**
 * class permettant de lancer un traitement par coeur du processeur
 *
 */
public class AlgoGloutonThread extends Thread 
{
	private ReadFile file;
	private IndiceOfCalcul indiceToCalcul;
	
	public AlgoGloutonThread(ReadFile f, IndiceOfCalcul i) 
	{
		this.file=f;
		indiceToCalcul = i;
	}

	public void run() 
	{
		AlgoGlouton a = new AlgoGlouton(file, indiceToCalcul);
		a.cliqueMaximumGlouton();
		GlobalAlgo.allResult.put(a.getResultatFinal().size(),a);
	}
}
