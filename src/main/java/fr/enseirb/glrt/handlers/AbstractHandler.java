package fr.enseirb.glrt.handlers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractHandler implements Route{
	@Override
    public Object handle(Request request, Response response) throws ClassNotFoundException, SQLException, JSONException, IOException, NoSuchAlgorithmException{
		Map<String, String[]> urlParams = new HashMap<String, String[]>();

		for (String par : request.queryParams()){
			urlParams.put(par, request.queryParamsValues(par));
		}
		Map<String, String> sessionAtts = new HashMap<String, String>();
		for (String att : request.session().attributes()){
			sessionAtts.put(att, request.session().attribute(att));
		}

		Map<String, String> answer = process(urlParams, sessionAtts);

		if(answer.containsKey("sessionLab")){
			request.session().attribute("sessionLab",answer.get("sessionLab"));
		}
		if(answer.containsKey("sessionTeacher")){
			request.session().attribute("sessionTeacher",answer.get("sessionTeacher"));
		}

		if(answer.containsKey("redirect"))
			response.redirect(answer.get("redirect"));
		return answer.get("response");
		
	}

	public  abstract Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts ) throws ClassNotFoundException, SQLException, JSONException, IOException, NoSuchAlgorithmException;
}
