package com.projettest.go4lunch.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.projettest.go4lunch.BuildConfig;
import com.projettest.go4lunch.R;
import com.projettest.go4lunch.datasource.NearbyPlace;
import com.projettest.go4lunch.datasource.NearbySearchResponse;
import com.projettest.go4lunch.datasource.PlaceDataSource;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Log.d("MapsFragment", "onMapReady: ");

        PlaceDataSource mPlaceDataSource = PlaceDataSource.retrofit.create(PlaceDataSource.class);
        mPlaceDataSource.getNearbySearch("48.864716,2.349014","restaurant", BuildConfig.MAPS_API_KEY,"2000")
                .enqueue(new Callback<NearbySearchResponse>() {
                    @Override
                    public void onResponse(Call<NearbySearchResponse> call, Response<NearbySearchResponse> response) {
                        Log.d("MapsFragment", "onResponse: ");
                        if (response.isSuccessful()){
                            NearbySearchResponse nearbySearchResponse = response.body();
                            Log.d("MapsFragment", "onResponse: "+nearbySearchResponse.getResults().size());
                            for (NearbyPlace nearbyPlace : nearbySearchResponse.getResults()) {
                             MarkerOptions marker = new MarkerOptions()
                                        .position(new LatLng(nearbyPlace.getGeometry().getLocation().getLat(),nearbyPlace.getGeometry().getLocation().getLng()));
                               mMap.addMarker(marker);
                                Log.d("MapsFragment", "onResponse: "+nearbyPlace);
                            }
                        }
                        else {
                            Log.d("MapsFragment", "onError: "+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<NearbySearchResponse> call, Throwable t) {
                        Log.d("MapsFragment", "onFailure: ");
                        t.printStackTrace();
                    }
                });
        // Add a marker in Paris and move the camera
        LatLng paris = new LatLng(48.864716, 2.349014);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 15));

    }
}