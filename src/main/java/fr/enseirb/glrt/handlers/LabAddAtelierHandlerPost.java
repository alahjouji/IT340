package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.enumerations.Jour;
import fr.enseirb.glrt.model.enumerations.Public;
import fr.enseirb.glrt.model.enumerations.Topics;


public class LabAddAtelierHandlerPost extends AbstractHandler{
	private Model model;
	public LabAddAtelierHandlerPost(Model model) {
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		Map<String, String> answer = new HashMap<String, String>();
		if(sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab") == "0"){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			String[] animateurs = urlParams.get("data[Atelier][animateurs]")[0].split(",");
			List<Topics> topics = new ArrayList<Topics>();
			for(String str : urlParams.get("data[Atelier][topics]"))
				topics.add(Topics.values()[Integer.parseInt(str)]);
			
			List<Jour> seances = new ArrayList<Jour>();
			for(String str : urlParams.get("data[Atelier][seances]"))
				seances.add(Jour.values()[Integer.parseInt(str)]);
			
			List<Public> publics = new ArrayList<Public>();
			for(String str : urlParams.get("data[Atelier][public]"))
				publics.add(Public.values()[Integer.parseInt(str)]);
			
			Atelier atelier = new Atelier(Integer.parseInt(sessionAtts.get("sessionLab")), urlParams.get("data[Atelier][titre]")[0], topics ,urlParams.get("data[Atelier][type]")[0], seances,urlParams.get("data[Atelier][lieu]")[0],Integer.parseInt(urlParams.get("data[Atelier][duree]")[0]),Integer.parseInt(urlParams.get("data[Atelier][capacite]")[0]),urlParams.get("data[Atelier][resume]")[0],Arrays.asList(animateurs),publics);
			model.createAtelier(atelier );
			answer.put("redirect", "/labs/dashboard");
			answer.put("response", "");
			return answer ;
		}
	
	}



}
