package com.example.fctcontrol.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fctcontrol.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TypedArray ar = getResources().obtainTypedArray(R.array.destinyArray);
//        int id = ar.getResourceId(0, 0);
//        ar.recycle();
        setupNavHost(R.id.visitsExpositorFragment);
    }

    private void setupNavHost(int startDestinationId) {
        //TODO AQUI DEBERIA RECIBIR EL int DEL SHAREDPREFERENCIESLIVEDATA
        NavHostFragment navHost =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        // Se obtiene el NavController del NavHostFragment
        NavController navController = Objects.requireNonNull(navHost).getNavController();
        // Se obtiene el inflador NavInflater del NavController
        NavInflater navInflater = navController.getNavInflater();
        // Se infla el grafo de navegación
        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);
        // Se determina el destino inicial
        // Se establece en el NavGraph el destino inicial
        navGraph.setStartDestination(startDestinationId);
        // Se establece NavGraph como grafo de navegación con el que
        // debe trabajar el NavController
        navController.setGraph(navGraph);
    }
}
