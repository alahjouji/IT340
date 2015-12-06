package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabLoginHandlerGet extends AbstractHandler{
	private FreeMarkerEngine freeMarkerEngine;
		
	public LabLoginHandlerGet(FreeMarkerEngine freeMarkerEngine) {
		this.freeMarkerEngine = freeMarkerEngine;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts){
		if (sessionAtts.get("sessionLab") == null) {
			Map<String, Object> attributes = new HashMap<>();
			if(urlParams.containsKey("warn") && urlParams.get("warn")[0].equals("1")){
				attributes.put("warn", "Email ou mot de passe incorrecte");
			}
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("1")){
				attributes.put("good", "Laboratoire créé avec succes");
			}
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labLogin.ftl")));
			return answer ;
		}else{
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/labs/dashboard");
			answer.put("response", "");
			return answer ;
		}
	}



}
