package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class TeacherDisconnectHandler extends AbstractHandler{

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		Map<String, String> answer = new HashMap<String, String>();
		if(sessionAtts.get("sessionTeacher") == null || sessionAtts.get("sessionTeacher").equals("0")){
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer;
		}else{
			answer.put("sessionTeacher", "0");
			answer.put("redirect", "/");
			answer.put("response", "");
			return answer ;
		}
	}


}
