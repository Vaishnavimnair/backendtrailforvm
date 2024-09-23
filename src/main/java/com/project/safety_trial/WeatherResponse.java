package com.project.safety_trial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Main main;
    private Weather[] weather;

    // Getters and setters
@Getter
@Setter
    public static class Main {
        private double temp;

        // Getter and setter
    }
    @Getter
    @Setter
    public static class Weather {
        private String main;
        private String description;

        // Getters and setters
    }
}