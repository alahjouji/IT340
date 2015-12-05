package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Inscription;
import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class TeacherListAtelierHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	public TeacherListAtelierHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException {
		if (sessionAtts.get("sessionTeacher") == null || sessionAtts.get("sessionTeacher").equals("0")) {
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer ;
		} else {
			List<Atelier> ateliers = model.getAllAteliers();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("ateliers", ateliers);
			List<Inscription> inscriptions = model.getTeacherInscriptionsValidated(sessionAtts.get("sessionTeacher"));
			attributes.put("inscriptionsV", inscriptions);
			List<Inscription> inscriptions1 = model.getTeacherInscriptionsWaiting(sessionAtts.get("sessionTeacher"));
			attributes.put("inscriptionsW", inscriptions1);
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("1")){
				attributes.put("good", "Demande d'inscription envoyé vers le laboratoire");
			}
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/listAtelier.ftl")));
			return answer ;
		}		
	}


}
