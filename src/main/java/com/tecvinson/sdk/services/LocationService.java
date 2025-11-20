package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.services.models.Location;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LocationService {
    private final ApiClient apiClient;

    public LocationService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Location createLocation(Location location) throws ApiException {
        var response = apiClient.post("/api/locations", location);
        return apiClient.parseResponse(response, Location.class);
    }

    public Location updateLocation(UUID id, Location location) throws ApiException {
        var response = apiClient.put("/api/locations/" + id.toString(), location);
        return apiClient.parseResponse(response, Location.class);
    }

    public Location getLocation(UUID id) throws ApiException {
        var response = apiClient.get("/api/locations/" + id.toString());
        return apiClient.parseResponse(response, Location.class);
    }

    public List<Location> getLocations() throws ApiException {
        var response = apiClient.get("/api/locations");
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }

    public List<Location> getLocationsByCountry(UUID countryId) throws ApiException {
        var response = apiClient.get("/api/locations/getbycountry/" + countryId.toString());
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }

    public List<Location> getLocationsByState(UUID stateId) throws ApiException {
        var response = apiClient.get("/api/locations/getbystate/" + stateId.toString());
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }

    public List<Location> getLocationsByCity(UUID cityId) throws ApiException {
        var response = apiClient.get("/api/locations/getbycity/" + cityId.toString());
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }

    public List<Location> getLocationsByArea(UUID areaId) throws ApiException {
        var response = apiClient.get("/api/locations/getbyarea/" + areaId.toString());
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }

    public List<Location> getBySearch(String searchTerm) throws ApiException {
        String encodedTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
        var response = apiClient.get("/api/locations/search?searchTerm=" + encodedTerm);
        Location[] locations = apiClient.parseResponse(response, Location[].class);
        return Arrays.asList(locations);
    }
}
