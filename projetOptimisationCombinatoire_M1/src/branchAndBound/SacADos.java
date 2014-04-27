package branchAndBound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Base;

public class SacADos {
	
	private HashMap<String, ArrayList<String>> resultatsPartiels;
	private int coutOptimal;
	private int tailleResultatsPartiels;
	
	public SacADos() {
		this.resultatsPartiels = new HashMap<String, ArrayList<String>>();
		this.coutOptimal = 0;
		this.tailleResultatsPartiels = 0;
	}
	
	public SacADos(SacADos sacADos) {
		HashMap<String, ArrayList<String>> tempor = new HashMap<String, ArrayList<String>>();
		for (Entry<String, ArrayList<String>> entry : sacADos.getResultatsPartiels().entrySet()) {
			tempor.put(entry.getKey(), new ArrayList<String>(entry.getValue()));
		}
		this.resultatsPartiels = tempor;
		this.coutOptimal = sacADos.getCoutOptimal();
		this.tailleResultatsPartiels = sacADos.getTailleResultatsPartiels();
		
	}

	public void ajouterEntree(String base, ArrayList<String> entreprises) {
		resultatsPartiels.put(base, entreprises);
		tailleResultatsPartiels ++;
	}
	
	public void ajouterEntreprise(String base, String entreprise) {
		resultatsPartiels.get(base).add(entreprise);
		tailleResultatsPartiels ++;
	}
	
	public int getCoutOptimal() {
		return coutOptimal;
	}
	
	public void incrementerCoutOptimal(int increment) {
		coutOptimal += increment;
	}
	
	public HashMap<String, ArrayList<String>> getResultatsPartiels() {
		return resultatsPartiels;
	}
	
	public int getTailleResultatsPartiels() {
		return tailleResultatsPartiels;
	}
	
	public boolean rechercherBase(Base base) {
		if (resultatsPartiels.containsKey(base.getNomBase())) {
			return true;
		} else {
			return false;
		}
	}
	
}