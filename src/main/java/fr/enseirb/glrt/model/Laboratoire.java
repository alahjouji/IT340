package fr.enseirb.glrt.model;

public class Laboratoire {
	private String nom;
	private String responsable;
	private String tel;
	private String email;
	private String motDePasse;

	public Laboratoire(String nom, String responsable, String tel, String email, String motDePasse) {
		this.setNom(nom);
		this.setResponsable(responsable);
		this.setTel(tel);
		this.setEmail(email);
		this.setMotDePasse(motDePasse);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
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
}
