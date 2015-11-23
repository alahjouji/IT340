package fr.enseirb.glrt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabDashboardHandler;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.enumerations.Jour;
import fr.enseirb.glrt.model.enumerations.Public;
import fr.enseirb.glrt.model.enumerations.Topics;
import freemarker.template.Configuration;

import spark.template.freemarker.FreeMarkerEngine;

public class TestLabDashboardPage {

	private Model model;
	private FreeMarkerEngine freeMarkerEngine;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
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
		
	}
	
	@Test
	public void testDashboard() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDashboardHandler handler = new LabDashboardHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		
		String responseHTML = handler.process(urlParams , sessionAtts).get("response");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labDashboard.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);
	}
	
	@Test
	public void testDashboardUnothorized() throws IOException, ClassNotFoundException, SQLException {
		LabDashboardHandler handler = new LabDashboardHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}

}