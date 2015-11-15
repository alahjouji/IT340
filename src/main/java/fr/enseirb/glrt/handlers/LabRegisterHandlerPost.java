package fr.enseirb.glrt.handlers;

import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import spark.Request;
import spark.Response;
import spark.Route;

public class LabRegisterHandlerPost implements Route{
	private Model model;

	public LabRegisterHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		Laboratoire lab = new Laboratoire(request.queryParams("data[Lab][nom]"),request.queryParams("data[Lab][respo]"),request.queryParams("data[Lab][tel]"),request.queryParams("data[Lab][email]"),request.queryParams("data[Lab][password]"));
		System.out.println(model.createLab(lab)); 
		response.redirect("/labs/login");
		return null;
	}

}
