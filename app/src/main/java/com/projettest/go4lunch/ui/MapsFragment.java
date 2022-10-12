package com.projettest.go4lunch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.projettest.go4lunch.R;
import com.projettest.go4lunch.injection.ViewModelFactory;
import com.projettest.go4lunch.model.MapRestaurant;


public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        MapViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MapViewModel.class);
        LatLng paris = new LatLng(48.864716, 2.349014);

        viewModel.getViewStateLiveData().observe(getViewLifecycleOwner(),
                RestaurantViewState -> {
                    if (RestaurantViewState != null) {
                        for (MapRestaurant restaurant : RestaurantViewState.getRestaurants()) {
                            // Création d'un marker à afficher sur la carte pour chaque restaurant

                            // La position du marker est la location de ton restaurant
                            MarkerOptions marker = new MarkerOptions().position(restaurant.getLocation());
                            // Ajout du marker à la carte
                            mMap.addMarker(marker);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    paris,
                                    15));
                        }
                    }
                });

    }
}