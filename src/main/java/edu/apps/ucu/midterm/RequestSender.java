package edu.apps.ucu.midterm;

import edu.apps.ucu.midterm.readers.GooglePlacesReader;
import edu.apps.ucu.midterm.readers.PDLReader;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;

@NoArgsConstructor
public class RequestSender {
    private static RequestSender requestSender;

    public static RequestSender getInstance() {
        if (requestSender == null) {
            requestSender = new RequestSender();
        }
        return requestSender;
    }

    @SneakyThrows
    public Response sendRequest(String website) {
        JSONObject data = PDLReader.getInstance().scrapeData(website);
        String address = GooglePlacesReader.getInstance().scrapeAddress(website);
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
        Response response = responseBuilder.address(address).build();
        return response;
    }
}
