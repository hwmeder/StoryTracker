package org.agile4d.storyTracker.trello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.agile4d.storyTracker.StoryTracker;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TrelloStoryTracker implements StoryTracker {

	public Map<String, Integer> getStoryStatusCounts() {
		String urlString = "https://api.trello.com/1/boards/528b705ccf2acdff4b0022a8?lists=open&list_fields=name&fields=name,desc";
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection uc = null;
		try {
			uc = url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
/*		String userpass = "guest" + ":" + "Gu3s|";

		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		uc.setRequestProperty("Authorization", basicAuth);
*/		InputStream inputStream = null;
		try {
			inputStream = uc.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = null;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		in = new BufferedReader(inputStreamReader);
		String inputLine = null;
		try {
			inputLine = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("JSON returned from Mingle:");
		System.out.println(inputLine);

		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HashMap<String, String> hashMap = new HashMap<String, String>();
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(inputLine);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			String status = jsonObject.get("id").getAsString();
			String count = jsonObject.get("name").getAsString();
			hashMap.put(status, count);
		}
		System.out.println("JSON returned from Mingle:");
		System.out.println(hashMap);
		
		HashMap<String, Integer> statusCounts = new HashMap<String, Integer>();
		return statusCounts;
	}

}
