package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.Area;
import com.tecvinson.sdk.models.PageResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AreaService {

    private final ApiClient apiClient;

    public AreaService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Area createArea(Area area) throws ApiException {

        var response = apiClient.post("/api/v1/areas", area);

        return apiClient.parseResponse(response, Area.class);
    }

    public Area updateArea(UUID id, Area area) throws ApiException {

        var response = apiClient.put("/api/v1/areas/" + id, area);

        return apiClient.parseResponse(response, Area.class);
    }

    public Area getArea(UUID id) throws ApiException {

        var response = apiClient.get("/api/v1/areas/" + id);

        return apiClient.parseResponse(response, Area.class);
    }

    public PageResponse<Area> getAreas(int page, int size) throws ApiException {
        var response = apiClient.get("/api/v1/areas?page=" + page + "&size=" + size);

        return apiClient.parseResponse(response, PageResponse.class);
    }

    public PageResponse<Area> getByName(String name, int page, int size) throws ApiException {

        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);

        var response = apiClient.get("/api/v1/areas/search?name=" + encodedName + "&page=" + page + "&size=" + size);

        return apiClient.parseResponse(response, PageResponse.class);
    }

    public PageResponse<Area> getByCity(UUID cityId, int page, int size) throws ApiException {

        var response = apiClient.get(
                "/api/v1/areas/getbycity/"
                        + cityId
                        + "?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(response, PageResponse.class);
    }

    public PageResponse<Area> getByState(UUID stateId, int page, int size) throws ApiException {

        var response = apiClient.get(
                "/api/v1/areas/getbystate/"
                        + stateId
                        + "?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(response, PageResponse.class);
    }

    public PageResponse<Area> getByCountry(UUID countryId, int page, int size) throws ApiException {

        var response = apiClient.get(
                "/api/v1/areas/getbycountry/"
                        + countryId
                        + "?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(response, PageResponse.class);
    }
}