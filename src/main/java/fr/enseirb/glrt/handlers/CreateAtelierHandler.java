
package fr.enseirb.glrt.handlers;


import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateAtelierHandler implements Route{
	private Model model;
	
	public CreateAtelierHandler(Model model){
		this.model=model;
	}

	@Override
	public Object handle(Request req, Response res) throws Exception {
		Laboratoire lab = new Laboratoire(req.params("nom"),req.params("responsable"),req.params("tel"),req.params("email"),req.params("motDePasse"));
		int labId = model.createLab(lab);
		String answer = String.valueOf(labId);
		res.type("text/html");
		res.body(answer);
		return answer;
	}
}
