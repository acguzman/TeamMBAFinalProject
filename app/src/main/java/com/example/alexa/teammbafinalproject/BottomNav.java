package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class BottomNav extends AppCompatActivity {

    private BottomNavigationItemView mainNav;
    private FrameLayout mainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        mainNav = (BottomNavigationItemView) findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);

    }
}
