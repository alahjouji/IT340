package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;


public class LabDeleteAtelierHandler extends AbstractHandler{

	private Model model;

	public LabDeleteAtelierHandler(Model model) {
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
		if(!b || sessionAtts.get("sessionLab") == null || !model.atelierOfLab(Integer.parseInt(urlParams.get("atelierId")[0]), Integer.parseInt(sessionAtts.get("sessionLab")))){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			model.deleteAtelier(Integer.parseInt(urlParams.get("atelierId")[0]));
			answer.put("redirect", "/labs/dashboard?good=2");
			answer.put("response", "");
			return answer ;
		}
	}




}
