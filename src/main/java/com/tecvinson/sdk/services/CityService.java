package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.City;
import com.tecvinson.sdk.models.PageResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CityService {

    private final ApiClient apiClient;

    public CityService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public City createCity(City city) throws ApiException {

        var response = apiClient.post("/api/v1/cities", city);

        return apiClient.parseResponse(response, City.class
        );
    }

    public City updateCity(UUID id, City city) throws ApiException {

        var response = apiClient.put(
                "/api/v1/cities/" + id,
                city
        );

        return apiClient.parseResponse(
                response,
                City.class
        );
    }

    public City getCity(UUID id) throws ApiException {

        var response = apiClient.get(
                "/api/v1/cities/" + id
        );

        return apiClient.parseResponse(
                response,
                City.class
        );
    }

    public PageResponse<City> getCities(
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/cities?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(
                response,
                PageResponse.class
        );
    }

    public PageResponse<City> getByName(
            String name,
            int page,
            int size
    ) throws ApiException {

        String encodedName = URLEncoder.encode(
                name,
                StandardCharsets.UTF_8
        );

        var response = apiClient.get(
                "/api/v1/cities/search?name="
                        + encodedName
                        + "&page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(
                response,
                PageResponse.class
        );
    }

    public PageResponse<City> getByState(
            UUID stateId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/cities/getbystate/"
                        + stateId
                        + "?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(
                response,
                PageResponse.class
        );
    }

    public PageResponse<City> getByCountry(
            UUID countryId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/cities/getbycountry/"
                        + countryId
                        + "?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(
                response,
                PageResponse.class
        );
    }
}