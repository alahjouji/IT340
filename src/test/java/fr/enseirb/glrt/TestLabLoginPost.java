package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
	}
	
	@Test
	public void testLoginValid() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("data[Lab][email]", "aaa@aaa.aaa");
		params.put("data[Lab][password]", "aaa");
		OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        writer.write(result.toString());

        writer.flush();
        writer.close();
        os.close();

		conn.connect();

		assertEquals(HttpURLConnection.HTTP_ACCEPTED,conn.getResponseCode());
	
	}
	
	@Test
	public void testLoginUnothorized() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("data[Lab][email]", "aaa@aaa.aaa");
		params.put("data[Lab][password]", "bbb");
		OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        writer.write(result.toString());

        writer.flush();
        writer.close();
        os.close();

		conn.connect();

		assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED,conn.getResponseCode());
	}

	@After
	public void after() throws SQLException {
		stop();
		model.closeBDDConnection();
	}

}
