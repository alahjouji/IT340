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
		List<String> list = new ArrayList<String>();
		list.add("eee");
		list.add("eee");
		list.add("eee");
		
		Atelier atelier= new Atelier(1, "titre", list, "type", list, "lieu", 1, 1, "resume", list, list);
		model.createAtelier(atelier);
		model.createAtelier(atelier);
		
	}
	
	@Test
	public void testDashboard() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDashboardHandler handler = new LabDashboardHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String> urlParams = new HashMap<String, String>();
		
		String responseHTML = handler.process(urlParams , sessionAtts).get("response");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labDashboard.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);
	}
	
	@Test
	public void testDashboardnUnothorized() throws IOException, ClassNotFoundException, SQLException {
		LabDashboardHandler handler = new LabDashboardHandler(freeMarkerEngine, model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String> urlParams = new HashMap<String, String>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}

}
