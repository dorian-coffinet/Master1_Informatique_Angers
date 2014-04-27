package readers;

import java.util.ArrayList;
import java.util.HashMap;

import model.Base;

public class Lecteur {
	
	private String prefixCheminBases = "./bin/docs/bases/";
	
	private LecteurBases lecteurBases;
	private LecteurEntreprises lecteurEntreprises;
	
	private ArrayList<Base> bases;
	private HashMap<String, ArrayList<Base>> entreprisesBases;

	public Lecteur(String cheminListeBases, String cheminListeEntreprises) {
		this.lecteurBases = new LecteurBases(cheminListeBases, prefixCheminBases);
		this.lecteurEntreprises = new LecteurEntreprises(cheminListeEntreprises);
	}
	
	public void lireBases() {
		lecteurBases.lire();
		bases = lecteurBases.getBases();
		entreprisesBases = lecteurBases.getEntreprisesBases();
	}
	
	public ArrayList<String> lireEntreprises() {
		
		return lecteurEntreprises.lire();
	}

	public ArrayList<Base> getBases() {
		return bases;
	}

	public void setBases(ArrayList<Base> bases) {
		this.bases = bases;
	}

	public HashMap<String, ArrayList<Base>> getEntreprisesBases() {
		return entreprisesBases;
	}

	public void setEntreprisesBases(
			HashMap<String, ArrayList<Base>> entreprisesBases) {
		this.entreprisesBases = entreprisesBases;
	}

}
