package org.agile4d.storyTracker.mingle;

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

public class MingleStoryTracker implements StoryTracker {

	public Map<String, Integer> getStoryStatusCounts() {
		String urlString = "https://agile3d.mingle.thoughtworks.com/api/v2/projects/dasg_minecraft/cards/execute_mql.json?mql=SELECT%20Status,%20count%28*%29%20where%20type%20=%20Work";
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
		String userpass = "guest" + ":" + "Gu3s|";

		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		uc.setRequestProperty("Authorization", basicAuth);
		InputStream inputStream = null;
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

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(inputLine);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			String status = jsonObject.get("Status").getAsString();
			Integer count = jsonObject.get("Count ").getAsInt();
			hashMap.put(status, count);
		}
		return hashMap;
	}

}
