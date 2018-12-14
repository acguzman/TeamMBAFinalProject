package com.example.alexa.teammbafinalproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipieDescriptionFragment extends Fragment implements View.OnClickListener{
    View inflateReviewRecycler;
    Button buttonDescriptionAdd;

    public RecipieDescriptionFragment() {
        // Required empty public constructor
    }
    private ArrayList<Review> reviews;
    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        buttonDescriptionAdd = getView().findViewById(R.id.buttonDescriptionAdd);
        buttonDescriptionAdd.setOnClickListener(this);
        inflateReviewRecycler = inflater.inflate(R.layout.fragment_recipie_description, container, false);
        reviews = new ArrayList<>();
        initRecyclerView();
        getContacts();
        return inflateReviewRecycler;



    }
    private void getContacts() {  //use contactsRef.limitToLast(10).addChildEventListnener....
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference contactsRef = database.getReference("Review");
        // Read from the database
        contactsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Review contact = child.getValue(Review.class);
                    reviews.add(contact);
                 }
                 recyclerViewAdapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = inflateReviewRecycler.findViewById(R.id.recycler_view_description);
        recyclerViewAdapter = new RecyclerViewAdapter(reviews);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {

    }
}

/**
 *test reviews
 reviews.add(new Review("555","Chicken", 3, "This recipie is amazing!", "ABourdain"));
 reviews.add(new Review("556","pork", 4, "This recipie is the mayor of flavortown!", "GFierri"));

 **/