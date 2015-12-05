package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;

public class TestLabLoginPost {

	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		String[] bddArgs= {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		model.createSeanceTable();

		Laboratoire lab = new Laboratoire("aaa", "aaa", "0666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);
		post("/labs/login", new LabLoginHandlerPost(model));
		awaitInitialization();
	}
	
	@Test
	public void testLoginValid() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=aaa");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/labs/dashboard", conn.getHeaderField("Location"));
		conn.disconnect();
	
	}
	
	@Test
	public void testLoginUnothorized() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=bbb");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/labs/login?warn=1", conn.getHeaderField("Location"));
		conn.disconnect();

	}

	@Test
	public void testAlreadyCon() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		LabLoginHandlerPost handler = new LabLoginHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/dashboard", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	
	@After
	public void after() throws SQLException {
		stop();
		model.closeBDDConnection();
	}

}
