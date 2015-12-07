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

import fr.enseirb.glrt.handlers.TeacherRegisterHandlerPost;
import fr.enseirb.glrt.model.Teacher;
import fr.enseirb.glrt.model.Model;


public class TestTeacherRegisterPost {

	private Model model;
	
	@Before
	public void before() throws IOException, ClassNotFoundException, SQLException {
		
		String[] bddArgs = {"jdbc:h2:mem:it340", "", ""};
		this.model = new Model(bddArgs);
		model.createTeacherTable();

		
		post("/teachers/register", new TeacherRegisterHandlerPost(model));
		awaitInitialization();

	}
	
	@Test
	public void testTeacherCreation() throws IOException, InterruptedException {
		URL url = new URL("http://localhost:4567/teachers/register");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes("data[Teacher][nom]=aaa&data[Teacher][etab]=aaa&data[Teacher][tel]=06666666&data[Teacher][email]=aaa@aaa.aaa&data[Teacher][password]=aaa&data[Teacher][password2]=aaa");
		wr.flush();
		wr.close();
		conn.connect();
		
		assertEquals("http://localhost:4567/teachers/login?good=1",conn.getHeaderField("Location"));
		conn.disconnect();
	
	}

	@Test
	public void testAlreadyCon() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		TeacherRegisterHandlerPost handler = new TeacherRegisterHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/teachers/dashboard", handler.process(urlParams , sessionAtts).get("redirect"));	
	}

	@Test
	public void testMailAlreadyExist() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Teacher teacher = new Teacher("CNRS", "Milan Kaback", "06666666", "aaa@aaa.aaa", "aaa");
		model.createTeacher(teacher );
		TeacherRegisterHandlerPost handler = new TeacherRegisterHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value = {"aaa@aaa.aaa"};
		urlParams.put("data[Teacher][email]", value);
		assertEquals("/teachers/register?warn=1", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	
	@Test
	public void testNotSimilarPasswords() throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		TeacherRegisterHandlerPost handler = new TeacherRegisterHandlerPost(model);

		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		String[] value2 = {"aaa@aaa.aaa"};
		urlParams.put("data[Teacher][email]", value2);
		String[] value = {"aaa"};
		String[] value1 = {"aaaa"};
		urlParams.put("data[Teacher][password]", value);
		urlParams.put("data[Teacher][password2]", value1);
		assertEquals("/teachers/register?warn=2", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	@After
	public void after() throws SQLException {
		stop();
		model.closeBDDConnection();
	}

}
