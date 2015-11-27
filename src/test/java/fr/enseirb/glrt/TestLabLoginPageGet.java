package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.LabLoginHandlerGet;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestLabLoginPageGet {

	private HttpURLConnection conn;
	@Before
	public void before() throws IOException {
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

		get("/labs/login", new LabLoginHandlerGet(freeMarkerEngine));
		awaitInitialization();

	}
	
	@Test
	public void testSparkConnection() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login");
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
	}
	
	@Test
	public void testHTMLResponse() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login");
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labLogin.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
	}
	
	@Test
	public void testHTMLResponseError() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login?warn=1");
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labLoginError.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
	}
	
	@Test
	public void testHTMLResponseGood() throws IOException {
		URL url = new URL("http://localhost:4567/labs/login?good=1");
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labLoginGood.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
	}
	@After
	public void after() {
		conn.disconnect();
		stop();
	}

}
