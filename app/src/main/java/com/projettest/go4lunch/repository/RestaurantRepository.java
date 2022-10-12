package com.projettest.go4lunch.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projettest.go4lunch.BuildConfig;
import com.projettest.go4lunch.datasource.NearbyPlace;
import com.projettest.go4lunch.datasource.NearbySearchResponse;
import com.projettest.go4lunch.datasource.PlaceDataSource;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {
    private final PlaceDataSource restaurantApi;


    public RestaurantRepository(PlaceDataSource restaurantApi) {
        this.restaurantApi = restaurantApi;
    }

    public LiveData<List<NearbyPlace>> getRestaurantLiveData() {
        MutableLiveData<List<NearbyPlace>> restaurantMutableLiveData = new MutableLiveData<>();


        restaurantApi.getNearbySearch("48.864716,2.349014", "restaurant", BuildConfig.MAPS_API_KEY, "2000").enqueue(new Callback<NearbySearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<NearbySearchResponse> call, @NonNull Response<NearbySearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restaurantMutableLiveData.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NearbySearchResponse> call, @NonNull Throwable t) {
                restaurantMutableLiveData.setValue(null);
            }
        });
        return restaurantMutableLiveData;
    }
}
