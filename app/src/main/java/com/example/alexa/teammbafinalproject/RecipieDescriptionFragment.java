package com.example.alexa.teammbafinalproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipieDescriptionFragment extends Fragment {

    public RecipieDescriptionFragment() {
        // Required empty public constructor
    }
    private ArrayList<Review> reviews;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_recipie_description, container, false);
    }

}

/**
 * initContacts();
 private void initContacts() { //this is a fake reviews list to test the recycler view
 reviews = new ArrayList<>();
 reviews.add(new Review("555",3, "This recipie is amazing!", "ABourdain"));
 reviews.add(new Review("556",4, "This recipie is the president of flavortown!", "GFierri"));
 initRecyclerView();
 }
 private void initRecyclerView() {
 RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
 RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(reviews, this.getContext());
 recyclerView.setAdapter(recyclerViewAdapter);
 recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
 }
 **/