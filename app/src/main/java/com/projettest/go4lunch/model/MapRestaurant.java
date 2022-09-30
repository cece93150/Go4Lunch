package com.projettest.go4lunch.model;

import com.google.android.gms.maps.model.LatLng;

public class MapRestaurant {
    private LatLng location;

    public LatLng getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "MapRestaurant{" +
                "location=" + location +
                '}';
    }

    public MapRestaurant(LatLng location){
        this.location = location;
    }
}


