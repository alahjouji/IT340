package fr.enseirb.glrt;

import static org.junit.Assert.assertEquals;
import static spark.Spark.stop;

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

import fr.enseirb.glrt.handlers.AtelierHandler;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestAtelierPage {

	private Model model;
	private FreeMarkerEngine freeMarkerEngine;

	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDefaultEncoding("UTF-8");
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		model.createSeanceTable();


		Laboratoire lab = new Laboratoire("CNRS", "Milan Kaback", "06666666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab );
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
	}

	@Test
	public void testHTMLResponse() throws IOException, ClassNotFoundException, JSONException, SQLException {
		AtelierHandler handler = new AtelierHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		
		String responseHTML = handler.process(urlParams , sessionAtts).get("response");
		String expectedHTML = IOUtils.toString(new FileInputStream("src/test/resources/atelier.html"), "UTF-8");
		assertEquals(responseHTML, expectedHTML);
	}

	@Test
	public void testIdNull() throws IOException, ClassNotFoundException, JSONException, SQLException {
		
		AtelierHandler handler = new AtelierHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testIdNotNumber() throws IOException, ClassNotFoundException, JSONException, SQLException {
		
		AtelierHandler handler = new AtelierHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"aa"};
		urlParams.put("atelierId", value );
		assertEquals("/", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
		stop();
	}
}