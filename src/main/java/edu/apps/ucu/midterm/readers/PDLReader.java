package edu.apps.ucu.midterm.readers;

import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.String.format;

@NoArgsConstructor
public class PDLReader extends Reader {
    private static PDLReader pdlReader;
    private static String API_KEY = "59baef6a4a4b96a12b99d5d6336c7be5c55b6804da3838996ebcd963459f4dcd";

    public static PDLReader getInstance() {
        if (pdlReader == null) {
            pdlReader = new PDLReader();
        }
        return pdlReader;
    }

    public JSONObject scrapeData(String website) throws IOException {
        String query = URLEncoder.encode(format("SELECT NAME FROM COMPANY WHERE WEBSITE='%s'", website), StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
        JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
        return data;
    }
}
