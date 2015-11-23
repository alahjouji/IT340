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

import fr.enseirb.glrt.handlers.LabRegisterHandlerGet;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestLabRegisterPageGet {

	private HttpURLConnection conn;
	@Before
	public void before() throws IOException {
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));;
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

		get("/labs/register", new LabRegisterHandlerGet(freeMarkerEngine));
		awaitInitialization();

		URL url = new URL("http://localhost:4567/labs/register");
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
	}
	
	@Test
	public void testSparkConnection() throws IOException {
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
	}
	
	@Test
	public void testHTMLResponse() throws IOException {
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/labRegister.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
	}
	
	@After
	public void after() {
		conn.disconnect();
		stop();
	}

}