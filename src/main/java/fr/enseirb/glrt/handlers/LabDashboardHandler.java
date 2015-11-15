package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

public class LabDashboardHandler implements Route {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	
	public LabDashboardHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		super();
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		Integer labId = request.session().attribute("labId");
		
		if (labId == null) {
			response.redirect("/labs/login");
			return null;
		} else {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("ateliers", model.getAteliers(labId));
			return freeMarkerEngine.render(new ModelAndView(attributes, "ftl/dashboard.ftl"));
		}
	}

}
