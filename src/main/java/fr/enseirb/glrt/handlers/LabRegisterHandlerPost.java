package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;

public class LabRegisterHandlerPost extends AbstractHandler {
	private Model model;

	public LabRegisterHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts)
			throws ClassNotFoundException, SQLException {
		if (sessionAtts.get("sessionLab") == null || sessionAtts.get("sessionLab").equals("0")) {
			Map<String, String> answer = new HashMap<String, String>();
			Laboratoire lab = new Laboratoire(urlParams.get("data[Lab][nom]")[0], urlParams.get("data[Lab][respo]")[0],
					urlParams.get("data[Lab][tel]")[0], urlParams.get("data[Lab][email]")[0],
					urlParams.get("data[Lab][password]")[0]);
			model.createLab(lab);
			answer.put("redirect", "/labs/login?good=1");
			answer.put("response", "");
			return answer;
	}else{
		Map<String, String> answer = new HashMap<String, String>();
		answer.put("redirect", "/labs/dashboard");
		answer.put("response", "");
		return answer ;
	}
}

}
