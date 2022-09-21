package com.projettest.go4lunch.datasource;

public class NearbyLocation {
    Double lat;
    Double lng;

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "NearbyLocation{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
