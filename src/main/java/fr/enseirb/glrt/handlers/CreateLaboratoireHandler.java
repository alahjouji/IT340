package fr.enseirb.glrt.handlers;

import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateLaboratoireHandler implements Route{
	private Model model;
	
	public CreateLaboratoireHandler(Model model){
		this.model=model;
	}

	@Override
	public Object handle(Request req, Response res) throws Exception {
		Laboratoire lab = new Laboratoire(req.params("nom"),req.params("responsable"),req.params("tel"),req.params("email"),req.params("motDePasse"));
		int labId = model.createLab(lab);
		return String.valueOf(labId);
	}
}
