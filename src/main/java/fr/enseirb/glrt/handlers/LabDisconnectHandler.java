package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class LabDisconnectHandler extends AbstractHandler{

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		Map<String, String> answer = new HashMap<String, String>();
		if(sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab") == "0"){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			answer.put("sessionLab", "0");
			answer.put("redirect", "/");
			answer.put("response", "");
			return answer ;
		}
	}


}
