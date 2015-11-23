package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class LabDashboardHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	
	public LabDashboardHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		super();
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException {
		if (sessionAtts.get("sessionLab") == null) {
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer ;
		} else {
			Integer sessionLab = Integer.parseInt(sessionAtts.get("sessionLab"));
			List<Atelier> ateliers = model.getAteliers(sessionLab);
			
			for (Atelier atelier : ateliers){
				List<String> disciplines = atelier.getDisciplines();
				List<String> disciplines1 = new ArrayList<String>();
				int i=0;
				while (i<disciplines.size()){
					if(i==disciplines.size()-1)
						disciplines1.add(disciplines.get(i));
					else
						disciplines1.add(disciplines.get(i) +",&nbsp;");
					i++;
				}
				
				atelier.setDisciplines(disciplines1);
			}
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("ateliers", ateliers);
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/dashboard.ftl")));
			return answer ;
		}		
	}


}
