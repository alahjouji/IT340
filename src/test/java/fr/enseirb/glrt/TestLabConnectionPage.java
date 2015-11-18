package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import fr.enseirb.glrt.handlers.LabLoginHandlerPost;
import fr.enseirb.glrt.model.Model;

public class TestLabConnectionPage {

	@Test
	public void testLoginSuccess() throws IOException {

		get("/labs/login", new LabLoginHandlerPost(new Model()));
		URL url = new URL("http://localhost:4567/labs/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setInstanceFollowRedirects( false );

		conn.setRequestMethod("POST");
		conn.setRequestProperty("data[Lab][email]", "aaa@aaa.aaa");
		conn.setRequestProperty("data[Lab][password]", "aaa");
		conn.connect();
		
		int responseCode = conn.getResponseCode();
		System.out.println( responseCode );
		String location = conn.getHeaderField( "Location" );
		System.out.println( location );
		System.out.println(conn.getHeaderField("Date"));
		//assertTrue(1==1);
		//stop();

	}
	
	@Test
	public void testLoginFailure() throws IOException {

		get("/labs/login", new LabLoginHandlerPost(new Model()));
		URL url = new URL("http://localhost:4567/labs/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("data[Lab][email]", "aaa@aaa.aaa");
		conn.setRequestProperty("data[Lab][password]", "bbb");
		conn.connect();
		assertTrue(HttpURLConnection.getFollowRedirects());
		stop();

	}
}
