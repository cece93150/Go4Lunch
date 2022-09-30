package com.projettest.go4lunch.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.projettest.go4lunch.RestaurantViewState;
import com.projettest.go4lunch.datasource.NearbyPlace;
import com.projettest.go4lunch.model.MapRestaurant;
import com.projettest.go4lunch.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel {
    private final RestaurantRepository repository;


    private final LiveData<RestaurantViewState> restaurantViewStateLiveData;

    public MapViewModel(RestaurantRepository restaurantRepository) {
        repository = restaurantRepository;

        // If the LiveData that contains the current page information changes...
        restaurantViewStateLiveData = Transformations.map(repository.getRestaurantLiveData(), nearbyPlaces ->
                // ... and we transform the data from the server to the ViewState (with a Transformations.map)
                mapDataToViewState(nearbyPlaces)
        );
    }

    // This is the "final product" of our ViewModel : every data needed from the view is in this LiveData
    public LiveData<RestaurantViewState> getViewStateLiveData() {
        return restaurantViewStateLiveData;
    }

    private RestaurantViewState mapDataToViewState(@Nullable List<NearbyPlace> restaurantList) {
        List<MapRestaurant> restaurantToBeDisplayed = new ArrayList<>();

        if (restaurantList != null) {
            // Mapping data from remote source to view data, ask to your mentor to know why it is important to do so
            for (NearbyPlace nearbyPlace : restaurantList) {
                double lat = nearbyPlace.getGeometry().getLocation().getLat();
                double lng = nearbyPlace.getGeometry().getLocation().getLng();
                MapRestaurant mapRestaurant = new MapRestaurant(new LatLng(lat, lng));
                restaurantToBeDisplayed.add(mapRestaurant);
            }
        }

        return new RestaurantViewState(restaurantToBeDisplayed);
    }
}
