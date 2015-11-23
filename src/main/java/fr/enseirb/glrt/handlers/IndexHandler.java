package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class IndexHandler extends AbstractHandler {

	private FreeMarkerEngine freeMarkerEngine;
	public IndexHandler(FreeMarkerEngine freeMarkerEngine) {
		this.freeMarkerEngine = freeMarkerEngine;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) {
		Map<String, Object> attributes = new HashMap<>();
		Map<String, String> answer = new HashMap<String, String>();
		answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/index.ftl")));
		return answer;
	}


}
