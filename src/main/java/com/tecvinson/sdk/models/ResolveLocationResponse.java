package com.tecvinson.sdk.models;

import java.util.UUID;

public class ResolveLocationResponse {

    private UUID continentId;
    private UUID countryId;
    private UUID stateId;
    private UUID cityId;
    private UUID areaId;
    private UUID locationId;

    public UUID getContinentId() {
        return continentId;
    }

    public void setContinentId(UUID continentId) {
        this.continentId = continentId;
    }

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }

    public UUID getStateId() {
        return stateId;
    }

    public void setStateId(UUID stateId) {
        this.stateId = stateId;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }
}