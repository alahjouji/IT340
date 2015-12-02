package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class TeacherDashboardHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	@SuppressWarnings("unused")
	private Model model;
	public TeacherDashboardHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
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
			//Integer sessionLab = Integer.parseInt(sessionAtts.get("sessionTeacher"));
			Map<String, Object> attributes = new HashMap<>();

			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/teacherDashboard.ftl")));
			return answer ;
		}		
	}


}
