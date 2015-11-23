package fr.enseirb.glrt;

import static org.junit.Assert.*;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

import fr.enseirb.glrt.handlers.LabDisconnectHandler;


public class TestLabDisconnect {
	
	@Test
	public void testDisconnectValid() throws IOException, ClassNotFoundException, SQLException {
		LabDisconnectHandler handler = new LabDisconnectHandler();
		
		Map<String, String> sessionAtts = new HashMap<String, String>();
		sessionAtts.put("sessionLab", "1");
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/", handler.process(urlParams , sessionAtts).get("redirect"));	
	}
	
	@Test
	public void testDisconnectUnvalid() throws IOException, ClassNotFoundException, SQLException {
		LabDisconnectHandler handler = new LabDisconnectHandler();
		
		Map<String, String> sessionAtts = new HashMap<String, String>();
		Map<String, String[]> urlParams = new HashMap<String, String[]>();
		assertEquals("/labs/login", handler.process(urlParams , sessionAtts).get("redirect"));	
	}


}
