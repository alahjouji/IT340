package fr.enseirb.glrt.model;

import java.util.List;

public class Atelier {
	private int id;
	private int labId;
	private String labName;
	private String titre;
	private List<String> disciplines;
	private String type;
	private List<Seance> seances;
	private String lieu;
	private int duree;
	private int capacite;
	private String resume;
	private List<String> animateurs;
	private List<String> publics;

	public Atelier() {

	}

	public Atelier(int lab_id, String titre, List<String> disciplines, String type, List<Seance> seances, String lieu,
			int duree, int capacite, String resume, List<String> animateurs, List<String> publics) {
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

	public int getLabId() {
		return labId;
	}

	public void setLabId(int lab_id) {
		this.labId = lab_id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public List<String> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<String> disciplines) {
		this.disciplines = disciplines;
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}

	public List<String> getPublics() {
		return publics;
	}

	public void setPublics(List<String> publics) {
		this.publics = publics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

}
