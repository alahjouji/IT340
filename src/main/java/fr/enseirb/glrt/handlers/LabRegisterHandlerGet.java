package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabRegisterHandlerGet extends AbstractHandler{
	private FreeMarkerEngine freeMarkerEngine;

	public LabRegisterHandlerGet(FreeMarkerEngine freeMarkerEngine) {
		super();
		this.freeMarkerEngine = freeMarkerEngine;
	}

	@Override
	public Map<String, String> process(Map<String, String> urlParams, Map<String, String> sessionAtts){
		Map<String, String> answer = new HashMap<String, String>();
		Map<String, Object> attributes = new HashMap<>();
		answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labRegister.ftl")));
		return answer;
	}



}
