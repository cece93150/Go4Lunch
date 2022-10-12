package com.projettest.go4lunch.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projettest.go4lunch.R;
import com.projettest.go4lunch.injection.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView mRecyclerView;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();

        RestaurantRecyclerViewAdapter adapter = new RestaurantRecyclerViewAdapter();
        mRecyclerView = view.findViewById(R.id.rcRestaurants);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));


        // initialize viewmodel
        ViewModelFactory restaurantsViewModelFactory = ViewModelFactory.getInstance();
        RestaurantViewModel restaurantsViewModel =
                new ViewModelProvider(this, restaurantsViewModelFactory)
                        .get(RestaurantViewModel.class);
        List<RestaurantsViewState> restaurantsViewStates = new ArrayList<>();
        RestaurantsViewState test = new RestaurantsViewState
                (120,"restaurant1","address","","120",
                        "opening",120,"1","",0);
        restaurantsViewStates.add(test);
        RestaurantsViewState test1 = new RestaurantsViewState
                (120,"restaurant2","address","","12",
                        "opening",12,"1","",0);
        restaurantsViewStates.add(test1);
        adapter.setRestaurantListData(restaurantsViewStates);
    }

}
