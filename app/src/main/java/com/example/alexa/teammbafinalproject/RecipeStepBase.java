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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.content.ContentValues.TAG;

public class RecipeStepBase extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButtonNext, floatingActionButtonNext2,
            floatingActionButtonNext3, floatingActionButtonNext4,
            floatingActionButtonNext5, floatingActionButtonNext6,
            floatingActionButtonNext7, floatingActionButtonChat;
    TextView textViewRecipeName, textViewStepName, textViewStepIngredientList;
    ImageView imageStepPhoto;
    MenuItem home_menu_item, admin_add_recipe_menu_item, logout_menu_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_base);

        floatingActionButtonChat = findViewById(R.id.floatingActionButtonChat);
        floatingActionButtonNext = findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext2 = findViewById(R.id.floatingActionButtonNext2);
        floatingActionButtonNext3 = findViewById(R.id.floatingActionButtonNext3);
        floatingActionButtonNext4 = findViewById(R.id.floatingActionButtonNext4);
        floatingActionButtonNext5 = findViewById(R.id.floatingActionButtonNext5);
        floatingActionButtonNext6 = findViewById(R.id.floatingActionButtonNext6);
        floatingActionButtonNext7 = findViewById(R.id.floatingActionButtonNext7);
        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewStepName = (TextView) findViewById(R.id.textViewCongrats);
        textViewStepIngredientList = (TextView) findViewById(R.id.textViewStepIngredientList);
        imageStepPhoto = (ImageView) findViewById(R.id.imageStepPhoto);

        textViewRecipeName.setText(getIntent().getExtras().getString("passedRecipeName"));
        //Toast.makeText(this, textViewRecipeName.getText().toString(), Toast.LENGTH_SHORT).show();


        floatingActionButtonNext.setOnClickListener(this);
        floatingActionButtonNext2.setOnClickListener(this);
        floatingActionButtonNext3.setOnClickListener(this);
        floatingActionButtonNext4.setOnClickListener(this);
        floatingActionButtonNext5.setOnClickListener(this);
        floatingActionButtonNext6.setOnClickListener(this);
        floatingActionButtonNext7.setOnClickListener(this);
        floatingActionButtonChat.setOnClickListener(this);

        autoPopulate();
    }

    private void autoPopulate() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");

        String findName = textViewRecipeName.getText().toString();

        myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Recipe findRecipe = dataSnapshot.getValue(Recipe.class);
                textViewRecipeName.setText(findRecipe.recipeName);
                textViewStepName.setText(findRecipe.getStepName(0));
                textViewStepIngredientList.setText(findRecipe.getStepIngredients(0));
                //imageStepPhoto.setImageBitmap(stepImage);
                LoadRecipeImage(findRecipe.getStepImage(0));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }


    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");

        if (v == floatingActionButtonChat) {
            Intent intentLiveChat = new Intent(this, ChatActivity.class);
            startActivity(intentLiveChat);
        } else if (v == floatingActionButtonNext) {
            MoveToNextStep(myRef, 1);
            floatingActionButtonNext2.show();
            floatingActionButtonNext.hide();
        } else if (v == floatingActionButtonNext2) {
            MoveToNextStep(myRef, 2);
            floatingActionButtonNext3.show();
            floatingActionButtonNext2.hide();
        } else if (v == floatingActionButtonNext3) {
            MoveToNextStep(myRef, 3);
            floatingActionButtonNext4.show();
            floatingActionButtonNext3.hide();
        } else if (v == floatingActionButtonNext4) {
            MoveToNextStep(myRef, 4);
            floatingActionButtonNext5.show();
            floatingActionButtonNext4.hide();
        } else if (v == floatingActionButtonNext5) {
            MoveToNextStep(myRef, 5);
            floatingActionButtonNext6.show();
            floatingActionButtonNext5.hide();

        } else if (v == floatingActionButtonNext6) {
            MoveToNextStep(myRef, 6);
            floatingActionButtonNext7.show();
            floatingActionButtonNext6.hide();
        } else if (v == floatingActionButtonNext7) {
            Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete
                    .class);
            intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText()
                    .toString());
            startActivity(intentRecipeStepComplete);
        }
    }

    private void MoveToNextStep(DatabaseReference myRef, final int currStepNumber) {
        String findName = textViewRecipeName.getText().toString();
        myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Recipe findRecipe = dataSnapshot.getValue(Recipe.class);
                String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                int numStepsInt = Integer.valueOf(numSteps);
                if (numStepsInt == currStepNumber) {

                    Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this,
                            RecipeStepComplete.class);
                    intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName
                            .getText().toString());
                    startActivity(intentRecipeStepComplete);

                } else {
                    textViewRecipeName.setText(findRecipe.recipeName);
                    textViewStepName.setText(findRecipe.getStepName(currStepNumber));
                    textViewStepIngredientList.setText(findRecipe.getStepIngredients(currStepNumber));
                    //imageStepPhoto.setImageBitmap(stepImage);
                    LoadRecipeImage(findRecipe.getStepImage(currStepNumber));
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void LoadRecipeImage(String stepIngredients) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(stepIngredients);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Drawable src = new BitmapDrawable(getResources(),
                        BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                if(imageStepPhoto==null) {
                    Log.e(TAG, "onSuccess: Image not initialized.", null);
                } else {
                    imageStepPhoto.setImageDrawable(src);
                    //imageStepPhoto.setBackground(src);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.mainmenu, menu);

        MenuItem addRecipe = menu.findItem(R.id.admin_add_recipe_menu_item);
        if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equalsIgnoreCase("admin@hc" +
                ".com")) {
            addRecipe.setVisible(true);
        } else {
            addRecipe.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent homeintent = new Intent(RecipeStepBase.this, BottomNav.class);
                startActivity(homeintent);
                return true;
            case R.id.logout_menu_item:
                Intent logoutintent = new Intent(RecipeStepBase.this, MainActivity.class);
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