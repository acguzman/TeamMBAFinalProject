package com.example.alexa.teammbafinalproject;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipieDescriptionFragment extends Fragment implements View.OnClickListener {
    View inflateReviewRecycler;
    Button buttonDescriptionAdd, buttonDescriptionCook;
    TextView textViewDescriptionRecipeTitle, textViewDescriptionDescriptionText, textViewDescriptionIngredientsText;
    String stringRecipeName; // = "Avocado Fettuccine";
    RatingBar ratingDescriptionRating;
    ImageView imageViewDescription;

    private ArrayList<User> favorites;

    public RecipieDescriptionFragment() {
        // Required empty public constructor
    }

    private ArrayList<Review> reviews;
    private ArrayList<Review> reviews2;
    private RecyclerViewAdapter recyclerViewAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Read data passed
        stringRecipeName = getArguments().getString("passedRecipeName");

        getRecipieDescription();

        // Inflate the layout for this fragment
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
        imageViewDescription = getView().findViewById(R.id.imageViewDescription);
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
                LoadRecipeImage(imageViewDescription, recipeCurrent.picture);
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

    private void LoadRecipeImage(final ImageView imageViewDescription, String recipePicturePath) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(recipePicturePath);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Drawable src = new BitmapDrawable(getResources(),
                        BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                if (imageViewDescription == null) {
                    Log.e(TAG, "onSuccess: Image not initialized.", null);
                } else {
                    imageViewDescription.setImageDrawable(src);
                    imageViewDescription.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.e(TAG, "onFailure: " + exception.getMessage(), exception);
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
                for (DataSnapshot child : dataSnapshot.getChildren()) {
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
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Review contact = child.getValue(Review.class);
                    reviews2.add(contact);
                }

                int length = reviews2.size();
                float sum = 0;
                for (int i = 0; i < length; i++) {
                    sum += Float.parseFloat(reviews2.get(i).stars);
                }
                float avg = sum / length;
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

        if (v == buttonDescriptionAdd) {

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("Users");

            String findUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            myRef.orderByChild("email").equalTo(findUser).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User findUserRecipe = dataSnapshot.getValue(User.class);
                    if (findUserRecipe.favorites == null) {
                        findUserRecipe.favorites = new ArrayList<String>();
                    }
                    findUserRecipe.favorites.add(stringRecipeName);
                    String key = dataSnapshot.getKey();
                    myRef.child(key).setValue(findUserRecipe);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e(TAG, "onChildChanged: ", null);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.e(TAG, "onChildRemoved: ", null);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e(TAG, "onChildMoved: ", null);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled: " + databaseError.getMessage(), databaseError.toException() );
                }

            });

            Toast.makeText(getActivity(), "Recipe Added Successfully", Toast.LENGTH_SHORT).show();


        } else if (v == buttonDescriptionCook) {
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