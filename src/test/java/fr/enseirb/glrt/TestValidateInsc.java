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

import fr.enseirb.glrt.handlers.LabValidateInsHandler;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;
import fr.enseirb.glrt.model.Teacher;

public class TestValidateInsc {

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
		
		Laboratoire lab = new Laboratoire("CNRS", "Milan Kaback", "06666666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab );
		Laboratoire lab1 = new Laboratoire("CNRS", "Milan Kaback", "06666666", "aaba@aaa.aaa", "aaa");
		model.createLab(lab1 );
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
		Teacher teacher = new Teacher("Bob Bob", "Enseirb", "077777", "bbb@bbb.bbb", "bbb");
		model.createTeacher(teacher );
		model.teacherInscrireAtelier("1", "1", "Lundi Matin", "Secondes", 1);
		model.teacherInscrireAtelier("1", "2", "Lundi Matin", "Secondes", 500);

	}
	
	@Test
	public void testValidate() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("insId", value);
		assertEquals("/labs/dashboard?good=4", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@Test
	public void testInsNotOfLab() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "2");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("insId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@Test
	public void testInsNotExist() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "2");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"5"};
		urlParams.put("insId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testWarn() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"2"};
		urlParams.put("insId", value);
		assertEquals("/labs/dashboard?warn=1", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testDeleteIdNull() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testDeleteIdNotNumber() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"aa"};
		urlParams.put("insId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testNoSessionLab() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		LabValidateInsHandler handler = new LabValidateInsHandler(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"3"};
		urlParams.put("insId", value);
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}
}
