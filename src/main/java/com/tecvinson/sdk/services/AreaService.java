package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.services.models.Area;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AreaService {
    private final ApiClient apiClient;

    public AreaService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Area createArea(Area area) throws ApiException {
        var response = apiClient.post("/api/areas", area);
        return apiClient.parseResponse(response, Area.class);
    }

    public Area updateArea(UUID id, Area area) throws ApiException {
        var response = apiClient.put("/api/areas/" + id.toString(), area);
        return apiClient.parseResponse(response, Area.class);
    }

    public Area getArea(UUID id) throws ApiException {
        var response = apiClient.get("/api/areas/" + id.toString());
        return apiClient.parseResponse(response, Area.class);
    }

    public List<Area> getAreas() throws ApiException {
        var response = apiClient.get("/api/areas");
        Area[] areas = apiClient.parseResponse(response, Area[].class);
        return Arrays.asList(areas);
    }

    public List<Area> getByName(String name) throws ApiException {
        String encodedName = java.net.URLEncoder.encode(name, java.nio.charset.StandardCharsets.UTF_8);
        var response = apiClient.get("/api/areas/search?name=" + encodedName);
        Area[] areas = apiClient.parseResponse(response, Area[].class);
        return Arrays.asList(areas);
    }

    public List<Area> getByCity(UUID cityId) throws ApiException {
        var response = apiClient.get("/api/areas/getbycity/" + cityId.toString());
        Area[] areas = apiClient.parseResponse(response, Area[].class);
        return Arrays.asList(areas);
    }

    public List<Area> getByState(UUID stateId) throws ApiException {
        var response = apiClient.get("/api/areas/getbystate/" + stateId.toString());
        Area[] areas = apiClient.parseResponse(response, Area[].class);
        return Arrays.asList(areas);
    }

    public List<Area> getByCountry(UUID countryId) throws ApiException {
        var response = apiClient.get("/api/areas/getbycountry/" + countryId.toString());
        Area[] areas = apiClient.parseResponse(response, Area[].class);
        return Arrays.asList(areas);
    }
}
