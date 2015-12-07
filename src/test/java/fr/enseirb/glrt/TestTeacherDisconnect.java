package fr.enseirb.glrt;

import static org.junit.Assert.*;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

import fr.enseirb.glrt.handlers.TeacherDisconnectHandler;


public class TestTeacherDisconnect {
	
	@Test
	public void testDisconnectValid() throws IOException, ClassNotFoundException, SQLException {
		TeacherDisconnectHandler handler = new TeacherDisconnectHandler();
		
		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionTeacher", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	
	@Test
	public void testDisconnectUnvalid() throws IOException, ClassNotFoundException, SQLException {
		TeacherDisconnectHandler handler = new TeacherDisconnectHandler();
		
		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/teachers/login", handler.process(urlParams , sessionAtts).get("redirect"));	
	}


}
