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

    private HomeFragment1 homeFragment1;
    private GroceryListFragment1 groceryListFragment1;
    private GetCookingFragment getCookingFragment;
    private ProfileFragment1 profileFragment1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        mainNav = findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

        homeFragment1 = new HomeFragment1();
        groceryListFragment1 = new GroceryListFragment1();
        getCookingFragment = new GetCookingFragment();
        profileFragment1 = new ProfileFragment1();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home_nav_item:
                setFragment(homeFragment1);
                return true;
            case R.id.groceries_nav_item:
                setFragment(groceryListFragment1);
                return true;
            // i created cooking fragment after unchecking two columns
                case R.id.cooking_nav_item:
                setFragment(getCookingFragment);
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
