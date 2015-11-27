package fr.enseirb.glrt;

import static org.junit.Assert.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabAddAtelierHandlerPost;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;


public class TestLabAddAtelierPost {

	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();

		Laboratoire lab = new Laboratoire("aaa", "aaa", "06666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);		
	}
	

	@Test
	public void testAddAtelier() throws IOException, ClassNotFoundException, SQLException {
		LabAddAtelierHandlerPost handler = new LabAddAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
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
		String[] publics = {"04","05"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"01","04","05"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"01","07"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/dashboard?good=1", handler.process(urlParams , sessionAtts).get("redirect"));

		assertTrue(model.getAtelier(1).getDisciplines().get(0).equals("Anthropologie"));
	}

	@Test
	public void testAddAtelierUnothorized() throws IOException, ClassNotFoundException, SQLException {
		LabAddAtelierHandlerPost handler = new LabAddAtelierHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] titre = {"titre"};
		urlParams.put("data[Atelier][titre]",titre );
		String[] type = {"type"};
		urlParams.put("data[Atelier][type]",type );
		String[] lieu = {"lieu"};
		urlParams.put("data[Atelier][lieu]",lieu );
		String[] duree = {"180"};
		urlParams.put("data[Atelier][duree]",duree );
		String[] capacite = {"18"};
		urlParams.put("data[Atelier][capacite]",capacite );
		String[] resume = {"resume"};
		urlParams.put("data[Atelier][resume]",resume );
		String[] animateurs = {"anim1"};
		urlParams.put("data[Atelier][animateurs]",animateurs );
		String[] publics = {"01","04"};
		urlParams.put("data[Atelier][public]",publics );
		String[] topics = {"12","04"};
		urlParams.put("data[Atelier][topics]",topics );
		String[] seances = {"08","01"};
		urlParams.put("data[Atelier][seances]",seances );
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));
	}

	@After
	public void after() throws SQLException {
		model.closeBDDConnection();
	}


}
