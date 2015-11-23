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

import fr.enseirb.glrt.handlers.LabRegisterHandlerPost;
import fr.enseirb.glrt.model.Model;


public class TestLabRegisterPost {

	private HttpURLConnection conn;
	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createLabTable();
		model.createAtelierTable();
		post("/labs/register", new LabRegisterHandlerPost(model));
		awaitInitialization();

		URL url = new URL("http://localhost:4567/labs/register");
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
	}
	
	@Test
	public void testLabCreation() throws IOException, InterruptedException {
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Lab][nom]=aaa&data[Lab][respo]=aaa&data[Lab][tel]=06666666&data[Lab][email]=aaa@aaa.aaa&data[Lab][password]=aaa&data[Lab][password2]=aaa");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/labs/login",conn.getHeaderField("Location"));
	
	}

	@After
	public void after() throws SQLException {
		stop();
		model.closeBDDConnection();
	}

}
