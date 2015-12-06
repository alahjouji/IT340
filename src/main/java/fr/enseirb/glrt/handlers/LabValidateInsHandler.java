package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Inscription;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;


public class LabValidateInsHandler extends AbstractHandler{

	private Model model;

	public LabValidateInsHandler(Model model) {
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
		if(!b || sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab").equals("0") || !model.inscriptionOfLab(Integer.parseInt(urlParams.get("insId")[0]), Integer.parseInt(sessionAtts.get("sessionLab")))){
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		}else{
			Inscription ins = model.getInscription(Integer.parseInt(urlParams.get("insId")[0]));
			int insc =0;
			Atelier at = model.getAtelier(ins.getAtelierId());
			for(Seance s : at.getSeances()){
				if(s.getNom().equals(ins.getSeance()))
					insc = s.getInscrit();
			}
			if(ins.getNombre()>at.getCapacite()-insc){
				model.deleteInscription(Integer.parseInt(urlParams.get("insId")[0]));
				answer.put("redirect", "/labs/dashboard?warn=1");
				answer.put("response", "");				
			}else{
				model.validateInscription(Integer.parseInt(urlParams.get("insId")[0]));
				answer.put("redirect", "/labs/dashboard?good=4");
				answer.put("response", "");				
			}

			return answer ;
		}
	}




}
