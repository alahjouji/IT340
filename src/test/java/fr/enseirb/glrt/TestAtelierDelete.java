package fr.enseirb.glrt;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabDeleteAtelierHandler;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;

public class TestAtelierDelete {

	private Model model;

	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
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
	}
	
	@Test
	public void testDelete() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDeleteAtelierHandler handler = new LabDeleteAtelierHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value);
		assertEquals("/labs/dashboard?good=2", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testDeleteIdNull() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDeleteAtelierHandler handler = new LabDeleteAtelierHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testDeleteIdNotNumber() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDeleteAtelierHandler handler = new LabDeleteAtelierHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"aa"};
		urlParams.put("atelierId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testDeleteNotOfLab() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDeleteAtelierHandler handler = new LabDeleteAtelierHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "2");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@Test
	public void testDeleteNoSessionLab() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabDeleteAtelierHandler handler = new LabDeleteAtelierHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}
}
