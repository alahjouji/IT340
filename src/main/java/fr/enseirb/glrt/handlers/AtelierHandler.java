package fr.enseirb.glrt.handlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import fr.enseirb.glrt.GoogleMaps;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class AtelierHandler extends AbstractHandler {
	private FreeMarkerEngine freeMarkerEngine;
	private Model model;
	public AtelierHandler(FreeMarkerEngine freeMarkerEngine, Model model) {
		this.freeMarkerEngine = freeMarkerEngine;
		this.model = model;
	}
	
	
	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts) throws ClassNotFoundException, SQLException, JSONException, IOException {
		
		boolean b = true;
	    try { 
	        Integer.parseInt(urlParams.get("atelierId")[0]);
	    } catch(NumberFormatException e) { 
	        b = false; 
	    } catch(NullPointerException e) {
	        b = false;
	    }
		
	    if(b){
	    	b = model.checkAtelierId(Integer.parseInt(urlParams.get("atelierId")[0]));
	    }
	    
		if (!b) {
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("redirect", "/");
			answer.put("response", "");
			return answer ;
		} else {
			Integer atelierId = Integer.parseInt(urlParams.get("atelierId")[0]);
			Atelier atelier = model.getAtelier(atelierId);

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("atelier", atelier);
			attributes.put("latitude", GoogleMaps.getLatLon(atelier.getLieu())[0]);
			attributes.put("longitude", GoogleMaps.getLatLon(atelier.getLieu())[1]);
			Map<String, String> answer = new HashMap<String, String>();
			answer.put("response", freeMarkerEngine.render(new ModelAndView(attributes, "ftl/atelier.ftl")));
			return answer ;
		}
	}


}
