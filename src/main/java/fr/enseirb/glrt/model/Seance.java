package fr.enseirb.glrt.model;


public class Seance {
	private String seance;
	private Integer inscrit;
	public Seance(String jour5, Integer ins) {
		this.seance = jour5;
		this.inscrit = ins;
	}
	public String getSeance() {
		return seance;
	}
	public void setSeance(String seance) {
		this.seance = seance;
	}
	public Integer getInscrit() {
		return inscrit;
	}
	public void setInscrit(Integer inscrit) {
		this.inscrit = inscrit;
	}
}
