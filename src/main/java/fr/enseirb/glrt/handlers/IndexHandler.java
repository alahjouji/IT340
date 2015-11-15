package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

public class IndexHandler implements Route {

	private FreeMarkerEngine freeMarkerEngine;
	public IndexHandler(FreeMarkerEngine freeMarkerEngine) {
		this.freeMarkerEngine = freeMarkerEngine;
	}

	@Override
	public Object handle(Request request, Response response) {
		Map<String, Object> attributes = new HashMap<>();
		return freeMarkerEngine.render(new ModelAndView(attributes, "ftl/index.ftl"));
	}
}
