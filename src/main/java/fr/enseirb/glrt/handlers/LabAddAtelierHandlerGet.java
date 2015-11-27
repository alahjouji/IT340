package fr.enseirb.glrt.handlers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabAddAtelierHandlerGet extends AbstractHandler{
	private FreeMarkerEngine freeMarkerEngine;
		
	public LabAddAtelierHandlerGet(FreeMarkerEngine freeMarkerEngine) {
		this.freeMarkerEngine = freeMarkerEngine;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts){
		Map<String, String> answer = new HashMap<String, String>();
		if(sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab").equals("0")){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			Map<String, Object> attributes = new HashMap<>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labAddAtelier.ftl")));
			return answer ;
		}

	}



}
