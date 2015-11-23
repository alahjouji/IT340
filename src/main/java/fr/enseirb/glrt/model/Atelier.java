package fr.enseirb.glrt.model;

import java.util.List;

import fr.enseirb.glrt.model.enumerations.Jour;
import fr.enseirb.glrt.model.enumerations.Public;
import fr.enseirb.glrt.model.enumerations.Topics;

public class Atelier {
	private int labId;
	private String titre;
	private List<Topics> disciplines;
	private String type;
	private List<Jour> seances;
	private String lieu;
	private int duree;
	private int capacite;
	private String resume;
	private List<String> animateurs;
	private List<Public> publics;

	public Atelier() {

	}

	public Atelier(int lab_id, String titre, List<Topics> disciplines, String type, List<Jour> seances, String lieu,
			int duree, int capacite, String resume, List<String> animateurs, List<Public> publics) {
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

	public List<Topics> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Topics> disciplines) {
		this.disciplines = disciplines;
	}

	public List<Jour> getSeances() {
		return seances;
	}

	public void setSeances(List<Jour> seances) {
		this.seances = seances;
	}

	public List<Public> getPublics() {
		return publics;
	}

	public void setPublics(List<Public> publics) {
		this.publics = publics;
	}

}
