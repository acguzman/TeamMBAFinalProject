package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
        textViewStepName = (TextView) findViewById(R.id.textViewStepName);
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
        imageStepPhoto.setImageResource(R.drawable.avocado_fettucine_step1);

    }

    //   @Override
    public void onClick(View v) {
        if (v == floatingActionButtonChat) {

            Intent intentLiveChat = new Intent(this, ChatActivity.class);
            startActivity(intentLiveChat);

        } else if (v == floatingActionButtonNext) {
            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 2: Prepare pasta according to package directions, in a" +
                    " saucepan. Drain; Add avocado mixture to pasta pan.");
            textViewStepIngredientList.setText("3/4 Pound Fettuccine");
            imageStepPhoto.setImageResource(R.drawable.avocado_fettucine_step2);

            floatingActionButtonNext2.show();
            floatingActionButtonNext.hide();
            floatingActionButtonNext3.hide();

        } else if (v == floatingActionButtonNext2) {

            textViewRecipeName.setText("Avocado Fettuccine");
            textViewStepName.setText("Step 3: Toss pasta gently until sauce is evenly incorporated into the fettuccine.");
            textViewStepIngredientList.setText("");
            imageStepPhoto.setImageResource(R.drawable.avocado_fettucine_step3);

            floatingActionButtonNext3.show();
            floatingActionButtonNext2.hide();
            floatingActionButtonNext.hide();

        } else if (v == floatingActionButtonNext3) {
            floatingActionButtonNext.show();
            floatingActionButtonNext2.hide();
            floatingActionButtonNext3.hide();

            Intent intentRecipeStepComplete = new Intent(this, RecipeStepComplete.class);
            startActivity(intentRecipeStepComplete);
        }
    }
}