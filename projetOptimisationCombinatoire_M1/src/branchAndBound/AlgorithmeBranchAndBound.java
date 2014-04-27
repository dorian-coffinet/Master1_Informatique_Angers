package branchAndBound;

import java.util.ArrayList;
import java.util.Map.Entry;

import model.Base;
import model.Model;

public class AlgorithmeBranchAndBound {
	
	private Model model;
	private int majorant;
	
	private SacADos resultat;
	private int compteurMajorants;
	private int compteurBranches;
	private int compteurBranchesCoupees;
	private int compteurNoeuds;
	
	private StringBuilder logs;

	public AlgorithmeBranchAndBound(Model model) {
		this.model = model;
		this.majorant = -1;
		this.resultat = null;
		
		this.compteurMajorants = 0;
		this.compteurBranches = 0;
		this.compteurBranchesCoupees = 0;
		this.compteurNoeuds = 0;
	
		this.logs = new StringBuilder();
		
	}
	
	public void executer() {
		rechercheOptimale(new SacADos(), 0);
	}
	
	public void rechercheOptimale(SacADos sacADos, int increment) {
		
		// L'incrément permet de parcourir les entreprises recherchées.
			
		// Nom de l'entreprise recherchée.
		String nomEntreprise = model.getEntreprises().get(increment);

		// Pour chaque base contenant l'entreprise recherchée.
		for (Base base : model.getEntreprisesBases().get(nomEntreprise)) {
			
			formattageLogs(increment);
			ajouterLogs(nomEntreprise + ", (");
			ajouterLogs(base.getNomBase() + ") ");	
			
			// Cout actuel du résultat partiel.
			// Nombre d'entreprises du résultat partiel.
			int coutTemporaire = sacADos.getCoutOptimal();
			int tailleListeTemporaire = sacADos.getTailleResultatsPartiels();
			
			// Indique si la base est présente dans le résultat partiel.
			boolean estPresentListePartielle = false;
			
			// On vérifie si la base est déjà ouverte.
			if (!sacADos.rechercherBase(base)) {
				coutTemporaire += base.getCoutBase();
				tailleListeTemporaire++;
			} else {
				tailleListeTemporaire++;
				estPresentListePartielle = true;
			}
			
			// Si la liste du résultat n'est pas complète.
			if(tailleListeTemporaire < model.getEntreprises().size()) {
				// et que le coût est inférieur au majorant.
				if (coutTemporaire < majorant || majorant == -1) {
					SacADos nouveauSac = new SacADos(sacADos);
					// Si la base fait déjà partie du résultat partiel il 
					// n'y a pas besoin d'incrémenter le coût optimal.
					completerResultatPartiel(estPresentListePartielle, nouveauSac, base, nomEntreprise);
					compteurNoeuds++;
					rechercheOptimale(nouveauSac, increment + 1);
				} else {
					// Sinon la branche est coupée.
					compteurNoeuds++;
					compteurBranches++;
					compteurBranchesCoupees++;
					ajouterLogs(" ---> ELAGAGE : " + coutTemporaire + "\t");
				}
			} else {
				compteurNoeuds++;
				compteurBranches++;
				// Si la liste du résultat du résultat partiel est complète.
				// et le coût est inférieur au majorant.
				if (coutTemporaire < majorant || majorant == -1) {
					majorant = coutTemporaire;
					compteurMajorants++;
					SacADos nouveauSac = new SacADos(sacADos);
					completerResultatPartiel(estPresentListePartielle, nouveauSac, base, nomEntreprise);
					resultat = nouveauSac;
					ajouterLogs(" ---> NOUVEAU MAJORANT : " + majorant + "\t");	
				}
			}
		}
	}
	
	private void completerResultatPartiel(boolean estPresentListePartielle, SacADos nouveauSac, Base base, String nomEntreprise) {
		if (estPresentListePartielle) {
			nouveauSac.ajouterEntreprise(base.getNomBase(), nomEntreprise);
		} else {
			ArrayList<String> nouvelleListeEntreprises = new ArrayList<String>();
			nouvelleListeEntreprises.add(nomEntreprise);
			nouveauSac.ajouterEntree(base.getNomBase(), nouvelleListeEntreprises);
			nouveauSac.incrementerCoutOptimal(base.getCoutBase());	
		}
	}
	
	private void formattageLogs(int increment) {
		ajouterLogs("\n");
		for (int i = 0; i < increment; i++) {
			ajouterLogs(" |  ");
		}
	}

	public void ajouterLogs(String logs) {
		this.logs.append(logs);
	}
	
	public String afficherResultat() {
		StringBuilder affichageResultat = new StringBuilder();
		affichageResultat.append(" Meilleur coût : " + resultat.getCoutOptimal() + ".\n\n");
		affichageResultat.append(" Nombre de majorants : " + compteurMajorants + ".\n");
		affichageResultat.append(" Nombre de noeuds : " + compteurNoeuds + ".\n");
		affichageResultat.append(" Nombre de branches : " + compteurBranches + ".\n");
		affichageResultat.append("   dont " + compteurBranchesCoupees + " branches coupées.\n\n");
		for (Entry<String, ArrayList<String>> entry : resultat.getResultatsPartiels().entrySet()) {
			affichageResultat.append("\tBase" + entry.getKey() + "\n");
			for (String entreprise : entry.getValue()) {
				affichageResultat.append("\t\t" + entreprise + "\n");
			}
			affichageResultat.append("\n");
		}
		return affichageResultat.toString();
	}
	
	public String getLogs() {
		return logs.toString();
	}
	
}