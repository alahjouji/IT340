package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractHandler implements Route{
	@Override
    public Object handle(Request request, Response response) throws ClassNotFoundException, SQLException{
		Map<String, String[]> urlParams = new HashMap<String, String[]>();

		for (String par : request.queryParams()){
			urlParams.put(par, request.queryParamsValues(par));
		}
		Map<String, String> sessionAtts = new HashMap<String, String>();

		for (String att : request.session().attributes()){
			sessionAtts.put(att, request.session().attribute(att));
		}
		Map<String, String> answer = process(urlParams, sessionAtts);
		if(answer.containsKey("redirect"))
			response.redirect(answer.get("redirect"));
		if(answer.containsKey("sessionLab"))
			request.session().attribute("sessionLab",answer.get("sessionLab"));
		return answer.get("response");
		
	}

	public  abstract Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts ) throws ClassNotFoundException, SQLException;
}
