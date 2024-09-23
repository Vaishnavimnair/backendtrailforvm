package com.project.safety_trial;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenStreetMapService {
    private static final String OSM_API_URL = "https://overpass-api.de/api/interpreter";
    private static final double SEARCH_RADIUS = 5000; // 5km radius

    public List<PoliceStation> getNearbyPoliceStations(double latitude, double longitude) {
        String query = String.format(
                "[out:json];node(around:%f,%f,%f)[amenity=police];out;",
                SEARCH_RADIUS, latitude, longitude
        );
        return fetchPOIs(query, PoliceStation.class);
    }

    public List<Hospital> getNearbyHospitals(double latitude, double longitude) {
        String query = String.format(
                "[out:json];node(around:%f,%f,%f)[amenity=hospital];out;",
                SEARCH_RADIUS, latitude, longitude
        );
        return fetchPOIs(query, Hospital.class);
    }

    private <T> List<T> fetchPOIs(String query, Class<T> poiClass) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(OSM_API_URL, query, String.class);

        // Parse the JSON response and create a list of POIs
        // This is a simplified version. You'll need to implement proper JSON parsing.
        List<T> pois = new ArrayList<>();
        // Add parsing logic here
        return pois;
    }
}

