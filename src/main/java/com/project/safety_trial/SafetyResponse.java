package com.project.safety_trial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SafetyResponse {
    private List<MapPoint> mapPoints;
    private WeatherInfo weatherInfo;

    // Constructor, getters, and setters
}