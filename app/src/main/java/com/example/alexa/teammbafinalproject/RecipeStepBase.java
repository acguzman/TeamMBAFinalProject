package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeStepBase extends Activity implements View.OnClickListener {

    FloatingActionButton floatingActionButtonNext, floatingActionButtonNext2,
            floatingActionButtonNext3, floatingActionButtonChat;
    TextView textViewRecipeName, textViewStepName, textViewStepIngredientList;
    EditText editTextRecipeName;
    ImageView imageStepPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_base);

        floatingActionButtonChat = findViewById(R.id.floatingActionButtonChat);
        floatingActionButtonNext = findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext2 = findViewById(R.id.floatingActionButtonNext2);
        floatingActionButtonNext3 = findViewById(R.id.floatingActionButtonNext3);
        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewStepName = (TextView) findViewById(R.id.textViewCongrats);
        textViewStepIngredientList = (TextView) findViewById(R.id.textViewStepIngredientList);
        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);
        imageStepPhoto = (ImageView) findViewById(R.id.imageStepPhoto);

        floatingActionButtonNext.setOnClickListener(this);
        floatingActionButtonNext2.setOnClickListener(this);
        floatingActionButtonNext3.setOnClickListener(this);
        floatingActionButtonChat.setOnClickListener(this);

        autoPopulate();
    }

    private void autoPopulate() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");

        String findName = "Avocado Fettucine";

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


    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");

        if (v == floatingActionButtonChat) {

            //Intent intentLiveChat = new Intent(this, ChatActivity.class);
            //startActivity(intentLiveChat);


        } else if (v == floatingActionButtonNext) {

            String findName = "Avocado Fettucine";

            myRef.orderByChild("recipeName").equalTo(findName).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);
                    textViewRecipeName.setText(findRecipe.recipeName);
                    textViewStepName.setText(findRecipe.getStepName(1));
                    textViewStepIngredientList.setText(findRecipe.getStepIngredients(1));
                    //imageStepPhoto.setImageBitmap(stepImage);

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

            floatingActionButtonNext2.show();
            floatingActionButtonNext.hide();
            floatingActionButtonNext3.hide();

            } else if (v == floatingActionButtonNext2) {

            String findName2 = "Avocado Fettucine";

            myRef.orderByChild("recipeName").equalTo(findName2).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe findRecipe = dataSnapshot.getValue(Recipe.class);
                    textViewRecipeName.setText(findRecipe.recipeName);
                    textViewStepName.setText(findRecipe.getStepName(2));
                    textViewStepIngredientList.setText(findRecipe.getStepIngredients(2));
                    //imageStepPhoto.setImageBitmap(stepImage);

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


            floatingActionButtonNext3.show();
            floatingActionButtonNext2.hide();
            floatingActionButtonNext.hide();

        } else if (v == floatingActionButtonNext3) {

            Intent intentRecipeStepComplete = new Intent(this, RecipeStepComplete.class);
            startActivity(intentRecipeStepComplete);
        }
    }
}