package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.model.Laboratoire;
import fr.enseirb.glrt.model.Model;

public class TestLabLoginPost {

	private HttpURLConnection conn;
	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		String bddURL = "jdbc:h2:mem:it340";
		this.model = new Model(bddURL);
		model.createLabTable();
		model.createAtelierTable();
		Laboratoire lab = new Laboratoire("aaa", "aaa", "0666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);
		post("/labs/login", new LabLoginHandlerPost(model));

		URL url = new URL("http://localhost:4567/labs/login");
		conn = (HttpURLConnection) url.openConnection();
		awaitInitialization();

//		conn.setReadTimeout(15000);
//		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
//		conn.setDoInput(true);
		conn.setDoOutput(true);
	}
	
//	@Test
//	public void testLoginValid() throws IOException {
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=aaa");
//		wr.flush();
//		wr.close();
//		conn.connect();
//		assertEquals(HttpURLConnection.HTTP_ACCEPTED,conn.getResponseCode());
//	
//	}
	
	@Test
	public void testLoginUnothorized() throws IOException {
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=bbb");
		wr.flush();
		wr.close();
		conn.connect();

		assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED,conn.getResponseCode());
	}

	@After
	public void after() throws SQLException {
		stop();
		model.closeBDDConnection();
	}

}
