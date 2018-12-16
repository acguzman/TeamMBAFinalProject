package com.example.alexa.teammbafinalproject;


import android.app.Activity;
import android.content.Intent;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipieDescriptionFragment extends Fragment implements View.OnClickListener{
    View inflateReviewRecycler;
    Button buttonDescriptionAdd, buttonDescriptionCook;
    TextView textViewDescriptionRecipeTitle, textViewDescriptionDescriptionText,textViewDescriptionIngredientsText;
    String stringRecipeName = "Avocado Fettuccine";
    RatingBar ratingDescriptionRating;

    public RecipieDescriptionFragment() {
        // Required empty public constructor
    }
    private ArrayList<Review> reviews;
    private ArrayList<Review> reviews2;
    private RecyclerViewAdapter recyclerViewAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getRecipieDescription();
        inflateReviewRecycler = inflater.inflate(R.layout.fragment_recipie_description, container, false);
        reviews = new ArrayList<>();
        initRecyclerView();
        getContacts();
        return inflateReviewRecycler;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        buttonDescriptionAdd = getView().findViewById(R.id.buttonDescriptionAdd);
        buttonDescriptionAdd.setOnClickListener(this);
        buttonDescriptionCook = getView().findViewById(R.id.buttonDescriptionCook);
        buttonDescriptionCook.setOnClickListener(this);
        textViewDescriptionRecipeTitle = getView().findViewById(R.id.textViewDescriptionRecipeTitle);
        textViewDescriptionDescriptionText = getView().findViewById(R.id.textViewDescriptionDescriptionText);
        textViewDescriptionIngredientsText = getView().findViewById(R.id.textViewDescriptionIngredientsText);
        ratingDescriptionRating = getView().findViewById(R.id.ratingDescriptionRating);
        getTotalReviewScore();
    }
    private void getRecipieDescription() { //pull recipie description from firebase
        FirebaseDatabase databaseDescription = FirebaseDatabase.getInstance();
        DatabaseReference recipeRef = databaseDescription.getReference("Recipe");
        // Read from the database
        recipeRef.orderByChild("recipeName").equalTo(stringRecipeName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //pull recipe information from Firebase
                Recipe recipeCurrent;
                recipeCurrent = dataSnapshot.getValue(Recipe.class);
                textViewDescriptionRecipeTitle.setText(recipeCurrent.recipeName);
                textViewDescriptionDescriptionText.setText(recipeCurrent.recipeDescription);
                textViewDescriptionIngredientsText.setText(recipeCurrent.ingredientSummary);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void getContacts() {  //pulling reviews from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("Review").orderByChild("recipeName").equalTo(stringRecipeName).limitToLast(10);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
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
                Toast.makeText(getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = inflateReviewRecycler.findViewById(R.id.recycler_view_description);
        recyclerViewAdapter = new RecyclerViewAdapter(reviews);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getTotalReviewScore() { //calculating and displaying average review score
        reviews2 = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("Review").orderByChild("recipeName").equalTo(stringRecipeName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Review contact = child.getValue(Review.class);
                    reviews2.add(contact);
                }
                int length = reviews2.size();
                float sum = 0;
                for( int i = 0; i < length; i++ ){
                    sum += Float.parseFloat(reviews2.get(i).stars);
                }
                float avg = sum/length;
                avg = Float.parseFloat(reviews2.get(0).stars);
                ratingDescriptionRating.setRating(avg);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v==buttonDescriptionAdd){


            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Recipe");
            DatabaseReference otherRef = database.getReference("User");

            //From Nevin's code: Not sure what to put in the set Value
            //DatabaseReference newDatabaseReference = myRef.push();
            //myRef.setValue(?);
           // String recipeKey = myRef.getKey();

            //How do I access the favorites child within the User class?? I've only done this with datasnapshot before and i don't think I want to add a newChildListener

            //otherRef.Favorites.add(recipeKey);

        }
        else if (v==buttonDescriptionCook){
            Intent intentRecipeStepBase = new Intent(getActivity(), RecipeStepBase.class);
            intentRecipeStepBase.putExtra("passedRecipeName", stringRecipeName);
            startActivity(intentRecipeStepBase);
        }

    }
}

/**
 *test reviews
 reviews.add(new Review("555","Chicken", 3, "This recipie is amazing!", "ABourdain"));
 reviews.add(new Review("556","pork", 4, "This recipie is the mayor of flavortown!", "GFierri"));

 **/