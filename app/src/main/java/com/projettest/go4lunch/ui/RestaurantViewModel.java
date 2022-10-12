package com.projettest.go4lunch.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.projettest.go4lunch.model.Restaurant;
import com.projettest.go4lunch.repository.RestaurantDetailsRepository;
import com.projettest.go4lunch.repository.RestaurantRepository;

public class RestaurantViewModel extends ViewModel {

        private final RestaurantRepository repository;


       // private final LiveData<RestaurantsViewState> restaurantViewStateLiveData;

    public RestaurantViewModel(RestaurantRepository restaurantRepository) {
            repository = restaurantRepository;

            // If the LiveData that contains the current page information changes...
           // restaurantViewStateLiveData = Transformations.map(repository.getRestaurantLiveData(), nearbyPlaces ->
                    // ... and we transform the data from the server to the ViewState (with a Transformations.map)
                    //mapDataToViewState(nearbyPlaces)
    //        );
        }

   // public LiveData<RestaurantsViewState> getViewStateLiveData() {
       // return restaurantViewStateLiveData;
  //  }

}
