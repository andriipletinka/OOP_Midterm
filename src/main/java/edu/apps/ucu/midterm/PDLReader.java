package edu.apps.ucu.midterm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLReader {
    public static void main(String[] args) throws IOException {
        String API_KEY = "59baef6a4a4b96a12b99d5d6336c7be5c55b6804da3838996ebcd963459f4dcd";
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='ucu.edu.ua'", StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
        JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
        Response.ResponseBuilder responseBuilder = Response.builder();
        String domain = data.getString("website");
        String name = data.getString("name");
        responseBuilder.domain(domain).name(name);
        if (!data.isNull("twitter_url")) {
            String twitter = data.getString("twitter_url");
            responseBuilder.twitter(twitter);
        }
        if (!data.isNull("facebook_url")) {
            String facebook = data.getString("facebook_url");
            responseBuilder.facebook(facebook);
        }
//        String logo = data.getString();
//        String icon = data.getString();
        if (!data.isNull("employee_count")) {
            int employees = data.getInt("employee_count");
            responseBuilder.employees(employees);
        }
        JSONObject location = data.getJSONObject("location");
        String address = location.getString("country");
        if (!location.isNull("street_address")) {
            address += ", " + location.getString("street_address");
        }
        Response response = responseBuilder.address(address).build();
        System.out.println(response);
    }
}
