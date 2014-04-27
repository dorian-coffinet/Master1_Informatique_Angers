package algorithme;


import java.io.IOException;
import java.util.TreeMap;

import context.IndiceOfCalcul;
import fileRW.ReadFile;

/**
 * Class permettant de lancer l'algorithme glouton
 *
 */
public class GlobalAlgo 
{
	private static IndiceOfCalcul tabIndices[];
	private static AlgoGloutonThread agt;
	
	private final static int NB_PROC_MINIM_TO_THREAD=4;
	public static TreeMap<Integer,AlgoGlouton> allResult;
	public static String result;
	public static int algo;
	public static long loadTime;
	public static long algoTime;
	
	
	

	/**
	 * @param args
	 */
	public static void launchAlgo(String path)
	{
		//Variable contenant le resultat a afficher
		result="";
		//recuperation du chemin du fichier a chager
		String pathFile= path;
		//nombre de processeur de la machine
		int nbProc = Runtime.getRuntime().availableProcessors();
		//taille du graphe
		int sizeGraphe = 0;
		ReadFile readFile = new ReadFile();
		
		allResult = new TreeMap<Integer,AlgoGlouton>();
		try 
		{
			sizeGraphe = readFile.readLines(pathFile);

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		result+=readFile.getGraphe().toString();
		System.err.println(readFile.getGraphe().toString());

		long startTime = System.currentTimeMillis();
		
		//si la machine possede plus de 4 coeur, on utilise le multithread
		if(nbProc>= NB_PROC_MINIM_TO_THREAD)
		{
			tabIndices = new IndiceOfCalcul[nbProc];
			int sizeCut = sizeGraphe/nbProc;
			tabIndices[0]=new IndiceOfCalcul(0, sizeCut);
			for (int i = 1; i < nbProc; i++)
			{
				tabIndices[i]=new IndiceOfCalcul(tabIndices[i-1].getFin()+1, tabIndices[i-1].getFin()+sizeCut);
			}
			
			for (int i = 0; i < tabIndices.length; i++)
			{
				agt = new AlgoGloutonThread(readFile, tabIndices[i]);
				agt.start();
			}
		}
		else
		{
			tabIndices[0]=new IndiceOfCalcul(0, sizeGraphe);
		}

		//on attend la fin de tous les thread pour choisir la meilleur clic trouvee parmis tous 
		while(!Thread.State.TERMINATED.equals(agt.getState()));
		
		allResult.lastEntry().getValue().display();
		
		long endTime = System.currentTimeMillis();
		algoTime=(endTime-startTime);
		
		result+="\n\nFile loaded in "+loadTime+" ms";
		result+="\nAlgorithm executed "+algoTime+" ms";
		result+="\nGlobal execution time "+(algoTime+loadTime)+" ms";
		System.err.println("File loaded in "+loadTime+" ms");
		System.err.println("Algorithm executed "+algoTime+" ms");
		System.err.println("Global execution time "+(algoTime+loadTime)+" ms");
	}
	
	
	public GlobalAlgo() 
	{
	}
}
