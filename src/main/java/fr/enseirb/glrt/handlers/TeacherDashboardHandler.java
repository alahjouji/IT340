package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.enseirb.glrt.model.Inscription;
import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class TeacherDashboardHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	public TeacherDashboardHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException {
		if (sessionAtts.get("sessionTeacher") == null) {
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer ;
		} else {
			Map<String, Object> attributes = new HashMap<>();
			List<Inscription> inscriptions = model.getTeacherInscriptionsValidated(sessionAtts.get("sessionTeacher"));
			for(Inscription ins : inscriptions)
				ins.setAtelierName(model.getAtelierNameFromId(ins.getAtelierId()));
			List<Inscription> inscriptions1 = model.getTeacherInscriptionsWaiting(sessionAtts.get("sessionTeacher"));
			for(Inscription ins : inscriptions1)
				ins.setAtelierName(model.getAtelierNameFromId(ins.getAtelierId()));
			attributes.put("inscriptionsV", inscriptions);
			attributes.put("inscriptionsW", inscriptions1);

			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/teacherDashboard.ftl")));
			return answer ;
		}		
	}


}
