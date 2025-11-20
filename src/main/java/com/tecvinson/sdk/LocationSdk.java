package com.tecvinson.sdk;

import com.tecvinson.sdk.services.*;

public class LocationSdk {

    private final ApiClient apiClient;

    public final ContinentService continent;
    public final CountryService country;
    public final StateService state;
    public final CityService city;
    public final AreaService area;
    public final LocationService location;

    public LocationSdk(String baseUrl, String tenantId, String clientId, String clientSecret) {
        this.apiClient = new ApiClient(baseUrl, tenantId, clientId, clientSecret);

        this.continent = new ContinentService(apiClient);
        this.country = new CountryService(apiClient);
        this.state = new StateService(apiClient);
        this.city = new CityService(apiClient);
        this.area = new AreaService(apiClient);
        this.location = new LocationService(apiClient);
    }
}
