package com.example.fctcontrol.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.fctcontrol.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO REPLACE PLACEHOLDER FOR SHARED PREFERENCES LIVE DATA
        setupNavHost(getDestinationId(""));
        setupDrawerLayout();
        setupNavigationView();
    }

    private void setupNavHost(int startDestinationId) {
        NavHostFragment navHost =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        navController = Objects.requireNonNull(navHost).getNavController();
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);
        navGraph.setStartDestination(startDestinationId);
        navController.setGraph(navGraph);
    }

    private void setupDrawerLayout() {
        Toolbar toolbar = ActivityCompat.requireViewById(this, R.id.toolbar);
        DrawerLayout drawerLayout = ActivityCompat.requireViewById(this, R.id.drawerLayout);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.
                Builder(R.id.visitsExpositorFragment, R.id.studentExpositorFragment,
                R.id.businessExpositorFragment, R.id.meetingsExpositorFragment)
                .setDrawerLayout(drawerLayout)
                .build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

    }

    private void setupNavigationView() {
        NavigationView navigationView =
                ActivityCompat.requireViewById(this, R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @SuppressWarnings("SameParameterValue")
    private int getDestinationId(String destiny) {
        switch (destiny) {
            case "Visits":
                return R.id.visitsExpositorFragment;
            case "Students":
                return R.id.studentExpositorFragment;
            case "Business":
                return R.id.businessExpositorFragment;
            case "Meetings":
                return R.id.meetingsExpositorFragment;
            default:
                return R.id.visitsExpositorFragment;
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
}
