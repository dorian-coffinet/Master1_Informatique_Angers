package context;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Class representant le graphe
 *
 */
public class Graphe
{
	//map contenant : clef = valeur sommet / valeur = liste des sommets adjacents
	private TreeMap<Integer,ArrayList<Integer>> mapGraphe;
	//liste contenant tous les sommets
	private ArrayList<Integer> allSommet;
	//nombre d'arretes
	private int nbEdge;
	
	/**
	 * constructeur sans parametres
	 */
	public Graphe() 
	{
		allSommet =new ArrayList<Integer>();
		mapGraphe=new TreeMap<Integer,ArrayList<Integer>>();
	}

	/**
	 * permet de construire une tree map contenant : clef sommet / value liste sommet adjacents
	 * @param sommet
	 * @param EdgeSommet
	 */
	private void implementGraphe(Integer sommet, Integer EdgeSommet)
	{
		ArrayList<Integer> adjacentTmp;
		
		//on ajoute le sommet adjacent au sommet en cours
		adjacentTmp = (mapGraphe.get(sommet)!=null) ? mapGraphe.get(sommet) : new ArrayList<Integer>();
		if(!adjacentTmp.contains(EdgeSommet)) adjacentTmp.add(EdgeSommet);
		mapGraphe.put(sommet, new ArrayList<Integer> (adjacentTmp));
	}
	
	/**
	 * Construit la liste des sommets 
	 */
	public int buildAllSommet()
	{
		allSommet= new ArrayList<Integer>(getMapGraphe().keySet());
		return allSommet.size();
	}
	
	/**
	 * construction du graphe
	 * @param sommet1
	 * @param sommet2
	 */
	public void buildGraphe(Integer sommet1, Integer sommet2)
	{
		implementGraphe(sommet1,sommet2);
		implementGraphe(sommet2,sommet1);
		nbEdge++;
	}
	
	@Override
	public String toString()
	{
		String grapheToAffiche="";
		grapheToAffiche= "Graph [Number of edges = " + nbEdge + ", Number of vertices = " + allSommet.size() + "]";
		
		return grapheToAffiche;
	}

	
	/**
	 * Getters & setters
	 */
	
	public ArrayList<Integer> getAllSommet()
	{
		return allSommet;
	}

	public void setAllSommet(ArrayList<Integer> allSommet) 
	{
		this.allSommet = allSommet;
	}

	public TreeMap<Integer, ArrayList<Integer>> getMapGraphe() 
	{
		return mapGraphe;
	}

	public void setMapGraphe(TreeMap<Integer, ArrayList<Integer>> mapGraphe) 
	{
		this.mapGraphe = mapGraphe;
	}
}
