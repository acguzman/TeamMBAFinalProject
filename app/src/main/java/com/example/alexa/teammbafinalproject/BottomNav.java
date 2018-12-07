package com.example.alexa.teammbafinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BottomNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private GetCookingFragment getCookingFragment;
    private DiscoverFragment discoverFragment;
    private ProfileFragment1 profileFragment1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        mainNav = findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

        getCookingFragment = new GetCookingFragment();
        discoverFragment = new DiscoverFragment();
        profileFragment1 = new ProfileFragment1();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.cooking_nav_item:
                setFragment(getCookingFragment);
                return true;
            case R.id.discover_nav_item:
                setFragment(discoverFragment);
                return true;
            case R.id.profile_nav_item:
                setFragment(profileFragment1);
                return true;
            default:
                return false;
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }
}
