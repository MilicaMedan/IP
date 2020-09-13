package service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import dao.CallCategoryDAO;
import dto.CallCategory;
import dto.HelpCall;

public class HelpCallService {
	
	private static final String getEmergencyCallURL = "http://localhost:8080/HelpApp/rest/call/calls";
	private static final String deleteEmergencyCallURL =  "http://localhost:8080/HelpApp/rest/call/block";
	
	
	public static ArrayList<HelpCall> getAll() {
	
		ArrayList<HelpCall> emergencyCallsArrayList = new ArrayList<>();
		try {
			URL url = new URL(getEmergencyCallURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			JSONArray result = new JSONArray();
			while ((output = bufferedReader.readLine()) != null) {
				try {
					result = new JSONArray(output);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			try {
				for (int i = 0; i < result.length(); i++) {
					JSONObject json = result.getJSONObject(i);
					HelpCall emergencyCall = new HelpCall();
					emergencyCall.setId(json.getInt("id"));
					emergencyCall.setTitle(json.getString("title"));
					emergencyCall.setLocation(json.getString("location"));
					CallCategory category= CallCategoryDAO.selectById(json.getInt("categoryId"));
					emergencyCall.setCategoryName(category.getName());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
					String getDate = json.getString("time");
					try {
						emergencyCall.setTime(simpleDateFormat.parse(getDate));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					emergencyCall.setDescription(json.getString("description"));
					emergencyCall.setPictureSrc(json.getString("pictureSrc"));
					emergencyCallsArrayList.add(emergencyCall);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emergencyCallsArrayList;
	}
	
	public static boolean blockCall(HelpCall call) {
		try {
			URL url = new URL(deleteEmergencyCallURL + "/" + call.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
				return false;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
