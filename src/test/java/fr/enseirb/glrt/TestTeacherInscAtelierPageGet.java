package fr.enseirb.glrt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.TeacherInscrireAtelierHandlerGet;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;
import fr.enseirb.glrt.model.Teacher;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestTeacherInscAtelierPageGet {

	private FreeMarkerEngine freeMarkerEngine;
	private Model model;

	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDefaultEncoding("UTF-8");
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		model.createSeanceTable();
		model.createTeacherTable();
		model.createInscriptionTable();
		
		Laboratoire lab = new Laboratoire("aaa", "aaa", "06666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);	
		List<String> list = new ArrayList<String>();
		list.add("Anthropologie");
		list.add("Environnement");
		list.add("Geographie");
		
		List<String> list3 = new ArrayList<String>();
		list3.add("Premières");
		list3.add("Secondes");
		List<String> list2 = new ArrayList<String>();
		list2.add("bob");
		list2.add("Martin");
		List<Seance> list1 = new ArrayList<Seance>();
		list1.add(new Seance("Lundi Matin",0));
		list1.add(new Seance("Jeudi Matin",0));

		
		Atelier atelier= new Atelier(1, " A la poursuite d'ennemis invisibles", list, "Atelier scientifique", list1, "1 avenue du Docteur Albert Schweitzer 33400 talence", 1, 1, "Cet Atelier est destiné aux personnes.", list2, list3);
		model.createAtelier(atelier);
		model.createAtelier(atelier);
		model.createAtelier(atelier);
		Teacher teacher = new Teacher("Bob Bob", "Enseirb", "077777", "bbb@bbb.bbb", "bbb");
		model.createTeacher(teacher );

	}
	
	@Test
	public void testHTMLResponse() throws IOException, ClassNotFoundException, SQLException {
		TeacherInscrireAtelierHandlerGet handler = new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		String responseHTML = handler.process(urlParams , sessionAtts).get("response");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherInsc.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
	}
	
	@Test
	public void testInscUnothorized() throws IOException, ClassNotFoundException, SQLException {
		TeacherInscrireAtelierHandlerGet handler = new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testIdNull() throws IOException, ClassNotFoundException, JSONException, SQLException {
		
		TeacherInscrireAtelierHandlerGet handler = new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	
	@Test
	public void testIdNotNumber() throws IOException, ClassNotFoundException, JSONException, SQLException {
		
		TeacherInscrireAtelierHandlerGet handler = new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"aa"};
		urlParams.put("atelierId", value );
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testIdNoTeacherSession() throws IOException, ClassNotFoundException, JSONException, SQLException {
		
		TeacherInscrireAtelierHandlerGet handler = new TeacherInscrireAtelierHandlerGet(model, freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}

}
