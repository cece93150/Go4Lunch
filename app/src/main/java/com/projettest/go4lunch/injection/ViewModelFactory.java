package com.projettest.go4lunch.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.projettest.go4lunch.datasource.PlaceDataSource;
import com.projettest.go4lunch.model.Restaurant;
import com.projettest.go4lunch.repository.RestaurantDetailsRepository;
import com.projettest.go4lunch.repository.RestaurantRepository;
import com.projettest.go4lunch.ui.MapViewModel;
import com.projettest.go4lunch.ui.RestaurantViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory();
                }
            }
        }
        return factory;
    }

    private final RestaurantDetailsRepository restaurantDetailsRepository =
            new RestaurantDetailsRepository(PlaceDataSource.getPlaceDataSource());

    private final RestaurantRepository restaurantRepository = new RestaurantRepository(
            PlaceDataSource.getPlaceDataSource()
    );

    private ViewModelFactory() {
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MapViewModel.class)) {
            // We inject the Repository in the ViewModel constructor
            return (T) new MapViewModel(restaurantRepository);
        }
        if (modelClass.isAssignableFrom(RestaurantViewModel.class)) {
            // We inject the Repository in the ViewModel constructor
            return (T) new RestaurantViewModel(restaurantRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
