package com.example.alexa.teammbafinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BottomNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private GetCookingFragment getCookingFragment;
    private RecipeDiscoverFragment discoverFragment;
    private ProfileFragment1 profileFragment1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        mainNav = findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

        getCookingFragment = new GetCookingFragment();
        discoverFragment = new RecipeDiscoverFragment();
        profileFragment1 = new ProfileFragment1();

        setFragment(getCookingFragment);
        mainNav.setOnNavigationItemSelectedListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.getcooking_menu_item:
                setFragment(getCookingFragment);
                return true;
            case R.id.discover_menu_item:
                setFragment(discoverFragment);
                return true;
            case R.id.profile_menu_item:
                setFragment(profileFragment1);
                return true;
            case R.id.logout_menu_item:
                Intent logoutintent = new Intent(BottomNav.this, MainActivity.class);
                startActivity(logoutintent);
                return true;
                //how to add this to logout button? Can not see menu in the emulator

            default:
                return false;
        }


    }
}
