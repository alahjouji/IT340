package fr.enseirb.glrt;

import fr.enseirb.glrt.handlers.IndexHandler;
import fr.enseirb.glrt.handlers.LabAddAtelierHandlerGet;
import fr.enseirb.glrt.handlers.LabAddAtelierHandlerPost;
import fr.enseirb.glrt.handlers.LabDashboardHandler;
import fr.enseirb.glrt.handlers.LabDisconnectHandler;
import fr.enseirb.glrt.handlers.LabLoginHandlerGet;
import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.handlers.LabRegisterHandlerGet;
import fr.enseirb.glrt.handlers.LabRegisterHandlerPost;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.enumerations.Jour;
import fr.enseirb.glrt.model.enumerations.Public;
import fr.enseirb.glrt.model.enumerations.Topics;
import freemarker.template.Configuration;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainServices {
	public static void main( String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Spark.staticFileLocation("/");

		String[] bddArgs = {"jdbc:h2:mem:it340", "user", "password"};
		Model model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		
		Laboratoire lab = new Laboratoire("aaa", "aaa", "06666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab );
		List<Topics> list = new ArrayList<Topics>();
		list.add(Topics.Anthropologie);
		list.add(Topics.Environnement);
		list.add(Topics.Geographie);
		
		List<Public> list3 = new ArrayList<Public>();
		list3.add(Public.Premiere);
		List<String> list2 = new ArrayList<String>();
		list2.add("bob");
		List<Jour> list1 = new ArrayList<Jour>();
		list1.add(Jour.JeudiAprem);
		Atelier atelier= new Atelier(1, "titre", list, "type", list1, "lieu", 1, 1, "resume", list2, list3);
		model.createAtelier(atelier);
		model.createAtelier(atelier);
		
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
		get("/labs/addAtelier", new LabAddAtelierHandlerGet(freeMarkerEngine));
		post("/labs/addAtelier", new LabAddAtelierHandlerPost(model));
		stop();
	}
}
