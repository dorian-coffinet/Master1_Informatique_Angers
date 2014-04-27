package glouton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Base;
import model.Model;

public class AlgorithmeGlouton {
	
	private Model model;
	private HashMap<String, ArrayList<String>> resultatListe;
	private int resultatCout;
	
	private StringBuilder logs;
	
	public AlgorithmeGlouton(Model model) {
		this.model = model;
		this.resultatListe = new HashMap<String, ArrayList<String>>();
		this.resultatCout = 0;
		this.logs = new StringBuilder();
		rechercheGlouton(0);
	}
	
	public void rechercheGlouton(int increment) {
		
		// L'incrément permet de parcourir les entreprises recherchées.
		if (increment < model.getEntreprises().size()) {
			
			// Nom de l'entreprise recherchée.
			String nomEntreprise = model.getEntreprises().get(increment);
			
			int compteurPourLogs = 0;
			
			String meilleurChoixBase = "";
			int meilleurChoixCout = 0;
			int meilleurChoixNbEntreprises = 0;
			boolean baseDejaPresente = false;
			
			formattageLogs(increment);
			
			// Pour chaque base contenant l'entreprise recherchée.
			for (Base base : model.getEntreprisesBases().get(nomEntreprise)) {
				if (!baseDejaPresente) {
					// On regarde si la base est déjà ouverte.
					for (Entry<String, ArrayList<String>> entry : resultatListe.entrySet()) {
						if (entry.getKey().equals(base.getNomBase())) {
							meilleurChoixBase = base.getNomBase();
							meilleurChoixCout = 0;
							meilleurChoixNbEntreprises = calculerNombreEntreprises(base);
							baseDejaPresente = true;
						}
					}
					// Si la base n'est pas déjà ouverte.
					if (!baseDejaPresente) {
						
						ajouterLogs(nomEntreprise, base, increment, compteurPourLogs);
						compteurPourLogs++;
						
						// On place la base comme meilleur choix si c'est la première.
						if ("".equals(meilleurChoixBase)) { // Premier résultat partiel
							meilleurChoixBase = base.getNomBase();
							meilleurChoixCout = base.getCoutBase();
							meilleurChoixNbEntreprises = calculerNombreEntreprises(base);
						} else {
							// Sinon on vérifie que son coût est inférieur au choix actuel
							if (base.getCoutBase() < meilleurChoixCout) {
								meilleurChoixBase = base.getNomBase();
								meilleurChoixCout = base.getCoutBase();
								meilleurChoixNbEntreprises = calculerNombreEntreprises(base);
							}
							// Si le coût est identique au choix actuel,
							// on prend la base qui possède le plus d'entreprises recherchées.
							if (base.getCoutBase() == meilleurChoixCout) {
								int temporaire = calculerNombreEntreprises(base);
								if (temporaire > meilleurChoixNbEntreprises) {
									meilleurChoixBase = base.getNomBase();
									meilleurChoixCout = base.getCoutBase();
								}
							}
						}
					}
				}
			}
			// Si la base est déjà ouverte elle est choisie 
			// d'office puisque son coût est de 0.
			if (baseDejaPresente) {
				ajouterLogs(meilleurChoixBase + " (Cout = 0)\n");
				resultatListe.get(meilleurChoixBase).add(nomEntreprise);
				rechercheGlouton(increment + 1);
			} else {
				// Sinon on ajoute la nouvelle base au résultat.
				ajouterLogs(" --> " + meilleurChoixBase + " (Cout = " + meilleurChoixCout + ")\n");
				ArrayList<String> entreprises = new ArrayList<String>();
				entreprises.add(nomEntreprise);
				resultatListe.put(meilleurChoixBase, entreprises);
				resultatCout += meilleurChoixCout;
				rechercheGlouton(increment + 1);
			}
		}
		
	}
	
	// Cette fonction permet de calculer ne nombre d'entreprises 
	// recherchées que contient la base.
	private int calculerNombreEntreprises(Base base) {
		int nbEntreprises = 0;
		for (String entreprise : base.getEntreprises()) {
			for (String ent : model.getEntreprises()) {
				if (entreprise.equals(ent)) {
					nbEntreprises++;
				}
			}
		}
		return nbEntreprises;
	}
	
	private void formattageLogs(int increment) {
		for (int i = 0; i < increment; i++) {
			ajouterLogs(" |  ");
		}
	}
	
	public void ajouterLogs(String nomEntreprise, Base base, int increment, int compteurPourLogs) {
		if (compteurPourLogs == 0) {
			ajouterLogs(" " + nomEntreprise + " : {");
		}
		
		if (compteurPourLogs == (model.getEntreprisesBases().get(nomEntreprise).size() - 1)) {
			ajouterLogs(base.getNomBase() + "}");
		} else {
			ajouterLogs(base.getNomBase() + ", ");
		}
	}
	
	public void ajouterLogs(String logs) {
		this.logs.append(logs);
	}

	public String afficherResultat() {
		String affichageResultat = "";
		affichageResultat += " Meilleur coût : " + resultatCout + "\n\n";
		for (Entry<String, ArrayList<String>> entry : resultatListe.entrySet()) {
			affichageResultat += "\tBase " + entry.getKey() + "\n";
			for (String entreprise : entry.getValue()) {
				affichageResultat += "\t\t" + entreprise + "\n";
			}
			affichageResultat += "\n";
		}
		return affichageResultat;
	}
	
	public String getLogs() {
		return logs.toString();
	}

}
