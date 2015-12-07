package fr.enseirb.glrt;

import fr.enseirb.glrt.handlers.AtelierHandler;
import fr.enseirb.glrt.handlers.IndexHandler;
import fr.enseirb.glrt.handlers.LabAddAtelierHandlerGet;
import fr.enseirb.glrt.handlers.LabAddAtelierHandlerPost;
import fr.enseirb.glrt.handlers.LabDashboardHandler;
import fr.enseirb.glrt.handlers.LabDeclineInsHandler;
import fr.enseirb.glrt.handlers.LabDeleteAtelierHandler;
import fr.enseirb.glrt.handlers.LabDisconnectHandler;
import fr.enseirb.glrt.handlers.LabEditAtelierHandlerGet;
import fr.enseirb.glrt.handlers.LabEditAtelierHandlerPost;
import fr.enseirb.glrt.handlers.LabLoginHandlerGet;
import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.handlers.LabRegisterHandlerGet;
import fr.enseirb.glrt.handlers.LabRegisterHandlerPost;
import fr.enseirb.glrt.handlers.LabValidateInsHandler;
import fr.enseirb.glrt.handlers.TeacherDashboardHandler;
import fr.enseirb.glrt.handlers.TeacherDisconnectHandler;
import fr.enseirb.glrt.handlers.TeacherInscrireAtelierHandlerPost;
import fr.enseirb.glrt.handlers.TeacherInscrireAtelierHandlerGet;
import fr.enseirb.glrt.handlers.TeacherListAtelierHandler;
import fr.enseirb.glrt.handlers.TeacherLoginHandlerGet;
import fr.enseirb.glrt.handlers.TeacherLoginHandlerPost;
import fr.enseirb.glrt.handlers.TeacherRegisterHandlerGet;
import fr.enseirb.glrt.handlers.TeacherRegisterHandlerPost;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Inscription;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;
import fr.enseirb.glrt.model.Teacher;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainServices {
	
	public static void main( String[] args) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException {
		String[] bddArgs = {"jdbc:h2:mem:it340", "user", "password"};
		Model model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		model.createSeanceTable();
		model.createTeacherTable();
		model.createInscriptionTable();
		
		Laboratoire lab = new Laboratoire("CNRS", "Milan Kaback", "06666666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab );
		Teacher teacher = new Teacher("Bob Bob", "Enseirb", "077777", "bbb@bbb.bbb", "bbb",new ArrayList<Inscription>());
		model.createTeacher(teacher );
		
		List<String> list = new ArrayList<String>();
		list.add("Anthropologie");
		list.add("Environnement");
		list.add("Géographie");
		
		List<String> list3 = new ArrayList<String>();
		list3.add("Premières");
		list3.add("Secondes");
		List<String> list2 = new ArrayList<String>();
		list2.add("bob");
		list2.add("Martin");
		List<Seance> list1 = new ArrayList<Seance>();
		list1.add(new Seance("Lundi matin",0));
		list1.add(new Seance("Jeudi matin",0));

		
		Atelier atelier= new Atelier(1, " A la poursuite d'ennemis invisibles", list, "Visite", list1, "1 avenue du Docteur Albert Schweitzer 33400 talence", 1, 100, "Cet Atelier est destiné aux personnes.", list2, list3);
		model.createAtelier(atelier);
		model.createAtelier(atelier);

		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDefaultEncoding("UTF-8");
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		staticFileLocation("/");
		get("/", new IndexHandler(freeMarkerEngine));
		get("/labs/login", new LabLoginHandlerGet(freeMarkerEngine));
		post("/labs/login", new LabLoginHandlerPost(model));
		get("/labs/register", new LabRegisterHandlerGet(freeMarkerEngine));
		post("/labs/register", new LabRegisterHandlerPost(model));
		get("/labs/dashboard", new LabDashboardHandler(freeMarkerEngine, model));
		get("/labs/disconnect", new LabDisconnectHandler());
		get("/labs/addAtelier", new LabAddAtelierHandlerGet(freeMarkerEngine));
		post("/labs/addAtelier", new LabAddAtelierHandlerPost(model));
		get("/atelier", new AtelierHandler(freeMarkerEngine, model));
		get("/labs/deleteAtelier", new LabDeleteAtelierHandler(model));
		get("/labs/editAtelier", new LabEditAtelierHandlerGet(freeMarkerEngine,model));
		post("/labs/editAtelier", new LabEditAtelierHandlerPost(model));
		get("/teachers/login", new TeacherLoginHandlerGet(freeMarkerEngine));
		post("/teachers/login", new TeacherLoginHandlerPost(model));
		get("/teachers/register", new TeacherRegisterHandlerGet(freeMarkerEngine));
		post("/teachers/register", new TeacherRegisterHandlerPost(model));
		get("/teachers/dashboard", new TeacherDashboardHandler(freeMarkerEngine, model));
		get("/teachers/disconnect", new TeacherDisconnectHandler());
		get("/teachers/listAteliers", new TeacherListAtelierHandler(freeMarkerEngine, model));
		get("/teachers/sinscrireAtelier", new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine));
		post("/teachers/sinscrireAtelier", new TeacherInscrireAtelierHandlerPost(model));
		get("/labs/declineIns", new LabDeclineInsHandler(model));
		get("/labs/validateIns", new LabValidateInsHandler(model));

		awaitInitialization();
	}
}
