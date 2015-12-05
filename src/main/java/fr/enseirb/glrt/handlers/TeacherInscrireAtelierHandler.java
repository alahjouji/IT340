package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;


public class TeacherInscrireAtelierHandler extends AbstractHandler{

	private Model model;

	public TeacherInscrireAtelierHandler(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("atelierId")[0]);
	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    
		Map<String, String> answer = new HashMap<String, String>();
		if(!b || sessionAtts.get("sessionTeacher") == null || sessionAtts.get("sessionTeacher").equals("0")){
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer;
		}else{
			model.teacherInscrireAtelier(sessionAtts.get("sessionTeacher"),urlParams.get("atelierId")[0]);
			answer.put("redirect", "/teachers/listAteliers?good=1");
			answer.put("response", "");
			return answer ;
		}
	}




}
