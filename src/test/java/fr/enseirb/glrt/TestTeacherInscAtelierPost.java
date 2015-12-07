package fr.enseirb.glrt;

import static org.junit.Assert.*;

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

import fr.enseirb.glrt.handlers.TeacherInscrireAtelierHandlerPost;
import fr.enseirb.glrt.model.Teacher;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;

public class TestTeacherInscAtelierPost {

	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
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
	public void TestPostValid() throws ClassNotFoundException, SQLException{
		TeacherInscrireAtelierHandlerPost handler = new TeacherInscrireAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		urlParams.put("nombre", value );
		String[] value1 = {"Lundi Matin"};
		urlParams.put("seance", value1 );
		String[] value2 = {"secondes"};
		urlParams.put("public", value2 );
		assertEquals("/teachers/listAteliers?good=1", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@Test
	public void TestPostWarn() throws ClassNotFoundException, SQLException{
		TeacherInscrireAtelierHandlerPost handler = new TeacherInscrireAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		String[] value3 = {"152"};
		urlParams.put("nombre", value3 );
		String[] value1 = {"Lundi Matin"};
		urlParams.put("seance", value1 );
		String[] value2 = {"secondes"};
		urlParams.put("public", value2 );
		assertEquals("/teachers/listAteliers?warn=1", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testInscUnothorized() throws IOException, ClassNotFoundException, SQLException {
		TeacherInscrireAtelierHandlerPost handler = new TeacherInscrireAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"1"};
		urlParams.put("atelierId", value );
		String[] value3 = {"aaa"};
		urlParams.put("nombre", value3 );
		String[] value1 = {"Lundi Matin"};
		urlParams.put("seance", value1 );
		String[] value2 = {"secondes"};
		urlParams.put("public", value2 );
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@Test
	public void testInscNullId() throws IOException, ClassNotFoundException, SQLException {
		TeacherInscrireAtelierHandlerPost handler = new TeacherInscrireAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value3 = {"aaa"};
		urlParams.put("nombre", value3 );
		String[] value1 = {"Lundi Matin"};
		urlParams.put("seance", value1 );
		String[] value2 = {"secondes"};
		urlParams.put("public", value2 );
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}
	
	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}

}
