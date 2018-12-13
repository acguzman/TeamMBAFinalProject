package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RecipeStepComplete extends Activity implements RatingBar.OnRatingBarChangeListener {

    TextView textViewRecipeName;
    EditText editTextCommentEntry;
    ImageButton imageButtonUploadPhoto;
    Button buttonSubmitReview;
    RatingBar ratingBarEntry;
    FirebaseDatabase fdb;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_complete);

        mAuth = FirebaseAuth.getInstance();

        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        editTextCommentEntry = (EditText) findViewById(R.id.editTextCommentEntry);
        imageButtonUploadPhoto = (ImageButton) findViewById(R.id.imageButtonUploadPhoto);
        buttonSubmitReview = (Button) findViewById(R.id.buttonSubmitReview);
        ratingBarEntry = (RatingBar) findViewById(R.id.ratingBarEntry);

        ratingBarEntry.setOnRatingBarChangeListener((RatingBar.OnRatingBarChangeListener) this);

        imageButtonUploadPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageButtonUploadPhotoClicked();
            }
        });
        buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSubmitReviewClicked();

            }
        });

    }


    public void buttonSubmitReviewClicked() {
     //   put here code to upload review information into review class

        fdb = FirebaseDatabase.getInstance();
        DatabaseReference myReview = fdb.getReference("Review");

        Review newReview = new Review(UUID.randomUUID().toString(), textViewRecipeName.getText().toString(),
                0, editTextCommentEntry.getText().toString(),mAuth.getCurrentUser().getEmail());

        myReview.push().setValue(newReview);
    }

    public void imageButtonUploadPhotoClicked() {
        //put here how to upload photo from phone
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        //put here what happens when rating is clicked
    }


}
