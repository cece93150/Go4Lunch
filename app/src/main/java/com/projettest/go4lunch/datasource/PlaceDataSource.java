package com.projettest.go4lunch.datasource;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceDataSource {
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/maps/api/place/nearbysearch/json")
    Call<NearbySearchResponse> getNearbySearch(
            @Query("location") String location,
            @Query("types") String types,
            @Query("key") String key,
            @Query("radius") String radius);

    @GET("/maps/api/place/details/json")
    Call<RestaurantDetailsResponse> getDetailsRestaurant(
            @Query("place_id") String placeId,
            @Query("key") String key
    );



    public static PlaceDataSource getPlaceDataSource() {
        return retrofit.create(PlaceDataSource.class);
    }
}

