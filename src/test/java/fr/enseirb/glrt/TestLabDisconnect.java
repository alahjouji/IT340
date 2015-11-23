package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabDisconnectHandler;

public class TestLabDisconnect {

	private HttpURLConnection conn;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		get("/labs/disconnect", new LabDisconnectHandler());
		awaitInitialization();

		URL url = new URL("http://localhost:4567/labs/disconnect");
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setInstanceFollowRedirects(false);
	}
	
	@Test
	public void testLoginValid() throws IOException {

		conn.connect();
		
		assertEquals("http://localhost:4567/", conn.getHeaderField("Location"));
	
	}

	@After
	public void after() throws SQLException {
		conn.disconnect();
		stop();
	}

}
