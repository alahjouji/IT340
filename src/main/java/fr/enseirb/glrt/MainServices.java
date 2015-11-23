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

		String[] bddArgs = {"jdbc:h2:mem:it340", "user", "password"};
		Model model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		
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
		awaitInitialization();
	}
}
