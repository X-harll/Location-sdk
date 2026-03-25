package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.Continent;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ContinentService {
    private final ApiClient apiClient;

    public ContinentService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Continent createContinent(Continent continent) throws ApiException {
        HttpResponse<String> response = apiClient.post("/api/v1/continents", continent);
        return apiClient.parseResponse(response, Continent.class);
    }

    public Continent updateContinent(String id, Continent continent) throws ApiException {
        HttpResponse<String> response = apiClient.put("/api/v1/continents/" + id, continent);
        return apiClient.parseResponse(response, Continent.class);
    }

    public Continent getContinent(String id) throws ApiException {
        HttpResponse<String> response = apiClient.get("/api/v1/continents/" + id);
        return apiClient.parseResponse(response, Continent.class);
    }


    public List<Continent> getContinents() throws ApiException {
        HttpResponse<String> response = apiClient.get("/api/v1/continents");
        Continent[] continents = apiClient.parseResponse(response, Continent[].class);
        return Arrays.asList(continents);
    }
}
