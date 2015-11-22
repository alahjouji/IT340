package fr.enseirb.glrt.handlers;

import java.net.HttpURLConnection;

import fr.enseirb.glrt.model.Model;
import spark.Request;
import spark.Response;
import spark.Route;

public class LabLoginHandlerPost implements Route{
	private Model model;
	public LabLoginHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		int id = model.checkLab(request.queryParams("data[Lab][email]"), request.queryParams("data[Lab][password]"));
		if(id!=0){
			request.session().attribute("labId",id);
			response.redirect("/labs/dashboard", HttpURLConnection.HTTP_ACCEPTED);
		}else{
			response.redirect("/labs/login", HttpURLConnection.HTTP_UNAUTHORIZED);
		}
		return null;
	}

}
