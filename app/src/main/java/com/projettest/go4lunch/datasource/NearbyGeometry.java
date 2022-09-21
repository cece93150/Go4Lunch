package com.projettest.go4lunch.datasource;

public class NearbyGeometry {
    public NearbyLocation getLocation() {
        return location;
    }

    NearbyLocation location;

    @Override
    public String toString() {
        return "NearbyGeometry{" +
                "location=" + location +
                '}';
    }
}
