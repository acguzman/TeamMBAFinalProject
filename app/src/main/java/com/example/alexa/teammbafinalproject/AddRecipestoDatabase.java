package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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


public class AddRecipestoDatabase extends Activity implements View.OnClickListener {

    EditText editTextRecipeName, editTextRecipeDescriptionEntry, editTextIngredientSummary;
    EditText editTextStepName1Entry, editTextStepIngredient1Entry;
    EditText editTextStepName2Entry, editTextStepIngredient2Entry;
    EditText editTextStepName3Entry, editTextStepIngredient3Entry;
    EditText editTextStepName4Entry, editTextStepIngredient4Entry;

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
        editTextStepName1Entry = findViewById(R.id.editTextStepName1Entry);
        editTextStepName2Entry = findViewById(R.id.editTextStepName2Entry);
        editTextStepName3Entry = findViewById(R.id.editTextStepName3Entry);
        editTextStepIngredient1Entry = findViewById(R.id.editTextStepIngredient1Entry);
        editTextStepIngredient2Entry = findViewById(R.id.editTextStepIngredient2Entry);
        editTextStepIngredient3Entry = findViewById(R.id.editTextStepIngredient3Entry);

        checkBoxVegEntry = findViewById(R.id.checkBoxVegEntry);
        checkBoxVeganEntry = findViewById(R.id.checkBoxVeganEntry);
        checkBoxDairyFreeEntry = findViewById(R.id.checkBoxDairyFreeEntry);
        checkBoxGlutenFreeEntry = findViewById(R.id.checkBoxGlutenFreeEntry);
        checkBoxNutFreeEntry = findViewById(R.id.checkBoxNutFreeEntry);

        buttonSaveRecipeEntry = findViewById(R.id.buttonSaveRecipeEntry);
        buttonSaveRecipeEntry.setOnClickListener(this);

/*        autoPopulate();
    }

    private void autoPopulate() {
        editTextRecipeName.setText("Avocado Fettucine");

        checkBoxVegEntry.setChecked(true);
        checkBoxVeganEntry.setChecked(true);
        checkBoxDairyFreeEntry.setChecked(true);
        checkBoxGlutenFreeEntry.setChecked(false);
        checkBoxNutFreeEntry.setChecked(true);
    }
/*
        editTextRecipeDescriptionEntry.setText("A quick and easy three step pasta recipe that's " +
                "perfect to whip up for a weekday dinner or a really busy weekend.\n" +
                "Prep Time: 5 min\n" +
                "Cook Time: 10 min\n");

        //Don't need the ingredients list. Will populate it from the individual ingredients of
        // each step.

        editTextStepName1Entry.setText("Combine all of the ingredients, except fettuccine, in" +
                " a food processor. Process until smooth and creamy.");
        editTextStepIngredient1Entry.setText("2,whole,Avocados, pitted;1,Tablespoon,Nutritional " +
                "yeast,;1,Tablespoon,Extra virgin olive oil,;1,Tablespoon,Fresh lemon juice,;1," +
                "whole,Clove Garlic, minced;1,Teaspoon,Salt,;¼,Teaspoon,black pepper,Freshly ground");

        editTextStepName2Entry.setText("Prepare pasta according to package directions, in a " +
                "saucepan. Drain; Add avocado mixture to pasta pan.");
        editTextStepIngredient2Entry.setText("¾,Pound,Fettucine,");

        editTextStepName3Entry.setText("Toss pasta gently until sauce is evenly incorporated " +
                "into the fettuccine.");
        editTextStepIngredient3Entry.setText(",,,");
    }


     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    }
@Override
    public void onClick(View v) {


        if (v == buttonSaveRecipeEntry) {
            fdb = FirebaseDatabase.getInstance();
            DatabaseReference myRef = fdb.getReference("Recipe");

/*            BitmapFactory.Options bmapOptions = new BitmapFactory.Options();
            bmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            //FileInputStream a = new FileInputStream();

            File temp = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS).toString() + "/AvocadoFettucineComplete.jpg");
            Log.e(TAG, "onClick: AAYUSH " + temp.getAbsolutePath(), null);
            Bitmap tempImage = BitmapFactory.decodeFile(temp.getAbsolutePath(), bmapOptions);
*/
            List<String> tempStepIDs = new ArrayList<String>() {{
                add("1");
                add("2");
                add("3");
            }};
            List<String> tempStepNames = new ArrayList<String>() {
                {
                    add(editTextStepName1Entry.getText().toString());
                    add(editTextStepName2Entry.getText().toString());
                    add(editTextStepName3Entry.getText().toString());
//                    add(editTextStepName4Entry.getText().toString());
                }
            };
/*
            List<Bitmap> tempStepImages = new ArrayList<Bitmap>();//{{add("1"); add("2"); add("3");
            Bitmap image1 = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS).toString() + "/AvocadoFettucineStep1.jpg", bmapOptions);
            tempStepImages.add(image1);
            Bitmap image2 = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS).toString() + "/AvocadoFettucineStep2.jpg", bmapOptions);
            tempStepImages.add(image2);
            Bitmap image3 = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS).toString() + "/AvocadoFettucineStep3.jpg", bmapOptions);
            tempStepImages.add(image3);
*/
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            image1.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
//            byte[] b = baos.toByteArray();
//            encImage = Base64.encodeToString(b, Base64.DEFAULT);
            /*Bitmap image1 = MediaStore.Images.Media.getBitmap(getContentResolver(), new Uri()
                    .fromFile(new File("file:D:\\umag_Drive\\Acads Ross\\18 Fall B\\TO 626 Mobile" +
                    " " +
                    "Innovation Development\\HappyCooking\\Meringue Banana Apple " +
                    "Pie\\Recipes\\Avocado Fettucine\\AvocadoFettucineStep1.jpg")));*/
            // }};

            List<String> tempStepIngredients = new ArrayList<String>() {
                {
                    add(editTextStepIngredient1Entry.getText().toString());
                    add(editTextStepIngredient2Entry.getText().toString());
                    add(editTextStepIngredient3Entry.getText().toString());
                }
            };
/*            try {
                int numberOfSteps = 3;
                for (int j = 0; j < numberOfSteps; j++) {
                    ArrayList<Ingredient> innerIngredientList = new ArrayList<Ingredient>();
                    ArrayList<String> innerIngredientAmountList = new ArrayList<String>();
                    ArrayList<String> innerIngredientPreparationList = new ArrayList<String>();

                    String[] innerList = giveInputs.get(j).split(";");

                    for (int i = 0; i < innerList.length; i++) {
                        Ingredient innerIngredient = new Ingredient(innerList[i]);
                        innerIngredientList.add(innerIngredient);
                        innerIngredientAmountList.add(String.valueOf(innerList[i].charAt(0)));
                        String[] parts = innerList[i].split(",");
                        if (parts.length == 4) {
                            innerIngredientPreparationList.add(parts[3]);
                        } else {
                            innerIngredientPreparationList.add("");
                        }
                    }

                    stepIngredientList.add(innerIngredientList);
                    stepIngredientAmountList.add(innerIngredientAmountList);
                    stepIngredientPreparationList.add(innerIngredientPreparationList);
                }
*/
                Recipe newRecipe = new Recipe(UUID.randomUUID().toString(), editTextRecipeName.getText().toString(),
                        editTextRecipeDescriptionEntry.getText().toString(), editTextIngredientSummary.getText().toString(),
                        null, checkBoxVegEntry.isChecked(), checkBoxVeganEntry.isChecked(),
                        checkBoxDairyFreeEntry.isChecked(), checkBoxGlutenFreeEntry.isChecked(),
                        checkBoxNutFreeEntry.isChecked(), tempStepIDs, tempStepNames, null,
                        tempStepIngredients);

                myRef.push().setValue(newRecipe);

            Toast.makeText(this, "New Recipe Added.", Toast.LENGTH_SHORT).show();

            }
//            catch (Throwable e) {
//                Log.e(TAG, "AAYUSH: ", e);
//            }
        }


    //@Override
    public void onClick_ALTERNATIVE(View v) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipe");
        Resources res = getResources();
//hard coded right now
        Drawable drawable = res.getDrawable(R.drawable.avocado_fettucine_complete);

        if (v == buttonSaveRecipeEntry) {

            String createIntredientSummary = editTextIngredientSummary.getText().toString();
            String createRecipeID = UUID.randomUUID().toString();
            String createRecipe = editTextRecipeName.getText().toString();
//            String createRecipeDescription = editTextRecipeDescriptionEmpty.getText().toString();
            //hard coded picture for now
            // Drawable createRecipeCompletePicture = res.getDrawable(R.drawable
            // .avocado_fettucine_complete);
//            String createIngredientSummary = editTextIngredientSummary.getText().toString();
            String createStep1Name = editTextStepName1Entry.getText().toString();
            String createStep2Name = editTextStepName2Entry.getText().toString();
            String createStep3Name = editTextStepName3Entry.getText().toString();
            //Step Pictures
            String createStep1Ingredient = editTextStepIngredient1Entry.getText().toString();
            String createStep2Ingredient = editTextStepIngredient2Entry.getText().toString();
            String createStep3Ingredient = editTextStepIngredient3Entry.getText().toString();

            //Recipe newRecipe = new Recipe(createRecipeID,createRecipe,createRecipeDescription,
            // createIngredientSummary,createStep1Name, createStep2Name, createStep3Name,
            // createStep1Ingredient,createStep2Ingredient,createStep3Ingredient);

            //myRef.push().setValue(newRecipe)


            //if(CheckBox.recipeCompletePicture){
            // CheckBox createCheckBoxVegEntry = xxx.iftrue;

        }
    }
}
