package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class AddRecipestoDatabase extends Activity implements View.OnClickListener {

    EditText editTextRecipeName, editTextRecipeDescriptionEntry;
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

        autoPopulate();
    }

    private void autoPopulate() {
        editTextRecipeName.setText("Avocado Fettucine");

        checkBoxVegEntry.setChecked(true);
        checkBoxVeganEntry.setChecked(true);
        checkBoxDairyFreeEntry.setChecked(true);
        checkBoxGlutenFreeEntry.setChecked(false);
        checkBoxNutFreeEntry.setChecked(true);

        editTextRecipeDescriptionEntry.setText("A quick and easy three step pasta recipe that's " +
                "perfect to whip up for a weekday dinner or a really busy weekend.\n" +
                "Prep Time: 5 min\n" +
                "Cook Time: 10 min\n");

        //Don't need the ingredients list. Will populate it from the individual ingredients of
        // each step.

        editTextStepName1Entry.setText("Combine all of the ingredients, except fettuccine, in" +
                " a food processor. Process until smooth and creamy.");
        editTextStepIngredient1Entry.setText("2 Avocados, pitted;1 Tablespoon Nutritional yeast;1" +
                " Tablespoon Extra virgin olive oil;1 Tablespoon Fresh lemon juice;1 Clove " +
                "Garlic, minced;1 Teaspoon Salt;¼ Teaspoon Freshly ground black pepper");

        editTextStepName2Entry.setText("Prepare pasta according to package directions, in a " +
                "saucepan. Drain; Add avocado mixture to pasta pan.");
        editTextStepIngredient2Entry.setText("¾ Pound Fettucine");

        editTextStepName3Entry.setText("Toss pasta gently until sauce is evenly incorporated " +
                "into the fettuccine.");
        editTextStepIngredient3Entry.setText("");
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
            DatabaseReference myRef = fdb.getReference("Recipe");

            BitmapFactory.Options bmapOptions = new BitmapFactory.Options();
            bmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            //FileInputStream a = new FileInputStream();

            File temp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +"/AvocadoFettucineComplete.jpg");
            Log.e(TAG, "onClick: AAYUSH "+ temp.getAbsolutePath(), null);
            Bitmap tempImage = BitmapFactory.decodeFile(temp.getAbsolutePath(), bmapOptions);

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

            List<Bitmap> tempStepImages = new ArrayList<Bitmap>();//{{add("1"); add("2"); add("3");
            Bitmap image1 = BitmapFactory.decodeFile( "D:\\umag_Drive\\Acads Ross\\18 Fall B\\TO " +
                    "626 Mobile Innovation Development\\HappyCooking\\Meringue Banana Apple " +
                    "Pie\\Recipes\\Avocado Fettucine\\AvocadoFettucineStep1.jpg", bmapOptions);
            tempStepImages.add(image1);
            Bitmap image2 = BitmapFactory.decodeFile("D:\\umag_Drive\\Acads Ross\\18 Fall B\\TO " +
                    "626 Mobile Innovation Development\\HappyCooking\\Meringue Banana Apple " +
                    "Pie\\Recipes\\Avocado Fettucine\\AvocadoFettucineStep2.jpg", bmapOptions);
            tempStepImages.add(image2);
            Bitmap image3 = BitmapFactory.decodeFile("D:\\umag_Drive\\Acads Ross\\18 Fall B\\TO " +
                    "626 Mobile Innovation Development\\HappyCooking\\Meringue Banana Apple " +
                    "Pie\\Recipes\\Avocado Fettucine\\AvocadoFettucineStep3.jpg", bmapOptions);
            tempStepImages.add(image3);

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

            Recipe newRecipe = new Recipe("AAA",
                    editTextRecipeName.getText().toString(), editTextRecipeDescriptionEntry
                    .getText().toString(),
                    tempImage,
                    checkBoxVegEntry.isChecked(), checkBoxVeganEntry.isChecked(),
                    checkBoxDairyFreeEntry.isChecked(),
                    checkBoxGlutenFreeEntry.isChecked(), checkBoxNutFreeEntry.isChecked(),
                    tempStepIDs, tempStepNames, tempStepImages,
                    null, null, null);
            myRef.push().setValue(newRecipe);
        }
    }
}
