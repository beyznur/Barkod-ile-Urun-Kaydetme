package com.example.sonkt;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomBar;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar=(BottomNavigationView)findViewById(R.id.main_activity_bottomNavigationView);
        homeFragment=new HomeFragment();
        searchFragment=new SearchFragment();
        profileFragment=new ProfileFragment();
        setFragment(homeFragment);


        mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottombar_menu_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.bottombar_menu_search:
                        setFragment(searchFragment);
                        return true;
                    case R.id.bottombar_menu_profile:
                        setFragment(profileFragment);
                        return true;
                    default:
                        return false;
                }
            }

        });

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_frameLayout,fragment);
        transaction.commit();

    }

}