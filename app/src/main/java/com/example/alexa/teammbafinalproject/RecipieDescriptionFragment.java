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
    View inflateReviewRecycler;

    public RecipieDescriptionFragment() {
        // Required empty public constructor
    }
    private ArrayList<Review> reviews;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflateReviewRecycler = inflater.inflate(R.layout.fragment_recipie_description, container, false);
        initContacts();
        return inflateReviewRecycler;
    }
    private void initContacts() { //this is a fake reviews list to test the recycler view
        reviews = new ArrayList<>();
        reviews.add(new Review("555","Chicken", 3, "This recipie is amazing!", "ABourdain"));
        reviews.add(new Review("556","pork", 4, "This recipie is the mayor of flavortown!", "GFierri"));
        initRecyclerView();
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = inflateReviewRecycler.findViewById(R.id.recycler_view_description);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(reviews);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}

/**
 *

 **/