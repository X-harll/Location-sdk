package com.tecvinson.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class ApiClient {

    private final String baseUrl;


    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Retry config
    private static final int MAX_RETRIES = 3;
    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(15);

    public ApiClient(String baseUrl, String tenantId, String clientId, String clientSecret) {
        this.baseUrl = baseUrl;


        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(CONNECT_TIMEOUT)
                .build();

        this.objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    // ✅ URL builder (prevents // bugs)
    private String buildUrl(String path) {
        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            return baseUrl + path.substring(1);
        } else if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
            return baseUrl + "/" + path;
        }
        return baseUrl + path;
    }

    private HttpRequest.Builder baseRequestBuilder(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(path)))
                .timeout(REQUEST_TIMEOUT)
                .header("Content-Type", "application/json");
    }

    // ========================
    // HTTP METHODS
    // ========================

    public HttpResponse<String> get(String path) throws ApiException {
        HttpRequest request = baseRequestBuilder(path)
                .GET()
                .build();

        return sendWithRetry(request, "GET");
    }

    public HttpResponse<String> post(String path, Object body) throws ApiException {
        HttpRequest request = baseRequestBuilder(path)
                .POST(HttpRequest.BodyPublishers.ofString(toJson(body)))
                .build();

        return sendWithRetry(request, "POST");
    }

    public HttpResponse<String> put(String path, Object body) throws ApiException {
        HttpRequest request = baseRequestBuilder(path)
                .PUT(HttpRequest.BodyPublishers.ofString(toJson(body)))
                .build();

        return sendWithRetry(request, "PUT");
    }

    // ========================
    // RETRY LOGIC (CRITICAL)
    // ========================

    private HttpResponse<String> sendWithRetry(HttpRequest request, String method) throws ApiException {
        int attempt = 0;

        while (true) {
            try {
                attempt++;

                HttpResponse<String> response =
                        httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                checkResponseStatus(response);

                return response;

            } catch (Exception e) {

                if (attempt >= MAX_RETRIES) {
                    throw new ApiException(method + " request failed after "
                            + attempt + " attempts: " + e.getMessage(), e);
                }

                // simple backoff
                try {
                    Thread.sleep(500L * attempt);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    // ========================
    // RESPONSE HANDLING
    // ========================

    private void checkResponseStatus(HttpResponse<String> response) throws ApiException {
        int status = response.statusCode();

        if (status < 200 || status >= 300) {
            throw new ApiException(
                    "HTTP " + status + " error for [" + response.uri() + "]: " + response.body()
            );
        }
    }

    public <T> T parseResponse(HttpResponse<String> response, Class<T> clazz) throws ApiException {
        try {
            return objectMapper.readValue(response.body(), clazz);
        } catch (Exception e) {
            throw new ApiException("Failed to parse JSON response: " + e.getMessage(), e);
        }
    }

    public String toJson(Object obj) throws ApiException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new ApiException("Failed to serialize object: " + e.getMessage(), e);
        }
    }
}