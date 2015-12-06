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

public class LabDashboardHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	public LabDashboardHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException {
		if (sessionAtts.get("sessionLab") == null) {
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer ;
		} else {
			Integer sessionLab = Integer.parseInt(sessionAtts.get("sessionLab"));
			List<Atelier> ateliers = model.getAteliers(sessionLab);
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("ateliers", ateliers);
			List<Inscription> inscriptions = model.getLabInscriptionsValidated(Integer.parseInt(sessionAtts.get("sessionLab")));
			List<Inscription> inscriptions1 = model.getLabInscriptionsWaiting(Integer.parseInt(sessionAtts.get("sessionLab")));
			for(Inscription ins : inscriptions){
				ins.setTeacherName(model.getTeacherNameFromId(ins.getTeacherId()));
				ins.setAtelierName(model.getAtelierNameFromId(ins.getAtelierId()));
			}
			for(Inscription ins : inscriptions1){
				ins.setTeacherName(model.getTeacherNameFromId(ins.getTeacherId()));
				ins.setAtelierName(model.getAtelierNameFromId(ins.getAtelierId()));
			}
			attributes.put("inscriptionsV", inscriptions);
			attributes.put("inscriptionsW", inscriptions1);

			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("1")){
				attributes.put("good", "Atelier créé avec succes");
			}
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("2")){
				attributes.put("good", "Atelier supprimé avec succes");
			}
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("3")){
				attributes.put("good", "Atelier modifié avec succes");
			}
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("4")){
				attributes.put("good", "Inscription validé avec succès");
			}
			if(urlParams.containsKey("good") && urlParams.get("good")[0].equals("5")){
				attributes.put("good", "Inscription decliné avec succès");
			}
			if(urlParams.containsKey("warn") && urlParams.get("warn")[0].equals("1")){
				attributes.put("warn", "Impossible de valider l'inscription car le nombre de places est insuffisant");
			}
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/labDashboard.ftl")));
			return answer ;
		}		
	}


}
