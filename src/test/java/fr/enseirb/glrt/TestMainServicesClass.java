package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;


public class TestMainServicesClass {

	@Test
	public void test() throws ClassNotFoundException, SQLException, IOException {
		MainServices.main(null);
		URL url = new URL("http://localhost:4567");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();	
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		String responseHTML = IOUtils.toString(conn.getInputStream(), "UTF-8");
		String expectedHTML= IOUtils.toString(new FileInputStream("src/test/resources/index.html"), "UTF-8");
		assertEquals(responseHTML, expectedHTML);
		stop();
	}

}
