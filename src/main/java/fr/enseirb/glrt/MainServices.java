package fr.enseirb.glrt;

import fr.enseirb.glrt.handlers.IndexHandler;
import fr.enseirb.glrt.handlers.LabDashboardHandler;
import fr.enseirb.glrt.handlers.LabLoginHandlerGet;
import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.handlers.LabRegisterHandlerGet;
import fr.enseirb.glrt.handlers.LabRegisterHandlerPost;
import fr.enseirb.glrt.model.Model;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MainServices {
	public static void main( String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Spark.staticFileLocation("/");

		String bddURL = "jdbc:h2:~/it340";
		Model model = new Model(bddURL);
//		model.createLabTable();
//		model.createAtelierTable();
		
		
		
//		Atelier a = new Atelier();
//		a.setTitre("ghghhhhhhhhhjkkkkkkkkkkkkkkhhhhhh");
//		a.setType("ghghkjhj");
//		List<String> disciplines = new ArrayList<String>();
//		disciplines.add("eee");
//		disciplines.add("eee");
//		disciplines.add("eee");
//		a.setCapacite(12);
//		a.setDuree(12);
//		a.setId(1);
//		a.setLabId(1);
//		a.setResume("fffgg");
//		a.setLieu("fggg");
//		a.setDisciplines(disciplines );
//		a.setAnimateurs(disciplines);
//		a.setSeances(disciplines);
//		a.setPublics(disciplines);
//		model.createAtelier(a);
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		get("/", new IndexHandler(freeMarkerEngine));
		get("/labs/login", new LabLoginHandlerGet(freeMarkerEngine));
		post("/labs/login", new LabLoginHandlerPost(model));
		get("/labs/register", new LabRegisterHandlerGet(freeMarkerEngine));
		post("/labs/register", new LabRegisterHandlerPost(model));
		get("/labs/dashboard", new LabDashboardHandler(freeMarkerEngine, model));
		stop();
	}
}
