package com.project.safety_trial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
    private double temperature;
    private String description;
    private String safetyTip;

    // Constructor, getters, and setters
}