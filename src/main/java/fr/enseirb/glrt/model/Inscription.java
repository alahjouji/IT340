package fr.enseirb.glrt.model;

public class Inscription {
	private int atelierId;
	private boolean validated;
	
	public Inscription(int atelierId, boolean validated) {
		this.setAtelierId(atelierId);
		this.setValidated(validated);
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
	
}
