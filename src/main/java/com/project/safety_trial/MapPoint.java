package com.project.safety_trial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapPoint {
    private String name;
    private String type; // "police" or "hospital"
    private double latitude;
    private double longitude;

    // Constructor, getters, and setters

}
