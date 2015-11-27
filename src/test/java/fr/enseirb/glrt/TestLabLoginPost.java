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
		
		String[] bddArgs= {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		Laboratoire lab = new Laboratoire("aaa", "aaa", "0666", "aaa@aaa.aaa", "aaa");
		model.createLab(lab);
		post("/labs/login", new LabLoginHandlerPost(model));
		awaitInitialization();

		URL url = new URL("http://localhost:4567/labs/login");
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
	}
	
	@Test
	public void testLoginValid() throws IOException {
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=aaa");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/labs/dashboard", conn.getHeaderField("Location"));
	
	}
	
	@Test
	public void testLoginUnothorized() throws IOException {
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=bbb");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/labs/login?warn=1", conn.getHeaderField("Location"));

	}

	@After
	public void after() throws SQLException {
		conn.disconnect();
		stop();
		model.closeBDDConnection();
	}

}
