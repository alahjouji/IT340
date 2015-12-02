package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;


public class TeacherLoginHandlerPost extends AbstractHandler{
	private Model model;
	public TeacherLoginHandlerPost(Model model) {
		this.model = model;
	}
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		if (sessionAtts.get("sessionTeacher") == null || sessionAtts.get("sessionTeacher").equals("0")) {
			Integer id = model.checkTeacher(urlParams.get("data[Teacher][email]")[0], urlParams.get("data[Teacher][password]")[0]);
			if(id!=0){
				Map<String, String> answer = new HashMap<String, String>();
				answer.put("sessionTeacher", id.toString());
				answer.put("redirect", "/teachers/dashboard");
				answer.put("response", "");
				return answer ;
			}else{
				Map<String, String> answer = new HashMap<String, String>();
				answer.put("redirect", "/teachers/login?warn=1");
				answer.put("response", "");
				return answer ;		
			}
		}else{
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/teachers/dashboard");
			answer.put("response", "");
			return answer ;
		}
	}



}
