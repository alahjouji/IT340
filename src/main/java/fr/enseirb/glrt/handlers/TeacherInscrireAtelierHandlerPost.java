package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;


public class TeacherInscrireAtelierHandlerPost extends AbstractHandler{

	private Model model;

	public TeacherInscrireAtelierHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("atelierId")[0]);
	        Integer.parseInt(urlParams.get("nombre")[0]);
	        urlParams.get("seance")[0].toString();
	        urlParams.get("public")[0].toString();

	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    
		Map<String, String> answer = new HashMap<String, String>();
		if(!b || sessionAtts.get("sessionTeacher") == null){
			answer.put("redirect", "/teachers/login");
			answer.put("response", "");
			return answer;
		}else{
			Atelier at = model.getAtelier(Integer.parseInt(urlParams.get("atelierId")[0]));
			int ins =0;
			for(Seance s : at.getSeances()){
				if(s.getNom().equals(urlParams.get("seance")[0]))
					ins = s.getInscrit();
			}
			if(Integer.parseInt(urlParams.get("nombre")[0])>at.getCapacite()-ins){
				answer.put("redirect", "/teachers/listAteliers?warn=1");
				answer.put("response", "");
			}
			else{
				model.teacherInscrireAtelier(sessionAtts.get("sessionTeacher"),urlParams.get("atelierId")[0],urlParams.get("seance")[0],urlParams.get("public")[0],Integer.parseInt(urlParams.get("nombre")[0]));
				answer.put("redirect", "/teachers/listAteliers?good=1");
				answer.put("response", "");
			}
			return answer ;
		}
	}




}
