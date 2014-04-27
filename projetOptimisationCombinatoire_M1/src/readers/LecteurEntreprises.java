package readers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LecteurEntreprises {

	private String cheminListeEntreprises;
	
	public LecteurEntreprises(String cheminListeEntreprises) {
		this.cheminListeEntreprises = cheminListeEntreprises;
	}
	
	public ArrayList<String> lire() {
		int compteurLignes = 0;
		int nombreEntreprises = 0;
		ArrayList<String> entreprises = new ArrayList<String>();
		
		try{
			InputStream ips = new FileInputStream(cheminListeEntreprises); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null){
				if (compteurLignes == 0) {
					nombreEntreprises = Integer.valueOf(ligne);
					compteurLignes++;
				} else {
					entreprises.add(ligne);
					compteurLignes++;
				}
			}
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		// On vérifie que le nombre d'entreprises
		// correspond à celui du fichier.
		if (entreprises.size() != nombreEntreprises) {
			System.err.println("LE NOMBRE D'ENTREPRISES NE CORRESPOND PAS !!!");
		}
		
		return entreprises;
	}
	
}
