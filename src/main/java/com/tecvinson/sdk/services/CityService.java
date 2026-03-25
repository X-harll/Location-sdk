package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.City;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CityService {
    private final ApiClient apiClient;

    public CityService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public City createCity(City city) throws ApiException {
        var response = apiClient.post("/api/v1/cities", city);
        return apiClient.parseResponse(response, City.class);
    }

    public City updateCity(UUID id, City city) throws ApiException {
        var response = apiClient.put("/api/v1/cities/" + id.toString(), city);
        return apiClient.parseResponse(response, City.class);
    }

    public City getCity(UUID id) throws ApiException {
        var response = apiClient.get("/api/v1/cities/" + id.toString());
        return apiClient.parseResponse(response, City.class);
    }

    public List<City> getCities() throws ApiException {
        var response = apiClient.get("/api/v1/cities");
        City[] cities = apiClient.parseResponse(response, City[].class);
        return Arrays.asList(cities);
    }

    public List<City> getByName(String name) throws ApiException {
        String encodedName = java.net.URLEncoder.encode(name, java.nio.charset.StandardCharsets.UTF_8);
        var response = apiClient.get("/api/v1/cities/search?name=" + encodedName);
        City[] cities = apiClient.parseResponse(response, City[].class);
        return Arrays.asList(cities);
    }

    public List<City> getByState(UUID stateId) throws ApiException {
        var response = apiClient.get("/api/v1/cities/getbystate/" + stateId.toString());
        City[] cities = apiClient.parseResponse(response, City[].class);
        return Arrays.asList(cities);
    }

    public List<City> getByCountry(UUID countryId) throws ApiException {
        var response = apiClient.get("/api/v1/cities/getbycountry/" + countryId.toString());
        City[] cities = apiClient.parseResponse(response, City[].class);
        return Arrays.asList(cities);
    }
}
