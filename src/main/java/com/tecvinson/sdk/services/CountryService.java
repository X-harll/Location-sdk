package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.Country;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CountryService {
    private final ApiClient apiClient;

    public CountryService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Country createCountry(Country country) throws ApiException {
        var response = apiClient.post("/api/v1/countries", country);
        return apiClient.parseResponse(response, Country.class);
    }

    public Country updateCountry(UUID id, Country country) throws ApiException {
        var response = apiClient.put("/api/v1/countries/" + id, country);
        return apiClient.parseResponse(response, Country.class);
    }

    public List<Country> getCountries() throws ApiException {
        var response = apiClient.get("/api/v1/countries");
        Country[] countries = apiClient.parseResponse(response, Country[].class);
        return Arrays.asList(countries);
    }

    public Country getCountry(UUID id) throws ApiException {
        var response = apiClient.get("/api/v1/countries/" + id);
        return apiClient.parseResponse(response, Country.class);
    }

    public List<Country> getCountriesByName(String name) throws ApiException {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        var response = apiClient.get("/api/v1/countries/search?name=" + encodedName);
        Country[] countries = apiClient.parseResponse(response, Country[].class);
        return Arrays.asList(countries);
    }

    public List<Country> getByContinent(UUID continentId) throws ApiException {
        var response = apiClient.get("/api/v1/countries/getbycontinent/" + continentId);
        Country[] countries = apiClient.parseResponse(response, Country[].class);
        return Arrays.asList(countries);
    }
}
