package fr.enseirb.glrt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class GoogleMaps {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			org.json.JSONObject json = new org.json.JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static String[] getLatLon(String locationAddress) throws JSONException, IOException {

		JSONObject obj = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(locationAddress, "UTF-8"));
		
		String[] latlon = new String[2];
		latlon[0] = ((Double) obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat")).toString();
		latlon[1] = ((Double) obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng")).toString();
		return latlon ;
		
	}
	
}
