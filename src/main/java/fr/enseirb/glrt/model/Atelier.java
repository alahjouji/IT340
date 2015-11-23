package fr.enseirb.glrt.model;

import java.util.List;

public class Atelier {
	private int id;
	private int lab_id;
	private String titre;
	private List<String> disciplines;
	private String type;
	private List<String> seances;
	private String lieu;
	private int duree;
	private int capacite;
	private String resume;
	private List<String> animateurs;
	private List<String> publics;
	
	public Atelier(){
		
	}
	public Atelier(int lab_id, String titre, List<String> disciplines, String type, List<String> seances, String lieu, int duree, int capacite, String resume, List<String> animateurs, List<String> publics){
		this.setLabId(lab_id);
		this.setTitre(titre);
		this.setDisciplines(disciplines);
		this.setType(type);
		this.setSeances(seances);
		this.setLieu(lieu);
		this.setDuree(duree);
		this.setCapacite(capacite);
		this.setResume(resume);
		this.setAnimateurs(animateurs);
		this.setPublics(publics);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLabId() {
		return lab_id;
	}
	public void setLabId(int lab_id) {
		this.lab_id = lab_id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<String> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(List<String> disciplines) {
		this.disciplines = disciplines;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public List<String> getSeances() {
		return seances;
	}
	public void setSeances(List<String> seances) {
		this.seances = seances;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public List<String> getAnimateurs() {
		return animateurs;
	}
	public void setAnimateurs(List<String> animateurs) {
		this.animateurs = animateurs;
	}
	public List<String> getPublics() {
		return publics;
	}
	public void setPublics(List<String> publics) {
		this.publics = publics;
	}

	
}
