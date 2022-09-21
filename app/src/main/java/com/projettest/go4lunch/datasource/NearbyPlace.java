package com.projettest.go4lunch.datasource;



public class NearbyPlace {
    @Override
    public String toString() {
        return "NearbyPlace{" +
                "name='" + name + '\'' +
                ", place_id='" + place_id + '\'' +
                ", geometry=" + geometry +
                '}';
    }

    String name;

    public String getPlace_id() {
        return place_id;
    }

    public NearbyGeometry getGeometry() {
        return geometry;
    }

    String place_id;
    NearbyGeometry geometry;

    public String getName() {
        return name;
    }
}


