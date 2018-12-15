package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class RecipeStepComplete extends AppCompatActivity {

    TextView textViewRecipeName;
    EditText editTextCommentEntry;
    ImageButton imageButtonUploadPhoto;
    Button buttonSubmitReview;
    RatingBar ratingBarEntry;
    FirebaseDatabase fdb;
    private FirebaseAuth mAuth;
    Bitmap bitmap;


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

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        //switches to camera and takes picture then sets it as imageButtonUploadPhoto
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageButtonUploadPhoto.setImageBitmap(bitmap);
    }

    public void buttonSubmitReviewClicked() {
     //   Upload review information into review class
        //Currently tested and working - haven't checked to see how image will pull from firebase,
        //          but something is loaded in there as a string value
        float rating = ratingBarEntry.getRating();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c);
        fdb = FirebaseDatabase.getInstance();
        DatabaseReference myReview = fdb.getReference("Review");
        Review newReview = new Review(UUID.randomUUID().toString(), textViewRecipeName.getText().toString(),
                String.valueOf(rating), editTextCommentEntry.getText().toString(),
                mAuth.getCurrentUser().getEmail(),imageButtonUploadPhoto.toString(),formattedDate);

        myReview.push().setValue(newReview);
    }

    public void imageButtonUploadPhotoClicked() {
        //put here how to upload photo from phone
        Intent intentUpload = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentUpload,0);

    }

    public static String encodeFromString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.mainmenu,menu);

        MenuItem addRecipe = menu.findItem(R.id.admin_add_recipe_menu_item);
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equalsIgnoreCase("admin@hc.com")) {
            addRecipe.setVisible(true);
        } else {
            addRecipe.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menu_item:
                Intent homeintent = new Intent(RecipeStepComplete.this, BottomNav.class );
                startActivity(homeintent);
                return true;
            case R.id.logout_menu_item:
                Intent logoutintent = new Intent(RecipeStepComplete.this, MainActivity.class);
                startActivity(logoutintent);
                return true;
            case R.id.admin_add_recipe_menu_item:
                Intent intentRecipes = new Intent(this, AddRecipestoDatabase.class);
                startActivity(intentRecipes);
                return true;
            default:
                return false;
        }


    }
}

