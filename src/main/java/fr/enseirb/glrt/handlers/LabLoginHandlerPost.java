package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;


public class LabLoginHandlerPost extends AbstractHandler{
	private Model model;
	public LabLoginHandlerPost(Model model) {
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		if (sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab").equals("0")) {
			Integer id = model.checkLab(urlParams.get("data[Lab][email]")[0], urlParams.get("data[Lab][password]")[0]);
			if(id!=0){
				Map<String, String> answer = new HashMap<String, String>();
				answer.put("sessionLab", id.toString());
				answer.put("redirect", "/labs/dashboard");
				answer.put("response", "");
				return answer ;
			}else{
				Map<String, String> answer = new HashMap<String, String>();
				answer.put("redirect", "/labs/login?warn=1");
				answer.put("response", "");
				return answer ;		
			}
		}else{
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/labs/dashboard");
			answer.put("response", "");
			return answer ;
		}
	}



}
