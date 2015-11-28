package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabRegisterHandlerGet extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;

	public LabRegisterHandlerGet(FreeMarkerEngine freeMarkerEngine) {
		super();
		this.freeMarkerEngine = freeMarkerEngine;
	}
	
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) {
		if (sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab").equals("0")) {
			Map<String, String> answer = new HashMap<String, String>();
			Map<String, Object> attributes = new HashMap<>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labRegister.ftl")));
			return answer;
		}else{
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/labs/dashboard");
			answer.put("response", "");
			return answer ;
		}

	}
}
