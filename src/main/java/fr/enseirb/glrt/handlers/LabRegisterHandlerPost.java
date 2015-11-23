package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;


public class LabRegisterHandlerPost extends AbstractHandler{
	private Model model;

	public LabRegisterHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		Map<String, String> answer = new HashMap<String, String>();
		Laboratoire lab = new Laboratoire(urlParams.get("data[Lab][nom]"),urlParams.get("data[Lab][respo]"),urlParams.get("data[Lab][tel]"),urlParams.get("data[Lab][email]"),urlParams.get("data[Lab][password]"));
		model.createLab(lab); 
		answer.put("redirect", "/labs/login");
		answer.put("response", "");
		return answer ;
	}


}
