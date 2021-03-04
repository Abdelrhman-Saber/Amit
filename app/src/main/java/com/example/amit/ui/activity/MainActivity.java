package com.example.amit.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.amit.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkBetweenBAndN();
    }
    protected void linkBetweenBAndN(){
        bottomNavigation=findViewById(R.id.bottomNavigation);
        navController= Navigation.findNavController(this,R.id.fragment_container);
        NavigationUI.setupWithNavController(bottomNavigation,navController);
    }
}