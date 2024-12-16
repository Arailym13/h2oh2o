package com.example.h2oh2o;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);

        // Инициализация NavController
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);

        // Настройка связки BottomNavigationView с NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }
}