package fr.enseirb.glrt.model;

import java.util.List;

public class Teacher {
	private String nom;
	private String etablissement;
	private String tel;
	private String email;
	private String motDePasse;
	private List<Inscription> inscriptions;

	public Teacher(String nom, String etablissement, String tel, String email, String motDePasse, List<Inscription> inscriptions) {
		this.setNom(nom);
		this.setEtablissement(etablissement);
		this.setTel(tel);
		this.setEmail(email);
		this.setMotDePasse(motDePasse);
		this.setInscriptions(inscriptions);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
}
