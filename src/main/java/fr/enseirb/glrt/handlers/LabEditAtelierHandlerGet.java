package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabEditAtelierHandlerGet extends AbstractHandler{
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
		
	public LabEditAtelierHandlerGet(FreeMarkerEngine freeMarkerEngine, Model model) {
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws NumberFormatException, SQLException{
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("atelierId")[0]);
	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    
		Map<String, String> answer = new HashMap<String, String>();
		if(!b || sessionAtts.get("sessionLab") == null || !model.atelierOfLab(Integer.parseInt(urlParams.get("atelierId")[0]), Integer.parseInt(sessionAtts.get("sessionLab")))){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("atelier", model.getAtelier(Integer.parseInt(urlParams.get("atelierId")[0])));
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labEditAtelier.ftl")));
			return answer ;
		}

	}



}
