package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Environment;
import android.os.Parcel;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class AddRecipestoDatabase extends AppCompatActivity implements View.OnClickListener {

    EditText editTextRecipeName, editTextRecipeDescriptionEntry, editTextIngredientSummary, editTextNumberOfSteps;
    EditText editTextStepName1Entry, editTextStepIngredient1Entry;
    EditText editTextStepName2Entry, editTextStepIngredient2Entry;
    EditText editTextStepName3Entry, editTextStepIngredient3Entry;
    EditText editTextStepName4Entry, editTextStepIngredient4Entry;
    EditText editTextStepName5Entry, editTextStepIngredient5Entry;
    EditText editTextStepName6Entry, editTextStepIngredient6Entry;
    EditText editTextStepName7Entry, editTextStepIngredient7Entry;

    CheckBox checkBoxVegEntry, checkBoxVeganEntry, checkBoxDairyFreeEntry,
            checkBoxGlutenFreeEntry, checkBoxNutFreeEntry;

    ImageView imageViewRecipeCompleteImage, imageViewStep1Image, imageViewStep2Image,
            imageViewStep3Image;

    Button buttonSaveRecipeEntry;


    FirebaseDatabase fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipesto_database);

        editTextRecipeName = findViewById(R.id.editTextRecipeName);
        editTextRecipeDescriptionEntry = findViewById(R.id.editTextRecipeDescriptionEntry);
        editTextIngredientSummary = findViewById(R.id.editTextIngredientSummary);
        editTextNumberOfSteps = findViewById(R.id.editTextNumberOfSteps);
        editTextStepName1Entry = findViewById(R.id.editTextStepName1Entry);
        editTextStepName2Entry = findViewById(R.id.editTextStepName2Entry);
        editTextStepName3Entry = findViewById(R.id.editTextStepName3Entry);
        editTextStepName4Entry = findViewById(R.id.editTextStepName4Entry);
        editTextStepName5Entry = findViewById(R.id.editTextStepName5Entry);
        editTextStepName6Entry = findViewById(R.id.editTextStepName6Entry);
        editTextStepName7Entry = findViewById(R.id.editTextStepName7Entry);
        editTextStepIngredient1Entry = findViewById(R.id.editTextStepIngredient1Entry);
        editTextStepIngredient2Entry = findViewById(R.id.editTextStepIngredient2Entry);
        editTextStepIngredient3Entry = findViewById(R.id.editTextStepIngredient3Entry);
        editTextStepIngredient4Entry = findViewById(R.id.editTextStepIngredient4Entry);
        editTextStepIngredient5Entry = findViewById(R.id.editTextStepIngredient5Entry);
        editTextStepIngredient6Entry = findViewById(R.id.editTextStepIngredient6Entry);
        editTextStepIngredient7Entry = findViewById(R.id.editTextStepIngredient7Entry);

        checkBoxVegEntry = findViewById(R.id.checkBoxVegEntry);
        checkBoxVeganEntry = findViewById(R.id.checkBoxVeganEntry);
        checkBoxDairyFreeEntry = findViewById(R.id.checkBoxDairyFreeEntry);
        checkBoxGlutenFreeEntry = findViewById(R.id.checkBoxGlutenFreeEntry);
        checkBoxNutFreeEntry = findViewById(R.id.checkBoxNutFreeEntry);

        buttonSaveRecipeEntry = findViewById(R.id.buttonSaveRecipeEntry);
        buttonSaveRecipeEntry.setOnClickListener(this);

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if (v == buttonSaveRecipeEntry) {
            fdb = FirebaseDatabase.getInstance();
            final DatabaseReference myRecipe = fdb.getReference("Recipe");

            BitmapFactory.Options bmapOptions = new BitmapFactory.Options();
            bmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            //FileInputStream a = new FileInputStream();

            File temp = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS).toString() + "/AvocadoFettucineComplete.jpg");
            Log.e(TAG, "onClick: AAYUSH " + temp.getAbsolutePath(), null);
            Bitmap tempImage = BitmapFactory.decodeFile(temp.getAbsolutePath(), bmapOptions);

            final List<String> stepIDs = new ArrayList<String>() {{
                    add("1");
                    add("2");
                    add("3");
                    add("4");
                    add("5");
                    add("6");
                    add("7");

            }};
            final List<String> stepNames = new ArrayList<String>() {
                {
                    add("Step 1: " + editTextStepName1Entry.getText().toString());
                    add("Step 2: " + editTextStepName2Entry.getText().toString());
                    add("Step 3: " + editTextStepName3Entry.getText().toString());
                    add("Step 4: " + editTextStepName4Entry.getText().toString());
                    add("Step 5: " + editTextStepName5Entry.getText().toString());
                    add("Step 6: " + editTextStepName6Entry.getText().toString());
                    add("Step 7: " + editTextStepName7Entry.getText().toString());
                }
            };



            final List<String> stepIngredients = new ArrayList<String>() {
                {
                    add(editTextStepIngredient1Entry.getText().toString());
                    add(editTextStepIngredient2Entry.getText().toString());
                    add(editTextStepIngredient3Entry.getText().toString());
                    add(editTextStepIngredient4Entry.getText().toString());
                    add(editTextStepIngredient5Entry.getText().toString());
                    add(editTextStepIngredient6Entry.getText().toString());
                    add(editTextStepIngredient7Entry.getText().toString());
                }
            };

            Recipe newRecipe = new Recipe(UUID.randomUUID().toString(), editTextRecipeName.getText().toString(),
                    editTextRecipeDescriptionEntry.getText().toString(), null, checkBoxVegEntry.isChecked(),
                    checkBoxVeganEntry.isChecked(), checkBoxDairyFreeEntry.isChecked(), checkBoxGlutenFreeEntry.isChecked(),
                    checkBoxNutFreeEntry.isChecked(), editTextIngredientSummary.getText().toString(),
                    editTextNumberOfSteps.getText().toString(), stepIDs, stepNames, null, stepIngredients);

            myRecipe.push().setValue(newRecipe);

            Toast.makeText(this, "Recipe Added Successfully", Toast.LENGTH_SHORT).show();
        }
        ;

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
                Intent homeintent = new Intent(AddRecipestoDatabase.this, BottomNav.class );
                startActivity(homeintent);
                return true;
            case R.id.logout_menu_item:
                Intent logoutintent = new Intent(AddRecipestoDatabase.this, MainActivity.class);
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
