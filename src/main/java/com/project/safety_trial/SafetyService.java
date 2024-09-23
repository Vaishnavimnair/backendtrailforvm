package com.project.safety_trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafetyService {
    @Autowired
    private OpenStreetMapService openStreetMapService;

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    public SafetyResponse getSafetyInfo(double latitude, double longitude) {
        List<MapPoint> mapPoints = new ArrayList<>();

        List<PoliceStation> policeStations = openStreetMapService.getNearbyPoliceStations(latitude, longitude);
        for (PoliceStation station : policeStations) {
            mapPoints.add(new MapPoint(station.getName(), "police", station.getLatitude(), station.getLongitude()));
        }

        List<Hospital> hospitals = openStreetMapService.getNearbyHospitals(latitude, longitude);
        for (Hospital hospital : hospitals) {
            mapPoints.add(new MapPoint(hospital.getName(), "hospital", hospital.getLatitude(), hospital.getLongitude()));
        }

        WeatherInfo weatherInfo = openWeatherMapService.getWeatherInfo(latitude, longitude);

        return new SafetyResponse(mapPoints, weatherInfo);
    }
}
