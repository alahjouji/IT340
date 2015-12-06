package fr.enseirb.glrt.handlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;

public class LabAddAtelierHandlerPost extends AbstractHandler {
	private Model model;

	public LabAddAtelierHandlerPost(Model model) {
		this.model = model;
	}

	@Override
	public Map<String, String> process(Map<String, String[]> urlParams, Map<String, String> sessionAtts)
			throws ClassNotFoundException, SQLException {
		Map<String, String> answer = new HashMap<String, String>();
		if (sessionAtts.get("sessionLab") == null) {
			answer.put("redirect", "/labs/login");
			answer.put("response", "");
			return answer;
		} else {
			String[] animateurs = urlParams.get("data[Atelier][animateurs]")[0].split(",");
			List<String> topics = new ArrayList<String>();
			for (String str : urlParams.get("data[Atelier][topics]")) {
				String topic;
				switch (Integer.parseInt(str)) {
				case 1:
					topic = "Anthropologie";
					break;
				case 2:
					topic = "Archéologie";
					break;
				case 3:
					topic = "Chimie";
					break;
				case 4:
					topic = "Environnement";
					break;
				case 5:
					topic = "Géographie";
					break;
				case 6:
					topic = "Histoire";
					break;
				case 7:
					topic = "Informatique";
					break;
				case 8:
					topic = "Mathématiques";
					break;
				case 9:
					topic = "Médecine - Santé";
					break;
				case 10:
					topic = "Philosophie";
					break;
				case 11:
					topic = "Physique";
					break;
				case 12:
					topic = "Physique - Chimie";
					break;
				case 13:
					topic = "Sciences de la Terre";
					break;
				case 14:
					topic = "Sciences de la vie";
					break;
				case 15:
					topic = "Sciences du numérique";
					break;
				case 16:
					topic = "Sciences économiques et sociales";
					break;
				default:
					topic = "";
					break;
				}
				topics.add(topic);
			}

			List<Seance> seances = new ArrayList<Seance>();
			for (String str : urlParams.get("data[Atelier][seances]")){
				String sean;
				switch (Integer.parseInt(str)) {
				case 1:
					sean = "Lundi matin";
					break;
				case 2:
					sean = "Lundi après-midi";
					break;
				case 3:
					sean = "Mardi matin";
					break;
				case 4:
					sean = "Mardi après-midi";
					break;
				case 5:
					sean = "Mercredi matin";
					break;
				case 6:
					sean = "Mercredi après-midi";
					break;
				case 7:
					sean = "Jeudi matin";
					break;
				case 8:
					sean = "Jeudi après-midi";
					break;
				case 9:
					sean = "Vendredi matin";
					break;
				case 10:
					sean = "Vendredi après-midi";
					break;
				default:
					sean = "";
					break;
				}
				seances.add(new Seance(sean, 0));
			}

			List<String> publics = new ArrayList<String>();
			for (String str : urlParams.get("data[Atelier][public]")){
				String publ;
				switch (Integer.parseInt(str)) {
				case 1:
					publ = "Primaires";
					break;
				case 2:
					publ = "6èmes et 5èmes";
					break;
				case 3:
					publ = "4èmes et 3èmes";
					break;
				case 4:
					publ = "Secondes";
					break;
				case 5:
					publ = "Premières";
					break;
				case 6:
					publ = "Terminales";
					break;
				case 7:
					publ = "Classes préparatoires";
					break;
				case 8:
					publ = "Universitaires";
					break;
				default:
					publ = "";
					break;
				}
				publics.add(publ);
			}

			String type;
			
			switch (Integer.parseInt(urlParams.get("data[Atelier][type]")[0])) {
			case 1:
				type = "Atelier Scientifique";
				break;
			case 2:
				type = "Exposition";
				break;
			case 3:
				type = "Conférence";
				break;
			case 4:
				type = "Visite";
				break;
			default:
				type = "";
				break;
			}
			
			Atelier atelier = new Atelier(Integer.parseInt(sessionAtts.get("sessionLab")),
					urlParams.get("data[Atelier][titre]")[0], topics, type, seances,
					urlParams.get("data[Atelier][lieu]")[0], Integer.parseInt(urlParams.get("data[Atelier][duree]")[0]),
					Integer.parseInt(urlParams.get("data[Atelier][capacite]")[0]),
					urlParams.get("data[Atelier][resume]")[0], Arrays.asList(animateurs), publics);
			model.createAtelier(atelier);
			answer.put("redirect", "/labs/dashboard?good=1");
			answer.put("response", "");
			return answer;
		}

	}

}
