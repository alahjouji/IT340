package fr.enseirb.glrt.handlers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Inscription;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Teacher;

public class TeacherRegisterHandlerPost extends AbstractHandler {
	private Model model;

	public TeacherRegisterHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		if (sessionAtts.get("sessionTeacher") == null) {
			
			if(model.checkTeacherExiste(urlParams.get("data[Teacher][email]")[0])){
				Map<String, String> answer = new HashMap<String, String>();
				answer.put("redirect", "/teachers/register?warn=1");
				answer.put("response", "");
				return answer;
			}else{
				if(!urlParams.get("data[Teacher][password]")[0].equals(urlParams.get("data[Teacher][password2]")[0])){
					Map<String, String> answer = new HashMap<String, String>();
					answer.put("redirect", "/teachers/register?warn=2");
					answer.put("response", "");
					return answer;
				}else{
					Map<String, String> answer = new HashMap<String, String>();
					Teacher teacher = new Teacher(urlParams.get("data[Teacher][nom]")[0], urlParams.get("data[Teacher][etab]")[0],
							urlParams.get("data[Teacher][tel]")[0], urlParams.get("data[Teacher][email]")[0],
							urlParams.get("data[Teacher][password]")[0], new ArrayList<Inscription>());
					model.createTeacher(teacher);
					answer.put("redirect", "/teachers/login?good=1");
					answer.put("response", "");
					return answer;
				}
			}
	}else{
		Map<String, String> answer = new HashMap<String, String>();
		answer.put("redirect", "/teachers/dashboard");
		answer.put("response", "");
		return answer ;
	}
}

}
