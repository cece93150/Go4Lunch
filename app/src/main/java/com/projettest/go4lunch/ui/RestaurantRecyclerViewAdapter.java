package com.projettest.go4lunch.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projettest.go4lunch.R;

import java.util.ArrayList;
import java.util.List;


public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder> {

    private List<RestaurantsViewState> restaurantList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantsViewState restaurant = restaurantList.get(position);
        holder.mRestaurantName.setText(restaurant.getName());
        holder.mAddress.setText(restaurant.getAddress());
        holder.mRating.setRating(restaurant.getRating());
        holder.mDistance.setText(restaurant.getDistanceText());
        holder.mOpeningHours.setText(restaurant.getOpeningHours());
        holder.mPeople.setText(restaurant.getUsersWhoChoseThisRestaurant());
                Glide.with(holder.mPicture.getContext())
                .load(restaurant.getPhoto())
                .into(holder.mPicture);
    }

    @Override
    public int getItemCount() {
        if (restaurantList == null) {
            return 0;
        } else {
            return restaurantList.size();
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setRestaurantListData(List<RestaurantsViewState> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mRestaurantName;
        public TextView mAddress;
        public TextView mOpeningHours;
        public TextView mDistance;
        public TextView mPeople;
        public RatingBar mRating;
        public ImageView mPicture;


        public ViewHolder(View view) {
            super(view);
            mRestaurantName = view.findViewById(R.id.tvListRestaurantName);
            mAddress = view.findViewById(R.id.tvListRestaurantAddress);
            mOpeningHours = view.findViewById(R.id.tvListRestaurantOpeningHours);
            mDistance = view.findViewById(R.id.tvListRestaurantDistance);
            mRating = view.findViewById(R.id.ivListRestaurantRating);
            mPeople = view.findViewById(R.id.tvListRestaurantNbPeopleEating);
            mPicture = view.findViewById(R.id.ivListRestaurantPhoto);
        }
    }
}

