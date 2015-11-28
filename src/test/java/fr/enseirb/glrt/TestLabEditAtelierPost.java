package fr.enseirb.glrt;

import static org.junit.Assert.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabEditAtelierHandlerPost;
import fr.enseirb.glrt.model.Atelier;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;
import fr.enseirb.glrt.model.Seance;


public class TestLabEditAtelierPost {

	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();

		Laboratoire lab = new Laboratoire("aaa", "aaa", "06666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);		
		List<String> list = new ArrayList<String>();
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

		
		Atelier atelier= new Atelier(1, " A la poursuite d'ennemis invisibles", list, "Visite", list1, "1 avenue du Docteur Albert Schweitzer 33400 talence", 1, 1, "Cet Atelier est destiné aux personnes.", list2, list3);
		model.createAtelier(atelier);
	}
	

	@Test
	public void testAddAtelier() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] atId = {"1"};
		urlParams.put("atelierId", atId);
		String[] titre = {"A la poursuite d'ennemis invisibles"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"05"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"1 avenue du Docteur Albert Schweitzer 33400 talence "};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"1"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"1"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"Cet Atelier est destiné aux personnes."};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"bob,Martin"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","02","03","04","05","06","07","08","09"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","02","03","04","05","06","07","08","09","10","11"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=3", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}

	@Test
	public void testAddAtelier1() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] atId = {"1"};
		urlParams.put("atelierId", atId);
		String[] titre = {"A la poursuite d'ennemis invisibles"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"01"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"1 avenue du Docteur Albert Schweitzer 33400 talence "};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"1"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"1"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"Cet Atelier est destiné aux personnes."};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"bob,Martin"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","02","03","04","05","06","07","08","09"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","02","03","04","05","06","07","08","09","10","11"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=3", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}
	
	@Test
	public void testAddAtelier2() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] atId = {"1"};
		urlParams.put("atelierId", atId);
		String[] titre = {"A la poursuite d'ennemis invisibles"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"02"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"1 avenue du Docteur Albert Schweitzer 33400 talence "};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"1"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"1"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"Cet Atelier est destiné aux personnes."};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"bob,Martin"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","02","03","04","05","06","07","08","09"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","02","03","04","05","06","07","08","09","10","11"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=3", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}
	
	@Test
	public void testAddAtelier3() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] atId = {"1"};
		urlParams.put("atelierId", atId);
		String[] titre = {"A la poursuite d'ennemis invisibles"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"03"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"1 avenue du Docteur Albert Schweitzer 33400 talence "};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"1"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"1"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"Cet Atelier est destiné aux personnes."};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"bob,Martin"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","02","03","04","05","06","07","08","09"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","02","03","04","05","06","07","08","09","10","11"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=3", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}
	
	@Test
	public void testAddAtelier4() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] atId = {"1"};
		urlParams.put("atelierId", atId);
		String[] titre = {"A la poursuite d'ennemis invisibles"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"04"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"1 avenue du Docteur Albert Schweitzer 33400 talence "};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"1"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"1"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"Cet Atelier est destiné aux personnes."};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"bob,Martin"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","02","03","04","05","06","07","08","09"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","02","03","04","05","06","07","08","09","10","11"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=3", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}
	@Test
	public void testAddAtelierUnothorized() throws IOException, ClassNotFoundException, SQLException {
		LabEditAtelierHandlerPost handler = new LabEditAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}


}
