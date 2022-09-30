package com.projettest.go4lunch.datasource;

import com.projettest.go4lunch.model.Restaurant;

import java.util.List;

public class NearbySearchResponse {
    List<NearbyPlace> results;

    public List<NearbyPlace> getResults() {
        return results;
    }
}
