package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeStepBase extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButtonNext, floatingActionButtonNext2,
            floatingActionButtonNext3, floatingActionButtonNext4,
            floatingActionButtonNext5,floatingActionButtonNext6,
            floatingActionButtonNext7,floatingActionButtonChat;
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

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {            }
        });
    }


    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");

        if (v == floatingActionButtonChat) {

            //Intent intentLiveChat = new Intent(this, ChatActivity.class);
            //startActivity(intentLiveChat);


        } else if (v == floatingActionButtonNext) {

            String findName = textViewRecipeName.getText().toString();
            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);
                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);
                    if(numStepsInt == 1){

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    }else {
                        textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(1));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(1));
                        //imageStepPhoto.setImageBitmap(stepImage);
                    }
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {                }
            });

            floatingActionButtonNext2.show();
            floatingActionButtonNext.hide();

            } else if (v == floatingActionButtonNext2) {

            String findName = textViewRecipeName.getText().toString();

            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);

                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);
                    if(numStepsInt == 2){

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    }else {
                        textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(2));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(2));
                        //imageStepPhoto.setImageBitmap(stepImage);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {                }
            });


            floatingActionButtonNext3.show();
            floatingActionButtonNext2.hide();

        } else if (v == floatingActionButtonNext3) {

            String findName = textViewRecipeName.getText().toString();

            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);

                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);
                    if (numStepsInt == 3) {

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    } else {
                         textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(3));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(3));
                        //imageStepPhoto.setImageBitmap(stepImage);
                    }


                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });

            floatingActionButtonNext4.show();
            floatingActionButtonNext3.hide();


        } else if (v == floatingActionButtonNext4) {

            String findName = textViewRecipeName.getText().toString();

            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);

                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);
                    if(numStepsInt == 4){

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    }else {
                        textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(4));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(4));
                        //imageStepPhoto.setImageBitmap(stepImage);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {                }
            });

            floatingActionButtonNext5.show();
            floatingActionButtonNext4.hide();

        } else if (v == floatingActionButtonNext5) {

            String findName = textViewRecipeName.getText().toString();

            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);

                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);
                    if(numStepsInt == 5){

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    }else {
                        textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(5));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(5));
                        //imageStepPhoto.setImageBitmap(stepImage);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {                }
            });

            floatingActionButtonNext6.show();
            floatingActionButtonNext5.hide();

        } else if (v == floatingActionButtonNext6) {

            String findName = textViewRecipeName.getText().toString();

            ChildEventListener childEventListener = myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);

                    String numSteps = dataSnapshot.getValue(Recipe.class).numberOfSteps;
                    int numStepsInt = Integer.valueOf(numSteps);


                    if (numStepsInt==6) {

                        Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
                        intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
                        startActivity(intentRecipeStepComplete);

                    } else {

                        textViewRecipeName.setText(findRecipe.recipeName);
                        textViewStepName.setText(findRecipe.getStepName(6));
                        textViewStepIngredientList.setText(findRecipe.getStepIngredients(6));
                        //imageStepPhoto.setImageBitmap(stepImage);

                    }
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });

            floatingActionButtonNext7.show();
            floatingActionButtonNext6.hide();

        } else if (v == floatingActionButtonNext7) {


            Intent intentRecipeStepComplete = new Intent(RecipeStepBase.this, RecipeStepComplete.class);
            intentRecipeStepComplete.putExtra("passedRecipeName", textViewRecipeName.getText().toString());
            startActivity(intentRecipeStepComplete);
        }
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
                Intent homeintent = new Intent(RecipeStepBase.this, BottomNav.class );
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