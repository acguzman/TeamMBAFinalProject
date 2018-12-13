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

public class RecipeStepComplete extends Activity implements RatingBar.OnRatingBarChangeListener {

    TextView textViewRecipeName;
    EditText editTextCommentEntry;
    ImageButton imageButtonUploadPhoto;
    Button buttonSubmitReview;
    RatingBar ratingBarEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_complete);

        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        editTextCommentEntry = (EditText) findViewById(R.id.editTextCommentEntry);
        imageButtonUploadPhoto = (ImageButton) findViewById(R.id.imageButtonUploadPhoto);
        buttonSubmitReview = (Button) findViewById(R.id.buttonSubmitReview);
        ratingBarEntry = (RatingBar) findViewById(R.id.ratingBarEntry);

        ratingBarEntry.setOnRatingBarChangeListener((RatingBar.OnRatingBarChangeListener) this);

        imageButtonUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonUploadPhotoClicked();
                buttonSubmitReviewClicked();
            }
        });

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        //put here what happens when rating is clicked
    }


    public void imageButtonUploadPhotoClicked() {
        //put here code for opening camera and uploading photo

    }

    public void buttonSubmitReviewClicked() {
        //put here code to upload review information into review class

    }


}
