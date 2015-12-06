package fr.enseirb.glrt.model;

public class Inscription {
	private int id;
	private int teacherId;
	private int atelierId;
	private boolean validated;
	private String seance;
	private String pub;
	private int nombre;
	private String atelierName;
	private String teacherName;
	
	public Inscription(int id, int teacherId, int atelierId, boolean validated, String seance, String pub, int nombre) {
		this.setId(id);
		this.setTeacherId(teacherId);
		this.setAtelierId(atelierId);
		this.setValidated(validated);
		this.setSeance(seance);
		this.setPub(pub);
		this.setNombre(nombre);
	}
	public int getAtelierId() {
		return atelierId;
	}
	public void setAtelierId(int atelierId) {
		this.atelierId = atelierId;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public String getSeance() {
		return seance;
	}
	public void setSeance(String seance) {
		this.seance = seance;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public String getAtelierName() {
		return atelierName;
	}
	public void setAtelierName(String atelierName) {
		this.atelierName = atelierName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
