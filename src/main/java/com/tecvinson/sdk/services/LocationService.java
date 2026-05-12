package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.Location;
import com.tecvinson.sdk.models.PageResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LocationService {

    private final ApiClient apiClient;

    public LocationService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Location createLocation(Location location) throws ApiException {

        var response = apiClient.post(
                "/api/v1/locations",
                location
        );

        return apiClient.parseResponse(
                response,
                Location.class
        );
    }

    public Location updateLocation(UUID id, Location location) throws ApiException {

        var response = apiClient.put(
                "/api/v1/locations/" + id,
                location
        );

        return apiClient.parseResponse(
                response,
                Location.class
        );
    }

    public Location getLocation(UUID id) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations/" + id
        );

        return apiClient.parseResponse(
                response,
                Location.class
        );
    }

    public PageResponse<Location> getLocations(
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations?page="
                        + page
                        + "&size="
                        + size
        );

        return apiClient.parseResponse(
                response,
                PageResponse.class
        );
    }

    public PageResponse<Location> getLocationsByCountry(
            UUID countryId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations/getbycountry/"
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

    public PageResponse<Location> getLocationsByState(
            UUID stateId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations/getbystate/"
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

    public PageResponse<Location> getLocationsByCity(
            UUID cityId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations/getbycity/"
                        + cityId
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

    public PageResponse<Location> getLocationsByArea(
            UUID areaId,
            int page,
            int size
    ) throws ApiException {

        var response = apiClient.get(
                "/api/v1/locations/getbyarea/"
                        + areaId
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

    public PageResponse<Location> getBySearch(
            String searchTerm,
            int page,
            int size
    ) throws ApiException {

        String encodedTerm = URLEncoder.encode(
                searchTerm,
                StandardCharsets.UTF_8
        );

        var response = apiClient.get(
                "/api/v1/locations/search?searchTerm="
                        + encodedTerm
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
}