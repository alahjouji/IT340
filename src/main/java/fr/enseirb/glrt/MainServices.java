package fr.enseirb.glrt;

import fr.enseirb.glrt.handlers.IndexHandler;
import fr.enseirb.glrt.handlers.LabDashboardHandler;
import fr.enseirb.glrt.handlers.LabDisconnectHandler;
import fr.enseirb.glrt.handlers.LabLoginHandlerGet;
import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.handlers.LabRegisterHandlerGet;
import fr.enseirb.glrt.handlers.LabRegisterHandlerPost;
//import fr.enseirb.glrt.model.Atelier;
//import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

public class MainServices {
	public static void main( String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Spark.staticFileLocation("/");

		String[] bddArgs = {"jdbc:h2:~/it340", "user", "password"};
		Model model = new Model(bddArgs);
//		model.createLabTable();
//		model.createAtelierTable();
//		
//		Laboratoire lab = new Laboratoire("aaa", "aaa", "06666", "aaa@aaa.aaa", "aaa");
//		model.createLab(lab );
//		List<String> list = new ArrayList<String>();
//		list.add("eee");
//		list.add("eee");
//		list.add("eee");
//		
//		Atelier atelier= new Atelier(1, "titre", list, "type", list, "lieu", 1, 1, "resume", list, list);
//		model.createAtelier(atelier);
//		model.createAtelier(atelier);
		
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
		get("/labs/disconnect", new LabDisconnectHandler());
		stop();
	}
}
