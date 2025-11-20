package com.tecvinson.sdk;

import java.net.URI;
import java.net.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClient {

    private final String baseUrl;
    private final String tenantId;
    private final String clientId;
    private final String clientSecret;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient(String baseUrl, String tenantId, String clientId, String clientSecret) {
        this.baseUrl = baseUrl;
        this.tenantId = tenantId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    private HttpRequest.Builder baseRequestBuilder(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("X-TENANT-ID", tenantId)
                .header("X-CLIENT-ID", clientId)
                .header("X-CLIENT-SECRET", clientSecret)
                .header("Content-Type", "application/json");
    }

    // GET request
    public HttpResponse<String> get(String path) throws ApiException {
        try {
            HttpRequest request = baseRequestBuilder(path)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            checkResponseStatus(response);
            return response;

        } catch (Exception e) {
            throw new ApiException("GET request failed: " + e.getMessage(), e);
        }
    }

    // POST request
    public HttpResponse<String> post(String path, Object body) throws ApiException {
        try {
            String jsonBody = toJson(body);

            HttpRequest request = baseRequestBuilder(path)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            checkResponseStatus(response);
            return response;

        } catch (Exception e) {
            throw new ApiException("POST request failed: " + e.getMessage(), e);
        }
    }

    // PUT request
    public HttpResponse<String> put(String path, Object body) throws ApiException {
        try {
            String jsonBody = toJson(body);

            HttpRequest request = baseRequestBuilder(path)
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            checkResponseStatus(response);
            return response;

        } catch (Exception e) {
            throw new ApiException("PUT request failed: " + e.getMessage(), e);
        }
    }

    // Validate HTTP Status
    private void checkResponseStatus(HttpResponse<String> response) throws ApiException {
        int status = response.statusCode();
        if (status < 200 || status >= 300) {
            throw new ApiException("HTTP Error: " + status + " - " + response.body());
        }
    }

    // Convert JSON response into object
    public <T> T parseResponse(HttpResponse<String> response, Class<T> clazz) throws ApiException {
        try {
            return objectMapper.readValue(response.body(), clazz);
        } catch (Exception e) {
            throw new ApiException("Failed to parse JSON response: " + e.getMessage(), e);
        }
    }

    // Serialize object to JSON
    public String toJson(Object obj) throws ApiException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new ApiException("Failed to serialize object: " + e.getMessage(), e);
        }
    }
}
