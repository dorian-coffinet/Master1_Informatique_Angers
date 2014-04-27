package readers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import model.Base;

public class LecteurBases {

	private String cheminListeBases;
	private String prefixCheminBases;
	
	private ArrayList<Base> bases;
	private HashMap<String, ArrayList<Base>> entreprisesBases;
	
	public LecteurBases(String cheminListeBases, String prefixCheminBases) {
		this.cheminListeBases = cheminListeBases;
		this.prefixCheminBases = prefixCheminBases;
		
		this.bases = new ArrayList<Base>();
		this.entreprisesBases = new HashMap<String, ArrayList<Base>>();
	}
	
	public void lire() {
		int compteurLignes = 0;
		int nombreBases = 0;
		
		
		try{
			InputStream ips = new FileInputStream(cheminListeBases); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null){
				if (compteurLignes == 0) {
					nombreBases = Integer.valueOf(ligne);
					compteurLignes++;
				} else {
					bases.add(lireBase(ligne));
					compteurLignes++;
				}
			}
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		// On vérifie que le nombre de bases
		// correspond à celui du fichier.
		if (bases.size() != nombreBases) {
			System.err.println("LE NOMBRE DE BASES NE CORRESPOND PAS !!!");
		}
		
	}
	
	private Base lireBase(String cheminFichierBase) {
		int compteurLignes = 0;
		int nombreEntreprises = 0;
		
		Base base = new Base();
		
		String nomBase = cheminFichierBase;
		int coutBase = 0;
		ArrayList<String> entreprises = new ArrayList<String>();
		try{
			InputStream ips = new FileInputStream(prefixCheminBases + cheminFichierBase); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null){
				if (compteurLignes == 0) {
					coutBase = Integer.valueOf(ligne);
					compteurLignes++;
				} else if (compteurLignes == 1) {
					nombreEntreprises = Integer.valueOf(ligne);
					compteurLignes++;
				} else {
					if (entreprisesBases.containsKey(ligne)) {
						ArrayList<Base> listeBases = entreprisesBases.get(ligne);
						listeBases.add(base);
					} else {
						ArrayList<Base> listeBases = new ArrayList<Base>();
						listeBases.add(base);
						entreprisesBases.put(ligne, listeBases);
					}
					entreprises.add(ligne);
					compteurLignes++;
				}
			}
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		base.setNomBase(nomBase);
		base.setCoutBase(coutBase);
		base.setEntreprises(entreprises);
		
		// On vérifie que le nombre d'entreprises
		// correspond à celui du fichier.
		if (entreprises.size() != nombreEntreprises) {
			System.err.println("LE NOMBRE D'ENTREPRISES NE CORRESPOND PAS !!!");
		}
		
		return base;
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