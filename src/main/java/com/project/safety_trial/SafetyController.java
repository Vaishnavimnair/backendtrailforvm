package com.project.safety_trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/safety")
public class SafetyController {
    @Autowired
    private SafetyService safetyService;

    @GetMapping
    public SafetyResponse getSafetyInfo(@RequestParam double latitude, @RequestParam double longitude) {
        return safetyService.getSafetyInfo(latitude, longitude);
    }

}
