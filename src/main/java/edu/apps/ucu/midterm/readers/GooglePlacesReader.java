package edu.apps.ucu.midterm.readers;

import com.google.maps.GeoApiContext;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public class GooglePlacesReader extends Reader {
    private static GooglePlacesReader googlePlacesReader;
    private static String API_KEY = "AIzaSyDcmv57yKEX5m4fCOp46I9D1-gFHO4nY90";

    public static GooglePlacesReader getInstance() {
        if (googlePlacesReader == null) {
            googlePlacesReader = new GooglePlacesReader();
        }
        return googlePlacesReader;
    }

    @SneakyThrows
    public String scrapeAddress(String query) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesResponse = new TextSearchRequest(context).query(query).await();
        if (placesResponse.results.length != 0) {
            String address = placesResponse.results[0].formattedAddress;
            return address;
        }
        context.shutdown();
        return "couldn't find";
    }
}