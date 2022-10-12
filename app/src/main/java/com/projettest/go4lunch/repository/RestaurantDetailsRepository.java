package com.projettest.go4lunch.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projettest.go4lunch.BuildConfig;
import com.projettest.go4lunch.datasource.NearbyPlace;
import com.projettest.go4lunch.datasource.PlaceDataSource;
import com.projettest.go4lunch.datasource.RestaurantDetails;
import com.projettest.go4lunch.datasource.RestaurantDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsRepository {
    PlaceDataSource mPlaceDataSource;

    public RestaurantDetailsRepository(PlaceDataSource placeDataSource) {
        this.mPlaceDataSource = placeDataSource;
    }


    public LiveData<RestaurantDetails> getRestaurantDetailsLiveData(String restaurantId) {
        MutableLiveData<RestaurantDetails> restaurantMutableLiveData = new MutableLiveData<>();
        mPlaceDataSource.getDetailsRestaurant(restaurantId, BuildConfig.MAPS_API_KEY).enqueue(new Callback<RestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailsResponse> call, Response<RestaurantDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restaurantMutableLiveData.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<RestaurantDetailsResponse> call, Throwable t) {
                restaurantMutableLiveData.setValue(null);
            }
        });
        return restaurantMutableLiveData;
    }

}
