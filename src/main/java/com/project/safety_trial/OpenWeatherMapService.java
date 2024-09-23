package com.project.safety_trial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {
    @Value("${openweathermap.api.key}")
    private String apiKey;

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric";

    public WeatherInfo getWeatherInfo(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = WEATHER_API_URL.replace("{lat}", String.valueOf(latitude))
                .replace("{lon}", String.valueOf(longitude))
                .replace("{apiKey}", apiKey);

        // Make API call and parse response
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

        String safetyTip = getSafetyTip(response.getMain().getTemp(), response.getWeather()[0].getMain());

        return new WeatherInfo(
                response.getMain().getTemp(),
                response.getWeather()[0].getDescription(),
                safetyTip
        );
    }

    private String getSafetyTip(double temperature, String weatherCondition) {
        // Logic to determine safety tip based on weather conditions
        if (temperature > 35) {
            return "High temperature alert! Stay hydrated and avoid prolonged sun exposure.";
        } else if (temperature < 0) {
            return "Freezing conditions! Dress warmly and be cautious of icy surfaces.";
        } else if (weatherCondition.equalsIgnoreCase("Rain")) {
            return "Rainy conditions. Drive carefully and carry an umbrella.";
        }
        // Add more conditions as needed
        return "Enjoy your day safely!";
    }
}