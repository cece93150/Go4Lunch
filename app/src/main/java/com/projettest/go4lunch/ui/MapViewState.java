package com.projettest.go4lunch.ui;

import androidx.annotation.NonNull;

import com.projettest.go4lunch.datasource.NearbyPlace;
import com.projettest.go4lunch.model.MapRestaurant;

import java.util.List;
import java.util.Objects;

public class MapViewState {
    @NonNull
    private List<MapRestaurant> restaurants;

    public MapViewState(
            @NonNull List<MapRestaurant> restaurants

    ) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "MapViewState{" +
                "restaurants=" + restaurants +
                '}';
    }

    @NonNull
    public List<MapRestaurant> getRestaurants() {
        return restaurants;
    }




}
