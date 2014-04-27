package model;

import java.util.ArrayList;

public class Base {
	
	private String nomBase;
	private int coutBase;
	private ArrayList<String> entreprises;
	
	public Base() {
		
	}
	
	public Base(String nomBase, int coutBase, ArrayList<String> entreprises) {
		this.nomBase = nomBase;
		this.coutBase = coutBase;
		this.entreprises = entreprises;
	}

	public String getNomBase() {
		return nomBase;
	}

	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}

	public int getCoutBase() {
		return coutBase;
	}

	public void setCoutBase(int coutBase) {
		this.coutBase = coutBase;
	}

	public ArrayList<String> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(ArrayList<String> entreprises) {
		this.entreprises = entreprises;
	}
	
}
