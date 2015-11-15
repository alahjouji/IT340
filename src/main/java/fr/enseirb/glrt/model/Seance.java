package fr.enseirb.glrt.model;

import fr.enseirb.glrt.model.enumerations.Jour;

public class Seance {
	private Jour jour;
	private String heure;
	public Jour getJour() {
		return jour;
	}
	public void setJour(Jour jour) {
		this.jour = jour;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	
}
