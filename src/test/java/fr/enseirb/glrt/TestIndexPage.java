package fr.enseirb.glrt;

import static org.junit.Assert.*;
import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import fr.enseirb.glrt.handlers.IndexHandler;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

public class TestIndexPage {

	@Test
	public void test() throws IOException {

		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources"));;
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

		get("/", new IndexHandler(freeMarkerEngine));
		URL url = new URL("http://localhost:4567");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		awaitInitialization();
		conn.connect();
		assertEquals(conn.getResponseMessage(), "OK");
		stop();
	}

}
