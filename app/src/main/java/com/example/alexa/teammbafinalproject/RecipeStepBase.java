package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeStepBase extends Activity implements View.OnClickListener {

    Button floatingActionButtonNext,floatingActionButtonNext2,floatingActionButtonNext3, floatingActionButtonChat;
    TextView textViewRecipeName, textViewStepName,textViewStepIngredientList;
    EditText editTextRecipeName;
    Drawable imageStepPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_base);

        floatingActionButtonChat = (Button) findViewById(R.id.floatingActionButtonChat);
        floatingActionButtonNext = (Button) findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonNext2 = (Button) findViewById(R.id.floatingActionButtonNext2);
        floatingActionButtonNext3 = (Button) findViewById(R.id.floatingActionButtonNext3);
        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewStepName = (TextView) findViewById(R.id.textViewStepName);
        textViewStepIngredientList = (TextView) findViewById(R.id.textViewStepIngredientList);
        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);

        floatingActionButtonNext.setOnClickListener(this);
        floatingActionButtonChat.setOnClickListener(this);

        autoPopulate();

        Toast.makeText(this, editTextRecipeName.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private void autoPopulate() {
        textViewRecipeName.setText("Avocado Fettuccine");
        textViewStepName.setText("Step 1: Combine all of the ingredients, except fettucine, in a food processor. Process until smooth and creamy.");
        textViewStepIngredientList.setText("2 whole Avocados, pitted" + "\n" + "1 Tablespoon Nutritional yeast" + "\n" +
                "1 Tablespoon Extra virgin olive oil" + "\n" + "1 Tablespoon Fresh lemon juice" + "\n" +
                "1 Clove Garlic, minced" + "\n" + "1 Teaspoon Salt" + "\n" + "¼ Teaspoon Freshly ground black pepper"
        );

    }

    //   @Override
    public void onClick(View v) {
        if(v == floatingActionButtonChat){

            //not ready for this yet

        }else if(v == floatingActionButtonNext){
            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 2: Prepare pasta according to package directions, in a saucepan. Drain; Add avocado mixture to pasta pan.");
            textViewStepIngredientList.setText("3/4 Pound Fettuccine");

            floatingActionButtonNext2.setVisibility(View.VISIBLE);
            floatingActionButtonNext.setVisibility(View.INVISIBLE);

        }else if (v == floatingActionButtonNext2){

            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 3: Prepare pasta according to package directions, in a saucepan. Drain; Add avocado mixture to pasta pan.");
            textViewStepIngredientList.setText("Toss pasta gently until sauce is evenly incorporated into the fettuccine.");

            floatingActionButtonNext3.setVisibility(View.VISIBLE);
            floatingActionButtonNext2.setVisibility(View.INVISIBLE);

        }else if (v == floatingActionButtonNext3) {

            Intent intentRecipeStepComplete = new Intent(this,RecipeStepComplete.class);
            startActivity(intentRecipeStepComplete);
        }
    }
}