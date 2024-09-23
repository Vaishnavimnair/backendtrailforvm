package com.project.safety_trial;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Hospital {
    private String name;
    private Location location;
    private double latitude;
    private double longitude;
    // Constructor, getters, and setters
    public Hospital(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}