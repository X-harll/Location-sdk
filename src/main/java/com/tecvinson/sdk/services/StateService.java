package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.services.models.State;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StateService {
    private final ApiClient apiClient;

    public StateService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public State createState(State state) throws ApiException {
        var response = apiClient.post("/api/states", state);
        return apiClient.parseResponse(response, State.class);
    }

    public State updateState(UUID id, State state) throws ApiException {
        var response = apiClient.put("/api/states/" + id, state);
        return apiClient.parseResponse(response, State.class);
    }

    public List<State> getStates() throws ApiException {
        var response = apiClient.get("/api/states");
        State[] states = apiClient.parseResponse(response, State[].class);
        return Arrays.asList(states);
    }

    public State getState(UUID id) throws ApiException {
        var response = apiClient.get("/api/states/" + id);
        return apiClient.parseResponse(response, State.class);
    }

    public List<State> getByCountryId(UUID countryId) throws ApiException {
        var response = apiClient.get("/api/states/getbycountry/" + countryId);
        State[] states = apiClient.parseResponse(response, State[].class);
        return Arrays.asList(states);
    }

    public List<State> getStatesByName(String name) throws ApiException {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        var response = apiClient.get("/api/states/api/search?name=" + encodedName);
        State[] states = apiClient.parseResponse(response, State[].class);
        return Arrays.asList(states);
    }
}
