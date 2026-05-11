package com.tecvinson.sdk.services;

import com.tecvinson.sdk.ApiClient;
import com.tecvinson.sdk.ApiException;
import com.tecvinson.sdk.models.ResolveLocationRequest;
import com.tecvinson.sdk.models.ResolveLocationResponse;

import java.net.http.HttpResponse;

public class ResolveService {

    private final ApiClient apiClient;

    public ResolveService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ResolveLocationResponse resolve(
            ResolveLocationRequest request
    ) throws ApiException {

        HttpResponse<String> response =
                apiClient.post(
                        "/api/locations/resolve",
                        request
                );

        return apiClient.parseResponse(
                response,
                ResolveLocationResponse.class
        );
    }
}