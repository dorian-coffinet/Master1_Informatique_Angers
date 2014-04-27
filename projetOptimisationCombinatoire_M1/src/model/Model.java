package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Model {
	
	private ArrayList<Base> bases;
	private ArrayList<String> entreprises;
	private HashMap<String, ArrayList<Base>> entreprisesBases;
	
	public Model() {
		
	}

	public ArrayList<Base> getBases() {
		return bases;
	}

	public void setBases(ArrayList<Base> bases) {
		this.bases = bases;
	}

	public ArrayList<String> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(ArrayList<String> entreprises) {
		this.entreprises = entreprises;
	}

	public HashMap<String, ArrayList<Base>> getEntreprisesBases() {
		return entreprisesBases;
	}

	public void setEntreprisesBases(
			HashMap<String, ArrayList<Base>> entreprisesBases) {
		this.entreprisesBases = entreprisesBases;
	}

	public void afficher() {
		afficherBases();
		afficherEntreprises();
		afficherEntreprisesBases();
	}
	
	public void afficherBases() {
		System.out.println("***** LISTE DES BASES *****");
		System.out.println("\n");
		for (Base base : bases) {
			System.out.println("Nom de la base : " + base.getNomBase());
			System.out.println("Coût de la base : " + base.getCoutBase());
			for (String nomEntreprise : base.getEntreprises()) {
				System.out.println(nomEntreprise);
			}
			System.out.println("\n");
		}
	}
	
	public void afficherEntreprises() {
		System.out.println("***** LISTE DES ENTREPRISES *****");
		System.out.println(entreprises.size());
		System.out.println("\n");
		for (String entreprise : entreprises) {
			System.out.println(entreprise);
		}
		System.out.println("\n");
	}
	
	public void afficherEntreprisesBases() {
		System.out.println("***** CORRESPONDANCES ENTREPRISES/BASES *****");
		System.out.println(entreprisesBases.size());
		System.out.println("\n");
		for (Entry<String, ArrayList<Base>> entry : entreprisesBases.entrySet()) {
			System.out.println("ENTREPRISE : " + entry.getKey());
			for (Base base : entry.getValue()) {
				System.out.print(base.getNomBase() + ", ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

}