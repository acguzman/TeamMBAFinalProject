package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddRecipestoDatabase extends Activity implements View.OnClickListener{

    EditText editTextRecipeName, editTextRecipeDescriptionEmpty, editTextIngredientSummary,editTextStepName1Entry,editTextStepName2Entry,editTextStepName3Entry,editTextStepIngredient1Entry,editTextStepIngredient2Entry,editTextStepIngredient3Entry;
    TextView textViewStep1Entry,textViewStep2Entry,textViewStep3Entry;
    Button buttonSaveRecipeEntry;
    CheckBox checkBoxVegEntry,checkBoxVeganEntry, checkBoxDairyFreeEntry,checkBoxGlutenFreeEntry,checkBoxNutFreeEntry;
    //Drawable imageViewRecipeCompleteImage, imageViewStep1Image,imageViewStep2Image,imageViewStep3Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipesto_database);

        Resources res = getResources();
        //Drawable drawable = res.getDrawable(R.drawable.avocado_fettucine_complete);

        //recipeID

        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName) ;
        editTextRecipeDescriptionEmpty = (EditText) findViewById(R.id.editTextRecipeDescriptionEntry);
        //imageViewRecipeCompleteImage = (Drawable) res.getDrawable(R.drawable.imageViewRecipeCompleteImage);
        checkBoxVegEntry = (CheckBox) findViewById(R.id.checkBoxVegEntry);
        checkBoxVeganEntry = (CheckBox) findViewById(R.id.checkBoxVeganEntry);
        checkBoxDairyFreeEntry = (CheckBox) findViewById(R.id.checkBoxDairyFreeEntry);
        checkBoxGlutenFreeEntry = (CheckBox) findViewById(R.id.checkBoxGlutenFreeEntry);
        checkBoxNutFreeEntry = (CheckBox) findViewById(R.id.checkBoxNutFreeEntry);
        editTextIngredientSummary = (EditText) findViewById(R.id.editTextIngredientSummary);
        editTextStepName1Entry = (EditText) findViewById(R.id.editTextStepName1Entry);
        editTextStepName2Entry = (EditText) findViewById(R.id.editTextStepName2Entry);
        editTextStepName3Entry = (EditText) findViewById(R.id.editTextStepName3Entry);
        //step image
        editTextStepIngredient1Entry = (EditText) findViewById(R.id.editTextStepIngredient1Entry);
        editTextStepIngredient2Entry = (EditText) findViewById(R.id.editTextStepIngredient2Entry);
        editTextStepIngredient3Entry = (EditText) findViewById(R.id.editTextStepIngredient3Entry);

        textViewStep1Entry = (TextView) findViewById(R.id.textViewStep1Entry);
        textViewStep2Entry = (TextView) findViewById(R.id.textViewStep2Entry);;
        textViewStep3Entry = (TextView) findViewById(R.id.textViewStep3Entry);;

        buttonSaveRecipeEntry = (Button) findViewById(R.id.buttonSaveRecipeEntry);

        buttonSaveRecipeEntry.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");
        Resources res = getResources();
//hard coded right now
        Drawable drawable = res.getDrawable(R.drawable.avocado_fettucine_complete);

        if(v == buttonSaveRecipeEntry){

            String createRecipeID = UUID.randomUUID().toString();
            String createRecipe = editTextRecipeName.getText().toString();
            String createRecipeDescription = editTextRecipeDescriptionEmpty.getText().toString();
            //hard coded picture for now
           // Drawable createRecipeCompletePicture = res.getDrawable(R.drawable.avocado_fettucine_complete);
            String createIngredientSummary = editTextIngredientSummary.getText().toString();
            String createStep1Name = editTextStepName1Entry.getText().toString();
            String createStep2Name = editTextStepName2Entry.getText().toString();
            String createStep3Name = editTextStepName3Entry.getText().toString();
            //Step Pictures
            String createStep1Ingredient = editTextStepIngredient1Entry.getText().toString();
            String createStep2Ingredient = editTextStepIngredient2Entry.getText().toString();
            String createStep3Ingredient = editTextStepIngredient3Entry.getText().toString();

            //Recipe newRecipe = new Recipe(createRecipeID,createRecipe,createRecipeDescription,createIngredientSummary,createStep1Name, createStep2Name, createStep3Name,createStep1Ingredient,createStep2Ingredient,createStep3Ingredient);

            //myRef.push().setValue(newRecipe)


            //if(CheckBox.recipeCompletePicture){
           // CheckBox createCheckBoxVegEntry = xxx.iftrue;

        }
    }
}
