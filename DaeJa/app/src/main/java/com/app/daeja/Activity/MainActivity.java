package com.app.daeja.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.daeja.Fragment.HistroyFragment;
import com.app.daeja.Fragment.HomeFragment;
import com.app.daeja.Fragment.SearchFragment;
import com.app.daeja.Fragment.SettingsFragment;
import com.app.daeja.R;
import com.app.daeja.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (R.id.menu_home == item.getItemId()){
                replaceFragment(new HomeFragment());
            }else if (R.id.menu_search == item.getItemId()){
                replaceFragment(new SearchFragment());
            }else if (R.id.menu_profile == item.getItemId()){
                replaceFragment(new HistroyFragment());
            }else if (R.id.menu_settings == item.getItemId()){
                replaceFragment(new SettingsFragment());
            }
            return true;
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecomendActivity.class)));


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }



}