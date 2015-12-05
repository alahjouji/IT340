package fr.enseirb.glrt.model;


public class Seance {
	private String nom;
	private Integer inscrit;
	public Seance(String jour, Integer ins) {
		setNom(jour);
		setInscrit(ins);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getInscrit() {
		return inscrit;
	}
	public void setInscrit(Integer inscrit) {
		this.inscrit = inscrit;
	}
}
