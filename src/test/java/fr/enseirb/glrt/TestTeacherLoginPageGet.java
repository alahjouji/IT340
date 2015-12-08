package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.glrt.handlers.TeacherLoginHandlerGet;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestTeacherLoginPageGet {

	private FreeMarkerEngine freeMarkerEngine;

	@Before
	public void before() throws IOException {
		freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDefaultEncoding("UTF-8");
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

		get("/teachers/login", new TeacherLoginHandlerGet(freeMarkerEngine));
		awaitInitialization();

	}
	
	@Test
	public void testSparkConnection() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		conn.disconnect();
	}
	
	@Test
	public void testHTMLResponse() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherLogin.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
		conn.disconnect();
	}
	
	@Test
	public void testHTMLResponseError() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login?warn=1");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherLoginError.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
		conn.disconnect();
	}
	
	@Test
	public void testHTMLResponseGood() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login?good=1");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherLoginGood.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);
		conn.disconnect();
	}
	
	@Test
	public void testHTMLResponseError2() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login?warn=2");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherLogin.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);	
		conn.disconnect();
	}
	
	@Test
	public void testHTMLResponseGood2() throws IOException {
		URL url = new URL("http://localhost:4567/teachers/login?good=2");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/teacherLogin.html"), "UTF-8");
		assertEquals(expectedHTML, responseHTML);
		conn.disconnect();
	}
	@Test
	public void testAlreadyCon() throws IOException {
		TeacherLoginHandlerGet handler = new TeacherLoginHandlerGet(freeMarkerEngine);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/teachers/dashboard", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	
	@After
	public void after() {
		stop();
	}

}
