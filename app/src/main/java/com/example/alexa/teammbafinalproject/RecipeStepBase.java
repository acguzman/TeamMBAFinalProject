package com.example.alexa.teammbafinalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeStepBase extends Activity implements View.OnClickListener {

    Button buttonNext, buttonNext2,
            buttonNext3, buttonChat;
    TextView textViewRecipeName, textViewStepName, textViewStepIngredientList;
    EditText editTextRecipeName;
    Drawable imageStepPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_base);

        buttonChat= findViewById(R.id.buttonChat);
        buttonNext= findViewById(R.id.buttonNext);
        buttonNext2= findViewById(R.id.buttonNext2);
        buttonNext3= findViewById(R.id.buttonNext3);
        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewStepName = (TextView) findViewById(R.id.textViewStepName);
        textViewStepIngredientList = (TextView) findViewById(R.id.textViewStepIngredientList);
        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);

        buttonNext.setOnClickListener(this);
        buttonNext2.setOnClickListener(this);
        buttonNext3.setOnClickListener(this);
        buttonChat.setOnClickListener(this);

        autoPopulate();

        Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, textViewRecipeName.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private void autoPopulate() {
        textViewRecipeName.setText("Avocado Fettuccine");
        textViewStepName.setText("Step 1: Combine all of the ingredients, except fettucine, in a " +
                "food processor. Process until smooth and creamy.");
        textViewStepIngredientList.setText("2 whole Avocados, pitted" + "\n" + "1 Tablespoon " +
                "Nutritional yeast" + "\n" +
                "1 Tablespoon Extra virgin olive oil" + "\n" + "1 Tablespoon Fresh lemon juice" +
                "\n" +
                "1 Clove Garlic, minced" + "\n" + "1 Teaspoon Salt" + "\n" + "¼ Teaspoon Freshly " +
                "ground black pepper"
        );

    }

    //   @Override
    //@SuppressLint("RestrictedApi")
   @Override
    public void onClick(View v) {
        if (v == buttonChat) {

            //not ready for this yet

        } else if (v == buttonNext) {
            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 2: Prepare pasta according to package directions, in a" +
                    " saucepan. Drain; Add avocado mixture to pasta pan.");
            textViewStepIngredientList.setText("3/4 Pound Fettuccine");

            buttonNext2.setVisibility(View.VISIBLE);
            buttonNext.setVisibility(View.INVISIBLE);

        } else if (v == buttonNext2) {

            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 3: Toss pasta gently until sauce is evenly incorporated into the fettuccine.");
            textViewStepIngredientList.setText("");

            buttonNext3.setVisibility(View.VISIBLE);
            buttonNext2.setVisibility(View.INVISIBLE);

        } else if (v == buttonNext3) {

            Intent intentRecipeStepComplete = new Intent(this, RecipeStepComplete.class);
            startActivity(intentRecipeStepComplete);
        }
    }
}