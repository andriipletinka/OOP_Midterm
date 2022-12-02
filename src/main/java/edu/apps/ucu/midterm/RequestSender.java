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
    public Response sendRequest(Request request) {
        String domain = request.getDomain();
        JSONObject data = PDLReader.getInstance().scrapeData(domain);
        String address = GooglePlacesReader.getInstance().scrapeAddress(domain);
        Response.ResponseBuilder responseBuilder = Response.builder();
        String website = data.getString("website");
        String name = data.getString("name");
        responseBuilder.domain(website).name(name);
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
        if (address.equals("couldn't find")) {
            JSONObject location = data.getJSONObject("location");
            String newAddress = location.getString("street_address") + ", " + location.getString("name");
            Response response = responseBuilder.address(newAddress).build();
            return response;
        } else {
            Response response = responseBuilder.address(address).build();
            return response;
        }
    }
}
