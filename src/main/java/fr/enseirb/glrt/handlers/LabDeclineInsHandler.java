package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Model;


public class LabDeclineInsHandler extends AbstractHandler{

	private Model model;

	public LabDeclineInsHandler(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException{
		
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("insId")[0]);
	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    
		Map<String, String> answer = new HashMap<String, String>();
		if(!b || sessionAtts.get("sessionLab") == null || !model.inscriptionOfLab(Integer.parseInt(urlParams.get("insId")[0]), Integer.parseInt(sessionAtts.get("sessionLab")))){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			model.deleteInscription(Integer.parseInt(urlParams.get("insId")[0]));
			answer.put("redirect", "/labs/dashboard?good=5");
			answer.put("response", "");
			return answer ;
		}
	}




}
