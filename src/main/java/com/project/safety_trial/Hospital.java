package com.project.safety_trial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}