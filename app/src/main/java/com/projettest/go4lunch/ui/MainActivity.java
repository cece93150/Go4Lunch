package com.projettest.go4lunch.ui;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projettest.go4lunch.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TOOLBAR
        toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        //Handle fragments in bottom navigation view
        bottomNavBarFragmentsManagement();

        //Display Map fragment by default
        displayFragment(new MapsFragment());

    }

    public void displayFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void bottomNavBarFragmentsManagement() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.map_bottom:
                    toolbar.setTitle("I'm Hungry");
                    MainActivity.this.displayFragment(new MapsFragment());
                    return true;
                case R.id.list_bottom:
                    toolbar.setTitle("I'm Hungry");
                    MainActivity.this.displayFragment(new ListFragment());
                    return true;
                case R.id.workmates_bottom:
                    toolbar.setTitle("Available Workmates");
                    MainActivity.this.displayFragment(new WorkmatesFragment());
                    return true;
            }
            return false;
        });
    }
}


