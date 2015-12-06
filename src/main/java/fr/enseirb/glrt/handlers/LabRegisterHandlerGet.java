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
		if (sessionAtts.get("sessionLab") == null) {
			Map<String, String> answer = new HashMap<String, String>();
			Map<String, Object> attributes = new HashMap<>();
			if(urlParams.containsKey("warn") && urlParams.get("warn")[0].equals("1")){
				attributes.put("warn", "Adresse mail existe déjà");
			}
			if(urlParams.containsKey("warn") && urlParams.get("warn")[0].equals("2")){
				attributes.put("warn", "Mots de passe non similaires");
			}
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
