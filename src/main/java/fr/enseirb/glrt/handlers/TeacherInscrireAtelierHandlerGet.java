package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;


public class TeacherInscrireAtelierHandlerGet extends AbstractHandler{

	private Model model;
	private FreeMarkerEngine freeMarkerEngine;

	public TeacherInscrireAtelierHandlerGet(Model model, FreeMarkerEngine freeMarkerEngine) {
		this.model = model;
		this.freeMarkerEngine = freeMarkerEngine;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("atelierId")[0]);
	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    
		Map<String, String> answer = new HashMap<String, String>();
		if(!b || sessionAtts.get("sessionTeacher") == null || sessionAtts.get("sessionTeacher").equals("0")){
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer;
		}else{
			Map<String, Object> attributes = new HashMap<>();
			Atelier at = model.getAtelier(Integer.parseInt(urlParams.get("atelierId")[0]));
			attributes.put("atelier", at);
			attributes.put("sessionTeacher", sessionAtts.get("sessionTeacher"));
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/teacherInscription.ftl")));
			return answer ;
		}
	}




}
