package com.project.safety_trial;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OpenStreetMapService {
    private static final Logger logger = LoggerFactory.getLogger(OpenStreetMapService.class);
    private static final String OSM_API_URL = "https://overpass-api.de/api/interpreter";
    private static final double SEARCH_RADIUS = 10000; // 10km radius

    private final RestTemplate restTemplate;

    public OpenStreetMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<PoliceStation> getNearbyPoliceStations(double latitude, double longitude) {
        String query = String.format(
                "[out:json];node(around:%f,%f,%f)[amenity=police];out;",
                SEARCH_RADIUS, latitude, longitude
        );
        return fetchPOIs(query, "police");
    }

    public List<Hospital> getNearbyHospitals(double latitude, double longitude) {
        String query = String.format(
                "[out:json];node(around:%f,%f,%f)[amenity=hospital];out;",
                SEARCH_RADIUS, latitude, longitude
        );
        return fetchPOIs(query, "hospital");
    }

    private <T> List<T> fetchPOIs(String query, String type) {
        List<T> pois = new ArrayList<>();
        try {
            String response = restTemplate.postForObject(OSM_API_URL, query, String.class);
            logger.info("OSM API Response: {}", response);

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray elements = jsonResponse.getJSONArray("elements");

            for (int i = 0; i < elements.length(); i++) {
                JSONObject element = elements.getJSONObject(i);
                String name = element.getJSONObject("tags").optString("name", "Unnamed");
                double lat = element.getDouble("lat");
                double lon = element.getDouble("lon");

                if ("police".equals(type)) {
                    pois.add((T) new PoliceStation(name, lat, lon));
                } else if ("hospital".equals(type)) {
                    pois.add((T) new Hospital(name, lat, lon));
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching POIs from OpenStreetMap", e);
        }
        return pois;
    }
}

//    public List<PoliceStation> getNearbyPoliceStations(double latitude, double longitude) {
//        String query = String.format(
//                "[out:json];node(around:%f,%f,%f)[amenity=police];out;",
//                SEARCH_RADIUS, latitude, longitude
//        );
//        return fetchPOIs(query, PoliceStation.class);
//    }
//
//    public List<Hospital> getNearbyHospitals(double latitude, double longitude) {
//        String query = String.format(
//                "[out:json];node(around:%f,%f,%f)[amenity=hospital];out;",
//                SEARCH_RADIUS, latitude, longitude
//        );
//        return fetchPOIs(query, Hospital.class);
//    }
//
//    private <T> List<T> fetchPOIs(String query, Class<T> poiClass) {
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.postForObject(OSM_API_URL, query, String.class);
//
//        // Parse the JSON response and create a list of POIs
//        // This is a simplified version. You'll need to implement proper JSON parsing.
//        List<T> pois = new ArrayList<>();
//        // Add parsing logic here
//        return pois;
//    }
//}

